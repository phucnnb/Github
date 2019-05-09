package com.example.democardslide


class PresenterLogic(view: ViewImp) : PresenterImp{

    private var viewImp : ViewImp = view
    private var listData: List<Item> = ArrayList()


    override fun thucHienPrepareListData() {
        val data1  = Item(R.drawable.chicken,"Gà Chiên","Nguyên Liệu Chính Là Gà")
        val data2  = Item(R.drawable.meat,"Thịt Chiên","Nguyên Liệu Chính Là Thịt Heo")
        val data3  = Item(R.drawable.vegetable,"Rau Củ Trộn","Nguyên Liệu Chính Là Rau Củ")
        (listData as ArrayList<Item>).add(data1)
        (listData as ArrayList<Item>).add(data2)
        (listData as ArrayList<Item>).add(data3)
        viewImp.prepareData(listData as ArrayList<Item>)

    }

    override fun thucHienCardStackView() {
        viewImp.cardStackView(listData)
    }

    override fun thucHienReload() {
        viewImp.reload(listData)
    }

    override fun thucHienSlidePanel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun thucHienRewind() {
    }
}