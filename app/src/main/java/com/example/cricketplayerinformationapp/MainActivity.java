package com.example.cricketplayerinformationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends NavigatorActivity {
    private EditText name, in1, in2, in3, in4,in5;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
        name=(EditText)findViewById(R.id.pname);
        in1=(EditText)findViewById(R.id.inning1);
        in2=(EditText)findViewById(R.id.inning2);
        in3=(EditText)findViewById(R.id.inning3);
        in4=(EditText)findViewById(R.id.inning4);
        in5=(EditText)findViewById(R.id.inning5);
        submit=(Button)findViewById(R.id.submit);
        //String root = Environment.getExternalStorageDirectory().toString();
        //File myDir = new File(root + "/saved_images");
        //myDir.mkdirs();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILENAME = "/Player/";
                String entry = name.getText().toString() + "," + in1.getText().toString() + ","
                        + in2.getText().toString() + "," + in3.getText().toString() + ","
                        + in4.getText().toString() + "," + in5.getText().toString() +"\n";
                try {
                    String namFile = Environment.getExternalStorageDirectory() + FILENAME;
                    File datfile = new File(namFile);
                    datfile.mkdirs();
                    //Toast.makeText(getApplicationContext(), "File is:  " + datfile, Toast.LENGTH_LONG).show();
                    File file = new File(datfile, "Player.txt");
                    //FileOutputStream os = null;
                    try {
                        //os = new FileOutputStream(file);
                        //os.write(entry.getBytes());
                        //os.close();
                        FileOutputStream fOut = new FileOutputStream(file, true);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(entry);
                        myOutWriter.close();
                        fOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
                    //out.write(entry.getBytes());
                    //out.close();
                    name.setText("");
                    in1.setText("");
                    in2.setText("");
                    in3.setText("");
                    in4.setText("");
                    in5.setText("");
                    toasting("Entry Saved");
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Write failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toasting(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

    }
}
