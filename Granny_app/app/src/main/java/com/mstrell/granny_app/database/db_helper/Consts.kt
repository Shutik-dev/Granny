package com.mstrell.granny_app.database.db_helper

// Data Base consts
val DATABASE_VERSION    = 1
val DATABASE_NAME       = "GDB"

// Recipes Table
val R_TABLE_NAME        = "recipes"
val R_COL_ID            = "_id"
val R_COL_TITLE         = "title"
val R_COL_IMG           = "img"
val R_COL_POINTS_AM     = "points_am"
val R_COL_INGS_AM       = "ings_am"
val R_COL_TOOLS_AM      = "tools_am"
val R_COL_LIKES         = "likes"
val R_COL_TOD           = "tod"
val R_COL_DESCR         = "descr"

// Points table
val P_TABLE_NAME        = "points"
val P_COL_ID            = "_id"
val P_COL_REC_ID        = "rec_id"
val P_COL_NUM           = "num"
val P_COL_TEXT          = "text"
val P_COL_TIMER         = "timer"

// Ings table
val I_TABLE_NAME        = "ings"
val I_COL_ID            = "_id"
val I_COL_REC_ID        = "rec_id"
val I_COL_NAME          = "name"
val I_COL_DOSE          = "dose"
val I_COL_TYPE          = "type"

// Tools table
val T_TABLE_NAME        = "tools"
val T_COL_ID            = "_id"
val T_COL_REC_ID        = "rec_id"
val T_COL_NAME          = "name"

// Creating consts
val CREATE_RECIPES_QUERY = ("CREATE TABLE $R_TABLE_NAME(" +
        "$R_COL_ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
        "$R_COL_TITLE VARCHAR(300), " +
        "$R_COL_IMG VARCHAR(300), " +
        "$R_COL_POINTS_AM INTEGER, " +
        "$R_COL_INGS_AM INT, " +
        "$R_COL_TOOLS_AM INTEGER, " +
        "$R_COL_LIKES INTEGER, " +
        "$R_COL_TOD VARCHAR(30), " +
        "$R_COL_DESCR TEXT);")
val CREATE_POINTS_QUERY = ("CREATE TABLE $P_TABLE_NAME(" +
        "$P_COL_ID INTEGER AUTO_INCREMENT, " +
        "$P_COL_REC_ID INTEGER, " +
        "$P_COL_NUM INTEGER, " +
        "$P_COL_TEXT TEXT, " +
        "$P_COL_TIMER INT);")
val CREATE_INGS_QUERY = ("CREATE TABLE $I_TABLE_NAME (" +
        "$I_COL_ID INTEGER AUTO_INCREMENT, " +
        "$I_COL_REC_ID INTEGER, " +
        "$I_COL_NAME TEXT, " +
        "$I_COL_DOSE INT, " +
        "$I_COL_TYPE VARCHAR(100));")
val CREATE_TOOLS_QUERY = ("CREATE TABLE $T_TABLE_NAME(" +
        "$T_COL_ID INTEGER AUTO_INCREMENT, " +
        "$T_COL_REC_ID INTEGER, " +
        "$T_COL_NAME TEXT);")