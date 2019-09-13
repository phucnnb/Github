package com.example.alphabet

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AdapterFragment(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> HiraganaFragment.newInstance()
        1 -> KatakanaFragment.newInstance()
        2 -> VocabularyFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> "Hiragana"
        1 -> "Katakana"
        2 -> "Vocabulary"
        else -> ""
    }

    override fun getCount(): Int = 3

}