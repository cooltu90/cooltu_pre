package core.processor.worker.deal;

import javax.lang.model.element.Element;

import core.processor.annotation.UseParams;
import core.processor.worker.deal.base.BaseDeal;

public class UseParamsDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        UseParams useParams = element.getAnnotation(UseParams.class);
        switch (useParams.type()) {
        }
    }
}
