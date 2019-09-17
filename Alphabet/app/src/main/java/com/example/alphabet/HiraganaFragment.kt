package com.example.alphabet

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlin.collections.ArrayList


class HiraganaFragment : Fragment() {

    private var checkTypeFace : Boolean = false
    private lateinit var viewButton: LinearLayout
    private lateinit var imageTypeFace : ImageView
    private lateinit var btnRoll : Button
    private lateinit var tvCount : TextView
    private lateinit var tvPronounce : TextView
    private lateinit var btnPronounce : Button
    private lateinit var btnTypeFace : Button
    private var count : Int = 0
    private var x : Int = 0
    private val listData : ArrayList<Int> = ArrayList()
    private var word : Word? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hiragana, container, false)
        init(view)
        prepareDataRandom()

        imageTypeFace.setOnClickListener {
            imageTypeFace.setImageDrawable(null)
            tvPronounce.text = ""
            btnRoll.text = "ROLL"
            x = 0
            count = 0
            tvCount.text = "$count/46"
            viewButton.visibility = View.GONE
            if (checkTypeFace) {
                imageTypeFace.setBackgroundResource(R.drawable.word_off)
                Toast.makeText(context,"Phiên Âm",Toast.LENGTH_SHORT).show()
                checkTypeFace = false
                prepareDataRandom()
            } else {
                imageTypeFace.setBackgroundResource(R.drawable.word_on)
                checkTypeFace = true
                Toast.makeText(context,"Mặt Chữ",Toast.LENGTH_SHORT).show()
                prepareDataRandom()
            }
        }
        btnRoll.setOnClickListener {
            if(btnRoll.text == "RESET") {
                tvPronounce.text = ""
                btnRoll.text = "ROLL"
                x = 0
                count = 0
                tvCount.text = "$count/46"
                prepareDataRandom()
                viewButton.visibility = View.GONE
            } else {
                viewButton.visibility = View.VISIBLE
                x = listData[count]
                word = getPronounce(x)
                Log.d("baophuc",checkTypeFace.toString())
                if (!checkTypeFace) {
                    tvPronounce.text = word!!.pronounce
                } else {
                    tvPronounce.text = word!!.typeface
                }

                tvCount.text = (count + 1).toString() + ("/46")
                count++
            }

            if(count == 46) {
                btnRoll.text = "RESET"
            }
        }
        btnPronounce.setOnClickListener {
            tvPronounce.text = word!!.pronounce
        }
        btnTypeFace.setOnClickListener {
            tvPronounce.text = word!!.typeface
        }
        return view
    }

    private fun prepareDataRandom() {
        listData.clear()
        for (i in 0..45) {
            listData.add(i)
        }
        listData.shuffle()
    }

    private fun init(view: View?) {
        btnRoll = view!!.findViewById(R.id.btnRoll)
        imageTypeFace = view.findViewById(R.id.imageTypeFace)
        viewButton = view.findViewById(R.id.viewButton)
        tvCount = view.findViewById(R.id.tvCount)
        tvPronounce = view.findViewById(R.id.tvPronounce)
        btnPronounce = view.findViewById(R.id.btnPronounce)
        btnTypeFace = view.findViewById(R.id.btnTypeFace)
    }

    private fun getPronounce(id : Int) : Word {
        var pronounce = ""
        var typeFace = ""
        when(id) {
            0 -> {
                pronounce = "a"
                typeFace = "あ"
            }
            1 -> {
                pronounce = "i"
                typeFace = "い"
            }
            2 -> {
                pronounce = "u"
                typeFace = "う"
            }
            3 -> {
                pronounce = "e"
                typeFace = "え"
            }
            4 -> {
                pronounce = "o"
                typeFace = "お"
            }
            5 -> {
                pronounce = "ka"
                typeFace = "か"
            }
            6 -> {
                pronounce = "ki"
                typeFace = "き"
            }
            7 -> {
                pronounce = "ku"
                typeFace = "く"
            }
            8 -> {
                pronounce = "ke"
                typeFace = "け"
            }
            9 -> {
                pronounce = "ko"
                typeFace = "こ"
            }
            10 -> {
                pronounce = "sa"
                typeFace = "さ"
            }
            11 -> {
                pronounce = "shi"
                typeFace = "し"
            }
            12 -> {
                pronounce = "su"
                typeFace = "す"
            }
            13 -> {
                pronounce = "se"
                typeFace = "せ"
            }
            14 -> {
                pronounce = "so"
                typeFace = "そ"
            }
            15 -> {
                pronounce = "ta"
                typeFace = "た"
            }
            16 -> {
                pronounce = "chi"
                typeFace = "ち"
            }
            17 -> {
                pronounce = "tsu"
                typeFace = "つ"
            }
            18 -> {
                pronounce = "te"
                typeFace = "て"
            }
            19 -> {
                pronounce = "to"
                typeFace = "と"
            }
            20 -> {
                pronounce = "na"
                typeFace = "な"
            }
            21 -> {
                pronounce = "ni"
                typeFace = "に"
            }
            22 -> {
                pronounce = "nu"
                typeFace = "ぬ"
            }
            23 -> {
                pronounce = "ne"
                typeFace = "ね"
            }
            24 -> {
                pronounce = "no"
                typeFace = "の"
            }
            25 -> {
                pronounce = "ha"
                typeFace = "は"
            }
            26 -> {
                pronounce = "hi"
                typeFace = "ひ"
            }
            27 -> {
                pronounce = "fu"
                typeFace = "ふ"
            }
            28 -> {
                pronounce = "he"
                typeFace = "へ"
            }
            29 -> {
                pronounce = "ho"
                typeFace = "ほ"
            }
            30 -> {
                pronounce = "ma"
                typeFace = "ま"
            }
            31 -> {
                pronounce = "mi"
                typeFace = "み"
            }
            32 -> {
                pronounce = "mu"
                typeFace = "む"
            }
            33 -> {
                pronounce = "me"
                typeFace = "め"
            }
            34 -> {
                pronounce = "mo"
                typeFace = "も"
            }
            35 -> {
                pronounce = "ya"
                typeFace = "や"
            }
            36 -> {
                pronounce = "yu"
                typeFace = "ゆ"
            }
            37 -> {
                pronounce = "yo"
                typeFace = "よ"
            }
            38 -> {
                pronounce = "ra"
                typeFace = "ら"
            }
            39 -> {
                pronounce = "ri"
                typeFace = "り"
            }
            40 -> {
                pronounce = "ru"
                typeFace = "る"
            }
            41 -> {
                pronounce = "re"
                typeFace = "れ"
            }
            42 -> {
                pronounce = "ro"
                typeFace = "ろ"
            }
            43 -> {
                pronounce = "wa"
                typeFace = "わ"
            }
            44 -> {
                pronounce = "wo"
                typeFace = "を"
            }
            45 -> {
                pronounce = "n"
                typeFace = "ん"
            }
        }
        return  Word(pronounce,typeFace)
    }

    companion object {
        fun newInstance(): HiraganaFragment = HiraganaFragment()
    }
}