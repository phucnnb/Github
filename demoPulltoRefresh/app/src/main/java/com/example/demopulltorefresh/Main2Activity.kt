package com.example.demopulltorefresh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.daimajia.swipe.util.Attributes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), Adapter2Student.Update {

    private lateinit var listStudent : ArrayList<Student>
    private lateinit var adapterStudent: Adapter2Student
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbarTitle2)
        actionBar = this.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""

        (recycler2.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        refresh_layout2.setOnRefreshListener {
            Log.d("AAA","refresh")
            adapterStudent.notifyDataSetChanged()
            // TimeUnit.MINUTES.sleep(1);
            recycler2.scrollToPosition(listStudent.size - 1)
            refresh_layout2.isRefreshing = false

        }

        listStudent = ArrayList()
        prepareListData()

        val layoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler2.layoutManager = layoutInflater
        adapterStudent = Adapter2Student(this,listStudent)
        adapterStudent.mode = Attributes.Mode.Single
       // adapterStudent.setListener(this)
        recycler2.adapter = adapterStudent
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
            R.id.menuUpdate -> {
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateData() {
        adapterStudent.notifyDataSetChanged()
        Log.d("AAA","aaaaaaaaaaaaaaaaaaaaaaaaa")
    }

}
