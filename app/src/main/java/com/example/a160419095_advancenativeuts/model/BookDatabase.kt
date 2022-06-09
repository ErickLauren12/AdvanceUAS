package com.example.a160419095_advancenativeuts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class, Account::class, Cart::class), version = 1)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDatabase():BookDao

    companion object{
        @Volatile private var instance: BookDatabase ? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                BookDatabase::class.java,
                "newbookdb"
            ).build()

        operator fun invoke(context: Context){
            if (instance!=null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also{
                        instance = it
                    }
                }
            }
        }
    }
}