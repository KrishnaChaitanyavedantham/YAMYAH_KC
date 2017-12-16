package com.example.krishnachaitanya.redcrossreliefeffort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HqActivity extends AppCompatActivity {
 //   private Firebase mref;
    private ListView mListView;
    private ArrayList<String> mHQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hq2);
       //mref = new Firebase("https://redcrossreliefeffort.firebaseio.com/");
        mListView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,andriod.R.layout.simple_list_item_1)

    }
}
