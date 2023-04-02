package core.processor.worker.model;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.constant.FullName;
import core.processor.annotation.ui.ActBack;
import core.processor.lib.ls.EachType;
import core.processor.lib.ls.TypeLss;
import core.processor.lib.tools.NameTools;
import core.processor.modelinterface.ActBackModelInterface;
import core.processor.worker.model.base.SubBaseModel;

public class ActBackModel extends SubBaseModel implements ActBackModelInterface {
    private boolean hasActBack;
    private String fromClassName;
    private String methodName;
    private List<? extends VariableElement> parameters;

    public ActBackModel(boolean hasActBack, ExecutableElement element) {
        this.hasActBack = hasActBack;
        ActBack actBack = element.getAnnotation(ActBack.class);
        fromClassName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actBack.value();
            }
        });
        methodName = element.getSimpleName().toString();
        parameters = element.getParameters();
    }

    /**************************************************
     *
     * setTag方法
     *
     **************************************************/

    @Override
    public void setTagFor_if(StringBuilder sb) {
        addIf(hasActBack);
    }

    @Override
    public void setTagFor_code(StringBuilder sb) {
        JavaInfo info = NameTools.getJavaInfoByName(fromClassName);
        String staticType = ConvertTool.toStaticType(info.name);
        addTag(sb, staticType);
    }

    @Override
    public void setTagFor_method(StringBuilder sb) {
        addTag(sb, methodName);
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        TypeLss.ls(parameters, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                if (position != 0) {
                    addTag(sb, ", ");
                }

                if (FullName.INTENT.equals(type)) {
                    addTag(sb, name);
                } else {
                    addTag(sb, "core.tools.Pass.[user](data)", name);
                }
            }
        });
    }
}
/* model_temp_start
            [[if]] (requestCode == core.tools.Code4Request.[[code]]) {
                [[method]]([[params]]);
            }
model_temp_end */
