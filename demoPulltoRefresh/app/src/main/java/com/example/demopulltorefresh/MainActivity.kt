package com.example.demopulltorefresh

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.dinuscxj.refresh.RecyclerRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import android.support.v7.widget.SimpleItemAnimator



class MainActivity : AppCompatActivity(), AdapterStudent.Update {

    private lateinit var listStudent : ArrayList<Student>
    private lateinit var adapterStudent: AdapterStudent
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarTitle)
        actionBar = this.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""

        (recycler.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        refresh_layout.setOnRefreshListener {
            Log.d("AAA","refresh")
            adapterStudent.notifyDataSetChanged()
           // TimeUnit.MINUTES.sleep(1);
            recycler.scrollToPosition(listStudent.size - 1)
            refresh_layout.isRefreshing = false

        }

        listStudent = ArrayList()
        prepareListData()

        val layoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutInflater
        adapterStudent = AdapterStudent(this,listStudent)
        adapterStudent.setListener(this)
        recycler.adapter = adapterStudent

    }

    private fun prepareListData() {
        val stu1 = Student("A",130111,"#DF0029",1995,false)
        val stu2 = Student("B",130222,"#FCF54C", 1995,false)
        val stu3 = Student("C",130333,"#0066FF", 1995,false)
        val stu4 = Student("D",130444,"#50A625", 1995,false)
        val stu5 = Student("E",130555,"#FF83FA", 1995,false)
        listStudent.add(stu1)
        listStudent.add(stu2)
        listStudent.add(stu3)
        listStudent.add(stu4)
        listStudent.add(stu5)

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuInsert -> {
                prepareListData()
                //adapterStudent.notifyDataSetChanged()
            }
            R.id.menuUpdate -> Log.d("AAA","update")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateData() {
        adapterStudent.notifyDataSetChanged()
        Log.d("AAA","aaaaaaaaaaaaaaaaaaaaaaaaa")
    }
}
