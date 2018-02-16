package com.ser210_02_asazhin.ser210_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {
    EditText _name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        _name = (EditText) findViewById(R.id.editText);
    }

    /*
    On clickcing of the start button it takes the text from the
    edit view and puts it into an intent for the GameActivity to take
     */
    public void onStartClick(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("playerName", _name.getText().toString());
        startActivityForResult(intent, 2);
    }
}
