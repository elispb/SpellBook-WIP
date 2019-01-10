package com.browngmail.p.elis.spellbook;

import android.content.Context;
import android.content.Intent;
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

        final DatabaseContact.SpellDBHelper mDbHelper = new DatabaseContact.SpellDBHelper(this);
        Spell spellToDisplay;
        try {
            spellToDisplay = new Spell(spellID);
            spellToDisplay = spellToDisplay.Load(mDbHelper);
            DisplaySpell(spellToDisplay);
        }
        catch(Exception e) {
            Toast toast = Toast.makeText(context, e.getMessage(), duration);
            toast.show();
        }
        //TODO display spell on screen
    }
    void DisplaySpell(Spell spellToDisplay){
        TextView spellNameView = findViewById(R.id.NameDisplay);
        spellNameView.setText(spellToDisplay.name);
        TextView ritualView = findViewById(R.id.RitualTag);
        if(spellToDisplay.ritual) {
            ritualView.setText("(Ritual)");
        }else{
            ritualView.setText("");
        }
        TextView levelAndSchool = findViewById(R.id.SchoolLevelDisplay);
        // Combine level & school into one
        StringBuilder sb = new StringBuilder();
        String lv = spellToDisplay.level;
        sb.append(lv);
        sb.append(" " + spellToDisplay.school);
        levelAndSchool.setText(sb.toString());
        TextView castingTime = findViewById(R.id.CastingTimeDisplay);
        castingTime.setText(castingTime.getText() + " " + spellToDisplay.castingTime);
        TextView range = findViewById(R.id.RangeDisplay);
        range.setText(range.getText() + " " + spellToDisplay.range);
        TextView components = findViewById(R.id.ComponentsDisplay);
        components.setText(components.getText() + " " + spellToDisplay.materialComponents); //TODO implement VSM booleans
        TextView duration = findViewById(R.id.DurationDisplay);
        duration.setText(duration.getText() + " " + spellToDisplay.duration);
        TextView description = findViewById(R.id.DescriptionDisplay);
        description.setText(spellToDisplay.description);
        TextView higherDesc = findViewById(R.id.AtHigherLevelText);
        higherDesc.setText(spellToDisplay.higherLevelDescription);
        TextView source = findViewById(R.id.SourceDisplay);
        source.setText(spellToDisplay.source);
        TextView page = findViewById(R.id.PageDisplay);
        page.setText(spellToDisplay.pageNo);
    }
}
