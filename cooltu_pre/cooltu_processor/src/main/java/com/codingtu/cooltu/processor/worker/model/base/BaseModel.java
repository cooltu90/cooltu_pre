package com.codingtu.cooltu.processor.worker.model.base;

import com.codingtu.cooltu.constant.Constant;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.model.ModelMap;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.worker.ModelType;

public class BaseModel {
    public boolean isForce;
    public String id;


    /**************************************************
     *
     * 分割线
     *
     **************************************************/
    protected JavaInfo info;

    public BaseModel(JavaInfo info) {
        this.info = info;
        if (info != null)
            this.id = info.fullName;
        else
            this.id = NameTools.getModelTypeBaseName(getClass());
        addToMap();
    }

    protected void addToMap() {
        ModelMap.add(getModelType(), this);
    }

    /**************************************************
     *
     * 创建方法
     *
     **************************************************/
    public void create() {
        if (info == null)
            return;
        beforCreate();
        File file = new File(info.path);
        if (isForce || !file.exists()) {
            List<String> lines = getLines();
            if (!CountTool.isNull(lines)) {
                FileWriter.to(file).cover().write(lines);
            }
        }
    }

    protected void beforCreate() {

    }

    protected void dealAllSetTagMethod() {
        Method[] methods = getClass().getMethods();
        Ts.ls(methods, new Each<Method>() {
            @Override
            public boolean each(int position, Method method) {
                String name = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (name.startsWith(Constant.PREFIX_METHOD_SET_TAG_FOR)
                        && CountTool.count(parameterTypes) == 1
                        && parameterTypes[0] == StringBuilder.class) {
                    //setTagFor_fileds
                    String tagName = name.substring(Constant.PREFIX_METHOD_SET_TAG_FOR.length());
                    StringBuilder sb = getTag(tagName);
                    try {
                        method.invoke(getThis(), sb);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    private BaseModel getThis() {
        return this;
    }

    /**************************************************
     *
     * 获取整个处理好的内容
     *
     **************************************************/
    public List<String> getLines() {
        dealAllSetTagMethod();
        return TagTools.getLines(map, getTempLines());
    }

    /**************************************************
     *
     * 直接将另一个model添加进来
     *
     **************************************************/
    protected void addModel(String tag, BaseModel model) {
        addModel(getTag(tag), model);
    }

    protected void addModel(StringBuilder sb, BaseModel model) {
        Ts.ls(model.getLines(), new Each<String>() {
            @Override
            public boolean each(int position, String s) {
                addLnTag(sb, s);
                return false;
            }
        });
    }

    protected void addModels(String tag, List<? extends BaseModel> models) {
        addModels(getTag(tag), models);
    }

    protected void addModels(StringBuilder sb, List<? extends BaseModel> models) {
        Ts.ls(models, new Each<BaseModel>() {
            @Override
            public boolean each(int position, BaseModel baseModel) {
                addModel(sb, baseModel);
                return false;
            }
        });
    }

    /**************************************************
     *
     * 获取模板
     *
     **************************************************/
    public List<String> getTempLines() {
        try {
            List<String> lines = FileReader.from(new File(NameTools.getModelPath(this))).readLine();
            int start = 0;
            int end = 0;
            for (int i = 0; i < CountTool.count(lines); i++) {
                String line = lines.get(i);
                if ("/* model_temp_start".equals(line.trim())) {
                    start = i + 1;
                } else if ("model_temp_end */".equals(line.trim())) {
                    end = i;
                }
            }
            if (start != end) {
                List<String> strings = lines.subList(start, end);
                return strings;
            }
        } catch (Exception e) {
            Logs.w(e);
        }
        return null;
    }

    /**************************************************
     *
     * 获取类型
     *
     **************************************************/
    public ModelType getModelType() {
        return ModelType.DEFAULT;
    }

    /**************************************************
     *
     * 对tag的存入操作
     *
     **************************************************/
    protected Map<String, StringBuilder> map = new StringBuilderValueMap<>();

    public StringBuilder getTag(String tag) {
        return map.get(tag);
    }

    public void addTag(String tag, String line, Object... values) {
        addTag(map.get(tag), line, values);
    }

    public void addLnTag(String tag, String line, Object... values) {
        addLnTag(map.get(tag), line, values);
    }

    public void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public void addTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags));
    }

    private String dealLine(String line, Object... values) {
        if (values != null && values.length > 0) {
            line = TagTools.getLine(line, values);
        }
        return line;
    }

}
