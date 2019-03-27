package com.example.rxandroidretrofit;

public class Android {
    private String ver;
    private String name;
    private String api;

    public Android(String ver, String name, String api) {
        this.ver = ver;
        this.name = name;
        this.api = api;
    }

    public Android(){

    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
