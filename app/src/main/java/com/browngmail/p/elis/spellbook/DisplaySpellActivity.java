package com.browngmail.p.elis.spellbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySpellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_spell);
        Intent intent = getIntent();

        Context context = getApplicationContext();
        CharSequence spellID = intent.getStringExtra("SpellID");
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, spellID, duration);
        toast.show();

        //TODO Read and display spell from DB
        final DatabaseContact.SpellDBHelper mDbHelper = new DatabaseContact.SpellDBHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContact.SpellEntry.COLUMN_NAME_SPELL_NAME,
                DatabaseContact.SpellEntry.COLUMN_NAME_RITUAL,
                DatabaseContact.SpellEntry.COLUMN_NAME_SCHOOl,
                DatabaseContact.SpellEntry.COLUMN_NAME_CASTING_TIME,
                DatabaseContact.SpellEntry.COLUMN_NAME_RANGE,
                DatabaseContact.SpellEntry.COLUMN_NAME_COMPONENTS,
                DatabaseContact.SpellEntry.COLUMN_NAME_DURATION,
                DatabaseContact.SpellEntry.COLUMN_NAME_DESCRIPTION,
                DatabaseContact.SpellEntry.COLUMN_NAME_AT_HIGHER_LEVELS,
                DatabaseContact.SpellEntry.COLUMN_NAME_SOURCE,
                DatabaseContact.SpellEntry.COLUMN_NAME_PAGE
        };

        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = {spellID.toString()};

        Cursor cursor = db.query(
                DatabaseContact.SpellEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String s = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContact.SpellEntry.COLUMN_NAME_SPELL_NAME));
        TextView spellNameView = findViewById(R.id.NameDisplay);
        spellNameView.setText(s);
    }
}
