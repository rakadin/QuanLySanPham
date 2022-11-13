package com.example.quanlysanpham;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Activity context;
    public SanPhamAdapter(Activity context, int layoutID, List<SanPham>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sanpham, null, false);
        }
        // Get item
        SanPham sanPham = getItem(position);
        // Get view
        TextView dmName = (TextView) convertView.findViewById(R.id.tendm);
        TextView spname = (TextView) convertView.findViewById(R.id.tensp);
        TextView masp = (TextView) convertView.findViewById(R.id.masp);
        TextView soluong = (TextView) convertView.findViewById(R.id.soluongsp);
        LinearLayout llParent = (LinearLayout) convertView.findViewById(R.id.item_parent);
        dmName.setText(sanPham.getDanhmuc());
        spname.setText(sanPham.getTensp());
        masp.setText(sanPham.getMasp());
        soluong.setText(String.valueOf(sanPham.getSoluong()));
        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            llParent.setBackgroundResource(R.color.white);
        }
        else
        {
            llParent.setBackgroundResource(R.color.teal_200);
        }
        return convertView;
    }
}
