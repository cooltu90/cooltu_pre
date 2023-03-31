package core.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.annotation.ui.DefaultCode;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.Code4RequestModel;

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
