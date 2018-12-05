package com.browngmail.p.elis.spellbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseContact {
    private void DatabaseContact(){

    }
    public static abstract class SpellEntry implements BaseColumns{
        public static final String TABLE_NAME = "Spells";
        public static final String COLUMN_NAME_SPELL_NAME = "SpellName";
        public static final String COLUMN_NAME_LEVEL = "Level";
        public static final String COLUMN_NAME_RITUAL = "Ritual";
        public static final String COLUMN_NAME_SCHOOl = "School";
        public static final String COLUMN_NAME_CASTING_TIME = "CastingTime";
        public static final String COLUMN_NAME_RANGE = "Range";
        public static final String COLUMN_NAME_COMPONENTS = "Components";
        public static final String COLUMN_NAME_DURATION = "Duration";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_AT_HIGHER_LEVELS = "AtHigherLevels";
        public static final String COLUMN_NAME_SOURCE = "Source";
        public static final String COLUMN_NAME_PAGE = "Page";
    }

    public static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_SPELLS_TABLE = "CREATE TABLE " + SpellEntry.TABLE_NAME +
            " (" + SpellEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            SpellEntry.COLUMN_NAME_SPELL_NAME + COMMA_SEP +
            SpellEntry.COLUMN_NAME_LEVEL + COMMA_SEP +
            SpellEntry.COLUMN_NAME_RITUAL + COMMA_SEP +
            SpellEntry.COLUMN_NAME_SCHOOl + COMMA_SEP +
            SpellEntry.COLUMN_NAME_CASTING_TIME + COMMA_SEP +
            SpellEntry.COLUMN_NAME_RANGE + COMMA_SEP +
            SpellEntry.COLUMN_NAME_COMPONENTS + COMMA_SEP +
            SpellEntry.COLUMN_NAME_DURATION + COMMA_SEP +
            SpellEntry.COLUMN_NAME_DESCRIPTION + COMMA_SEP +
            SpellEntry.COLUMN_NAME_AT_HIGHER_LEVELS + COMMA_SEP +
            SpellEntry.COLUMN_NAME_SOURCE + COMMA_SEP +
            SpellEntry.COLUMN_NAME_PAGE + " )";
    public static final String SQL_DELETE_SPELLS_TABLE = "DROP TABLE IF EXISTS " + SpellEntry.TABLE_NAME;


    public static class SpellDBHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Spells.db";
        public SpellDBHelper(Context pContext){
            super(pContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase pDb){
            pDb.execSQL(DatabaseContact.SQL_CREATE_SPELLS_TABLE);
        }

        public void onUpgrade(SQLiteDatabase pDb, int i, int v){}
    }
}
