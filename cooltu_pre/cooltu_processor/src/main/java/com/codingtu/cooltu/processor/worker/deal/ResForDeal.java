package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.model.AdapterModels;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.deal.base.BaseResForDeal;
import com.codingtu.cooltu.processor.worker.model.ActBaseModel;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;
import com.codingtu.cooltu.processor.worker.model.PassModel;
import com.codingtu.cooltu.processor.worker.model.StartModel;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ResForDeal extends BaseResForDeal {
    private BaseParentModel baseParentModel;

    @Override
    public void deal(Element element) {

        TypeElement te = (TypeElement) element;
        ResFor resFor = te.getAnnotation(ResFor.class);
        String actFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return resFor.value();
            }
        });

        baseParentModel = getBaseParentModel(actFullName);
        if (baseParentModel == null) {
            return;
        }

        ToastDialogUse toastDialogUse = te.getAnnotation(ToastDialogUse.class);
        if (toastDialogUse != null) {
            dealToastDialogUse();
        }

        NoticeDialogUse noticeDialogUse = te.getAnnotation(NoticeDialogUse.class);
        if (noticeDialogUse != null) {
            dealNoticeDialogUse();
        }

        List<VariableElement> startGroups = new ArrayList<>();
        Ts.ls(te.getEnclosedElements(), (position, element1) -> {
            if (element1 instanceof VariableElement) {
                VariableElement ve = (VariableElement) element1;
                StartGroup startGroup = ve.getAnnotation(StartGroup.class);
                if (startGroup != null) {
                    startGroups.add(ve);
                }

                Adapter adapter = ve.getAnnotation(Adapter.class);
                if (adapter != null) {
                    dealAdapter(ve, adapter);
                }

                ColorRes colorRes = ve.getAnnotation(ColorRes.class);
                if (colorRes != null) {
                    dealColorRes(ve, colorRes);
                }

                ColorStr colorStr = ve.getAnnotation(ColorStr.class);
                if (colorStr != null) {
                    dealColorStr(ve, colorStr);
                }

                Dp dp = ve.getAnnotation(Dp.class);
                if (dp != null) {
                    dealDp(ve, dp);
                }

                Dimen dimen = ve.getAnnotation(Dimen.class);
                if (dimen != null) {
                    dealDimen(ve, dimen);
                }

                EditDialogUse editDialogUse = ve.getAnnotation(EditDialogUse.class);
                if (editDialogUse != null) {
                    dealEditDialogUse(ve, editDialogUse);
                }
                DialogUse dialogUse = ve.getAnnotation(DialogUse.class);
                if (dialogUse != null) {
                    dealDialogUse(ve, dialogUse);
                }

            }
            return false;
        });
        dealStartGroup(actFullName, startGroups);

        String baseClass = baseParentModel.getBaseClass();

        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.colorStrMap, baseClass), new BaseTs.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                dealColorStr(ve, ve.getAnnotation(ColorStr.class));
                return false;
            }
        });

        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.colorResMap, baseClass), new BaseTs.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                dealColorRes(ve, ve.getAnnotation(ColorRes.class));
                return false;
            }
        });

        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.dpMap, baseClass), new BaseTs.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                dealDp(ve, ve.getAnnotation(Dp.class));
                return false;
            }
        });

        Ts.ls(ResForBaseDeal.getTs(ResForBaseDeal.dimenMap, baseClass), new BaseTs.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                dealDimen(ve, ve.getAnnotation(Dimen.class));
                return false;
            }
        });


    }

    private void dealStartGroup(String actFullName, List<VariableElement> startGroups) {
        if (NameTools.isFragment(actFullName)) {
            return;
        }

        ActBaseModel actBaseModel = ActBaseDeal.getActBaseModel(actFullName);


        List<VariableElement> ts = ResForBaseDeal.getTs(ResForBaseDeal.startGroupMap, actBaseModel.getBaseClass());
        ts.addAll(startGroups);

//        startGroups.addAll(
//                ResForBaseDeal.getTs(ResForBaseDeal.startGroupMap, actBaseModel.getBaseClass()));

        startGroups = ts;


        String actStaticName = ConvertTool.toStaticType(NameTools.getJavaSimpleName(actFullName));
        if (CountTool.isNull(startGroups)) {
            //没有
            StartModel.model.addStart(actFullName, actStaticName, null);
        } else {
            ListValueMap<Integer, KV<String, String>> ikv = new ListValueMap<>();
            Ts.ls(startGroups, (position, element) -> {
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
                        Ts.ts(group).ls(new BaseTs.EachTs<Integer>() {
                            @Override
                            public boolean each(int position, Integer integer) {
                                ikv.get(integer).add(kv);
                                return false;
                            }
                        });
                    }
                }
                return false;
            });
            try {
                actBaseModel.addStartParams(ikv);
            } catch (Exception e) {

            }
            StartModel.model.addStart(actFullName, actStaticName, ikv);
        }

    }

    private void dealDimen(VariableElement element, Dimen dimen) {
        IdTools.Id id = IdTools.elementToId(element, Dimen.class, dimen.value());
        baseParentModel.addDimen(ElementTools.simpleName(element), id);
    }

    private void dealDp(VariableElement element, Dp dpAnno) {
        baseParentModel.addDp(ElementTools.getType(element), ElementTools.simpleName(element), dpAnno.value());
    }

    private void dealColorStr(VariableElement element, ColorStr colorStr) {
        baseParentModel.addColorStr(ElementTools.simpleName(element), colorStr.value());
    }

    private void dealColorRes(VariableElement element, ColorRes colorRes) {
        IdTools.Id id = IdTools.elementToId(element, ColorRes.class, colorRes.value());
        baseParentModel.addColorRes(ElementTools.simpleName(element), id);
    }

    private void dealAdapter(VariableElement ve, Adapter adapter) {
        BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(adapter.type());
        adapterModel.setAdapter(ElementTools.getType(ve));
        adapterModel.setRvName(adapter.rvName());
        adapterModel.setAdapterName(ElementTools.simpleName(ve));
        baseParentModel.setAdapter(adapterModel, "");
    }

    private void dealEditDialogUse(VariableElement ve, EditDialogUse use) {
        baseParentModel.addEditDialog(toEditDialogInfo(ve, use));
    }

    private void dealToastDialogUse() {
        baseParentModel.addToastDialog();
    }

    private void dealNoticeDialogUse() {
        baseParentModel.addNoticeDialog();
    }

    private void dealDialogUse(VariableElement ve, DialogUse use) {
        baseParentModel.addDialog(toDialogInfo(ve, use));
    }
}
