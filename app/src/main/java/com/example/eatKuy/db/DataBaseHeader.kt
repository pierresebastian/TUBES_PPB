package com.example.eatKuy.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.eatKuy.model.CartFood

val DATABASE_NAME = "EatKuyDB"
val TABLE_NAME = "Cart"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_QTY = "qty"
val COL_IMAGE = "image"
val COL_ID = "id"

class DataBaseHeader(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " ("+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_QTY + " INTEGER," +
                COL_PRICE + " INTEGER," +
                COL_IMAGE + " VARCHAR(256)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(cartFood: CartFood){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, cartFood.name)
        cv.put(COL_QTY, cartFood.qty)
        cv.put(COL_PRICE, cartFood.price)
        cv.put(COL_IMAGE, cartFood.image)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData(): MutableList<CartFood>{
        var list : MutableList<CartFood> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from "+ TABLE_NAME
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var cartFood = CartFood()
                cartFood.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                cartFood.name = result.getString(result.getColumnIndex(COL_NAME))
                cartFood.qty= result.getString(result.getColumnIndex(COL_QTY)).toInt()
                cartFood.price= result.getString(result.getColumnIndex(COL_PRICE)).toInt()
                cartFood.image= result.getString(result.getColumnIndex(COL_IMAGE))
                list.add(cartFood)
            }while (result.moveToNext()
            )
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

}