package com.browngmail.p.elis.spellbook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
                Spell s = ValidateInput();
                if(s != null) {
                    //Save Spell
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SPELL_NAME, s.name);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RITUAL, s.ritual.toString());
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_LEVEL, s.level);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SCHOOl, s.school);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_CASTING_TIME, s.castingTime);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RANGE, s.range);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_COMPONENTS, s.materialComponents);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DURATION, s.duration);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DESCRIPTION, s.description);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_AT_HIGHER_LEVELS,s.higherLevelDescription);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SOURCE, s.source);
                    contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_PAGE, s.pageNo);

                    long newRowId = db.insert(DatabaseContact.SpellEntry.TABLE_NAME, null, contentValues);

                    //Saved MSG
                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.SavedMSG);
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    //View Spell
                    Intent intent = new Intent(view.getContext(), DisplaySpellActivity.class);
                    intent.putExtra("SpellID", String.valueOf(newRowId));
                    startActivity(intent);
                }
            }
        });
    }

    Spell ValidateInput(){
        String errorMSG = "";

        EditText spellNameView = findViewById(R.id.SpellNameField);
        String spellName = spellNameView.getText().toString();
        if(spellName.isEmpty()){
            errorMSG = "noName";
        }

        RadioButton Ritual = findViewById(R.id.RitualRadioButton);

        Intent intent = getIntent();
        String level = intent.getStringExtra("Level");

        Spinner spin = findViewById(R.id.SchoolSpinner);
        String school = spin.getSelectedItem().toString();
        if(school.isEmpty()){
            errorMSG = "noSchool";
        }

        EditText castingTimeView = findViewById(R.id.CastingTimeField);
        String castingTime = castingTimeView.getText().toString();
        if(castingTime.isEmpty()){
            errorMSG = "noCastingTime";
        }

        EditText rangeView = findViewById(R.id.RangeField);
        String range = rangeView.getText().toString();
        if(range.isEmpty()){
            errorMSG = "noRange";
        }

        EditText componentsView = findViewById(R.id.ComponentsField);
        String material = componentsView.getText().toString();
        if(material.isEmpty()){
            errorMSG = "noMaterial";
        }

        EditText durationView = findViewById(R.id.DurationField);
        String duration = durationView.getText().toString();
        if(duration.isEmpty()){
            errorMSG = "noDuration";
        }

        EditText descriptionView = findViewById(R.id.DescField);
        String description = descriptionView.getText().toString();
        if(description.isEmpty()){
            errorMSG = "noDescription";
        }

        EditText higherDescView = findViewById(R.id.HigherDesc);
        String higherDesc = higherDescView.getText().toString();

        EditText sourceView = findViewById(R.id.SourceField);
        String source = sourceView.getText().toString();

        EditText pageView = findViewById(R.id.PageField);
        String page = pageView.getText().toString();

        if(errorMSG.isEmpty()) {
            Spell s = new Spell(spellName, Ritual.isChecked(), level, school, castingTime, range, null, null, null, material, duration, description, higherDesc, source, page);
            return s;
        }
        return null;
    }
}
