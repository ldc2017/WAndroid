package com.ldc.wandroid.model;

public class MePersonalModel {
    private String name;
    private int icon;
    private String info = null;

    public MePersonalModel(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public MePersonalModel(String name, int icon, String info) {
        this.name = name;
        this.icon = icon;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
