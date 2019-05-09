package com.example.democardslide

class Item(private var linkImage: Int, private var title: String, private var substance: String) {

    fun setLinkImage(linkImage : Int){
        this.linkImage = linkImage
    }

    fun getLinkImage() : Int {
        return this.linkImage
    }

    fun seTitle(title : String){
        this.title = title
    }

    fun getTitle() : String {
        return this.title
    }

    fun setSubstance(substance : String){
        this.substance = substance
    }

    fun getSubstance() : String {
        return this.substance
    }

}