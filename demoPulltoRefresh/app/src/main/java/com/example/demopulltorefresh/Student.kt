package com.example.demopulltorefresh

class Student(private var name: String, private var number: Long, private var color: String, private var birthday: Int, private var expanded : Boolean){

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getNumber(): Long? {
        return number
    }

    fun setNumber(number: Long) {
        this.number = number
    }

    fun getColor(): String? {
        return color
    }

    fun setColor(color: String) {
        this.color = color
    }

    fun getBirthday(): Int? {
        return birthday
    }

    fun setBirthday(birthday: Int) {
        this.birthday = birthday
    }

    fun getExpanded(): Boolean? {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }
}