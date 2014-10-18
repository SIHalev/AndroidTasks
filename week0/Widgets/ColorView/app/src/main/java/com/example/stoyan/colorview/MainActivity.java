package com.example.stoyan.colorview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends Activity {
    private final String HEX_PATTERN = "#[0-9A-Fa-f]{6}";
    private EditText editText;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        editText = (EditText) findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable textState) {
                String color = textState.toString();

                if(color.length() == 0) {
                    editText.setText("#");
                    color = "#";
                    editText.setSelection(1);
                }

                while (color.length() < 7) {
                    color += "0";
                }

                boolean isHex = Pattern.compile(HEX_PATTERN).matcher(color).matches();

                if (isHex) {
                    view.setBackgroundColor(Color.parseColor(color));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Hex color", Toast.LENGTH_SHORT).show();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }
}
