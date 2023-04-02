package core.processor.worker.model;

import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.CountTool;
import core.processor.lib.tools.ElementTools;
import core.processor.modelinterface.ActBasePermissionBackModelInterface;
import core.processor.worker.model.base.SubBaseModel;

public class ActBasePermissionBackModel extends SubBaseModel implements ActBasePermissionBackModelInterface {
    private boolean hasPermissionBack;
    private String methodName;
    private List<? extends VariableElement> parameters;

    public ActBasePermissionBackModel(boolean hasPermissionBack, ExecutableElement element) {
        this.hasPermissionBack = hasPermissionBack;
        methodName = ElementTools.simpleName(element);
        parameters = element.getParameters();
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    @Override
    public void setTagFor_if(StringBuilder sb) {
        addIf(hasPermissionBack);
    }

    @Override
    public void setTagFor_code(StringBuilder sb) {
        addTag(sb, ConvertTool.toStaticType(methodName));
    }

    @Override
    public void setTagFor_methodName(StringBuilder sb) {
        addTag(sb, methodName);
    }

    @Override
    public void setTagFor_allow(StringBuilder sb) {
        if (CountTool.count(parameters) == 1) {
            String type = parameters.get(0).asType().toString();
            if (ClassTool.isType(type, boolean.class, Boolean.class)) {
                addTag(sb, "[tools].allow(grantResults)", FullName.PERMISSION_TOOL);
            }
        }
    }

}
/* model_temp_start
        [[if]] (requestCode == core.tools.Permissions.CODE_[[code]]) {
            [[methodName]]([[allow]]);
        }
model_temp_end */
