package com.example.quanlysanpham;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DanhmucAdapter extends ArrayAdapter<String> {
    private Activity context;
    public DanhmucAdapter(Activity context, int layoutID, List<String>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, null, false);
        }
        // Get item
        String danhmuc = getItem(position);
        // Get view
        TextView dmName = (TextView) convertView.findViewById(R.id.item_dmName);
        dmName.setText(danhmuc);
        return convertView;
    }

}
