package com.codingtu.cooltu.lib4a.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cooltu.lib4j.tools.CountTool;

public class KVS {

    private List<String> keys;
    private List<String> values;

    public KVS() {
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<String>();
    }

    public KVS add(String key, String value) {
        keys.add(key);
        values.add(value);
        return this;
    }

    public int size() {
        return CountTool.count(keys);
    }

    public String key(int index) {
        return keys.get(index);
    }

    public String value(int index) {
        return values.get(index);
    }

    public String value(String name) {
        return values.get(keys.indexOf(name));
    }

    public KVS add(KVS kvs) {
        if (kvs != null && kvs.size() > 0) {
            this.keys.addAll(kvs.keys);
            this.values.addAll(kvs.values);
        }
        return this;
    }

    @Override
    public String toString() {
        return "KVS{" +
                "names=" + keys +
                ", values=" + values +
                '}';
    }

    public List<String> keys() {
        ArrayList<String> ns = new ArrayList<String>();
        for (int i = 0; i < CountTool.count(keys); i++) {
            ns.add(keys.get(i));
        }
        return ns;
    }

    public String orderKvs() {
        List<String> names = keys();
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(name + "=" + value(name));
        }
        return sb.toString();
    }

    public String orderKvsWithEncode() throws UnsupportedEncodingException {
        List<String> names = keys();
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(name + "=" + URLEncoder.encode(value(name), "utf-8").replaceAll("\\+", "%20"));
        }
        return sb.toString();
    }

    public String kvs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(keys.get(i) + "=" + values.get(i));
        }
        return sb.toString();
    }

}
