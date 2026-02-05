package com.example.restaurantratingapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myRestaurants.db";
        private static final int DATABASE_VERSION = 2;
        private static final String CREATE_TABLE_RESTAURANT =
                "create table restaurant (restaurantID integer primary key autoincrement,"
                        + "editTextRestNameID text not null, editTextStrAddress text, "
                        + "editTextCityAddress text, editTextStateAddress text, editTextZipAddress text);";
        private static final String CREATE_TABLE_DISH =
            "create table dish (dishID integer primary key autoincrement,"
                    + "editTextDishNameID text not null, editTextDishTypeID text, "
                    + "ratingViewID REAL, restaurantID int,"
                    +  "FOREIGN KEY(restaurantID) REFERENCES restaurant(restaurantID));";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_RESTAURANT);
            db.execSQL(CREATE_TABLE_DISH);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBHelper.class.getName(),
                    "Upgrading database from version" + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS dish");
            db.execSQL("DROP TABLE IF EXISTS restaurant");
            onCreate(db);
        }
    }


