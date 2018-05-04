package com.example.nbroadbent.passgen;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipboardManager;

public class MainActivity extends AppCompatActivity {

    Button clipboard;
    Button gen;
    EditText len;
    TextView pass;
    CheckBox words;
    CheckBox phrase;

    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clipboard = findViewById(R.id.clipboard);
        len = findViewById(R.id.len);
        gen = findViewById(R.id.gen);
        pass = findViewById(R.id.pass);
        words = findViewById(R.id.word);
        phrase = findViewById(R.id.phrase);

        phrase.setVisibility(View.INVISIBLE);
        words.setVisibility(View.INVISIBLE);

        clipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Pass", password);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, password + " copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });

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
                password = generator.generate();
                pass.setText(password);
            }
        });
    }
}
