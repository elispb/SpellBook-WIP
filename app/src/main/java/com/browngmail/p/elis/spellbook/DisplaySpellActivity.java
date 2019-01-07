package com.browngmail.p.elis.spellbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DisplaySpellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_spell);
        Intent intent = getIntent();

        Context context = getApplicationContext();
        CharSequence text = intent.getStringExtra("SpellID");
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //TODO Read and display spell from DB
    }
}
