package com.example.democardslide

class Item{
    private var linkImage : Int = 0
    private var title : String = ""
    private var substance : String = ""

    constructor(linkImage : Int,title : String, substance : String){
        this.linkImage = linkImage
        this.title = title
        this.substance = substance
    }

    constructor()

    fun setLinkImage(linkImage : Int){
        this.linkImage = linkImage
    }

    fun getLinkImage() : Int {
        return this.linkImage
    }

    fun setTitle(title : String){
        this.title = title
    }

    fun getTitle() : String {
        return this.title
    }

    fun setSubstance(HoTen : String){
        this.substance = substance
    }

    fun getSubstance() : String {
        return this.substance
    }

}