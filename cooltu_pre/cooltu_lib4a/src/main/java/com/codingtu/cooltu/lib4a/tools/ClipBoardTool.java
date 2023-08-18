package com.codingtu.cooltu.lib4a.tools;

import android.content.ClipData;
import android.content.ClipboardManager;

import com.codingtu.cooltu.lib4j.tools.StringTool;

public class ClipBoardTool {
    /**
     * 获取剪切板内容
     *
     * @return
     */
    public static String paste() {
        ClipboardManager manager = SystemTool.getClipboardManager();
        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(addedText);
                if (!StringTool.isBlank(addedTextString)) {
                    return addedTextString;
                }
            }
        }
        return "";
    }

    /**
     * 清空剪切板
     */

    public static void clear() {
        ClipboardManager manager = SystemTool.getClipboardManager();
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setPrimaryClip(ClipData.newPlainText("", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
