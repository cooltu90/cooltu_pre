package com.codingtu.cooltu.processor.worker.model.net_retrofit;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.modelinterface.ApiServiceModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class ApiServiceModel extends BaseModel implements ApiServiceModelInterface {

    private List<ExecutableElement> apiMethodElments = new ArrayList<>();

    public ApiServiceModel(JavaInfo info) {
        super(info);
        isForce = true;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void addMethod(ExecutableElement ee) {
        apiMethodElments.add(ee);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_methods(StringBuilder sb) {
        Ts.ls(apiMethodElments, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                addModel(sb, new ApiServiceMethodModel(ee));
                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools.net.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface [[name]] {
[[methods]]
}
model_temp_end */
