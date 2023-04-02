package core.processor.worker.model.net_retrofit;

import com.codingtu.cooltu.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.lib.bean.NetMethodDeal;
import core.processor.lib.tools.ElementTools;
import core.processor.modelinterface.NetModelInterface;
import core.processor.worker.model.base.SingleCoreToolsBaseModel;

public class NetModel extends SingleCoreToolsBaseModel implements NetModelInterface {

    public static final NetModel model = new NetModel();
    private List<ExecutableElement> methodElements = new ArrayList<>();
    private List<String> baseUrls = new ArrayList<>();
    private List<String> apiTypeNames = new ArrayList<>();
    private NetMethodDeal netMethodDeal;

    public NetModel() {
        super(Constant.NAME_NET);
    }

    public void add(ExecutableElement ee, String baseUrl, String apiTypeName) {
        methodElements.add(ee);
        baseUrls.add(baseUrl);
        apiTypeNames.add(apiTypeName);
        netMethodDeal = new NetMethodDeal(ee);
    }

    @Override
    public void setTagFor_backNameFileds(StringBuilder sb) {
        Ts.ls(methodElements, new Each<ExecutableElement>() {
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
        Ts.ls(methodElements, new Each<ExecutableElement>() {
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
        Ts.ls(methodElements, new Each<ExecutableElement>() {
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

import core.lib4a.net.NetTool;
import core.lib4a.net.api.API;
import core.lib4a.net.api.CreateApi;
import core.lib4a.net.bean.CoreSendParams;
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
