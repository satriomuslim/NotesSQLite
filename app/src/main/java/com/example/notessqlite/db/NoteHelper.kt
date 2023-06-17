package com.example.notessqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.notessqlite.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import java.sql.SQLException

class NoteHelper(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: NoteHelper? = null

        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NoteHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

}