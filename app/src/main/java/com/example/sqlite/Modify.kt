package com.example.sqlite

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import kotlinx.android.synthetic.main.activity_modify.*

class Modify : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

    }
    inner class myAsync: AsyncTask<Student, Unit, Unit>() {
        override fun doInBackground(vararg p0: Student) {
            val myDb= DataBaseManager(applicationContext)
            myDb.writableDatabase
            myDb.modify(p0[0])
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            finish()
        }
    }
    fun modifyDb(view: View) {
        if(isEmpty(modifyIdEditText.text) )
        {
            modifyIdEditText.setHint("MUST PUT IN VALUE!")
            return
        }
        if(isEmpty(modifyNameEditText.text))
        {
            modifyNameEditText.setHint("MUST PUT IN VALUE")
            return
        }
        myAsync().execute(Student(modifyIdEditText.text.toString().toInt(), modifyNameEditText.text.toString()))

    }

    fun modifycancel(view: View) {
        finish()
    }
}