package com.example.androidexcercise1;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;





import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText ed1;
    private SeekBar s;
    private Button b;
    private RadioGroup r;
    private RadioButton rad1,rad2;
    String age="";
    String[] agerange = {"0-18", "18-60", "60+"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spino = findViewById(R.id.spinner);
        spino.setOnItemSelectedListener(this);





        s = (SeekBar) findViewById(R.id.seekBar);
        ed1 = (EditText) findViewById(R.id.editTextNumber);
        b = (Button) findViewById(R.id.button);
        r=(RadioGroup)findViewById(R.id.radiogrp);


        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agerange);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spino.setAdapter(ad);
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {








            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                double height = Float.parseFloat(String.valueOf(ed1.getText()));
                height = height / 100.00;
                double weight = Double.valueOf(s.getProgress());
                double BMI = weight/(height*height);
                BMI=Math.round((BMI*100.0)/100.0);

                if(age.equals("0-18"))
                {
                    Toast.makeText(getApplicationContext(),"BMI SCALE NOT RECOMMENDED",Toast.LENGTH_LONG).show();
                }
                else if (BMI< 25 && BMI>18) {
                    Toast.makeText(getApplicationContext(), "BMI"+" "+BMI+" "+"NORMAL", Toast.LENGTH_LONG).show();
                } else if (BMI > 25) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("You have an Abnormal BMI for your Age : Obese"+" "+"BMI"+" "+BMI);
                    alertDialogBuilder.setTitle("WARNING!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.setPositiveButton(
                                    "OK",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which)
                                        {
                                            
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog=alertDialogBuilder.create();
                    alertDialog.show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "BMI"+" "+BMI+" "+"TOO LOW and ANAEMIC", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),agerange[position] , Toast.LENGTH_LONG).show();
        age=agerange[position];
    }
     public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}