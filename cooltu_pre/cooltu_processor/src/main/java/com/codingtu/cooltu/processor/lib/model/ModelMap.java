package com.codingtu.cooltu.processor.lib.model;

import java.util.List;
import java.util.Map;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.lib4j.ts.getter.Getter;
import com.codingtu.cooltu.processor.worker.ModelType;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

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
        return (T) Ts.get(MAP.get(type.ordinal()), new Getter<Integer, BaseModel>() {
            @Override
            public boolean get(Integer key, BaseModel baseModel) {
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
        Ts.ls(ModelType.values(), new Each<ModelType>() {
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
        Ts.ls(models, new Each<BaseModel>() {
            @Override
            public boolean each(int position, BaseModel model) {
                model.create();
                return false;
            }
        });
    }
}
