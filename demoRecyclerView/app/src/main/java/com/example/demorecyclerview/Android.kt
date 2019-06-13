package com.example.demorecyclerview

class Android {
    //@SerializedName("ver")
    //@Expose
    private var ver: String? = null
    //@SerializedName("name")
    //@Expose
    private var name: String? = null
    //@SerializedName("api")
    //@Expose
    private var api: String? = null

    constructor(ver: String?, name: String?, api: String?) {
        this.ver = ver
        this.name = name
        this.api = api
    }


    fun getVer(): String? {
        return ver
    }

    fun setVer(ver: String) {
        this.ver = ver
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getApi(): String? {
        return api
    }

    fun setApi(api: String) {
        this.api = api
    }
}