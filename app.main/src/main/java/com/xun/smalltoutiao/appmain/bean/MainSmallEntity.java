package com.xun.smalltoutiao.appmain.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xunwang on 2017/11/20.
 */

public class MainSmallEntity implements Serializable {

    private int manifest_code;
    private int updates_code;
    private int additions_code;
    private MainManifestEntity manifest;
    private List<MainPluginEntity> updates;
    private List<MainPluginEntity> additions;

    public int getManifest_code() {
        return manifest_code;
    }

    public void setManifest_code(int manifest_code) {
        this.manifest_code = manifest_code;
    }

    public int getUpdates_code() {
        return updates_code;
    }

    public void setUpdates_code(int updates_code) {
        this.updates_code = updates_code;
    }

    public int getAdditions_code() {
        return additions_code;
    }

    public void setAdditions_code(int additions_code) {
        this.additions_code = additions_code;
    }

    public MainManifestEntity getManifest() {
        return manifest;
    }

    public void setManifest(MainManifestEntity manifest) {
        this.manifest = manifest;
    }

    public List<MainPluginEntity> getUpdates() {
        return updates;
    }

    public void setUpdates(List<MainPluginEntity> updates) {
        this.updates = updates;
    }

    public List<MainPluginEntity> getAdditions() {
        return additions;
    }

    public void setAdditions(List<MainPluginEntity> additions) {
        this.additions = additions;
    }

}