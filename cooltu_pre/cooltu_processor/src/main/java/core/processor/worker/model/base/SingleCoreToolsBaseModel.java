package core.processor.worker.model.base;

import core.processor.lib.tools.NameTools;

public class SingleCoreToolsBaseModel extends BaseModel {

    public SingleCoreToolsBaseModel(String typeName) {
        super(NameTools.getCoreToolsInfo(typeName));
        isForce = true;
    }
}
