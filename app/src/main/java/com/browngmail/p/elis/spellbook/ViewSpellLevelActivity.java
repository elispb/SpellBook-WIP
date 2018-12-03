package com.browngmail.p.elis.spellbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewSpellLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_spell_level);
        TextView title = findViewById(R.id.SpellLevelTitle);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("level"));

        Button AddSpell = findViewById(R.id.AddSpellButton);
        AddSpell.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddSpellActivity.class);
                startActivity(intent);
            }
        });
    }
}
