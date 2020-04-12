package com.example.sqlite

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

    }
    inner class myAsync: AsyncTask<Student, Unit, Unit>() {
        override fun doInBackground(vararg p0: Student) {
            val myDb= DataBaseManager(applicationContext)
            myDb.writableDatabase
            myDb.insert(p0[0])
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            finish()
        }
    }

    fun insertIntoDb(view: View) {
       if(isEmpty(insertIdEditText.text) )
       {
           insertIdEditText.setHint("MUST PUT IN VALUE!")
           return
       }
        if(isEmpty(insertNameEditText.text))
        {
            insertNameEditText.setHint("MUST PUT IN VALUE")
            return
        }
        myAsync().execute(Student(insertIdEditText.text.toString().toInt(), insertNameEditText.text.toString()))

    }

    fun insertcancel(view: View) {
        finish()
    }
}
