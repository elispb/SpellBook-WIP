package com.browngmail.p.elis.spellbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void NewBook(View view){
        Intent intent = new Intent(this, CreateSpellbookActivity.class);
        startActivity(intent);
    }
}