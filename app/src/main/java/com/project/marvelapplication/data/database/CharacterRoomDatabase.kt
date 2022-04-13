package com.project.marvelapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterRoom::class], version = 1, exportSchema = false)
abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterRoomDao

    companion object {

        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(ctx: Context): CharacterRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    CharacterRoomDatabase::class.java,
                "character_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}