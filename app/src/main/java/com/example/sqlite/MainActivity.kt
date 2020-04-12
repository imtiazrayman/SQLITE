package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun updateView():Unit
    {
        theStudents.text=""
        val myDb= DataBaseManager(applicationContext)
        myDb.writableDatabase
        val listToDispay=myDb.selectAll()
        for(i in listToDispay)
        {
            theStudents.append("id: ${i.studentId}  name: ${i.studentName}\n")
        }
    }

    fun insertActivity(view: View) {
        val myIntent= Intent(this, Insert::class.java)
        startActivity(myIntent)
    }
    fun deleteActivity(view: View) {
        val myIntent= Intent(this, Delete::class.java)
        startActivity(myIntent)
    }
    fun updateActivity(view: View) {
        val myIntent= Intent(this, Modify::class.java)
        startActivity(myIntent)
    }
}
