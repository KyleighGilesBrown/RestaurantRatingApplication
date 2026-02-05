package com.example.restaurantratingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class RatingDataSource {



        private SQLiteDatabase database;
        private DBHelper dbHelper;

        public RatingDataSource (Context context) {
            dbHelper = new DBHelper(context);

        }
        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public boolean insertRestaurant(Restaurant c) {
            boolean didSucceed = false;
            try {
                ContentValues initialValues = new ContentValues();
                initialValues.put("editTextRestNameID", c.getRestName());
                initialValues.put("editTextStrAddress", c.getStrAddress());
                initialValues.put("editTextCityAddress", c.getCity());
                initialValues.put("editTextZipAddress", c.getZipCode());
                initialValues.put("editTextStateAddress", c.getState());


                didSucceed = database.insert("restaurant", null, initialValues) > 0;
            }
            catch (Exception e) {

            }
            return didSucceed;

        }
        public boolean updateRestaurant(Restaurant c) {
            boolean didSucceed = false;
            try {
                Long rowId = (long) c.getRestaurantID();
                ContentValues updateValues = new ContentValues();
                updateValues.put("editTextRestNameID", c.getRestName());
                updateValues.put("editTextStrAddress", c.getStrAddress());
                updateValues.put("editTextCityAddress", c.getCity());
                updateValues.put("editTextZipAddress", c.getZipCode());
                updateValues.put("editTextStateAddress", c.getState());

                didSucceed = database.update("restaurant", updateValues, "restaurantID = " + rowId, null) > 0;
            }
            catch (Exception e) {

            }
            return didSucceed;

        }

    public boolean insertDish(Dish c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("editTextDishNameID", c.getDishName());
            initialValues.put("editTextDishTypeID", c.getDishType());
            initialValues.put("ratingViewID", c.getRating());
            initialValues.put("restaurantID", c.getRestaurantID());


            didSucceed = database.insert("dish", null, initialValues) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;

    }
    public boolean updateDish(Dish c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getDishID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("editTextDishNameID", c.getDishName());
            updateValues.put("editTextDishTypeID", c.getDishType());
            updateValues.put("ratingViewID", c.getRating());
            updateValues.put("restaurantID", c.getRestaurantID());


            didSucceed = database.update("dish", updateValues, "dishID = " + rowId, null) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;

    }

    public int getLastRestaurantID() {
            int lastId;
            try {
                String query = "Select MAX(restaurantID) from restaurant";
                Cursor cursor = database.rawQuery(query, null);
                cursor.moveToFirst();
                lastId = cursor.getInt(0);
                cursor.close();
            }
            catch(Exception e) {
                lastId = -1;

            }
            return lastId;
        }

    public int getLastDishID() {
        int lastId;
        try {
            String query = "Select MAX(dishID) from dish";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch(Exception e) {
            lastId = -1;

        }
        return lastId;
    }
    }



