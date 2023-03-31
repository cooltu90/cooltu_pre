package core.processor.worker.model.base;

public class SubBaseModel extends BaseModel {

    public SubBaseModel() {
        super(null);
    }

    protected void addIf(boolean has) {
        addTag("if", has ? "else if" : "if");
    }

    @Override
    protected void addToMap() {
    }

    @Override
    public void create() {
    }
}
