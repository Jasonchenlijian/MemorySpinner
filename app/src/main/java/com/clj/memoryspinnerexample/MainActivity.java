package com.clj.memoryspinnerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.clj.memoryspinner.MemorySpinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MemorySpinner memorySpinner = (MemorySpinner) findViewById(R.id.ms);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("C_1", "C_2", "C_3", "C_4", "C_5",
                "C_6", "C_7", "C_8", "C_9", "C_10", "C_11", "C_12", "C_13", "C_14"));
        memorySpinner.setMemoryCount(4);
        memorySpinner.setData(null, list);
    }

}
