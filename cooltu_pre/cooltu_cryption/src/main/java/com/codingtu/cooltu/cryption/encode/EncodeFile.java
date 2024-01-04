package com.codingtu.cooltu.cryption.encode;

import com.codingtu.cooltu.cryption.tools.CryptionFile;
import com.codingtu.cooltu.cryption.tools.CryptionListener;
import com.codingtu.cooltu.cryption.tools.CryptionTools;
import com.codingtu.cooltu.cryption.tools.CryptionTypes;
import com.codingtu.cooltu.lib4j.tools.MD5;
import com.codingtu.cooltu.lib4j.tools.StringTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeFile extends CryptionFile {

    private final boolean isRename;
    private String newFilePath;

    public EncodeFile(boolean isRename, File file, byte[] pswBytes, CryptionListener listener) {
        super(file, pswBytes, listener);
        this.isRename = isRename;
    }

    @Override
    protected void startWithException() throws IOException {

        ipt = new FileInputStream(file);
        byte[] bytes = new byte[CryptionTools.MAX_READ_LEN];
        //第一次读取
        int len = ipt.read(bytes);

        byte[] signBytes = encode(CryptionTools.signBytes());

        if (CryptionTools.isEncode(signBytes, bytes)) {
            throw new RuntimeException("此文件是已加密文件");
        }

        if (listener != null) {
            listener.start(file);
        }

        //未加密
        File newFile = isRename ? getFile(this.file) : this.file;
        newFilePath = newFile.getAbsolutePath();
        opt = new FileOutputStream(newFile);
        //写入标记
        opt.write(signBytes);
        //写入类型
        opt.write(CryptionTypes.lastType());

        type = CryptionTypes.getLastType();

        //写入最后更新时间
        opt.write(encode(CryptionTools.lastModifyBytes(this.file)));

        byte[] nameBytes = encode(file.getName().getBytes(CryptionTools.CHARSET));

        //写入名字长度
        opt.write(nameBytes.length);
        //写入名字
        opt.write(nameBytes);

        long readLen = CryptionTools.getInfosLen() + nameBytes.length;

        long totalLen = this.file.length() + readLen;

        //写入文件实体
        do {
            if (len > 0) {
                opt.write(encode(bytes, len), 0, len);
                readLen += len;
            }
            percent(readLen, totalLen);
        }
        while ((len = ipt.read(bytes)) > 0);

    }

    @Override
    protected void finish() {
        //验证
        if (StringTool.isBlank(newFilePath)) {
            error("加密文件为空？？");
            return;
        }
        File newFile = new File(newFilePath);
        try {
            ipt = new FileInputStream(newFile);
            byte[] bytes = new byte[CryptionTools.MAX_READ_LEN];
            //获取标志
            ipt.read(bytes, 0, CryptionTools.signLen());
            byte[] signBytes = encode(CryptionTools.signBytes());
            if (!CryptionTools.isEncode(signBytes, bytes)) {
                error("加密失败");
                return;
            } else {
                //获取类型
                type = CryptionTypes.getType(ipt.read());
                //获取最后修改时间
                long lastModify = Long.parseLong(read(bytes, CryptionTools.lastModifyLen()));
                //获取名字长度
                int nameLen = ipt.read();
                //获取名字
                String name = read(bytes, nameLen);
                if (file.getName().equals(name)) {
                    this.file.delete();
                } else {
                    error("加密失败");
                    return;
                }
            }
            super.finish();
        } catch (Exception e) {
            error(e);
            newFile.delete();
        } finally {
            if (ipt != null) {
                try {
                    ipt.close();
                } catch (IOException e) {
                }
                ipt = null;
            }
        }
    }

    private static File getFile(File file) {
        String name = file.getName();
        int times = 2;
        while (true) {
            if (times > 10) {
                throw new RuntimeException("重名文件");
            }
            File newFile = new File(file.getParentFile().getAbsolutePath(), MD5.md5(name, times++));
            if (!newFile.exists()) {
                return newFile;
            }
        }
    }

}
