package core.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.Suffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.annotation.ui.ActBack;
import core.processor.lib.ls.EachType;
import core.processor.lib.ls.TypeLss;
import core.processor.lib.tools.NameTools;
import core.processor.lib.tools.ParamTools;
import core.processor.modelinterface.ActBackIntentModelInterface;
import core.processor.worker.model.base.SingleCoreToolsBaseModel;

public class ActBackIntentModel extends SingleCoreToolsBaseModel implements ActBackIntentModelInterface {

    public static final ActBackIntentModel model = new ActBackIntentModel();

    private List<ExecutableElement> ees = new ArrayList<>();
    private HashMap<String, String> names = new HashMap<>();

    public ActBackIntentModel() {
        super(Constant.NAME_ACT_BACK_INTENT);
    }


    public void add(ExecutableElement ee) {
        ees.add(ee);
    }


    @Override
    public void setTagFor_methods(StringBuilder sb) {
        Ts.ls(ees, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                List<? extends VariableElement> parameters = ee.getParameters();
                if (CountTool.isNull(parameters)) {
                    return false;
                }

                ActBack actBack = ee.getAnnotation(ActBack.class);
                String fullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return actBack.value();
                    }
                });
                JavaInfo javaInfo = NameTools.getJavaInfoByName(fullName);
                String methodName = ConvertTool.toMethodType(javaInfo.name.replace(Suffix.ACTIVITY, ""));
                String params = ParamTools.getDefaultParam(ee).getParams();
                String id = methodName + params;
                if (StringTool.isNotBlank(names.get(id))) {
                    return false;
                }

                names.put(id, id);

                addLnTag(sb, "    public static Intent [methodName]([params]) {", methodName, params);
                addLnTag(sb, "        Intent intent = new Intent();");

                TypeLss.ls(parameters, new EachType() {
                    @Override
                    public void each(int position, String type, String name) {
                        String line = null;
                        if (ClassTool.isBaseClass(type)) {
                            line = "        intent.putExtra(Pass.[staticName], [name]);";
                        } else {
                            line = "        intent.putExtra(Pass.[staticName], [name].toJson());";
                        }
                        addLnTag(sb, line, ConvertTool.toStaticType(name), name);
                    }
                });
                addLnTag(sb, "        return intent;");
                addLnTag(sb, "    }");

                return false;
            }
        });
    }

}
/* model_temp_start
package core.tools;

import android.content.Intent;

public class ActBackIntent {

[[methods]]

}

model_temp_end */
