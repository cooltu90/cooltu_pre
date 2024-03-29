package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.lib.model.ModelMap;
import com.codingtu.cooltu.processor.lib.tools.App;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.worker.SupportTypes;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    private Set<String> supportTypes = new HashSet<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTools.trees = Trees.instance(processingEnv);
        IdTools.rScanner = new IdTools.RScanner();
        App.init(processingEnv);
        createModelInterface();
        dealSupportTypes();
    }

    private void createModelInterface() {
        File modeDirFile = new File(NameTools.getModelPath());

        FileLister.dir(modeDirFile).list(new ListFile() {
            @Override
            public void list(File file) {
                if (!file.isDirectory()) {
                    try {
                        createModelInterface(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void createModelInterface(File file) throws Exception {
        List<String> lines = FileReader.from(file).readLine();
        int start = 0;
        int end = 0;
        for (int i = 0; i < CountTool.count(lines); i++) {
            String line = lines.get(i);
            if ("/* model_temp_start".equals(line.trim())) {
                start = i + 1;
            } else if ("model_temp_end */".equals(line.trim())) {
                end = i;
            }
        }

        if (start == end) {
            return;
        }

        lines = lines.subList(start, end);
        List<String> tags = TagTools.getTags(lines);
        //ActBackModel.java
        String name = file.getName();
        //ActBackModelInterface
        String typeName = NameTools.cutSuffix(name, FileType.d_JAVA) + Suffix.INTERFACE;

        ArrayList<String> newLines = new ArrayList<>();
        newLines.add("package com.codingtu.cooltu.processor.modelinterface;");
        newLines.add("");
        newLines.add("import java.util.ArrayList;");
        newLines.add("import java.util.List;");
        newLines.add("");
        newLines.add("public interface " + typeName + " {");
        newLines.add("");
        Ts.ls(tags, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                //newLines.add("    public static final String " + NameTools.toStaticType(s) + " = \"" + s + "\";");
                newLines.add("    public void " + Constant.PREFIX_METHOD_SET_TAG_FOR + s + "(StringBuilder sb);");
                newLines.add("");
                return false;
            }
        });

        newLines.add("    default List<String> getTempLinesArray() {");
        newLines.add("        ArrayList<String> lines = new ArrayList<>();");
        Ts.ls(lines, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                newLines.add("        lines.add(\"" + s.replace("\"", "\\\"") + "\");");
                return false;
            }
        });
        newLines.add("        return lines;");
        newLines.add("    }");
        newLines.add("}");

        String path = NameTools.getModelInterPath(typeName);
        FileWriter.to(path).cover().write(newLines);
    }

    public void dealSupportTypes() {
//        String path = FileTools.getProjectDir() +
//                "\\core_processor" +
//                Names.PATH_SRC_MAIN_JAVA + NameTools.pkgToPath(Names.PKG_DEAL);
//        File file = new File(path);
        Ts.ls(SupportTypes.types(), new BaseTs.EachTs<Class>() {
            @Override
            public boolean each(int position, Class aClass) {
                supportTypes.add(aClass.getCanonicalName());
                return false;
            }
        });
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, final RoundEnvironment roundEnv) {
        Ts.ls(SupportTypes.types(), new BaseTs.EachTs<Class>() {
            @Override
            public boolean each(int position, Class clazz) {
                String className = clazz.getCanonicalName();
                Class annoClass = ClassTool.getClass(className);
                JavaInfo info = NameTools.getJavaInfoByName(className);
                String dealFullName = Pkg.DEAL + "." + info.name + "Deal";
                final Class dealClass = ClassTool.getClass(dealFullName);
                if (dealClass != null) {
                    Set<Element> es = roundEnv.getElementsAnnotatedWith(annoClass);
                    Ts.ts(es).ls(new SetTs.SetEach<Element>() {
                        @Override
                        public boolean each(Element element) {
                            try {
                                BaseDeal deal = (BaseDeal) dealClass.newInstance();
                                deal.dealElement(element);
                            } catch (Exception e) {
                            }
                            return false;
                        }
                    });
                }
                return false;
            }
        });

        ModelMap.create();

        return true;
    }

}
