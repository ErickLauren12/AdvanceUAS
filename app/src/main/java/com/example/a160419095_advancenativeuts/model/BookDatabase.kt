package com.example.a160419095_advancenativeuts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160419095_advancenativeuts.util.MIGRATION_1_2
import com.example.a160419095_advancenativeuts.util.MIGRATION_2_3

@Database(entities = arrayOf(Book::class, Transaksi::class, Cart::class), version = 3)
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
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()

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