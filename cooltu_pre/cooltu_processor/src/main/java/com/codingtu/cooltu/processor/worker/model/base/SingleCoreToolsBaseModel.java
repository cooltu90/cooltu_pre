package com.codingtu.cooltu.processor.worker.model.base;

import com.codingtu.cooltu.processor.lib.tools.NameTools;

public class SingleCoreToolsBaseModel extends BaseModel {

    public SingleCoreToolsBaseModel(String typeName) {
        super(NameTools.getCoreToolsInfo(typeName));
        isForce = true;
    }
}
