package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.tools.FormTool;
import com.codingtu.cooltu.processor.modelinterface.BindHandlerModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

import java.util.HashMap;
import java.util.List;

public class BindHandlerModel extends SubBaseModel implements BindHandlerModelInterface {

    private String type;
    private String bean;
    private ListValueMap<Integer, FormItemInfo> formItemMap;
    private HashMap<String, String> needLinkMap;

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public void setCases(ListValueMap<Integer, FormItemInfo> formItemMap) {
        this.formItemMap = formItemMap;
    }

    public void setNeedLinks(HashMap<String, String> needLinkMap) {
        this.needLinkMap = needLinkMap;
    }

    @Override
    public void setTagFor_type(StringBuilder sb) {
        addTag(sb, type);
    }

    @Override
    public void setTagFor_bean(StringBuilder sb) {
        addTag(sb, bean);
    }

    @Override
    public void setTagFor_cases(StringBuilder sb) {
//        setTagFor_cases(sb, formItemMap.get(FormType.EDIT_TEXT), FormType.EDIT_TEXT);
//        setTagFor_cases(sb, formItemMap.get(FormType.TEXT_VIEW), FormType.TEXT_VIEW);
//        setTagFor_cases(sb, formItemMap.get(FormType.RADIO_GROUP), FormType.RADIO_GROUP);
//        setTagFor_cases(sb, formItemMap.get(FormType.SEEK_BAR), FormType.SEEK_BAR);
        Ts.ts(FormType.getNeedType()).ls(new BaseTs.EachTs<Integer>() {
            @Override
            public boolean each(int position, Integer integer) {
                setTagFor_cases(sb, formItemMap.get(integer), integer);
                return false;
            }
        });

    }

    @Override
    public void setTagFor_ListValueMapFullName(StringBuilder sb) {
        sb.append(FullName.LIST_VALUE_MAP);
    }

    @Override
    public void setTagFor_TsFullName(StringBuilder sb) {
        sb.append(FullName.TS);
    }

    @Override
    public void setTagFor_EachFullName(StringBuilder sb) {
        sb.append(FullName.EACH);
    }

    private void setTagFor_cases(StringBuilder sb, List<FormItemInfo> etTvItems, int index) {
        if (!CountTool.isNull(etTvItems)) {
            addLnTag(sb, "            if (msg.what == [0]) {", index);
            addLnTag(sb, "                switch (msg.arg1) {");


            Ts.ls(etTvItems, new BaseTs.EachTs<FormItemInfo>() {
                @Override
                public boolean each(int position, FormItemInfo info) {

                    addLnTag(sb, "                    case [1]:", position);
                    addLnTag(sb, "                        [xxx] = [toBean];", info.beanField, FormTool.toBean(info));

                    String viewId = info.viewId.toString();
                    if (needLinkMap.containsKey(viewId)) {
                        addLnTag(sb, "                        link([R.id.t1Et]);", viewId);
                    }

                    addLnTag(sb, "                        break;");

                    return false;
                }
            });

            addLnTag(sb, "                }");
            addLnTag(sb, "            }");

        }
    }

}

/* model_temp_start
    public static class BindHandler extends android.os.Handler {
        private [[type]] [[bean]];

        public BindHandler([[type]] [[bean]]) {
            this.[[bean]] = [[bean]];
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
[[cases]]

        }


        private [[ListValueMapFullName]]<Integer, com.codingtu.cooltu.lib4a.form.FormLink> links;

        private [[ListValueMapFullName]]<Integer, com.codingtu.cooltu.lib4a.form.FormLink> getLinks() {
            if (links == null) {
                links = new [[ListValueMapFullName]]<>();
            }
            return links;
        }

        public void addLink(int index, com.codingtu.cooltu.lib4a.form.FormLink link) {
            getLinks().get(index).add(link);
        }

        private void link(int id) {
            [[TsFullName]].ls(getLinks().get(id), new [[EachFullName]]<com.codingtu.cooltu.lib4a.form.FormLink>() {
                @Override
                public boolean each(int position, com.codingtu.cooltu.lib4a.form.FormLink formLink) {
                    formLink.link();
                    return false;
                }
            });
        }
    }
model_temp_end */
