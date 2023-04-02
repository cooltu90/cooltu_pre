package com.codingtu.cooltu.processor.worker.model.net;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.processor.lib.ls.EachType;
import com.codingtu.cooltu.processor.lib.ls.TypeLss;
import com.codingtu.cooltu.processor.modelinterface.SendParamsModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class SendParamsModel extends BaseModel implements SendParamsModelInterface {
    private List<? extends VariableElement> parameters;

    //id=getUserById
    public SendParamsModel(JavaInfo info, ExecutableElement element) {
        super(info);
        isForce = true;
        parameters = element.getParameters();
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_fields(StringBuilder fieldsSb) {
        TypeLss.ls(parameters, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                addLnTag(fieldsSb, "    public [type] [age];", type, name);
            }
        });
    }
}
/* model_temp_start
package core.tools.net.params;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

public class [[name]] extends CoreSendParams {
[[fields]]
}
model_temp_end */
