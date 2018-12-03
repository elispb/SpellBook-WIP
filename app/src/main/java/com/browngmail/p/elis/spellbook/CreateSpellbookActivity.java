package com.browngmail.p.elis.spellbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateSpellbookActivity extends AppCompatActivity {

    private ListView Spellbook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spellbook);

        Spellbook = findViewById(R.id.SpellbookList);

        final List<String> SpellLevels = new ArrayList<>();
        SpellLevels.add("Cantrips");
        SpellLevels.add("1st Level");
        SpellLevels.add("2nd Level");
        SpellLevels.add("3rd Level");
        SpellLevels.add("4th Level");
        SpellLevels.add("5th Level");
        SpellLevels.add("6th Level");
        SpellLevels.add("7th Level");
        SpellLevels.add("8th Level");
        SpellLevels.add("9th Level");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                SpellLevels );

        Spellbook.setAdapter(arrayAdapter);

        final ListView finalSpellLevelList = Spellbook;

        Spellbook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ViewSpellLevelActivity.class);
                String level = finalSpellLevelList.getAdapter().getItem(position).toString();
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });
    }
}
