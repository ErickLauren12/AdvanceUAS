package com.example.a160419095_advancenativeuts.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAccount(vararg account: Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book: Book)

    @Query("SELECT * FROM book")
    suspend fun selectAllBook(): List<Book>

    @Query("SELECT * FROM book WHERE bookId= :id")
    suspend fun selectBook(id:Int): Book

    @Query("SELECT * FROM book WHERE bookId= :id")
    suspend fun selectBookId(id:Int): Book

    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    suspend fun loginAccount(username: String, password: String): Account

    @Query("SELECT * FROM cart")
    suspend fun selectAllCart(): List<Cart>

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Delete
    suspend fun deleteBook(book: Book)
}

