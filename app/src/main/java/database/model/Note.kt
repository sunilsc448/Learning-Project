package database.model

import android.database.sqlite.SQLiteDatabase

class Note {
    private var id = 0
    private var note: String? = null
    private var timestamp: String? = null

    companion object{
        val COLUMN_ID = "id"
        val COLUMN_NOTE = "note"
        val COLUMN_TIMESTAMP = "timestamp"
        val TABLE_NAME = "notes"
        // Create table SQL query
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }

    constructor(id: Int, note: String?, timestamp: String?){
        this.id = id
        this.note = note
        this.timestamp = timestamp
    }

    fun getId(): Int {
        return id
    }

    fun getNote(): String? {
        return note
    }

    fun setNote(note: String?) {
        this.note = note
    }

    fun getTimestamp(): String? {
        return timestamp
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setTimestamp(timestamp: String?) {
        this.timestamp = timestamp
    }
}