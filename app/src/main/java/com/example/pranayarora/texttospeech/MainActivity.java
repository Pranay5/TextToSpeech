package com.example.pranayarora.texttospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {
    EditText et1;
    Button b1;
    TextToSpeech tts;
    TextView t2,t3,t4;
    RadioButton r1,r2;
    Spinner s1,s2;
    String s[] ={"Slow", "Very Slow" , "Normal" ,  "Fast" ,"Very Fast" };
    //String ss[] = {"High" , "Low"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.edittext);
        b1 = (Button)findViewById(R.id.button2);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        t4 = (TextView)findViewById(R.id.textView4);
        r1 = (RadioButton)findViewById(R.id.radioButton2);
        r2 = (RadioButton)findViewById(R.id.radioButton3);
        tts = new TextToSpeech(getApplicationContext(),this);
        b1.setOnClickListener(this);
        s1 = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item,R.id.textView,s);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s1.setAdapter(aa);
        s2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter ab = new ArrayAdapter(this,R.layout.spinner_item,R.id.textView,s);
        s2.setAdapter(ab);



    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.ERROR) {
            Toast.makeText(this, "Language Not Supported", Toast.LENGTH_LONG).show();
        }
        else {
            b1.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == b1.getId())
        {
            double f=0,f1 = 0;
            if(s1.getSelectedItemPosition() == 0)
            {
                f = 0.1;

            }
            else if(s1.getSelectedItemPosition() == 1)
            {
                f = 0.5;
            }
            else if(s1.getSelectedItemPosition() == 2)
            {
                f = 1.0;
            }
            else if(s1.getSelectedItemPosition() == 3)
            {
                f = 1.5;
            }

            else if(s1.getSelectedItemPosition() == 4)
            {
                f = 2.0;
            }


            if(s2.getSelectedItemPosition() == 0)
            {
                f1 = 0.5;
            }
            else if(s2.getSelectedItemPosition() == 1) {
                f1 = 1.0;
            }
            else if(s2.getSelectedItemPosition() == 2)
            {
                f = 1.0;
            }
            else if(s2.getSelectedItemPosition() == 3)
            {
                f = 1.5;
            }

            else if(s2.getSelectedItemPosition() == 4)
            {
                f = 2.0;
            }
            Log.d("aa",""+f+f1);
                tts.setSpeechRate((float) f);
                tts.setPitch((float) f1);

            if(r1.isChecked())
            {
                tts.setLanguage(Locale.UK);
            }
            if(r2.isChecked())
            {
                tts.setLanguage(Locale.US);
            }
            tts.speak(et1.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
        }


    }
    public void onDestroy()
    {
        if(tts!= null)
        {
            tts.stop();
            tts.shutdown();
        }
    }


}
