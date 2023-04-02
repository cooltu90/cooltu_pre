package core.processor.worker.model;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;

import javax.lang.model.element.ExecutableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.StringTool;
import core.processor.annotation.net.NetBack;
import core.processor.lib.ls.EachType;
import core.processor.lib.ls.TypeLss;
import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.NameTools;
import core.processor.modelinterface.ActBaseNetBackModelInterface;
import core.processor.worker.model.base.SubBaseModel;

public class ActBaseNetBackModel extends SubBaseModel implements ActBaseNetBackModelInterface {

    private boolean hasNetBack;
    private ExecutableElement netBackElement;
    private String methodName;
    private String methodBaseName;

    public ActBaseNetBackModel(boolean hasNetBack, ExecutableElement element) {
        this.hasNetBack = hasNetBack;
        netBackElement = element;
        methodName = ElementTools.simpleName(netBackElement);
        methodBaseName = NameTools.cutSuffix(methodName, Suffix.NET_BACK);
        info = NameTools.getNetBackInfo(methodBaseName);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    @Override
    public void setTagFor_backClass(StringBuilder sb) {
        addTag(sb, NameTools.getNetBackInfo(methodBaseName).fullName);
    }

    @Override
    public void setTagFor_backName(StringBuilder sb) {
        addTag(sb, methodName);
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        NetBack netBack = netBackElement.getAnnotation(NetBack.class);
        boolean mock = netBack.mock();
        String mockName = Pkg.MOCK + "." + ConvertTool.toClassType(methodBaseName) + Suffix.MOCK;

        //params
        TypeLss.ls(netBackElement.getParameters(), new EachType() {
            @Override
            public void each(int position, String type, String name) {
                if (position != 0) {
                    sb.append(", ");
                }
                //core.tools.net.params.GetUserByIdParams
                JavaInfo paramsInfo = NameTools.getSendParamsInfo(methodBaseName);

                if (type.equals(info.fullName)) {
                    sb.append("this");
                } else if (ClassTool.isType(type, String.class)) {
                    if (mock) {
                        addTag(sb, "new [name]().json", mockName);
                    } else {
                        sb.append("json");
                    }
                } else if (ClassTool.isList(type)) {
                    String beanType = StringTool.getSub(type, "List", "<", ">");
                    if (ClassTool.isObject(beanType)) {
                        sb.append("objs");
                    } else {
                        JavaInfo info1 = NameTools.getJavaInfoByName(beanType);

                        if (mock) {
                            addTag(sb, "new [name]().[user]s", mockName, ConvertTool.toMethodType(info1.name));
                        } else {
                            sb.append(ConvertTool.toMethodType(info1.name)).append("s");
                        }
                    }
                } else if (type.equals(paramsInfo.fullName)) {
                    addTag(sb, "([params]) params", type);
                } else {
                    JavaInfo info1 = NameTools.getJavaInfoByName(type);
                    if (mock) {
                        addTag(sb, "new [name]().[user]", mockName, ConvertTool.toMethodType(info1.name));
                    } else {
                        sb.append(ConvertTool.toMethodType(info1.name));
                    }
                }
            }
        });
    }

    @Override
    public void setTagFor_if(StringBuilder sb) {
        addIf(hasNetBack);
    }

}
/* model_temp_start
        [[if]] ("[[backName]]".equals(code)) {
            [[backClass]] back = new [[backClass]]() {
                @Override
                public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    [[backName]]([[params]]);
                }
            };
            back.accept(code, result, params, objs);
        }
model_temp_end */
