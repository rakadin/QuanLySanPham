package com.example.quanlysanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
new adapter chỉ có string thì item_danh_muc k bọc bởi layout
-> chỉ có TextView trong layout

nhớ thêm dòng code xin phép ghi file SDCard trong manifest
 */
public class MainActivity extends AppCompatActivity {
    EditText edtmasp,edttensp,edtsoluong;
    DanhmucAdapter adapter;
    ArrayList<String> arrayListDM = new ArrayList<String>();
    Spinner spinner ;
    SanPhamAdapter adapterSP;
    ArrayList<SanPham> arrayListSP = new ArrayList<SanPham>();
    ListView lvproduct;
    String spinnerClick; // tên danh mục click chọn
    int selectedID = -1;// xem id của itemCLick trong listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinnerdanhmuc);
        lvproduct = findViewById(R.id.lvproduct);

        edtmasp = findViewById(R.id.masp);
        edttensp = findViewById(R.id.tensp);
        edtsoluong = findViewById(R.id.soluong);

        arrayListDM.add("Quần áo");
        arrayListDM.add("Tẩy rửa");
        arrayListDM.add("Phụ kiện");
        arrayListDM.add("Ốc vít");
        arrayListDM.add("Khăn lau");
        // thêm adapter vào spinner
        adapter = new DanhmucAdapter(this,R.layout.item_danh_muc,arrayListDM);
        spinner.setAdapter(adapter);
        // thêm sp vào listview
        adapterSP = new SanPhamAdapter(this,R.layout.item_sanpham,arrayListSP);
        lvproduct.setAdapter(adapterSP);

        // sự kiện lấy thông tin khi click spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                spinnerClick = (String) adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // sự kiện click vào listView
        lvproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // lay thong tin click san pham chon
                int spinnerID =0;
                selectedID = i;
                Adapter adapter = adapterView.getAdapter();
                SanPham spClick = (SanPham) adapter.getItem(i);
                String dmClick = spClick.getDanhmuc();
                String tenspClick = spClick.getTensp();
                String maspClick = spClick.getMasp();
                int countClick = spClick.getSoluong();
                if (dmClick.equals("Quần áo"))
                {
                    spinnerID = 0;
                }
                if (dmClick.equals("Tẩy rửa"))
                {
                    spinnerID = 1;
                }
                if (dmClick.equals("Phụ kiện"))
                {
                    spinnerID = 2;
                }
                if (dmClick.equals("Ốc vít"))
                {
                    spinnerID = 3;
                }
                if (dmClick.equals("Khăn lau"))
                {
                    spinnerID = 4;
                }
                // set text
                edtmasp.setText(maspClick);
                edtsoluong.setText(String.valueOf(countClick));
                edttensp.setText(tenspClick);
                spinner.setSelection(spinnerID);
            }
        });

    }

    // set giao dien menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_item, menu);
//        if(menu instanceof MenuBuilder){
//            MenuBuilder m = (MenuBuilder) menu;
////            m.setOptionalIconsVisible(true);
//        }
        return true;
    }

    // khi click vao option menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.themhang:
                themhangFunc();
                return true;
            case R.id.suahang:
                suahangFunc();
                return true;
            case R.id.xoahang:
                xoahangFunc();
                return true;
            case R.id.luuds:
                luudanhsach();
                return true;
            case R.id.docds:
                docdanhsach();
                return true;
            case R.id.dong:
                dong();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // function them san pham
    public void themhangFunc()
    {
        String masp = edtmasp.getText().toString().trim();
        String tensp = edttensp.getText().toString().trim();
        int count = Integer.valueOf(edtsoluong.getText().toString().trim());
        SanPham sp = new SanPham(spinnerClick,masp,tensp,count);
        Toast.makeText(this, "+"+masp+"-"+tensp+"--"+count+"--"+spinnerClick, Toast.LENGTH_LONG).show();
        arrayListSP.add(sp);
        adapterSP.notifyDataSetChanged();
        edtmasp.requestFocus();
    }
    // function sua san pham
    public void suahangFunc()
    {
        String masp = edtmasp.getText().toString().trim();
        String tensp = edttensp.getText().toString().trim();
        int count = Integer.valueOf(edtsoluong.getText().toString().trim());
        arrayListSP.get(selectedID).setDanhmuc(spinnerClick);
        arrayListSP.get(selectedID).setMasp(masp);
        arrayListSP.get(selectedID).setTensp(tensp);
        arrayListSP.get(selectedID).setSoluong(count);
        adapterSP.notifyDataSetChanged();
    }
    // function xoa san pham
    public void xoahangFunc()
    {
        arrayListSP.remove(selectedID);
        adapterSP.notifyDataSetChanged();
    }
    // function ghi file vao the nho
    public void luudanhsach()
    {
        String sb = "";
        for(int i=0;i<arrayListSP.size();i++)
        {
            sb += (""+arrayListSP.get(i).getDanhmuc());
            sb += ("\n"+arrayListSP.get(i).getMasp());
            sb += ("\n"+arrayListSP.get(i).getTensp());
            sb +=("\n"+String.valueOf(arrayListSP.get(i).getSoluong()));
            sb += "\n";
        }
        String sdcard=this.getExternalFilesDir(null).getAbsolutePath()+"/DanhSachHang.txt";
        try {
            OutputStreamWriter writer= new OutputStreamWriter(new FileOutputStream(sdcard));
            writer.write(String.valueOf(sb));//dữ liệu cần ghi
            writer.close();
            Toast.makeText(this, "Lưu thành công",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "lỗi ghi file",Toast.LENGTH_SHORT).show();
            Log.e("GhiFile",e.toString());
        }
    }
    // doc danh sach tu the nho
    public void docdanhsach()
    {
        String sdcard=this.getExternalFilesDir(null).getAbsolutePath()+"/DanhSachHang.txt";
        try {
            Scanner scan=new Scanner(new File(sdcard));
                while(scan.hasNext())
                {
                        SanPham sp = new SanPham();
                        sp.setDanhmuc(scan.nextLine());
                        sp.setTensp(scan.nextLine());
                        sp.setMasp(scan.nextLine());
                        sp.setSoluong(Integer.valueOf(scan.nextLine()));
                        arrayListSP.add(sp);
                        adapterSP.notifyDataSetChanged();
                }
            scan.close();
            Toast.makeText(this, "Đọc dữ liệu thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đọc file:", Toast.LENGTH_SHORT).show();
            Log.e("DocFile",e.toString());
        }
    }
    public void dong()
    {
        finish();
    }
}