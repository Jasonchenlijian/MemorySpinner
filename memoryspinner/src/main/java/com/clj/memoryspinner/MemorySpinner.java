package com.clj.memoryspinner;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class MemorySpinner extends Spinner {

    private Context mContext;
    private ArrayList<String> prepareList = new ArrayList<>();
    private ArrayList<String> normalList = new ArrayList<>();
    private MemorySpinnerAdapter memorySpinnerAdapter;
    private int memoryCount = 5;

    public MemorySpinner(Context context) {
        super(context);
        this.mContext = context;
    }

    public MemorySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        memorySpinnerAdapter
                = new MemorySpinnerAdapter(context, attrs);
    }

    public MemorySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        memorySpinnerAdapter
                = new MemorySpinnerAdapter(context, attrs);
    }

    /**
     * @param prepareList : 预设的memory选项，可控
     * @param normalList： 全部选项，不能为空
     */
    public void setData(ArrayList<String> prepareList, ArrayList<String> normalList) {
        if (prepareList != null) {
            this.prepareList = prepareList;
        }
        this.normalList = normalList;

        initData();
    }

    /**
     * 设置记住的选项数量，默认是5
     * @param count
     */
    public void setMemoryCount(int count) {
        memoryCount = count;
    }

    private void initData() {

        final ArrayList<String> saveMemoryList = new ArrayList<>();
        SharedPreferences sp = mContext.getSharedPreferences("memorySpinner", 0);
        String saveMemoryString = sp.getString("clj_memory_spinner", null);
        if (saveMemoryString != null) {
            try {
                @SuppressWarnings("unchecked")
                ArrayList<String> saveList = (ArrayList<String>)
                        MemorySpinnerUtils.String2SceneList(saveMemoryString);
                saveMemoryList.addAll(saveList);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (saveMemoryList.size() <= 0) {
            for (int i = 0; i < prepareList.size() && i < memoryCount; i++) {
                saveMemoryList.add(prepareList.get(i));
            }
        }
        memorySpinnerAdapter.addData(saveMemoryList);
        memorySpinnerAdapter.addData(normalList);

        this.setAdapter(memorySpinnerAdapter);

        this.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String select = memorySpinnerAdapter.getItem(position);

                saveMemoryList.add(0, select);
                for (int i = 1; i < saveMemoryList.size(); i++) {
                    if (saveMemoryList.get(i).equals(select)) {
                        saveMemoryList.remove(i);
                    }
                }
                ArrayList<String> newMemoryList = saveMemoryList;
                if (saveMemoryList.size() > memoryCount) {
                    newMemoryList = MemorySpinnerUtils.cutString(saveMemoryList, 0, memoryCount);
                }

                memorySpinnerAdapter.setMemoryCount(newMemoryList.size());
                memorySpinnerAdapter.clear();
                memorySpinnerAdapter.addData(newMemoryList);
                memorySpinnerAdapter.addData(normalList);
                memorySpinnerAdapter.notifyDataSetChanged();
                spinner.setSelection(0);

                try {
                    String memoryString = MemorySpinnerUtils.SceneList2String(newMemoryList);
                    SharedPreferences sp = mContext.getSharedPreferences("memorySpinner", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("clj_memory_spinner", memoryString);
                    editor.apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        super.setOnItemSelectedListener(listener);


    }
}
