package com.example.cricketplayerinformationapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends NavigatorActivity
        implements AdapterView.OnItemClickListener {

    private List<String> playernames;
    private List<Integer> inning1, inning2, inning3, inning4, inning5, total;
    private List<Double> average;
    String updateplayername;
    int updateinning1;
    int updateinning2;
    int updateinning3;
    int updateinning4;
    int updateinning5;
    int updatetotal;
    Double updateaverage;
    ListAdapter la;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_display, contentFrameLayout);
        lv = (ListView)findViewById(R.id.listView);
        playernames=new ArrayList<String>();
        inning1=new ArrayList<Integer>();
        inning2=new ArrayList<Integer>();
        inning3=new ArrayList<Integer>();
        inning4=new ArrayList<Integer>();
        inning5=new ArrayList<Integer>();
        total=new ArrayList<Integer>();
        average=new ArrayList<Double>();
        int i=0;
        String myData="";
        String FILENAME = "/Player/Player.txt";
        String namFile = Environment.getExternalStorageDirectory() + FILENAME;
        try {
            FileInputStream fis = new FileInputStream(namFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if(myData=="") {
                    myData = myData + strLine;
                }else {
                    myData = myData + "\n" + strLine;
                }
                i=i+1;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Records", myData);
        String[] records = myData.split("\\u000A");
        for (int j=0; j<i; j++)
        {
            String[] tuple = records[j].split(",");
            Log.d("Subrecord", records[j]);
            Log.d("Player", tuple[0]);
            playernames.add(tuple[0]);
            inning1.add(Integer.parseInt(tuple[1]));
            inning2.add(Integer.parseInt(tuple[2]));
            inning3.add(Integer.parseInt(tuple[3]));
            inning4.add(Integer.parseInt(tuple[4]));
            inning5.add(Integer.parseInt(tuple[5]));
            total.add(Integer.parseInt(tuple[1])+Integer.parseInt(tuple[2])+Integer.parseInt(tuple[3])+Integer.parseInt(tuple[4])+Integer.parseInt(tuple[5]));
            average.add(Double.valueOf(Integer.parseInt(tuple[1])+Integer.parseInt(tuple[2])+Integer.parseInt(tuple[3])+Integer.parseInt(tuple[4])+Integer.parseInt(tuple[5]))/5);
        }

        la = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                playernames);
        lv.setAdapter(la);
        lv.setOnItemClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        playernames.clear();
        inning1.clear();
        inning2.clear();
        inning3.clear();
        inning4.clear();
        inning5.clear();
        total.clear();
        average.clear();
        int i=0;
        String myData="";
        String FILENAME = "/Player/Player.txt";
        String namFile = Environment.getExternalStorageDirectory() + FILENAME;
        try {
            FileInputStream fis = new FileInputStream(namFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if(myData=="") {
                    myData = myData + strLine;
                }else {
                    myData = myData + "\n" + strLine;
                }
                i=i+1;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] records = myData.split("\n");
        for (int j=0; j<i; j++)
        {
            String[] tuple = records[j].split(",");
            playernames.add(tuple[0]);
            inning1.add(Integer.parseInt(tuple[1]));
            inning2.add(Integer.parseInt(tuple[2]));
            inning3.add(Integer.parseInt(tuple[3]));
            inning4.add(Integer.parseInt(tuple[4]));
            inning5.add(Integer.parseInt(tuple[5]));
            total.add(Integer.parseInt(tuple[1])+Integer.parseInt(tuple[2])+Integer.parseInt(tuple[3])+Integer.parseInt(tuple[4])+Integer.parseInt(tuple[5]));
            average.add(Double.valueOf(Integer.parseInt(tuple[1])+Integer.parseInt(tuple[2])+Integer.parseInt(tuple[3])+Integer.parseInt(tuple[4])+Integer.parseInt(tuple[5]))/5);
        }

        la = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                playernames);
        lv.setAdapter(la);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        updateplayername = playernames.get(position);
        updateinning1 = inning1.get(position);
        updateinning2 = inning2.get(position);
        updateinning3 = inning3.get(position);
        updateinning4 = inning4.get(position);
        updateinning5 = inning5.get(position);
        updatetotal = total.get(position);
        updateaverage = average.get(position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Save Details");
        dialog.setMessage("Do you want to save details of "+ playernames.get(position) + " in another text file?");
        dialog.setPositiveButton("Save Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String FILENAME = "/Player/";
                String entry = updateplayername + "," + updateinning1 + ","
                        + updateinning2 + "," + updateinning3 + ","
                        + updateinning4 + "," + updateinning5 + ","
                        + updatetotal + "," + updateaverage + "\n";
                try {
                    String namFile = Environment.getExternalStorageDirectory() + FILENAME;
                    File datfile = new File(namFile);
                    //Toast.makeText(getApplicationContext(), "File is:  " + datfile, Toast.LENGTH_LONG).show();
                    File file = new File(datfile, "PlayerAvg.txt");
                    try {
                        FileOutputStream fOut = new FileOutputStream(file, true);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(entry);
                        myOutWriter.close();
                        fOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //FileOutputStream fOut = new FileOutputStream(datfile);
                    //OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    //myOutWriter.append(entry);
                    //myOutWriter.close();
                    //fOut.close();
                    //FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
                    //out.write(entry.getBytes());
                    //out.close();
                    Toast.makeText(getApplicationContext(), "Entry Saved", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Write failure", Toast.LENGTH_SHORT).show();
                }
                onResume();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onResume();
            }
        });

        dialog.create();
        dialog.show();

    }
}
