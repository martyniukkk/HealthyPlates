package com.themeal.app.data.meal_storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.themeal.app.common.Constants
import com.themeal.app.data.meal_storage.dto.MealDto

class MealDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE ${Constants.TABLE_MEAL} (
                ${Constants.COLUMN_ID} TEXT PRIMARY KEY,
                ${Constants.COLUMN_NAME} TEXT,
                ${Constants.COLUMN_CATEGORY} TEXT,
                ${Constants.COLUMN_AREA} TEXT,
                ${Constants.COLUMN_INSTRUCTIONS} TEXT,
                ${Constants.COLUMN_THUMB} TEXT,
                ${Constants.COLUMN_YOUTUBE} TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_MEAL}")
        onCreate(db)
    }

    fun addMeal(mealDto: MealDto): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Constants.COLUMN_ID, mealDto.idMeal)
            put(Constants.COLUMN_NAME, mealDto.strMeal)
            put(Constants.COLUMN_CATEGORY, mealDto.strCategory)
            put(Constants.COLUMN_AREA, mealDto.strArea)
            put(Constants.COLUMN_INSTRUCTIONS, mealDto.strInstructions)
            put(Constants.COLUMN_THUMB, mealDto.strMealThumb)
            put(Constants.COLUMN_YOUTUBE, mealDto.strYoutube)
        }

        val result = db.insert(Constants.TABLE_MEAL, null, values)
        return result != -1L
    }

    fun getAllMeals(): List<MealDto> {
        val db = this.readableDatabase
        val mealList = mutableListOf<MealDto>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${Constants.TABLE_MEAL}", null)
        if (cursor.moveToFirst()) {
            do {
                val meal = MealDto(
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_CATEGORY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_AREA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_INSTRUCTIONS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_THUMB)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_YOUTUBE)),
                )
                mealList.add(meal)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return mealList
    }

    fun deleteMeal(mealDto: MealDto): Boolean {
        val db = this.writableDatabase
        val result =
            db.delete(Constants.TABLE_MEAL, "${Constants.COLUMN_ID}=?", arrayOf(mealDto.idMeal))
        return result > 0
    }
}