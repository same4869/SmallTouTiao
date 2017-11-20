package com.xun.smalltoutiao.appmain.bean;

import java.io.Serializable;

/**
 * Created by xunwang on 2017/11/20.
 */

public class BundleEntity implements Serializable {

    private String uri;
    private String pkg;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

}
