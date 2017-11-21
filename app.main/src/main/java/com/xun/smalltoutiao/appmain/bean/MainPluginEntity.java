package com.xun.smalltoutiao.appmain.bean;

import java.io.Serializable;

/**
 * Created by xunwang on 2017/11/20.
 */

public class MainPluginEntity implements Serializable {

    private String pkg;
    private String url;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
