package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.lib.tools.RenameTools;
import com.codingtu.cooltu.processor.modelinterface.Code4RequestModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

import java.util.ArrayList;
import java.util.List;

public class Code4RequestModel extends SingleCoreToolsBaseModel implements Code4RequestModelInterface {
    public static final Code4RequestModel model = new Code4RequestModel();
    public int code = 0;
    private List<String> actStaticNames = new ArrayList<>();

    public Code4RequestModel() {
        super(Constant.NAME_CODE_4_REQUEST);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void addActStaticName(String actStaticName) {
        if (!actStaticNames.contains(actStaticName)) {
            actStaticNames.add(actStaticName);
        }
    }

    /***************************************
     *
     *
     *
     ***************************************/
    @Override
    public void setTagFor_fields(StringBuilder sb) {
        Ts.ls(actStaticNames, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String actStaticName) {
                //ONE_ACTIVITY
                addLnTag(
                        sb,
                        "    public static final int [name] = [code];",
                        RenameTools.lsActs(actStaticName, new RenameTools.RenameLs() {
                            @Override
                            public String ls(String oldFullName, String newFullName) {
                                String oldStaticName = RenameTools.actFullNameToStaticName(oldFullName);
                                if (oldStaticName.equals(actStaticName)) {
                                    return RenameTools.actFullNameToStaticName(newFullName);
                                }
                                return null;
                            }
                        }),
                        code++);
                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools;

public class Code4Request {
[[fields]]
}
model_temp_end */
