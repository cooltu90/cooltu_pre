package com.codingtu.cooltu.processor.lib.model;

import com.codingtu.cooltu.constant.AdapterType;

import com.codingtu.cooltu.processor.worker.model.AdapterDefaultModel;
import com.codingtu.cooltu.processor.worker.model.AdapterDefaultMoreModel;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;

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
