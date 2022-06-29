package com.example.a160419095_advancenativeuts.model

import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(vararg cart: Cart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun checkOut(vararg transaction: Transaksi)

    @Query("SELECT * FROM book")
    suspend fun selectAllBook(): List<Book>

    @Query("SELECT * FROM book WHERE bookId= :id")
    suspend fun selectBook(id:Int): Book

    @Query("SELECT * FROM book WHERE bookId= :id")
    suspend fun selectBookId(id:Int): Book

    @Query("UPDATE book SET stock =  :stock WHERE bookId = :id")
    suspend fun updateStock(id: Int, stock:String)

    @Query("UPDATE book SET title = :title, description = :description, writer = :writer, releaseDate= :release, stock=:stock, photoUrl=:photoUrl WHERE bookId = :id")
    suspend fun update(id: Int, title:String, description:String, writer: String, release:String, stock: String, photoUrl:String)

    @Query("SELECT * FROM transaksi")
    suspend fun historyTransaction():List<Transaksi>

    @Query("SELECT * FROM cart")
    suspend fun selectAllCart(): List<Cart>

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Delete
    suspend fun deleteBook(book: Book)
}

