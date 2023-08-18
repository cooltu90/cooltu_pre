package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.Code4RequestModel;

public class DefaultCodeDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultCode defaultCode = element.getAnnotation(DefaultCode.class);
        String[] value = defaultCode.value();
        Ts.ls(value, new Each<String>() {
            @Override
            public boolean each(int position, String s) {
                Code4RequestModel.model.addActStaticName(s);
                return false;
            }
        });
    }
}
