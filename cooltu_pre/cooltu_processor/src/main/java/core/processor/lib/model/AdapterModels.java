package core.processor.lib.model;

import core.constant.AdapterType;
import core.processor.worker.model.AdapterDefaultModel;
import core.processor.worker.model.AdapterDefaultMoreModel;
import core.processor.worker.model.base.BaseAdapterModel;

public class AdapterModels {

    public static BaseAdapterModel getAdapterModel(int type) {
        switch (type) {
            case AdapterType.DEFAULT_LIST:
                return new AdapterDefaultModel();
            case AdapterType.DEFAULT_MORE_LIST:
                return new AdapterDefaultMoreModel();
        }
        return null;
    }
}
