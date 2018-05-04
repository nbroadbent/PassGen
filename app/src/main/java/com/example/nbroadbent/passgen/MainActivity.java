package com.example.nbroadbent.passgen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button gen;
    EditText len;
    TextView pass;
    CheckBox words;
    CheckBox phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        len = findViewById(R.id.len);
        gen = findViewById(R.id.gen);
        pass = findViewById(R.id.pass);
        words = findViewById(R.id.word);
        phrase = findViewById(R.id.phrase);

        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (words.isChecked()) {
                    phrase.setVisibility(View.VISIBLE);
                } else {
                    phrase.setVisibility(View.INVISIBLE);
                    phrase.setChecked(false);
                }
            }
        });

        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = 8;

                // Get length input.
                try {
                    l = Integer.parseInt(len.getText().toString());
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (l < 0 || l > 64) {
                    Toast.makeText(MainActivity.this, "Length must be between 0 and 64!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Generate password.
                Generator generator = new Generator(l);
                String password = generator.generate();
                System.out.println(password);
                pass.setText(password);
            }
        });
    }
}
