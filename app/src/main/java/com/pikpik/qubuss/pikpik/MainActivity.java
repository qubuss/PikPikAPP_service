package com.pikpik.qubuss.pikpik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button playButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        playButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);


    }

    public void playAudio(View view) {


        if(editText.getText().toString().length()>0){
            Intent audioMenager = new Intent(this, MyService.class);
            int sleep = Integer.parseInt(editText.getText().toString());

            audioMenager.putExtra("sleep", sleep);
            startService(audioMenager);
        }else {
            Toast.makeText(this, "Podaj ilosć sekund", Toast.LENGTH_LONG).show();
        }

    }

    public void stopAudio(View view) {
        Intent audioMenager = new Intent(this, MyService.class);
        stopService(audioMenager);
    }
}
