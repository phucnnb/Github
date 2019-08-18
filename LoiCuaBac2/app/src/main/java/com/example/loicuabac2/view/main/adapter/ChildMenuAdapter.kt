package com.example.loicuabac2.view.main.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.loicuabac2.Constants
import com.example.loicuabac2.R
import com.example.loicuabac2.entity.ChildMenu
import com.example.loicuabac2.view.category.CategoryActivity
import com.example.loicuabac2.view.music.MusicActivity
import com.example.loicuabac2.view.readstory.ReadStoryActivity
import java.text.Normalizer
import java.util.regex.Pattern

class ChildMenuAdapter(private var context: Context?, private var childs: ArrayList<ChildMenu>?) : RecyclerView.Adapter<ChildMenuAdapter.ChildMenuHolder>() {

    private var listener : OnClickChildMenu? = null

    override fun onBindViewHolder(p0: ChildMenuHolder, p1: Int) {
        val childMenu = childs?.get(p1)
        p0.txtTitleChild.text = childMenu!!.child

        p0.itemChild.setOnClickListener {
            val idChildMenu = removeAccent(childMenu!!.child)
            Log.d("baophuc", idChildMenu)
            var intent : Intent? = null

            if (idChildMenu == context!!.getString(R.string.am_nhac)) {
                intent = Intent(context,MusicActivity::class.java)
                intent.putExtra(Constants.SPECIES_CHILD_MENU,idChildMenu)
            } else {
                intent = Intent(context,CategoryActivity::class.java)
                intent.putExtra(Constants.SPECIES_CHILD_MENU,idChildMenu)
            }

            context?.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChildMenuHolder {
        val layoutInflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater.inflate(R.layout.item_title_child,p0,false)
        return ChildMenuHolder(itemView)
    }

    override fun getItemCount(): Int {
        return childs!!.size
    }

    class ChildMenuHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!){
        var txtTitleChild : TextView = itemView!!.findViewById(R.id.txtTitleChild)
        var itemChild : LinearLayout = itemView!!.findViewById(R.id.itemChild)
    }

    interface OnClickChildMenu{
        fun listenClickItem(id: String)
    }

    fun setListener(listener: OnClickChildMenu) {
        this.listener = listener
    }

    fun removeAccent(s: String): String {

        val temp = Normalizer.normalize(s, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(temp).replaceAll("").toLowerCase().replace(" ".toRegex(), "").replace(",".toRegex(), "")
            .replace(
                "đ".toRegex(), "d"
            )
    }

}