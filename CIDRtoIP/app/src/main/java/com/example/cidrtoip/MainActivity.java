package com.example.cidrtoip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button b;
    private TextView t1,t2,t3;
    private EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        t1=findViewById(R.id.textView4);
        t2=findViewById(R.id.textView3);
        t3=findViewById(R.id.textView5);
        e=findViewById(R.id.etUsername);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IP=e.getText().toString();
                String range=String.valueOf(IP.charAt(IP.length()-2))+String.valueOf(IP.charAt(IP.length()-1));
                int r=Integer.parseInt(range);
                int nhosts= (int) Math.pow(2,32-r);
                t1.setText("Total no of Hosts: "+nhosts);
                String line[]=IP.split("\\.");
                int ran=Integer.parseInt(range);
                int len=line.length;
                int temp=line[len-1].indexOf("/");
                String tmp="";
                for(int i=0;i<temp;i++)
                {
                    tmp+=line[len-1].charAt(i);
                }
                line[len-1]=tmp;
                String tarr[]=line;
                int residue=32-ran;
                int q=residue/8;
                int rem=residue%8;
                len= line.length;
                while(q!=0)
                {
                    line[len-1]="0";
                    q--;
                    len--;
                }
                while(rem!=0)
                {
                    int t=Integer.parseInt(line[len-1]);
                    String ans="";
                    String bin=Integer.toString(t,2);
                    for(int i=0;i<bin.length()-rem;i++)
                    {
                        ans+=String.valueOf(bin.charAt(i));
                    }
                    for(int i=0;i<rem;i++)
                    {
                        ans+="0";
                    }
                    int decimal=Integer.parseInt(ans,2);
                    line[len-1]=String.valueOf(decimal);
                    rem=0;
                }
                String ans="";
                for(int i=0;i<line.length;i++)
                {
                    if(i!=line.length-1) {
                        ans = ans + line[i] + ".";
                    }
                    else
                    {
                        ans = ans + line[i];
                    }
                }
                t2.setText("First IP address : "+ans);
                q=residue/8;
                rem=residue%8;
                len= tarr.length;
                while(q!=0)
                {
                    tarr[len-1]="255";
                    q--;
                    len--;
                }
                while(rem!=0)
                {
                    int t=Integer.parseInt(tarr[len-1]);
                    ans="";
                    String bin=Integer.toString(t,2);
                    if(bin.length()!=8)
                    {
                        for(int i=bin.length();i<8;i++)
                        {
                            bin="0"+bin;
                        }
                    }

                    for(int i=0;i<bin.length()-rem;i++)
                    {
                        ans+=String.valueOf(bin.charAt(i));
                    }

                    for(int i=0;i<rem;i++)
                    {
                        ans+="1";
                    }
                    int decimal=Integer.parseInt(ans,2);

                    tarr[len-1]=String.valueOf(decimal);
                    rem=0;
                }
                ans="";
                for(int i=0;i<tarr.length;i++)
                {
                    if(i!=tarr.length-1) {
                        ans = ans + tarr[i] + ".";
                    }
                    else
                    {
                        ans = ans + tarr[i];
                    }
                }
                t3.setText("Last IP address : "+ans);
                Toast.makeText(getApplicationContext(),"The results",Toast.LENGTH_SHORT).show();

            }
        });
    }
}