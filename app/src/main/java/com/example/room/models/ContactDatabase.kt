package com.example.room.models

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.Converter

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Converter::class)
abstract  class ContactDatabase:RoomDatabase() {
    abstract fun contactDao():ContactDao
    companion object{
        val migration_1_2= object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN email  TEXT ")
            }

        }

        @Volatile
        private var INSTANT:ContactDatabase?=null
        public fun getDatabase(context: Context):ContactDatabase
        {
            if(INSTANT==null)
            {
                synchronized(this){
                    INSTANT= Room.databaseBuilder(context.applicationContext,
                        ContactDatabase::class.java, "contactDB"
                    )   .addMigrations(migration_1_2)
                        .build()
                }

            }
            return INSTANT!!
        }
    }
}