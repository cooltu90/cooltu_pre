package core.processor.lib.model;

import com.codingtu.cooltu.constant.AdapterType;

import core.processor.worker.model.AdapterDefaultModel;
import core.processor.worker.model.AdapterDefaultMoreModel;
import core.processor.worker.model.base.BaseAdapterModel;

public class AdapterModels {

    public static BaseAdapterModel getAdapterModel(AdapterType type) {
        switch (type) {
            case DEFAULT_LIST:
                return new AdapterDefaultModel();
            case DEFAULT_MORE_LIST:
                return new AdapterDefaultMoreModel();
        }
        return null;
    }
}
