package com.codingtu.cooltu.processor.worker.deal;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.ui.ActRes;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.model.AdapterModels;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.ActBaseModel;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;
import com.codingtu.cooltu.processor.worker.model.PassModel;
import com.codingtu.cooltu.processor.worker.model.StartModel;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;

public class ActResDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        TypeElement te = (TypeElement) element;
        ActRes actRes = te.getAnnotation(ActRes.class);
        String actFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actRes.value();
            }
        });

        ToastDialogUse toastDialogUse = te.getAnnotation(ToastDialogUse.class);
        if (toastDialogUse != null) {
            dealToastDialogUse(actFullName, toastDialogUse);
        }

        List<VariableElement> startGroups = new ArrayList<>();
        Ts.ls(te.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof VariableElement) {
                    VariableElement ve = (VariableElement) element;
                    StartGroup startGroup = ve.getAnnotation(StartGroup.class);
                    if (startGroup != null) {
                        startGroups.add(ve);
                    }

                    Adapter adapter = element.getAnnotation(Adapter.class);
                    if (adapter != null) {
                        dealAdapter(actFullName, ve, adapter);
                    }

                    ColorRes colorRes = element.getAnnotation(ColorRes.class);
                    if (colorRes != null) {
                        dealColorRes(actFullName, ve, colorRes);
                    }

                    ColorStr colorStr = element.getAnnotation(ColorStr.class);
                    if (colorStr != null) {
                        dealColorStr(actFullName, ve, colorStr);
                    }

                    Dp dp = element.getAnnotation(Dp.class);
                    if (dp != null) {
                        dealDp(actFullName, ve, dp);
                    }

                    Dimen dimen = element.getAnnotation(Dimen.class);
                    if (dimen != null) {
                        dealDimen(actFullName, ve, dimen);
                    }

                    EditDialogUse editDialogUse = element.getAnnotation(EditDialogUse.class);
                    if (editDialogUse != null) {
                        dealEditDialogUse(actFullName, ve, editDialogUse);
                    }
                    DialogUse dialogUse = element.getAnnotation(DialogUse.class);
                    if (dialogUse != null) {
                        dealDialogUse(actFullName, ve, dialogUse);
                    }

                }
                return false;
            }
        });
        dealStartGroup(actFullName, startGroups);
    }

    private void dealStartGroup(String actFullName, List<VariableElement> startGroups) {
        String actStaticName = ConvertTool.toStaticType(NameTools.getJavaSimpleName(actFullName));
        if (CountTool.isNull(startGroups)) {
            //没有
            StartModel.model.addStart(actFullName, actStaticName, null);
        } else {
            ListValueMap<Integer, KV<String, String>> ikv = new ListValueMap<>();
            Ts.ls(startGroups, new Each<Element>() {
                @Override
                public boolean each(int position, Element element) {
                    if (element instanceof VariableElement) {
                        KV<String, String> kv = ElementTools.getFiledKv((VariableElement) element);
                        PassModel.model.add(kv);

                        int[] group = null;
                        StartGroup startGroup = element.getAnnotation(StartGroup.class);
                        if (startGroup != null) {
                            group = startGroup.value();
                        }
                        if (CountTool.isNull(group)) {
                            ikv.get(0).add(kv);
                        } else {
                            Ts.ls(group, new Each<Integer>() {
                                @Override
                                public boolean each(int position, Integer integer) {
                                    ikv.get(integer).add(kv);
                                    return false;
                                }
                            });
                        }
                    }
                    return false;
                }
            });
            try {
                ActBaseModel actBaseModel = ActBaseDeal.getActBaseModel(actFullName);
                actBaseModel.addStartParams(ikv);
            } catch (Exception e) {

            }
            StartModel.model.addStart(actFullName, actStaticName, ikv);
        }

    }


    private void dealDimen(String classFullName, VariableElement element, Dimen dimen) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        IdTools.Id id = IdTools.elementToId(element, Dimen.class, dimen.value());
        if (baseParentModel != null) {
            baseParentModel.addDimen(ElementTools.simpleName(element), id);
        }
    }

    private void dealDp(String classFullName, VariableElement element, Dp dpAnno) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        if (baseParentModel != null) {
            baseParentModel.addDp(ElementTools.getType(element), ElementTools.simpleName(element), dpAnno.value());
        }
    }

    private void dealColorStr(String classFullName, VariableElement element, ColorStr colorStr) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        if (baseParentModel != null) {
            baseParentModel.addColorStr(ElementTools.simpleName(element), colorStr.value());
        }
    }

    private void dealColorRes(String classFullName, VariableElement element, ColorRes colorRes) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        IdTools.Id id = IdTools.elementToId(element, ColorRes.class, colorRes.value());
        if (baseParentModel != null) {
            baseParentModel.addColorRes(ElementTools.simpleName(element), id);
        }
    }

    private void dealAdapter(String classFullName, VariableElement ve, Adapter adapter) {
        BaseParentModel baseModel = getBaseParentModel(classFullName);
        if (baseModel != null) {
            BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(adapter.type());
            adapterModel.setAdapter(ElementTools.getType(ve));
            adapterModel.setRvName(adapter.rvName());
            adapterModel.setAdapterName(ElementTools.simpleName(ve));
            baseModel.setAdapter(adapterModel, "");
        }
    }

    private void dealEditDialogUse(String actFullName, VariableElement ve, EditDialogUse use) {
        BaseParentModel baseParentModel = getBaseParentModel(actFullName);
        if (baseParentModel != null) {
            EditDialogInfo info = new EditDialogInfo();
            KV<String, String> kv = ElementTools.getFiledKv(ve);
            info.name = kv.v;
            info.title = use.title();
            info.hint = use.hint();
            info.inputType = use.inputType();
            info.stopAnimation = use.stopAnimation();
            info.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return use.objType();
                }
            });
            info.reserve = use.reserve();
            baseParentModel.addEditDialog(info);
        }
    }

    private void dealToastDialogUse(String actFullName, ToastDialogUse toastDialogUse) {
        BaseParentModel baseParentModel = getBaseParentModel(actFullName);
        if (baseParentModel != null) {
            baseParentModel.addToastDialog();
        }
    }

    private void dealDialogUse(String actFullName, VariableElement ve, DialogUse use) {
        BaseParentModel baseParentModel = getBaseParentModel(actFullName);
        if (baseParentModel != null) {
            KV<String, String> kv = ElementTools.getFiledKv(ve);

            DialogInfo dialogInfo = new DialogInfo();
            dialogInfo.name = kv.v;
            dialogInfo.title = use.title();
            dialogInfo.content = use.content();
            dialogInfo.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return use.objType();
                }
            });
            baseParentModel.addDialog(dialogInfo);
        }
    }
}
