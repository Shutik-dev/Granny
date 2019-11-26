package com.mstrell.granny_app.database.db_helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mstrell.granny_app.database.models.Ingredient
import com.mstrell.granny_app.database.models.Point
import com.mstrell.granny_app.database.models.Recipe
import com.mstrell.granny_app.database.models.Tool

class DBHelper(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_RECIPES_QUERY)
        db.execSQL(CREATE_POINTS_QUERY)
        db.execSQL(CREATE_INGS_QUERY)
        db.execSQL(CREATE_TOOLS_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val recipes: List<Recipe>
        get() {
            val lstRecipes = ArrayList<Recipe>()
            val db = this.writableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM $R_TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                val id_index = cursor.getColumnIndex(R_COL_ID)
                val title_index = cursor.getColumnIndex(R_COL_TITLE)
                val img_index = cursor.getColumnIndex(R_COL_IMG)
                val points_am_index = cursor.getColumnIndex(R_COL_POINTS_AM)
                val ings_am_index = cursor.getColumnIndex(R_COL_INGS_AM)
                val tools_am_index = cursor.getColumnIndex(R_COL_TOOLS_AM)
                val likes_index = cursor.getColumnIndex(R_COL_LIKES)
                val tod_index = cursor.getColumnIndex(R_COL_TOD)
                val descr_index = cursor.getColumnIndex(R_COL_DESCR)
                do {
                    val recipe = Recipe(
                        cursor.getInt(id_index),
                        cursor.getString(title_index),
                        cursor.getString(img_index),
                        cursor.getInt(points_am_index),
                        cursor.getInt(ings_am_index),
                        cursor.getInt(tools_am_index),
                        cursor.getInt(likes_index),
                        cursor.getString(tod_index),
                        cursor.getString(descr_index)
                    )
                    lstRecipes.add(recipe)
                } while (cursor.moveToNext())
            }
            return lstRecipes
        }

//    if (this.recipes.isNotEmpty()) {
//        recipes.forEach {
//            if (it._id == point.rec_id) it.points.add(point)
//        }
//    }

    val points: List<Point>
        get() {
            val lstPoints = ArrayList<Point>()
            val db = this.writableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM $P_TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                val id_index = cursor.getColumnIndex(P_COL_ID)
                val rec_id_index = cursor.getColumnIndex(P_COL_REC_ID)
                val num_index = cursor.getColumnIndex(P_COL_NUM)
                val text_index = cursor.getColumnIndex(P_COL_TEXT)
                val timer_index = cursor.getColumnIndex(P_COL_TIMER)
                do {
                    val point = Point(
                        cursor.getInt(id_index),
                        cursor.getInt(rec_id_index),
                        cursor.getInt(num_index),
                        cursor.getString(text_index),
                        cursor.getInt(timer_index))
                    lstPoints.add(point)
                } while (cursor.moveToNext())
            }
            return lstPoints
        }

    val ings: List<Ingredient>
        get() {
            val lstIngs = ArrayList<Ingredient>()
            val db = this.writableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM $I_TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                val id_index = cursor.getColumnIndex(I_COL_ID)
                val rec_id_index = cursor.getColumnIndex(I_COL_REC_ID)
                val name_index = cursor.getColumnIndex(I_COL_NAME)
                val dose_index = cursor.getColumnIndex(I_COL_DOSE)
                val type_index = cursor.getColumnIndex(I_COL_TYPE)
                do {
                    val ing = Ingredient(
                        cursor.getInt(id_index),
                        cursor.getString(rec_id_index),
                        cursor.getString(name_index),
                        cursor.getInt(dose_index),
                        cursor.getString(type_index))
                    lstIngs.add(ing)
                } while (cursor.moveToNext())
            }
            return lstIngs
        }

    val tools: List<Tool>
        get() {
            val lstTools = ArrayList<Tool>()
            val db = this.writableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM $T_TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                val id_index = cursor.getColumnIndex(T_COL_ID)
                val rec_id_index = cursor.getColumnIndex(T_COL_REC_ID)
                val name_index = cursor.getColumnIndex(T_COL_NAME)
                do {
                    val tool = Tool(
                        cursor.getInt(id_index),
                        cursor.getInt(rec_id_index),
                        cursor.getString(name_index))
                    lstTools.add(tool)
                } while (cursor.moveToNext())
            }
            return lstTools
        }

    fun uploadPIT() {
        points.forEach {
            recipes.forEach { it1 ->
                if (it1._id == it.rec_id) it1.points.add(it)
            }
        }
        ings.forEach {
            recipes.forEach { it1 ->
                if (it1._id == it._id) it1.ings.add(it)
            }
        }
        tools.forEach {
            recipes.forEach { it1 ->
                if (it1._id == it.rec_id) it1.tools.add(it)
            }
        }
    }
}