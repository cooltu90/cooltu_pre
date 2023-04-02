package core.processor.worker.model.net_retrofit;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import core.processor.annotation.net.Param;
import core.processor.annotation.net.ParamType;
import core.processor.lib.bean.NetMethodDeal;
import core.processor.lib.ls.TypeLss;
import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.TagTools;
import core.processor.modelinterface.ApiServiceMethodModelInterface;
import core.processor.worker.model.base.SubBaseModel;

public class ApiServiceMethodModel extends SubBaseModel implements ApiServiceMethodModelInterface {

    private final ExecutableElement ee;
    private final List<? extends VariableElement> parameters;
    private final NetMethodDeal netMethodDeal;

    public ApiServiceMethodModel(ExecutableElement ee) {
        this.ee = ee;
        parameters = ee.getParameters();
        netMethodDeal = new NetMethodDeal(ee);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_methodName(StringBuilder sb) {
        addTag(sb, ElementTools.simpleName(this.ee));
    }

    @Override
    public void setTagFor_method(StringBuilder sb) {
        if (netMethodDeal.isFormUrlEncoded()) {
            addLnTag(sb, "    @retrofit2.http.FormUrlEncoded");
        }
        addTag(sb, "    @retrofit2.http.[PUT](\"[sss]\")", netMethodDeal.netMethodName(), netMethodDeal.value());
    }


    @Override
    public void setTagFor_params(StringBuilder sb) {
        TypeLss.ls(parameters, new TypeLss.EachTypePlus() {
            @Override
            public void each(int position, VariableElement ve, String type, String name) {
                int paramType = ParamType.DEFAULT;
                String value = name;
                boolean encoded = false;

                Param param = ve.getAnnotation(Param.class);
                if (param == null) {
                    if (netMethodDeal.isJsonBody()) {
                        return;
                    }
                } else {
                    paramType = param.type();
                    if (StringTool.isNotBlank(param.value())) {
                        value = param.value();
                    }
                    encoded = param.encoded();
                }

                if (paramType == ParamType.DEFAULT) {
                    String line = TagTools.getLine("[value = ]\"[page]\"[, encoded = true]",
                            encoded ? "value = " : "", value, encoded ? ", encoded = true" : "");

                    if (position == CountTool.count(parameters) - 1 && !netMethodDeal.isJsonBody()) {
                        addTag(sb, "            @retrofit2.http.[Field]([query]) [int] [page]",
                                netMethodDeal.getFieldType(), line, type, name);
                    } else {
                        addLnTag(sb, "            @retrofit2.http.[Field]([query]) [int] [page],",
                                netMethodDeal.getFieldType(), line, type, name);
                    }
                } else if (paramType == ParamType.PATH) {
                    String line = TagTools.getLine("[value = ]\"[page]\"[, encoded = true]",
                            encoded ? "value = " : "", value, encoded ? ", encoded = true" : "");

                    if (position == CountTool.count(parameters) - 1 && !netMethodDeal.isJsonBody()) {
                        addTag(sb, "            @retrofit2.http.Path([query]) [int] [page]",
                                line, type, name);
                    } else {
                        addLnTag(sb, "            @retrofit2.http.Path([query]) [int] [page],",
                                line, type, name);
                    }
                } else if (paramType == ParamType.HEADER) {
                    if (position == CountTool.count(parameters) - 1 && !netMethodDeal.isJsonBody()) {
                        addTag(sb, "            @retrofit2.http.Header(\"[query]\") [int] [page]",
                                value, type, name);
                    } else {
                        addLnTag(sb, "            @retrofit2.http.Header(\"[query]\") [int] [page],",
                                value, type, name);
                    }
                }


            }
        });

        if (netMethodDeal.isJsonBody()) {
            addTag(sb, "            @retrofit2.http.Body okhttp3.RequestBody body");
        }
    }
}
/* model_temp_start
[[method]]
    Flowable<Result<ResponseBody>> [[methodName]](
[[params]]
    );
model_temp_end */
