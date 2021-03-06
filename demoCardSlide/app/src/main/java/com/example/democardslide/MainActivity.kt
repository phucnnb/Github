package com.example.democardslide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter



class MainActivity : AppCompatActivity(),ViewImp, CardStackListener {

    private lateinit var logic: PresenterLogic
    private lateinit var adapterSwipe : SwipeStackAdapter
    private var size : Int = 0
    private var check : Int = 0
    private lateinit var manager : CardStackLayoutManager
    private lateinit var arrayAdapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = CardStackLayoutManager(this,this)
        logic = PresenterLogic(this)

        logic.thucHienPrepareListData()
        logic.thucHienCardStackView()

        btnBack.setOnClickListener {
            logic.thucHienRewind()
        }

    }

    override fun prepareData(listData: ArrayList<Item>) {
        Log.d("AAA", listData.toString())
    }

    override fun prepareList(your_array_list: List<String>) {
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,your_array_list)
        //list.adapter = arrayAdapter
    }
    override fun cardStackView(listData: List<Item>) {
        size = listData.size - 1
        adapterSwipe = SwipeStackAdapter(this,listData)
        card_stack_view.layoutManager = CardStackLayoutManager(this,this)
        card_stack_view.adapter = adapterSwipe
    }

    override fun slidePanel(listData: List<Item>, check: Int) {
        imageSlide.setImageResource(listData[check].getLinkImage())
        txtNameSlide.text = listData[check].getTitle()
    }

    override fun reload(listData: List<Item>) {
        adapterSwipe.setList(listData)
        adapterSwipe.notifyDataSetChanged()

        Log.d("BBB",listData[check].getTitle())
    }

    override fun rewind() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardDisappeared(view: android.view.View?, position: Int) {
        Log.d("AAA", position.toString())
        Log.d("AAA", size.toString())
        if(position == 0){
             btnBack.visibility= View.VISIBLE
         }
        check = position

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("AAA", "onCardDragging")

    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("AAA", "onCardSwiped")
        if(check == size){
             logic.thucHienReload()
        }

    }

    override fun onCardCanceled() {
        Log.d("AAA", "onCardCanceled")
    }

    override fun onCardAppeared(view: android.view.View?, position: Int) {
        Log.d("AAA", "onCardAppeared $position")
        logic.thucHienSlidePanel(position)
    }

    override fun onCardRewound() {
        Log.d("AAA", "onCardRewound")
    }

}
