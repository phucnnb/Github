package com.example.loicuabac2.view.readstory

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.util.PrefUtil

class SettingFragment: BottomSheetDialogFragment() {

    private lateinit var btnWhite : Button
    private lateinit var btnPink : Button
    private lateinit var btnBlack : Button
    private lateinit var sbZoom : SeekBar
    private lateinit var viewReadStory : ReadStoryView
    private var size : Float = 0.0F

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewReadStory = context as ReadStoryView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.setting_fragment, container, false)

        init(view)

        val sizeD =  PrefUtil.data.getFloat(this.activity!!, Constants.BACK_GROUND, Constants.TEXT_SIZE, 16F)
        sbZoom.progress = ((sizeD - 16)/1.5).toInt()
        viewReadStory.setSizeText(sizeD)

        when(PrefUtil.data.getString(this.activity!! as Context,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"white")) {

            "white" -> btnWhite.setBackgroundResource(R.drawable.button_white_press_true)
            "pink" -> btnPink.setBackgroundResource(R.drawable.button_pink_press_true)
            "black" -> btnBlack.setBackgroundResource(R.drawable.button_black_press_true)
        }
        btnWhite.setOnClickListener {
            btnWhite.setBackgroundResource(R.drawable.button_white_press_true)
            btnPink.setBackgroundResource(R.drawable.button_pink_press_false)
            btnBlack.setBackgroundResource(R.drawable.button_black_press_false)
            PrefUtil.data.putString(this.activity!!,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"white")
            viewReadStory.setColorBackGround()
        }

        btnPink.setOnClickListener {
            btnWhite.setBackgroundResource(R.drawable.button_white_press_false)
            btnPink.setBackgroundResource(R.drawable.button_pink_press_true)
            btnBlack.setBackgroundResource(R.drawable.button_black_press_false)
            PrefUtil.data.putString(this.activity!!,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"pink")
            viewReadStory.setColorBackGround()
        }

        btnBlack.setOnClickListener {
            btnWhite.setBackgroundResource(R.drawable.button_white_press_false)
            btnPink.setBackgroundResource(R.drawable.button_pink_press_false)
            btnBlack.setBackgroundResource(R.drawable.button_black_press_true)
            PrefUtil.data.putString(this.activity!!,Constants.BACK_GROUND,Constants.COLOR_BACK_GROUND,"black")
            viewReadStory.setColorBackGround()
        }

        sbZoom.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                size = ((progress * 1.5) + 16).toFloat()
                viewReadStory.setSizeText(size)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("baophuc","Start")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                context?.let { PrefUtil.data.putFloat(it, Constants.BACK_GROUND, Constants.TEXT_SIZE, size)}
            }

        })
        return view
    }

    private fun init(view: View) {
        btnWhite = view.findViewById(R.id.backGroundWhite)
        btnPink = view.findViewById(R.id.backGroundPink)
        btnBlack = view.findViewById(R.id.backGroundBlack)
        sbZoom = view.findViewById(R.id.sbZoom)

    }
}