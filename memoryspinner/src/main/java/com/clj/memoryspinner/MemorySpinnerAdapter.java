package com.clj.memoryspinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MemorySpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private Context context;

    private List<String> list;
    private int topCount;

    private int msItemTextColor;
    private float msItemTextSize;

    private int msDropTitleBackgroundColor;
    private String msDropTitleText ="常用";
    private int msDropTitleTextColor;
    private float msDropTitleTextSize;

    private int msDropItemBackgroundColor;
    private String msDropItemText="全部选项";
    private int msDropItemTextColor;
    private float msDropItemTextSize;

    public MemorySpinnerAdapter(Context context, AttributeSet attrs) {
        this.context = context;
        list = new ArrayList<>();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MemorySpinner);

        msItemTextColor = typedArray.getColor(R.styleable.MemorySpinner_msItemTextColor, 0xff000000);
        msItemTextSize = MemorySpinnerUtils.px2sp(context, typedArray.getDimension(R.styleable.MemorySpinner_msItemTextSize, 14));

        msDropTitleBackgroundColor = typedArray.getColor(R.styleable.MemorySpinner_msDropTitleBackgroundColor, 0xff3F51B5);
        msDropTitleText = typedArray.getString(R.styleable.MemorySpinner_msDropTitleText);
        msDropTitleTextColor = typedArray.getColor(R.styleable.MemorySpinner_msDropTitleTextColor, 0xff000000);
        msDropTitleTextSize = MemorySpinnerUtils.px2sp(context, typedArray.getDimension(R.styleable.MemorySpinner_msDropTitleTextSize, 12));

        msDropItemBackgroundColor = typedArray.getColor(R.styleable.MemorySpinner_msDropItemBackgroundColor, 0xffffffff);
        msDropItemText = typedArray.getString(R.styleable.MemorySpinner_msDropItemText);
        msDropItemTextColor = typedArray.getColor(R.styleable.MemorySpinner_msDropItemTextColor, 0xff000000);
        msDropItemTextSize = MemorySpinnerUtils.px2sp(context, typedArray.getDimension(R.styleable.MemorySpinner_msDropItemTextSize, 14));

        typedArray.recycle();
    }

    public void clear() {
        list.clear();
    }

    public void addData(ArrayList<String> list) {
        this.list.addAll(list);
    }

    public void setMemoryCount(int i) {
        topCount = i;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 不下拉时候展示的view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.spinner_item_view, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.txt_spinner_item = (TextView) convertView
                    .findViewById(R.id.txt_spinner_item);
            holder.txt_spinner_item.setTextColor(msItemTextColor);
            holder.txt_spinner_item.setTextSize(msItemTextSize);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);

        holder.txt_spinner_item.setText(item);

        return convertView;
    }

    /**
     * 下拉时候展示的view
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.spinner_dropdown_view, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.txt_spinner_title = (TextView) convertView
                    .findViewById(R.id.txt_spinner_title);
            holder.txt_spinner_title.setBackgroundColor(msDropTitleBackgroundColor);
            holder.txt_spinner_title.setTextColor(msDropTitleTextColor);
            holder.txt_spinner_title.setTextSize(msDropTitleTextSize);

            holder.txt_spinner_item = (TextView) convertView
                    .findViewById(R.id.txt_spinner_item);
            holder.txt_spinner_item.setBackgroundColor(msDropItemBackgroundColor);
            holder.txt_spinner_item.setTextColor(msDropItemTextColor);
            holder.txt_spinner_item.setTextSize(msDropItemTextSize);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);

        holder.txt_spinner_item.setText(item);

        if (topCount == 0) {
            switch (position) {
                case 0:
                    holder.txt_spinner_title.setVisibility(View.VISIBLE);
                    holder.txt_spinner_title.setText(msDropItemText);
                    break;

                default:
                    holder.txt_spinner_title.setVisibility(View.GONE);
                    holder.txt_spinner_title.setText("");
                    break;
            }
        } else {
            if (position == 0) {
                holder.txt_spinner_title.setVisibility(View.VISIBLE);
                holder.txt_spinner_title.setText(msDropTitleText);
            } else if (position == topCount) {
                holder.txt_spinner_title.setVisibility(View.VISIBLE);
                holder.txt_spinner_title.setText(msDropItemText);
            } else {
                holder.txt_spinner_title.setVisibility(View.GONE);
                holder.txt_spinner_title.setText("");
            }
        }

        return convertView;
    }

    class ViewHolder {
        TextView txt_spinner_item;
        TextView txt_spinner_title;
    }


}
