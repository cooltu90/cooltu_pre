package com.codingtu.cooltu.processor.worker.model.net_retrofit;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.lib.bean.NetMethodDeal;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.modelinterface.NetModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class NetModel extends SingleCoreToolsBaseModel implements NetModelInterface {

    public static final NetModel model = new NetModel();
    private List<ExecutableElement> methodElements = new ArrayList<>();
    private List<String> baseUrls = new ArrayList<>();
    private List<String> apiTypeNames = new ArrayList<>();
    //    private NetMethodDeal netMethodDeal;
    private List<NetMethodDeal> netMethodDeals = new ArrayList<>();

    public NetModel() {
        super(Constant.NAME_NET);
    }

    public void add(ExecutableElement ee, String baseUrl, String apiTypeName) {
        methodElements.add(ee);
        baseUrls.add(baseUrl);
        apiTypeNames.add(apiTypeName);
//        netMethodDeal = new NetMethodDeal(ee);
        netMethodDeals.add(new NetMethodDeal(ee));
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_backNameFileds(StringBuilder sb) {
        Ts.ls(methodElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                String methodName = ElementTools.simpleName(ee);
                addLnTag(sb, "    private static final String [GET_USER_LIST1] = \"[getUserList1]Back\";",
                        ConvertTool.toStaticType(methodName), methodName);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_baseUrls(StringBuilder sb) {
        HashMap<String, String> urlNames = new HashMap<>();
        Ts.ls(methodElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                String baseUrl = baseUrls.get(position);
                String apiTypeName = apiTypeNames.get(position);
                String apiBaseUrlName = ConvertTool.toStaticType(apiTypeName);

                if (!urlNames.containsKey(apiBaseUrlName)) {
                    if (StringTool.isNotBlank(baseUrl)) {
                        urlNames.put(apiBaseUrlName, apiBaseUrlName);
                        addLnTag(sb, "    private static final String BASE_URL_[USER_API] = \"[baseUrl]\";",
                                ConvertTool.toStaticType(apiTypeName), baseUrl);
                    }
                }

                NetMethodDeal netMethodDeal = netMethodDeals.get(position);

                if (StringTool.isNotBlank(netMethodDeal.getMethodBaseUrl())) {
                    addLnTag(sb, "    private static final String BASE_URL_[USER_API]_[methodName] = \"[baseUrl]\";",
                            apiBaseUrlName, ConvertTool.toStaticType(ElementTools.simpleName(ee)), netMethodDeal.getMethodBaseUrl());
                }

                return false;
            }
        });
    }

    @Override
    public void setTagFor_methods(StringBuilder sb) {
        Ts.ls(methodElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                String baseUrl = baseUrls.get(position);
                String apiTypeName = apiTypeNames.get(position);
                addModel(sb, new NetMethodModel(ee, baseUrl, apiTypeName));
                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools;

import com.codingtu.cooltu.lib4a.net.NetTool;
import com.codingtu.cooltu.lib4a.net.api.API;
import com.codingtu.cooltu.lib4a.net.api.CreateApi;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class Net {

[[backNameFileds]]

[[baseUrls]]

[[methods]]

}
model_temp_end */
