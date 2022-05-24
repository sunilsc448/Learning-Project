package database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import database.model.Note

class DatabaseHelper constructor(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        // Database Version
        private var DATABASE_VERSION = 1

        // Database Name
        private var DATABASE_NAME = "notes_db"
    }

    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        // create notes table
        db.execSQL(Note.CREATE_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME)

        // Create tables again
        onCreate(db)
    }

    fun insertNote(note:String):Long{
        val db = this.writableDatabase
        val contentValues =  ContentValues()
        contentValues.put(Note.COLUMN_NOTE, note)
        val id = db.insert(Note.CREATE_TABLE, null, contentValues)
        db.close()
        return id
    }

    fun getAllNotes():List<Note>{
        var list = mutableListOf<Note>()

        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM ${Note.TABLE_NAME} ORDER BY ${Note.COLUMN_TIMESTAMP} DESC"
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        do{
            val note = Note(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                            cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)))
            list.add(note)
        }
        while (cursor.moveToNext())

        // prepare note object
        val note = Note(
            cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
            cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP))
        )
        cursor.close()
        return list
    }

    fun getNote(id:Long):Note{
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $Note.TABLE_NAME where $Note.COLUMN_ID=$id"
        val cursor:Cursor = db.rawQuery(selectQuery, null)
        if(cursor != null)
            cursor.moveToFirst()
        val note = Note(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
        cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
        cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)))
        cursor.close()
        return note
    }
}