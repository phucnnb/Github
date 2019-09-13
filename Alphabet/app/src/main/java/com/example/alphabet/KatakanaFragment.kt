package com.example.alphabet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class KatakanaFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_katakana, container, false)
    }

    companion object {
        fun newInstance(): KatakanaFragment = KatakanaFragment()
    }
}