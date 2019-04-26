package com.example.demorecycelkt

class SinhVien{
    private var HoTen: String = ""
    private var NamSinh: Int = 0

    override fun toString(): String {
        return "SinhVien(HoTen='$HoTen', NamSinh=$NamSinh)"
    }

    constructor(HoTen: String, NamSinh: Int){
        this.HoTen = HoTen
        this.NamSinh = NamSinh
    }

    constructor()

    fun setHoTen(HoTen : String){
        this.HoTen = HoTen
    }

    fun getHoTen() : String {
        return this.HoTen
    }

    fun setNamSinh(NamSinh : Int){
        this.NamSinh = NamSinh
    }

    fun getNamSinh() : Int {
        return this.NamSinh
    }
}