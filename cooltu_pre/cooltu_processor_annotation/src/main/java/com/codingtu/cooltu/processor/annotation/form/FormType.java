package com.codingtu.cooltu.processor.annotation.form;

public class FormType {
    public static final int TOTAL = 10000;
    public static final int EDIT_TEXT = 0;
    public static final int TEXT_VIEW = 1;
    public static final int RADIO_GROUP = 2;
    public static final int SEEK_BAR = 3;

    public static int[] getNeedType() {
        return new int[]{EDIT_TEXT, TEXT_VIEW, RADIO_GROUP, SEEK_BAR};
    }
}
