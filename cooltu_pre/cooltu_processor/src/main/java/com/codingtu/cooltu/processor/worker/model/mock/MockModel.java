package com.codingtu.cooltu.processor.worker.model.mock;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.modelinterface.MockModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

public class MockModel extends SingleCoreToolsBaseModel implements MockModelInterface {
    public static final MockModel model = new MockModel();
    public HashMap<String, String> map = new HashMap<>();

    public MockModel() {
        super(Constant.NAME_MOCKS);
        List<String> lines = null;
        try {
            lines = FileReader.from(info.path).readLine();
        } catch (Exception e) {
        }
        //      public static final boolean TEST_NET_ACTIVITY_GET_USER_BY_ID_BACK = false;

        StringBuilder fieldSb = getTag("fields");
        Ts.ls(lines, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                if (s.contains("public static final boolean")) {
                    String sub = StringTool.getSub(s, "boolean", " ", " ");
                    map.put(sub, sub);
                    addLnTag(fieldSb, s);
                }
                return false;
            }
        });
    }

    public void add(ExecutableElement element) {
        //TestNetActivity
        Element typeElement = element.getEnclosingElement();
        //getUserByIdBack
        String actName = typeElement.getSimpleName().toString();
        String methodName = element.getSimpleName().toString();
        String name = ConvertTool.toStaticType(actName) + "_" + ConvertTool.toStaticType(methodName);
        if (!map.containsKey(name)) {
            map.put(name, name);
            addLnTag("fields", "    public static final boolean [name] = false;", name);
        }

    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_fields(StringBuilder sb) {
    }
}
/* model_temp_start
package core.tools;

public class Mocks {
[[fields]]
}
model_temp_end */
