package com.browngmail.p.elis.spellbook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddSpellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spell);

        //Populate Spinner
        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add(getResources().getString(R.string.Abjuration));
        spinnerArray.add(getResources().getString(R.string.Conjuration));
        spinnerArray.add(getResources().getString(R.string.Divination));
        spinnerArray.add(getResources().getString(R.string.Enchantment));
        spinnerArray.add(getResources().getString(R.string.Evocation));
        spinnerArray.add(getResources().getString(R.string.Illusion));
        spinnerArray.add(getResources().getString(R.string.Necromancy));
        spinnerArray.add(getResources().getString(R.string.Transmutation));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.SchoolSpinner);
        sItems.setAdapter(adapter);

        final DatabaseContact.SpellDBHelper mDbHelper = new DatabaseContact.SpellDBHelper(this);

        Button SaveSpell = findViewById(R.id.SaveSpellButton);
        SaveSpell.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                //Save Spell
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                EditText SpellName = findViewById(R.id.SpellNameField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SPELL_NAME, SpellName.getText().toString());
                RadioButton Ritual = findViewById(R.id.RitualRadioButton);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RITUAL, (String) Ritual.getText());
                Intent intent = getIntent();
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_LEVEL,(intent.getStringExtra("Level")) );
                Spinner spin = findViewById(R.id.SchoolSpinner);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SCHOOl, spin.getSelectedItem().toString());
                EditText CastingTime = findViewById(R.id.CastingTimeField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_CASTING_TIME, CastingTime.getText().toString());
                EditText Range = findViewById(R.id.RangeField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RANGE, Range.getText().toString());
                EditText Components = findViewById(R.id.ComponentsField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_COMPONENTS, Components.getText().toString());
                EditText Duration = findViewById(R.id.DurationField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DURATION, Duration.getText().toString());
                EditText Description = findViewById(R.id.DescField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DESCRIPTION, Description.getText().toString());
                EditText HigherLevel = findViewById(R.id.HigherDesc);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_AT_HIGHER_LEVELS, HigherLevel.getText().toString());
                EditText Source = findViewById(R.id.SourceField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SOURCE, Source.getText().toString());
                EditText Page = findViewById(R.id.PageField);
                contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_PAGE, Page.getText().toString());
                db.insert(DatabaseContact.SpellEntry.TABLE_NAME, null, contentValues);

                //Saved MSG
                Context context = getApplicationContext();
                CharSequence text = getResources().getString(R.string.SavedMSG);
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
