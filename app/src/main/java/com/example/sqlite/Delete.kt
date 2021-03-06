package com.example.sqlite

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import kotlinx.android.synthetic.main.activity_delete.*

class Delete : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

    }
    inner class myAsync: AsyncTask<Student, Unit, Unit>() {
        override fun doInBackground(vararg p0: Student) {
            val myDb= DataBaseManager(applicationContext)
            myDb.writableDatabase
            myDb.delete(p0[0])
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            finish()
        }
    }
    fun deleteFromDb(view: View) {
        if(isEmpty(deleteEditText.text) )
        {
            deleteEditText.setHint("MUST PUT IN VALUE!")
            return
        }
        myAsync().execute(Student(deleteEditText.text.toString().toInt(), null))

    }

    fun deletecancel(view: View) {
        finish()
    }
}