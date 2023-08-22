package com.codingtu.cooltu.processor.lib.model;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.worker.ModelType;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.List;
import java.util.Map;

public class ModelMap {
    public static final Map<Integer, List<BaseModel>> MAP = new ListValueMap<>();

    /**************************************************
     *
     * 添加方法
     *
     **************************************************/

    public static void add(BaseModel model) {
        add(ModelType.DEFAULT, model);
    }

    public static void add(ModelType type, BaseModel model) {
        MAP.get(type.ordinal()).add(model);
    }

    /**************************************************
     *
     * 查找
     *
     **************************************************/
    public static <T> T find(ModelType type, String id) {
        return (T) Ts.ts(MAP.get(type.ordinal())).get(new BaseTs.IsThisOne<BaseModel>() {
            @Override
            public boolean isThisOne(int position, BaseModel baseModel) {
                return baseModel.id.equals(id);
            }
        });
    }

    /**************************************************
     *
     * 创建
     *
     **************************************************/
    public static void create() {
        Ts.ls(ModelType.values(), new BaseTs.EachTs<ModelType>() {
            @Override
            public boolean each(int position, ModelType modelType) {
                create(modelType.ordinal());
                return false;
            }
        });
        MAP.clear();
    }

    private static void create(int type) {
        List<BaseModel> models = MAP.get(type);
        Ts.ls(models, new BaseTs.EachTs<BaseModel>() {
            @Override
            public boolean each(int position, BaseModel model) {
                model.create();
                return false;
            }
        });
    }
}
