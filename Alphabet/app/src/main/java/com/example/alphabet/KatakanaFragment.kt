package com.example.alphabet

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class KatakanaFragment : Fragment(){

    private var checkTypeFace : Boolean = false
    private lateinit var viewButtonKata: LinearLayout
     
    private lateinit var imageTypeFaceKata : ImageView
    private lateinit var btnRollKata : Button
    private lateinit var tvCountKata : TextView
    private lateinit var tvPronounceKata : TextView
    private lateinit var btnPronounceKata : Button
    private lateinit var btnTypeFaceKata : Button
    private var count : Int = 0
    private var x : Int = 0
    private val listData : ArrayList<Int> = ArrayList()
    private var word : Word? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_katakana, container, false)
        init(view)
        prepareDataRandom()

        imageTypeFaceKata.setOnClickListener {
            imageTypeFaceKata.setImageDrawable(null)
            tvPronounceKata.text = ""
            btnRollKata.text = "ROLL"
            x = 0
            count = 0
            tvCountKata.text = "$count/46"
            viewButtonKata.visibility = View.GONE
            if (checkTypeFace) {
                imageTypeFaceKata.setBackgroundResource(R.drawable.word_off)
                Toast.makeText(context,"Phiên Âm", Toast.LENGTH_SHORT).show()
                checkTypeFace = false
                prepareDataRandom()
            } else {
                imageTypeFaceKata.setBackgroundResource(R.drawable.word_on)
                checkTypeFace = true
                Toast.makeText(context,"Mặt Chữ", Toast.LENGTH_SHORT).show()
                prepareDataRandom()
            }
        }
        btnRollKata.setOnClickListener {
            if(btnRollKata.text == "RESET") {
                tvPronounceKata.text = ""
                btnRollKata.text = "ROLL"
                x = 0
                count = 0
                tvCountKata.text = "$count/46"
                prepareDataRandom()
                viewButtonKata.visibility = View.GONE
            } else {
                viewButtonKata.visibility = View.VISIBLE
                x = listData[count]
                word = getPronounce(x)
                if (!checkTypeFace) {
                    tvPronounceKata.text = word!!.pronounce
                } else {
                    tvPronounceKata.text = word!!.typeface
                }
                tvCountKata.text = (count + 1).toString() + ("/46")
                count++
            }

            if(count == 46) {
                btnRollKata.text = "RESET"
            }
        }
        btnPronounceKata.setOnClickListener {
            tvPronounceKata.text = word!!.pronounce
        }
        btnTypeFaceKata.setOnClickListener {
            tvPronounceKata.text = word!!.typeface
        }
        return view
    }

    private fun getPronounce(id : Int) : Word {
        var pronounce = ""
        var typeFace = ""
        when(id) {
            0 -> {
                pronounce = "a"
                typeFace = "ア"
            }
            1 -> {
                pronounce = "i"
                typeFace = "イ"
            }
            2 -> {
                pronounce = "u"
                typeFace = "ウ"
            }
            3 -> {
                pronounce = "e"
                typeFace = "エ"
            }
            4 -> {
                pronounce = "o"
                typeFace = "オ"
            }
            5 -> {
                pronounce = "ka"
                typeFace = "カ"
            }
            6 -> {
                pronounce = "ki"
                typeFace = "キ"
            }
            7 -> {
                pronounce = "ku"
                typeFace = "ク"
            }
            8 -> {
                pronounce = "ke"
                typeFace = "ケ"
            }
            9 -> {
                pronounce = "ko"
                typeFace = "コ"
            }
            10 -> {
                pronounce = "sa"
                typeFace = "サ"
            }
            11 -> {
                pronounce = "shi"
                typeFace = "シ"
            }
            12 -> {
                pronounce = "su"
                typeFace = "ス"
            }
            13 -> {
                pronounce = "se"
                typeFace = "セ"
            }
            14 -> {
                pronounce = "so"
                typeFace = "ソ"
            }
            15 -> {
                pronounce = "ta"
                typeFace = "タ"
            }
            16 -> {
                pronounce = "chi"
                typeFace = "チ"
            }
            17 -> {
                pronounce = "tsu"
                typeFace = "ツ"
            }
            18 -> {
                pronounce = "te"
                typeFace = "テ"
            }
            19 -> {
                pronounce = "to"
                typeFace = "ト"
            }
            20 -> {
                pronounce = "na"
                typeFace = "ナ"
            }
            21 -> {
                pronounce = "ni"
                typeFace = "二"
            }
            22 -> {
                pronounce = "nu"
                typeFace = "ヌ"
            }
            23 -> {
                pronounce = "ne"
                typeFace = "ネ"
            }
            24 -> {
                pronounce = "no"
                typeFace = "ノ"
            }
            25 -> {
                pronounce = "ha"
                typeFace = "ハ"
            }
            26 -> {
                pronounce = "hi"
                typeFace = "ヒ"
            }
            27 -> {
                pronounce = "fu"
                typeFace = "フ"
            }
            28 -> {
                pronounce = "he"
                typeFace = "へ"
            }
            29 -> {
                pronounce = "ho"
                typeFace = "ホ"
            }
            30 -> {
                pronounce = "ma"
                typeFace = "マ"
            }
            31 -> {
                pronounce = "mi"
                typeFace = "ミ"
            }
            32 -> {
                pronounce = "mu"
                typeFace = "ム"
            }
            33 -> {
                pronounce = "me"
                typeFace = "メ"
            }
            34 -> {
                pronounce = "mo"
                typeFace = "モ"
            }
            35 -> {
                pronounce = "ya"
                typeFace = "ヤ"
            }
            36 -> {
                pronounce = "yu"
                typeFace = "ユ"
            }
            37 -> {
                pronounce = "yo"
                typeFace = "ヨ"
            }
            38 -> {
                pronounce = "ra"
                typeFace = "ラ"
            }
            39 -> {
                pronounce = "ri"
                typeFace = "リ"
            }
            40 -> {
                pronounce = "ru"
                typeFace = "ル"
            }
            41 -> {
                pronounce = "re"
                typeFace = "レ"
            }
            42 -> {
                pronounce = "ro"
                typeFace = "ロ"
            }
            43 -> {
                pronounce = "wa"
                typeFace = "ワ"
            }
            44 -> {
                pronounce = "wo"
                typeFace = "ヲ"
            }
            45 -> {
                pronounce = "n"
                typeFace = "ン"
            }
        }
        return  Word(pronounce,typeFace)
    }

    private fun prepareDataRandom() {
        listData.clear()
        for (i in 0..45) {
            listData.add(i)
        }
        listData.shuffle()
    }

    private fun init(view: View?) {
        btnRollKata = view!!.findViewById(R.id.btnRollKata)
        imageTypeFaceKata = view.findViewById(R.id.imageTypeFaceKata)
        viewButtonKata = view.findViewById(R.id.viewButtonKata)
        tvCountKata = view.findViewById(R.id.tvCountKata)
        tvPronounceKata = view.findViewById(R.id.tvPronounceKata)
        btnPronounceKata = view.findViewById(R.id.btnPronounceKata)
        btnTypeFaceKata = view.findViewById(R.id.btnTypeFaceKata)
    }

    companion object {
        fun newInstance(): KatakanaFragment = KatakanaFragment()
    }
}