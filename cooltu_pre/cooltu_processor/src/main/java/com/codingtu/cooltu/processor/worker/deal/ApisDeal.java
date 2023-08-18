package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.net.SendParamsModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.ApiServiceMethodModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.ApiServiceModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.NetBackModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.NetMethodModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.NetModel;

/**************************************************
 *
 * 相关文件:
 * XxxxApiService
 * Net
 * XxxxNetBack
 * XxxxSendParams
 *
 **************************************************/
@To({ApiServiceMethodModel.class, NetModel.class, NetMethodModel.class})
public class ApisDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        forRetrofit(element);
    }

    private void forRetrofit(Element element) {
        Apis apis = element.getAnnotation(Apis.class);
        String baseUrl = apis.baseUrl();

        String apiTypeName = ElementTools.simpleName(element);
        JavaInfo info = NameTools.getApiServiceInfo(apiTypeName);
        ApiServiceModel apiServiceModel = new ApiServiceModel(info);

        Ts.ls(element.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                ExecutableElement ee = (ExecutableElement) element;
                //XxxxApiService
                apiServiceModel.addMethod(ee);
                //Net
                NetModel.model.add(ee, baseUrl, apiTypeName);

                String classTypeName = ConvertTool.toClassType(ElementTools.simpleName(ee));
                //XxxxSendParams
                if (!CountTool.isNull(ee.getParameters())) {
                    new SendParamsModel(NameTools.getSendParamsInfo(classTypeName), ee);
                }
                //XxxxNetBack
                new NetBackModel(NameTools.getNetBackInfo(classTypeName), ee);

                return false;
            }
        });
    }
}
