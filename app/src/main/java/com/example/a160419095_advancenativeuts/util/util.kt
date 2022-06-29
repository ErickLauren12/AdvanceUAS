package com.example.a160419095_advancenativeuts.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160419095_advancenativeuts.R
import com.example.a160419095_advancenativeuts.model.Book
import com.example.a160419095_advancenativeuts.model.BookDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

val DB_NAME = "newbookdb"

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "DROP TABLE account"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE transaksi ADD COLUMN idBook INTEGER DEFAULT 0 NOT NULL"
        )
    }
}

fun buildDb(context: Context): BookDatabase{
    val db = Room.databaseBuilder(context, BookDatabase:: class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    return db
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadImageFromUrl(view: ImageView, url: String?, pb: ProgressBar){
    if(url != ""){
        view.loadImage(url, pb)
    }
}


fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback{
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })
}