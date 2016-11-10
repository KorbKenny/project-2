package com.korbkenny.doodle_1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.korbkenny.doodle_1.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KorbBookProReturns on 10/31/16.
 */

public class ShopSQLHelper extends SQLiteOpenHelper {
    private static final String TAG = ShopSQLHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "DoodleShopItems.db";
    public static final String SHOP_TABLE = "SHOP_TABLE";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_COLOR = "COLOR";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_BOUGHT = "BOUGHT";
    public static final String COL_ICONID = "ICONID";

    public static final String[] ALL_COLUMNS = {COL_ID,COL_NAME,COL_PRICE,COL_TYPE,COL_COLOR,COL_DESCRIPTION,COL_BOUGHT,COL_ICONID};
    public static final String[] ICONID_COLUMN = {COL_ICONID, COL_BOUGHT};
    public static final String[] TEXT_COLUMNS = {COL_NAME,COL_TYPE,COL_COLOR};

    private static final String CREATE_TABLE =
            "CREATE TABLE " + SHOP_TABLE + "(" +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_NAME + " TEXT, " +
                    COL_PRICE + " INTEGER, " +
                    COL_TYPE + " TEXT, " +
                    COL_COLOR + " TEXT, " +
                    COL_DESCRIPTION + " TEXT, " +
                    COL_BOUGHT + " INTEGER, " +
                    COL_ICONID + " INTEGER)";

    private static ShopSQLHelper mInstance;

    public static ShopSQLHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new ShopSQLHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private ShopSQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOP_TABLE);
        this.onCreate(db);
    }

    //////////////////////////////
    //                          //
    //   SQL HELPER METHODS     //
    //                          //
    //////////////////////////////


    //////////////////////////////////////////////////////
    // GET A LIST OF EVERYTHING THAT HASN'T BEEN BOUGHT YET
    //////////////////////////////////////////////////////
    // --for shop activity

    public List<ShopItem> getAllAsList(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOP_TABLE,
                ALL_COLUMNS,
                COL_BOUGHT + " = ?",
                new String[] {Integer.toString(0)},
                null,
                null,
                null);

        List<ShopItem> itemList = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Integer id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int price = cursor.getInt(cursor.getColumnIndex(COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                String color = cursor.getString(cursor.getColumnIndex(COL_COLOR));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                int bought = cursor.getInt(cursor.getColumnIndex(COL_BOUGHT));
                int iconid = cursor.getInt(cursor.getColumnIndex(COL_ICONID));

                ShopItem shopItem = new ShopItem(id,name,price,type,color,description,bought,iconid);
                itemList.add(shopItem);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return itemList;
    }


    //////////////////////////////////////////////////////
    // GET A LIST OF EVERYTHING THAT'S BEEN BOUGHT ALREADY
    //////////////////////////////////////////////////////
    // --for equip activity

    public List<ShopItem> getBought(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOP_TABLE,
                ALL_COLUMNS,
                COL_BOUGHT + " = ?",
                new String[] {Integer.toString(1)},
                null,
                null,
                null);

        List<ShopItem> itemList = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Integer id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int price = cursor.getInt(cursor.getColumnIndex(COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                String color = cursor.getString(cursor.getColumnIndex(COL_COLOR));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                int bought = cursor.getInt(cursor.getColumnIndex(COL_BOUGHT));
                int iconid = cursor.getInt(cursor.getColumnIndex(COL_ICONID));

                ShopItem shopItem = new ShopItem(id,name,price,type,color,description,bought,iconid);
                itemList.add(shopItem);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return itemList;
    }


    //////////////////////////////////////////////////////
    // RETURNS A SHOPITEM BY TAKING IN THE ID OF THAT ITEM
    //////////////////////////////////////////////////////
    // --for detail activity

    public ShopItem getItemByID(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(SHOP_TABLE, // a. table
                ALL_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                new String[] {String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        ShopItem theItem;

        if(cursor.moveToFirst()){
                String title = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                int price = cursor.getInt(cursor.getColumnIndex(COL_PRICE));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                String color = cursor.getString(cursor.getColumnIndex(COL_COLOR));
                int bought = cursor.getInt(cursor.getColumnIndex(COL_BOUGHT));
                int iconid = cursor.getInt(cursor.getColumnIndex(COL_ICONID));

            theItem = new ShopItem(id,title,price,type,color,description,bought,iconid);
            cursor.close();
            return theItem;
            }
        return null;
    }


    //////////////////////////////////////////////////////
    // RETURNS LIST OF ITEMS THAT MATCH THE SEARCH QUERY
    // IN THE CATEGORIES OF NAME, TYPE, AND COLOR
    //////////////////////////////////////////////////////
    // --for shop activity search bar

    public List<ShopItem> searchQuery(String query){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOP_TABLE,
                null,
                COL_NAME + " LIKE ? OR " + COL_COLOR + " LIKE ? OR " + COL_TYPE + " LIKE ?",
                new String[] {"%"+query+"%","%"+query+"%","%"+query+"%"},
                null,
                null,
                null);

        List<ShopItem> itemList = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                if (cursor.getInt(cursor.getColumnIndex(COL_BOUGHT)) == 0){
                Integer id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int price = cursor.getInt(cursor.getColumnIndex(COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
                String color = cursor.getString(cursor.getColumnIndex(COL_COLOR));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                int bought = cursor.getInt(cursor.getColumnIndex(COL_BOUGHT));
                int iconid = cursor.getInt(cursor.getColumnIndex(COL_ICONID));

                    ShopItem shopItem = new ShopItem(id,name,price,type,color,description,bought,iconid);
                    itemList.add(shopItem);
                    cursor.moveToNext();
                }
                else{
                    cursor.moveToNext();
                }
            }
        }
        cursor.close();
        return itemList;
    }


    //////////////////////////////////////////////////////
    // TAKES IN AN ITEM, AND CHANGES IT TO 'BOUGHT'
    //////////////////////////////////////////////////////
    // --for cart activity checkout button

    public void changeToBought(ShopItem shopItem){
        ContentValues values = new ContentValues();
            values.put(COL_BOUGHT, 1);
        SQLiteDatabase db = getWritableDatabase();
        db.update(SHOP_TABLE,values,COL_ID+" = ?",new String[]{Integer.toString(shopItem.getID())});
        db.close();
    }


    //////////////////////////////////////////////////////
    // TAKES IN A LIST OF SHOPITEMS, AND CHANGES
    // EACH ITEM INSIDE FROM 'BOUGHT' TO NOT
    //////////////////////////////////////////////////////
    // --for main activity refresh everything button

    public void nothingBought(List<ShopItem> shopItemList) {
        ContentValues values = new ContentValues();
//        if (!shopItemList.isEmpty()) {
            for (ShopItem item : shopItemList) {
                values.put(COL_BOUGHT, 0);
            }
            SQLiteDatabase db = getWritableDatabase();
            db.update(SHOP_TABLE, values, null, null);
            db.close();
//        }
    }
}


