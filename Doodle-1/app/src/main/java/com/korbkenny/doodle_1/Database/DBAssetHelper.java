package com.korbkenny.doodle_1.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by KorbBookProReturns on 11/2/16.
 */

public class DBAssetHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "DoodleShopItems.db";
    private static final int DATABASE_VERSION = 1;

    public DBAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
