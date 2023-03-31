package core.processor.annotation.ui;

public @interface UseEditDialog {
    //在AcbBase中的名字
    String[] names() default {"editDialog"};

    //标题
    String[] titles();

    //输入框的提示文字
    String[] hints();

    //输入框的输入类型
    int[] inputTypes() default {1};

    //是否储存上次输入的值
    boolean[] reserves() default {true};

    //传递的值类型
    Class[] objTypes() default {Void.class};
}
