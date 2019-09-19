package com.example.alphabet

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.alphabet.retrofit.APIUtils
import com.example.alphabet.retrofit.DataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularyFragment : Fragment() {

    private lateinit var tvVietnamese : TextView
    private lateinit var tvPronounce : TextView
    private lateinit var tvTypeFace : TextView
    private lateinit var btnPronounce : Button
    private lateinit var btnTypeFace : Button
    private lateinit var btnRoll : Button
    private lateinit var tvCount : TextView
    private lateinit var viewButton1 : LinearLayout
    private lateinit var viewButton2 : LinearLayout
    private var count : Int = 0
    private var x : Int = 0
    private val listData : ArrayList<Int> = ArrayList()
    private var vocabulary : Vocabulary? = null
    private var sumItem = 0
    private lateinit var listVocabulary : ArrayList<Vocabulary>
    private var check : Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vocabulary, container, false)

        init(view)

        prepareDataRandom()

        btnRoll.setOnClickListener {
            if (check) {
                tvPronounce.text = ""
                tvTypeFace.text = ""
                if(btnRoll.text == "RESET") {
                    tvVietnamese.text = ""
                    btnRoll.text = "ROLL"
                    x = 0
                    count = 0
                    tvCount.text = "$count/$sumItem"
                    listVocabulary.shuffle()
                    viewButton1.visibility = View.GONE
                    viewButton2.visibility = View.GONE
                } else {
                    viewButton1.visibility = View.VISIBLE
                    viewButton2.visibility = View.VISIBLE
                    vocabulary = listVocabulary[count]
                    tvVietnamese.text = vocabulary!!.vietnamese
                    tvCount.text = (count + 1).toString() + ("/$sumItem")
                    count++
                }

                if(count == sumItem) {
                    btnRoll.text = "RESET"
                }
            }
        }

        btnPronounce.setOnClickListener {
            tvPronounce.text = vocabulary!!.pronounce
        }

        btnTypeFace.setOnClickListener {
            tvTypeFace.text = vocabulary!!.typeface
        }
        return view


    }

    private fun prepareData() {
        for (i in 0 until sumItem) {
            listData.add(i)
        }
        listData.shuffle()
    }

    private fun prepareDataRandom() {

        val dataClient : DataClient = APIUtils.data.getData()
        val callVocabulary : Call<List<Vocabulary>> = dataClient.getVocabulary()

        callVocabulary.enqueue(object : Callback<List<Vocabulary>> {
            override fun onFailure(call: Call<List<Vocabulary>>, t: Throwable) {
                Log.d("AAA",t.toString())
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Vocabulary>>, response: Response<List<Vocabulary>>) {
                Log.d("baophuc",response.toString())
                listVocabulary = response.body() as ArrayList<Vocabulary>

                if (listVocabulary.size > 0) {
                    listVocabulary.shuffle()
                    sumItem = listVocabulary.size
                    tvCount.text = "0/$sumItem"
                    check = true
                }
            }

        })
    }

    private fun init(view: View?) {
        tvVietnamese = view!!.findViewById(R.id.tvVietnamese)
        tvPronounce = view.findViewById(R.id.tvPronounce)
        tvTypeFace = view.findViewById(R.id.tvTypeFace)
        btnPronounce = view.findViewById(R.id.btnPronounce)
        btnTypeFace = view.findViewById(R.id.btnTypeFace)
        btnRoll = view.findViewById(R.id.btnRoll)
        tvCount = view.findViewById(R.id.tvCount)
        viewButton1 = view.findViewById(R.id.viewButton1)
        viewButton2 = view.findViewById(R.id.viewButton2)
    }

    companion object {
        fun newInstance(): VocabularyFragment = VocabularyFragment()
    }
}