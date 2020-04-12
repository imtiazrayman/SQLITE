package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val TABLE_STUDENT="Student"
val ID="ID_Number"
val NAME= "Student_Name"

class DataBaseManager(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "student.db"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_STUDENT} (" +
                "${ID} INTEGER PRIMARY KEY, " +
                "${NAME} TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists $TABLE_STUDENT")
        onCreate(p0)
    }

    fun insert(studentToInsert:Student)
    {
        val db = this.writableDatabase
        val insertString= "INSERT INTO $TABLE_STUDENT " +
                "VALUES ('${studentToInsert.studentId}', '${studentToInsert.studentName}')"
        db.execSQL(insertString)
    }

   fun selectAll():ArrayList<Student>
    {
        val sqlQuery = "select * from $TABLE_STUDENT"

        val db = this.writableDatabase
        var toReturn= ArrayList<Student>()
        var cursor=db.rawQuery(sqlQuery, null);
        while(cursor.moveToNext())
        {
            toReturn.add(Student(cursor.getInt(0), cursor.getString(1)))

        }
        return toReturn
    }
    fun delete(studentToDelete:Student)
    {
        val db = this.writableDatabase
        val deleteString = "DELETE FROM $TABLE_STUDENT " +
                "WHERE ${ID} = ${studentToDelete.studentId}"
        db.execSQL(deleteString)
    }
    fun modify(studentToModify:Student)
    {
        val db = this.writableDatabase
        val modifyString = "UPDATE $TABLE_STUDENT " +
                "SET ${NAME} = '${studentToModify.studentName}' " +
                "WHERE ${ID} = ${studentToModify.studentId}"
        db.execSQL(modifyString)
    }



}