package com.codingtu.cooltu.lib4a.view.textview;

import android.widget.EditText;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;

import com.codingtu.cooltu.lib4j.tools.StringTool;

import com.codingtu.cooltu.lib4a.tools.ViewTool;

public class PrefixFixedSizeTextWather extends DefaultTextWatcher implements OnDestroy {
    private int bit;
    private EditText et;
    private String prefix;

    public PrefixFixedSizeTextWather(Destroys destroys, EditText et, int bit, String prefix) {
        destroys.add(this);
        this.bit = bit;
        this.et = et;
        this.prefix = prefix;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String label = s.toString();
        if (!label.startsWith(prefix)) {
            ViewTool.setEditTextAndSelection(et, prefix + label);
        } else {
            String sub = label.substring(prefix.length());
            if (sub.length() < bit) {
                int rest = bit - sub.length();
                String s1 = StringTool.repeatString(rest, "0");
                ViewTool.setEditTextAndSelection(et, prefix + s1 + sub);
            } else if (sub.length() > bit) {
                int index = -1;
                for (int i = 0; i < sub.length(); i++) {
                    char c = sub.charAt(i);
                    if (c == '0') {
                        index = i;
                    } else {
                        break;
                    }
                }
                index++;
                if (index == 0) {
                    sub = sub.substring(0, bit);
                    System.out.println(sub);
                    ViewTool.setEditTextAndSelection(et, prefix + sub);
                } else {
                    sub = sub.substring(index);
                    System.out.println(sub);
                    int rest = bit - sub.length();
                    String s1 = StringTool.repeatString(rest, "0");
                    ViewTool.setEditTextAndSelection(et, prefix + s1 + sub);
                }
            }
        }
    }

    @Override
    public void destroy() {
        et = null;
    }
}
