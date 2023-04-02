package core.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.annotation.permission.Permission;
import core.processor.lib.tools.ElementTools;
import core.processor.modelinterface.PermissionModelInterface;
import core.processor.worker.model.base.SingleCoreToolsBaseModel;

public class PermissionModel extends SingleCoreToolsBaseModel implements PermissionModelInterface {

    public static final PermissionModel model = new PermissionModel();

    private int code = 0;

    public List<ExecutableElement> elements = new ArrayList<>();

    public PermissionModel() {
        super(Constant.NAME_PERMISSION);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_fields(StringBuilder fieldsSb) {
        Ts.ls(elements, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(fieldsSb,
                        "    public static final int CODE_[name] = [num];",
                        ConvertTool.toStaticType(ElementTools.simpleName(element)),
                        code++);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_methods(StringBuilder methodsSb) {
        Ts.ls(elements, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                String simpleName = ElementTools.simpleName(element);
                addLnTag(methodsSb, "    public static void [name](Activity act) {", simpleName);
                Permission permission = element.getAnnotation(Permission.class);
                String[] value = permission.value();
                StringBuilder sb = new StringBuilder();
                Ts.ls(value, new Each<String>() {
                    @Override
                    public boolean each(int position, String s) {
                        sb.append(", \"").append(s).append("\"");
                        return false;
                    }
                });
                addLnTag(methodsSb, "        [tool].check(act, CODE_[name] [params]);", FullName.PERMISSION_TOOL, ConvertTool.toStaticType(simpleName), sb.toString());
                addLnTag(methodsSb, "    }");

                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools;

import android.Manifest;
import android.app.Activity;

public class Permissions {

[[fields]]

[[methods]]

}
model_temp_end */
