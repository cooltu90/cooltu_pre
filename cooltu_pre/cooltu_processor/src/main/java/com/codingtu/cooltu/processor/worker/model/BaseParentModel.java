package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FieldName;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.ui.BusBack;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBaseActBack;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.LongClickView;
import com.codingtu.cooltu.processor.lib.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.bean.FromItemInfoForRg;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.ls.EachType;
import com.codingtu.cooltu.processor.lib.ls.TypeLs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTool;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.ParamTools;
import com.codingtu.cooltu.processor.lib.tools.RenameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.worker.deal.ResForBaseDeal;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public abstract class BaseParentModel extends BaseModel {

    private final boolean isAct;
    protected String baseClass;
    protected String layoutName;
    protected String rPkg;
    protected IdTools.Id layoutId;
    protected List<LayoutTools.ViewInfo> vis;
    protected String beanType;
    protected String beanName;
    protected List<LayoutTools.ViewInfo> bindVis = new ArrayList<>();
    protected HashMap<String, String> fields = new HashMap<>();
    protected List<ExecutableElement> clickViews = new ArrayList<>();
    protected List<ExecutableElement> longClickViews = new ArrayList<>();
    protected List<BaseAdapterModel> adapters = new ArrayList<>();
    protected List<ExecutableElement> actBackElements = new ArrayList<>();
    protected List<ExecutableElement> busBackElements = new ArrayList<>();

    protected List<ExecutableElement> netBackElements = new ArrayList<>();
    protected List<DialogInfo> dialogInfos = new ArrayList<>();
    protected List<EditDialogInfo> editDialogInfos = new ArrayList<>();

    protected boolean isToastDialog;
    protected boolean isNoticeDialog;
    protected List<KV<String, String>> otherFields = new ArrayList<>();
    //resource
    protected List<KV<String, Float>> dpKvs = new ArrayList<>();
    protected List<String> dpTypes = new ArrayList<>();
    protected List<KV<String, IdTools.Id>> resKvs = new ArrayList<>();
    protected List<KV<String, IdTools.Id>> colorIdKvs = new ArrayList<>();
    protected List<KV<String, String>> colorStrKvs = new ArrayList<>();

    public BaseParentModel(JavaInfo info, boolean isAct) {
        super(info);
        this.isAct = isAct;
        isForce = true;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }

    public String getBaseClass() {
        return baseClass;
    }

    public void setLayout(String rPkg, String layoutName) {
        this.layoutName = layoutName;
        this.rPkg = rPkg;
        layoutId = new IdTools.Id(rPkg, Constant.R_TYPE_LAYOUT, layoutName);
        vis = LayoutTools.read(layoutId.rName, null);
        Ts.ls(vis, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo vi) {
                if (StringTool.isNotBlank(vi.tag)) {
                    if (StringTool.isNotBlank(vi.bindType)) {
                        beanType = vi.bindType;
                        beanName = vi.bindName;
                    } else if (StringTool.isNotBlank(vi.bindCase)) {
                        bindVis.add(vi);
                    }
                }
                return false;
            }
        });
    }

    public void addNetBack(ExecutableElement element) {
        netBackElements.add(element);
    }

    public void addClickView(ExecutableElement element) {
        clickViews.add(element);
    }

    public void addLongClickView(ExecutableElement element) {
        longClickViews.add(element);
    }

    public void setAdapter(BaseAdapterModel adapterModel, String uiType) {
        adapterModel.setUiType(uiType);
        adapters.add(adapterModel);
    }

    public void addDialog(DialogInfo info) {
        if (!dialogInfos.contains(info)) {
            addField(FullName.DIALOG_VIEW, info.name);
            dialogInfos.add(info);
        }
    }


    public void addEditDialog(EditDialogInfo info) {
        if (!editDialogInfos.contains(info)) {
            addField(FullName.EDIT_DIALOG_VIEW, info.name);
            editDialogInfos.add(info);
        }
    }

    public void addToastDialog() {
        isToastDialog = true;
    }

    public void addNoticeDialog() {
        isNoticeDialog = true;
    }

    public void addDp(String type, String name, float dp) {
        otherFields.add(new KV<>(type, name));
        dpTypes.add(type);
        dpKvs.add(new KV<>(name, dp));
    }

    public void addDimen(String name, IdTools.Id id) {
        otherFields.add(new KV<>(FullName.INT, name));
        resKvs.add(new KV<>(name, id));
    }

    public void addColorRes(String name, IdTools.Id id) {
        otherFields.add(new KV<>(FullName.INT, name));
        colorIdKvs.add(new KV<>(name, id));
    }

    public void addColorStr(String name, String value) {
        otherFields.add(new KV<>(FullName.INT, name));
        colorStrKvs.add(new KV<>(name, value));
    }

    public void addField(String type, String name) {
        otherFields.add(new KV<>(type, name));
    }

    @Override
    protected void beforCreate() {
        super.beforCreate();
        info.path = RenameTools.lsActs(info.path, new RenameTools.RenameLs() {
            @Override
            public String ls(String oldFullName, String newFullName) {
                String oldBaseFullName = RenameTools.actFullNameToActBaseInfo(oldFullName).fullName;
                if (oldBaseFullName.equals(info.fullName)) {
                    return RenameTools.actFullNameToActBaseInfo(newFullName).path;
                }
                return null;
            }
        });
    }

    public void addActBack(ExecutableElement element) {
        actBackElements.add(element);
    }

    public void addBusBack(ExecutableElement ee) {
        busBackElements.add(ee);
    }

    /***************************************
     *
     *
     *
     ***************************************/


    public void setTagFor_name(StringBuilder sb) {
        String name = RenameTools.lsActs(info.name, new RenameTools.RenameLs() {
            @Override
            public String ls(String oldFullName, String newFullName) {
                String oldBaseFullName = RenameTools.actFullNameToActBaseInfo(oldFullName).fullName;
                if (oldBaseFullName.equals(info.fullName)) {
                    return RenameTools.actFullNameToActBaseInfo(newFullName).name;
                }
                return null;
            }
        });
        addTag(sb, name);
    }

    public void setTagFor_base(StringBuilder sb) {
        addTag(sb, baseClass);
    }

    protected void addFieldSb(StringBuilder fieldSb, String type, String name) {
        if (ResForBaseDeal.isInBase(baseClass, name)) {
            return;
        }

        String key = type + name;
        if (StringTool.isNotBlank(fields.get(key))) {
            return;
        }
        fields.put(key, key);

        addLnTag(fieldSb, "    public [type] [name];", type, name);
    }


    public void setTagFor_rPkg(StringBuilder sb) {
        addTag(sb, rPkg);
    }

    public void setTagFor_layoutName(StringBuilder sb) {
        addTag(sb, RenameTools.lsActs(layoutName, new RenameTools.RenameLs() {
            @Override
            public String ls(String oldFullName, String newFullName) {
                JavaInfo oldBaseJi = RenameTools.actFullNameToActBaseInfo(oldFullName);
                if (oldBaseJi.fullName.equals(info.fullName)) {
                    return RenameTools.actFullNameToLayout(newFullName);
                }
                return null;
            }
        }));
    }

    public void setTagFor_findView(StringBuilder findViewSb) {
        Ts.ls(vis, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo vi) {
                String viParent = LayoutTools.getViParent(vi);
                boolean isCoreR = vi.id.startsWith("core_");
                addLnTag(findViewSb, "        [n1] = [n2]findViewById([n3].R.id.[n4]);",
                        LayoutTools.getViFieldName(vi),
                        getFindViewByIdParent(viParent),
                        isCoreR ? Pkg.COOLTU_LIB4A : layoutId.rPackage,
                        vi.id);
                return false;
            }
        });
    }

    protected abstract String getFindViewByIdParent(String viParent);

    public void setTagFor_netMethos(StringBuilder sb) {
        Ts.ls(netBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addModel(sb, new ActBaseNetBackModel(position != 0, element));
                return false;
            }
        });
    }


    public void setTagFor_netBackMethods(StringBuilder sb) {
        Ts.ls(netBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(sb,
                        "    public void [methodName]([params]) {}",
                        ElementTools.simpleName(element),
                        ParamTools.getDefaultParam(element).getParams());
                return false;
            }
        });
    }

    /***************************************
     *
     * 表单绑定
     *
     ***************************************/

    private KV<String, String> formBeanKv;


    public void addFormBean(String type, String name) {
        addField(type, name);
        this.formBeanKv = new KV<>(type, name);
    }

    private ListValueMap<Integer, FormItemInfo> formItemMap = new ListValueMap<>();
    private HashMap<String, String> needLinkMap = new HashMap<>();

    public void addFormItem(FormItemInfo info) {
        formItemMap.get(info.formItemType).add(info);
        formItemMap.get(FormType.TOTAL).add(info);

        if (StringTool.isNotBlank(info.linkClass)) {
            Ts.ts(info.ids).ls(new BaseTs.EachTs<Integer>() {
                @Override
                public boolean each(int position, Integer integer) {
                    String id = info.idMap.get(integer).toString();
                    needLinkMap.put(id, id);
                    return false;
                }
            });
        }

        addField("BindHandler", "handler");

        if (info.formItemType == FormType.RADIO_GROUP) {
            addField(FullName.RADIO_GROUP, info.fieldName);
            FromItemInfoForRg rgInfo = (FromItemInfoForRg) info;
            if (rgInfo.hasOnSetItem) {
                addField(rgInfo.onSetItemClass, rgInfo.onSetItemName);
            }
            return;
        }


    }

    public void setTagFor_binds(StringBuilder bindsSb) {
        if (formBeanKv != null) {


            HashMap<String, String> map111 = new HashMap<>();
            Ts.ls(formItemMap.get(FormType.RADIO_GROUP), new BaseTs.EachTs<FormItemInfo>() {
                @Override
                public boolean each(int position, FormItemInfo info) {
                    FromItemInfoForRg rgInfo = (FromItemInfoForRg) info;
                    if (rgInfo.hasOnSetItem) {
                        KV<String, String> kv = Ts.ts(otherFields).get(new BaseTs.IsThisOne<KV<String, String>>() {
                            @Override
                            public boolean isThisOne(int position, KV<String, String> kv) {
                                if (kv.v.equals(rgInfo.onSetItemName)) {
                                    return true;
                                }
                                return false;
                            }
                        });
                        if (kv != null && kv.k.equals(rgInfo.onSetItemClass)) {
                            if (!map111.containsKey(rgInfo.onSetItemName)) {
                                map111.put(rgInfo.onSetItemName, rgInfo.onSetItemName);
                                addLnTag(bindsSb, "");
                                addLnTag(bindsSb, "        [defaultOnSetItem] = new [DefaultOnSetItem]();"
                                        , rgInfo.onSetItemName, rgInfo.onSetItemClass);
                            }
                        } else {
                            rgInfo.onSetItemName = "";
                        }
                    }

                    String setOnSetItem = "";
                    if (rgInfo.hasOnSetItem) {
                        if (StringTool.isBlank(rgInfo.onSetItemName)) {
                            setOnSetItem = TagTools.getLine(".setOnSetItem(new [onSetItem]())", rgInfo.onSetItemClass);
                        } else {
                            setOnSetItem = TagTools.getLine(".setOnSetItem([onSetItem])", rgInfo.onSetItemName);
                        }
                    }


                    addLnTag(bindsSb, "        [qualifiedRg] = [RadioGroup].obtain(this).setBts([typeLl])[setOnSetItem];",
                            info.fieldName, FullName.RADIO_GROUP, info.viewName, setOnSetItem);
                    addLnTag(bindsSb, "        [hardRl].setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, [diff2CheckRg]);",
                            info.viewName, info.fieldName);
                    return false;
                }
            });


            //addLnTag(bindsSb, "        [form] = new [FormFrom]();", FieldName.CHECK_FORM, formClass);
            addLnTag(bindsSb, "        initFormView();");
            addLnTag(bindsSb, "        if ([user] == null) {", formBeanKv.v);
            addLnTag(bindsSb, "            [user] = new [User]();", formBeanKv.v, formBeanKv.k);
            addLnTag(bindsSb, "            [initFormBean] = true;", FieldName.INIT_FORM_BEAN);
            addLnTag(bindsSb, "        }");
            addLnTag(bindsSb, "        handler = new BindHandler([user]);", formBeanKv.v);

            List<FormItemInfo> tvInfos = formItemMap.get(FormType.TEXT_VIEW);
            List<FormItemInfo> etInfos = formItemMap.get(FormType.EDIT_TEXT);
            List<FormItemInfo> rgInfos = formItemMap.get(FormType.RADIO_GROUP);
            List<FormItemInfo> sbInfos = formItemMap.get(FormType.SEEK_BAR);

            addChange(bindsSb, etInfos, "addTextChangedListener", FullName.HANDLER_TEXT_WATCHER, FormType.EDIT_TEXT);
            addChange(bindsSb, tvInfos, "addTextChangedListener", FullName.HANDLER_TEXT_WATCHER, FormType.TEXT_VIEW);
            addChange(bindsSb, rgInfos, "addOnSelectChange", FullName.HANDLER_ON_SELECT_CHANGE, FormType.RADIO_GROUP);
            addChange(bindsSb, sbInfos, "setOnSeekBarChangeListener", FullName.HANDLER_ON_SEEK_BAR_CHANGE_LISTENER, FormType.SEEK_BAR);


            addLnTag(bindsSb, "        if (![initFormBean]) {", FieldName.INIT_FORM_BEAN);

            bindEchoForViewTool(bindsSb, etInfos, "setEditTextAndSelection");
            bindEchoForViewTool(bindsSb, tvInfos, "setText");
            bindEcho(bindsSb, rgInfos, "setSelected");
            bindEcho(bindsSb, sbInfos, "setProgress");

            addLnTag(bindsSb, "        }");
        }
    }

    private void bindEcho(StringBuilder sb, List<FormItemInfo> infos, String method) {
        Ts.ls(infos, new BaseTs.EachTs<FormItemInfo>() {
            @Override
            public boolean each(int position, FormItemInfo info) {
                if (info.echoCheck) {
                    addLnTag(sb, FormTool.check1(formBeanKv.v, info));
                    addLnTag(sb,
                            "                [f1].[method]([params]);", info.fieldName, method, FormTool.toView(info)
                    );
                    addLnTag(sb, "            }");
                } else {
                    addLnTag(sb,
                            "            [f1].[method]([params]);", info.fieldName, method, FormTool.toView(info)
                    );
                }
                return false;
            }
        });
    }

    private void addChange(StringBuilder sb, List<FormItemInfo> infos, String method, String changeType, int type) {
        Ts.ls(infos, new BaseTs.EachTs<FormItemInfo>() {
            @Override
            public boolean each(int position, FormItemInfo info) {
                addLnTag(sb,
                        "        [nameEt].[addOnSelectChange](new [HandlerTextWatcher](handler,[type], [index]));",
                        info.fieldName,
                        method,
                        changeType,
                        type,
                        position);

                if (StringTool.isNotBlank(info.linkClass)) {
                    addLnTag(sb, "");
                    addLnTag(sb, "        [FormLink] [tv]Link = new [AverageLink](this)",
                            FullName.FORM_LINK, info.fieldName, info.linkClass);
                    addLnTag(sb, "                .setBean([calibration])", formBeanKv.v);
                    addLnTag(sb, "                .setTarget([tv])", info.fieldName);

                    ArrayList<String> lines = new ArrayList<>();
                    StringBuilder viewSb = new StringBuilder();
                    Ts.ts(info.ids).ls(new BaseTs.EachTs<Integer>() {
                        @Override
                        public boolean each(int position, Integer integer) {
                            try {
                                IdTools.Id id = info.idMap.get(integer);
                                if (position != 0) {
                                    viewSb.append(", ");
                                }
                                viewSb.append(id.rName);

                                lines.add(TagTools.getLine("        handler.addLink([id], [filed]Link);",
                                        id.toString(), info.fieldName));
                            } catch (Exception e) {
                                Logs.e(e);
                            }

                            return false;
                        }
                    });

                    addLnTag(sb, "                .setSrcs([srcs]);", viewSb.toString());
                    Ts.ls(lines, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String line) {
                            addLnTag(sb, line);
                            return false;
                        }
                    });
                    addLnTag(sb, "");
                }

                return false;
            }
        });
    }


    private void bindEchoForViewTool(StringBuilder sb, List<FormItemInfo> infos, String method) {
        Ts.ls(infos, new BaseTs.EachTs<FormItemInfo>() {
            @Override
            public boolean each(int position, FormItemInfo info) {
                if (info.echoCheck) {
                    addLnTag(sb, FormTool.check1(formBeanKv.v, info));
                    addLnTag(sb,
                            "                [ViewTool].[setText]([taskTv], [calibration.taskName]);",
                            FullName.VIEW_TOOL, method, info.fieldName, FormTool.toView(info));
                    addLnTag(sb, "            }");
                } else {
                    addLnTag(sb,
                            "            [ViewTool].[setText]([taskTv], [calibration.taskName]);",
                            FullName.VIEW_TOOL, method, info.fieldName, FormTool.toView(info));
                }
                return false;
            }
        });
    }


    public void setTagFor_bindHandler(StringBuilder sb) {
        if (formBeanKv != null) {
            BindHandlerModel bindHandlerModel = new BindHandlerModel();
            bindHandlerModel.setType(formBeanKv.k);
            bindHandlerModel.setBean(formBeanKv.v);
            bindHandlerModel.setCases(formItemMap);
            bindHandlerModel.setNeedLinks(needLinkMap);
            addModel(sb, bindHandlerModel);
        }
    }

    public void setTagFor_bindCheck(StringBuilder bindCheckSb) {
        if (formBeanKv != null) {
            addLnTag(bindCheckSb, "    protected boolean check[User]() {", ConvertTool.toClassType(formBeanKv.v));

            Ts.ls(formItemMap.get(FormType.TOTAL), new BaseTs.EachTs<FormItemInfo>() {
                @Override
                public boolean each(int position, FormItemInfo info) {
                    if (StringTool.isNotBlank(info.prompt)) {
                        addLnTag(bindCheckSb, FormTool.check(formBeanKv.v, info));
                        addLnTag(bindCheckSb, "            toast(\"[prompt]\");", info.prompt);
                        addLnTag(bindCheckSb, "            return false;");
                        addLnTag(bindCheckSb, "        }");
                    }
                    return false;
                }
            });

            addLnTag(bindCheckSb, "        return true;");
            addLnTag(bindCheckSb, "    }");
        }

    }

    public void setTagFor_setClick(StringBuilder setClickSb) {
        clickViewDeal(setClickSb, new ClickViewDealer() {
            @Override
            public void deal(StringBuilder sb, ClickViewInfo info) {
                if (info.inAct)
                    for (int i = 0; i < CountTool.count(info.ids); i++) {
                        addLnTag(setClickSb, "        [name].setOnClickListener(this);", info.idMap.get(info.ids[i]).rName);
                    }
            }
        });
    }


    public void setTagFor_clickMethods(StringBuilder methodsSb) {
        Ts.ls(clickViews, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(methodsSb,
                        "    protected void [methodName]([params]) {}",
                        ElementTools.simpleName(element),
                        ParamTools.getDefaultParam(element).getParams());
                return false;
            }
        });
    }

    public void setTagFor_onclicks(StringBuilder sb) {
        clickViewDeal(sb, new ClickViewDealer() {
            @Override
            public void deal(StringBuilder sb, ClickViewInfo info) {
                for (int i = 0; i < CountTool.count(info.ids); i++) {
                    IdTools.Id id = info.idMap.get(info.ids[i]);
                    addLnTag(sb, "            case [rPkg].R.id.[name]:", id.rPackage, id.rName);

                    if (info.checkLogin) {
                        if (isAct) {
                            addLnTag(sb, "                if (!isLogin(getThis())) {");
                        } else {
                            addLnTag(sb, "                if (!isLogin(getActivity())) {");
                        }
                        addLnTag(sb, "                    return;");
                        addLnTag(sb, "                }");
                    }

                    if (info.check && formBeanKv != null) {
                        addLnTag(sb, "                if (!check[User]()) {", ConvertTool.toClassType(formBeanKv.v));
                        addLnTag(sb, "                    return;");
                        addLnTag(sb, "                }");
                    }
                }


                final List<? extends VariableElement> ps = info.element.getParameters();
                final StringBuilder pSb = new StringBuilder();
                final int[] nums = new int[1];
                TypeLs.ls(ps, new EachType() {
                    @Override
                    public void each(int position, String type, String name) {
                        if (position != 0) {
                            pSb.append("\r\n");
                        }
                        pSb.append("                        ");
                        if (FullName.VIEW.equals(type)) {
                            pSb.append("view");
                        } else {
                            try {
                                addTag(pSb, "([type]) view.getTag([lib4a].R.id.tag_[n])", type, Pkg.COOLTU_LIB4A, nums[0]);
                            } catch (Exception e) {
                                Logs.i(e);
                            }
                            nums[0]++;
                        }
                        if (position != CountTool.count(ps) - 1) {
                            pSb.append(",");
                        }
                    }
                });

                addLnTag(sb, "                [methodName](", ElementTools.simpleName(info.element));
                addLnTag(sb, pSb.toString());
                addLnTag(sb, "                );");
                addLnTag(sb, "                break;");
            }
        });

    }

    private void clickViewDeal(StringBuilder sb, ClickViewDealer dealer) {
        Ts.ls(clickViews, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                dealer.deal(sb, new ClickViewInfo(element, element.getAnnotation(ClickView.class)));
                return false;
            }
        });

        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.clickViewMap, baseClass), new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                dealer.deal(sb, new ClickViewInfo(element, element.getAnnotation(InBaseClickView.class)));
                return false;
            }
        });
    }

    public static interface ClickViewDealer {
        void deal(StringBuilder sb, ClickViewInfo info);
    }


    public void setTagFor_onCreates(StringBuilder sb) {
        Ts.ls(adapters, new BaseTs.EachTs<BaseAdapterModel>() {
            @Override
            public boolean each(int position, BaseAdapterModel adapterModel) {
                addLnTag(sb, adapterModel.getOnCreates(info).toString());
                return false;
            }
        });
    }

    public void setTagFor_onDestroys(StringBuilder sb) {
        Ts.ls(adapters, new BaseTs.EachTs<BaseAdapterModel>() {
            @Override
            public boolean each(int position, BaseAdapterModel adapterModel) {
                addLnTag(sb, adapterModel.getOnDestorys(info).toString());
                return false;
            }
        });
    }


    public void setTagFor_others(StringBuilder sb) {
        Ts.ls(adapters, new BaseTs.EachTs<BaseAdapterModel>() {
            @Override
            public boolean each(int position, BaseAdapterModel adapterModel) {
                addLnTag(sb, adapterModel.getOthers(info).toString());
                return false;
            }
        });

        if (formBeanKv != null)
            addLnTag(sb, "    protected void initFormView() {}");
    }

    public void setTagFor_toastDialog(StringBuilder sb) {
        if (isToastDialog || !CountTool.isNull(ResForBaseDeal.getTs(ResForBaseDeal.toastDialogUseMap, baseClass))) {
            addModel(sb, new DialogForToastMethodModel(isAct));
        }
    }

    public void setTagFor_noticeDialog(StringBuilder sb) {
        if (isNoticeDialog || !CountTool.isNull(ResForBaseDeal.getTs(ResForBaseDeal.noticeDialogUseMap, baseClass))) {
            addModel(sb, new DialogForNoticeMethodModel(isAct));
        }
    }


    public void setTagFor_dialogs(StringBuilder sb) {
        dialogInfos.addAll(ResForBaseDeal.getTs(ResForBaseDeal.dialogMap, baseClass));
        Ts.ls(dialogInfos, new BaseTs.EachTs<DialogInfo>() {
            @Override
            public boolean each(int position, DialogInfo info) {
                if (info != null) {
                    addModel(sb, new DialogMethodModel(info, isAct));
                }
                return false;
            }
        });
    }


    public void setTagFor_editDialog(StringBuilder sb) {
        editDialogInfos.addAll(ResForBaseDeal.getTs(ResForBaseDeal.editDialogMap, baseClass));
        Ts.ls(editDialogInfos, new BaseTs.EachTs<EditDialogInfo>() {
            @Override
            public boolean each(int position, EditDialogInfo info) {
                if (info != null) {
                    addModel(sb, new DialogForEditMethodModel(info, isAct));
                }
                return false;
            }
        });
    }

    public void setTagFor_longClickListener(StringBuilder sb) {
        if (CountTool.isNull(longClickViews))
            return;
        sb.append(", ").append(FullName.ON_LONG_CLICK_LISTENER);
    }

    public void setTagFor_onLongClick(StringBuilder sb) {
        if (CountTool.isNull(longClickViews))
            return;

        addLnTag(sb, "    @Override");
        addLnTag(sb, "    public boolean onLongClick(View view) {");
        addLnTag(sb, "        switch (view.getId()) {");

        Ts.ls(longClickViews, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {

                LongClickView clickView = element.getAnnotation(LongClickView.class);
                int[] ids = clickView.value();
                Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(element, LongClickView.class, ids);

                for (int i = 0; i < CountTool.count(ids); i++) {
                    IdTools.Id id = idMap.get(ids[i]);
                    addLnTag(sb, "            case [rPkg].R.id.[name]:", id.rPackage, id.rName);

                    if (clickView.checkLogin()) {
                        if (isAct) {
                            addLnTag(sb, "                if (!isLogin(getThis())) {");
                        } else {
                            addLnTag(sb, "                if (!isLogin(getActivity())) {");
                        }
                        addLnTag(sb, "                    return false;");
                        addLnTag(sb, "                }");
                    }

                    if (clickView.check() && StringTool.isNotBlank(beanName)) {
                        addLnTag(sb, "                if (!check[User]()) {", ConvertTool.toClassType(beanName));
                        addLnTag(sb, "                    return false;");
                        addLnTag(sb, "                }");
                    }
                }

                addLnTag(sb, "                return [methodName](", ElementTools.simpleName(element));

                final List<? extends VariableElement> ps = element.getParameters();
                final int[] nums = new int[1];
                TypeLs.ls(ps, new EachType() {
                    @Override
                    public void each(int position, String type, String name) {
                        if (position != 0) {
                            addTag(sb, "\r\n");
                        }
                        addTag(sb, "                        ");
                        if (FullName.VIEW.equals(type)) {
                            addTag(sb, "view");
                        } else {
                            try {
                                addTag(sb, "([type]) view.getTag([lib4a].R.id.tag_[n])",
                                        type, Pkg.COOLTU_LIB4A, nums[0]);
                            } catch (Exception e) {
                                Logs.i(e);
                            }
                            nums[0]++;
                        }
                        if (position != CountTool.count(ps) - 1) {
                            addTag(sb, ",");
                        }
                    }
                });


                addTag(sb, "\r\n");
                addLnTag(sb, "                );");

                return false;
            }
        });

        addLnTag(sb, "        }");
        addLnTag(sb, "        return false;");
        addLnTag(sb, "    }");
    }

    public void setTagFor_onLongClickMethods(StringBuilder sb) {
        Ts.ls(longClickViews, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(sb,
                        "    protected boolean [methodName]([params]) { return false; }",
                        ElementTools.simpleName(element),
                        ParamTools.getDefaultParam(element).getParams());
                return false;
            }
        });
    }

    public void setTagFor_setLongClick(StringBuilder sb) {
        Ts.ls(longClickViews, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                LongClickView clickView = element.getAnnotation(LongClickView.class);
                int[] ids = clickView.value();
                boolean inAct = clickView.inAct();
                Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(element, LongClickView.class, ids);

                if (inAct)
                    for (int i = 0; i < CountTool.count(ids); i++) {
                        addLnTag(sb, "        [name].setOnLongClickListener(this);", idMap.get(ids[i]).rName);
                    }

                return false;
            }
        });
    }

    public void setTagFor_field(StringBuilder sb) {
        Ts.ls(vis, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo vi) {
                addFieldSb(sb, vi.name, LayoutTools.getViFieldName(vi));
                return false;
            }
        });

        if (StringTool.isNotBlank(beanType)) {
            addFieldSb(sb, beanType, beanName);
            addFieldSb(sb, "BindHandler", "handler");
        }

        Ts.ls(otherFields, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addFieldSb(sb, kv.k, kv.v);
                return false;
            }
        });
    }

    public void setTagFor_resources(StringBuilder sb) {
        Ts.ls(dpKvs, new BaseTs.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                String type = dpTypes.get(position);
                if (FullName.INT.equals(type)) {
                    addLnTag(sb, "        [name] = [MobileTool].dpToPx([11.2]f);",
                            kv.k,
                            FullName.MOBILE_TOOL,
                            kv.v);
                } else if (FullName.FLOAT.equals(type)) {
                    addLnTag(sb, "        [name] = [MobileTool].dpToPxFloat([11.2]f);",
                            kv.k,
                            FullName.MOBILE_TOOL,
                            kv.v);
                }

                return false;
            }
        });

        Ts.ls(resKvs, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addLnTag(sb, "        [name] = [ResourceTool].getDimen([id]);",
                        kv.k,
                        FullName.RESOURCE_TOOL,
                        kv.v.toString()
                );
                return false;
            }
        });

        Ts.ls(colorIdKvs, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addLnTag(sb, "        [bgColor] = [ResourceTool].getColor([r]);",
                        kv.k,
                        FullName.RESOURCE_TOOL,
                        kv.v.toString());
                return false;
            }
        });

        Ts.ls(colorStrKvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addLnTag(sb, "        [fgColor] = [Color].parseColor(\"[color]\");",
                        kv.k,
                        FullName.COLOR,
                        kv.v
                );
                return false;
            }
        });

    }

    public void setTagFor_resultMethos(StringBuilder sb) {
        Ts.ls(actBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(
                        sb,
                        "    public void [index]([params]) {}",
                        ElementTools.simpleName(element),
                        ParamTools.getDefaultParam(element).getParams());
                return false;
            }
        });
    }

    public void setTagFor_results(StringBuilder sb) {
        Ts.ls(actBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addModel(sb, new ActBackModel(position != 0, element));
                return false;
            }
        });
        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.actBackMap, baseClass), new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addModel(sb, new ActBackModel(!CountTool.isNull(actBackElements) || position != 0, ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return element.getAnnotation(InBaseActBack.class).value();
                    }
                }), element));
                return false;
            }
        });
    }

    public void setTagFor_addBus(StringBuilder sb) {
        Ts.ls(busBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                BusBack busBack = ee.getAnnotation(BusBack.class);
                String busClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return busBack.value();
                    }
                });
                JavaInfo busInfo = NameTools.getJavaInfoByName(busClass);

                List<? extends VariableElement> parameters = ee.getParameters();
                int count = CountTool.count(parameters);
                String params = "";
                String realParams = "";
                if (count != 0) {
                    VariableElement ve = parameters.get(0);
                    KV<String, String> kv = ElementTools.getFiledKv(ve);
                    params = kv.k + " " + kv.v;
                    realParams = kv.v;
                }

                addLnTag(sb, "        addBus(new [BluetoothBus]() {", busInfo.fullName);
                addLnTag(sb, "            @Override");
                addLnTag(sb, "            protected void [bluetoothBus]Back([params]) {",
                        ConvertTool.toMethodType(busInfo.name),
                        params);
                addLnTag(sb, "                [base].this.[name]([data]);",
                        info.name,
                        ElementTools.simpleName(ee),
                        realParams);
                addLnTag(sb, "            }");
                addLnTag(sb, "        });");

                return false;
            }
        });
    }


    public void setTagFor_busBackMethods(StringBuilder sb) {
        Ts.ls(busBackElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {

                List<? extends VariableElement> parameters = ee.getParameters();
                int count = CountTool.count(parameters);
                String params = "";
                if (count != 0) {
                    VariableElement ve = parameters.get(0);
                    KV<String, String> kv = ElementTools.getFiledKv(ve);
                    params = kv.k + " " + kv.v;
                }

                addLnTag(sb, "    public void [bluetoothBusBack]([String data]) {}",
                        ElementTools.simpleName(ee), params);
                return false;
            }
        });
    }

}
