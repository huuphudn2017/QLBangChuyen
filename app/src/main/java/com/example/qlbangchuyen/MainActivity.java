package com.example.qlbangchuyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtChuyen, txtQC, txtToTruong, txtLDHienDien, txtLDVang, txtTime, txtMaHang, txtMau, txtNgay, txtChiTiet, txtTongHangDat, txtTongHangKhongDat, txtTongDaSua, txtMucTieu, txtTongNangSuatKiem, txtDat, txtTongLoi;
    Button btnTatMay, btnCapNhat, btnDoiHangHang, btnMoTV, btnHangDat, btnHangKhongDat, btnHangDaSua, btnHuy, btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ánh xạ
        txtChuyen =(TextView) findViewById(R.id.txtChuyen);
        txtQC =(TextView) findViewById(R.id.txtQC);
        txtToTruong =(TextView) findViewById(R.id.txtToTruong);
        txtLDHienDien =(TextView) findViewById(R.id.txtLDHienDien);
        txtLDVang =(TextView) findViewById(R.id.txtLDVang);
        txtTime =(TextView) findViewById(R.id.txtTime);
        txtMaHang =(TextView) findViewById(R.id.txtMaHang);
        txtMau =(TextView) findViewById(R.id.txtMau);
        txtNgay =(TextView) findViewById(R.id.txtNgay);
        txtChiTiet =(TextView) findViewById(R.id.txtChiTiet);
        txtTongHangDat =(TextView) findViewById(R.id.txtTongHangDat);
        txtTongHangKhongDat =(TextView) findViewById(R.id.txtTongHangKhongDat);
        txtTongDaSua =(TextView) findViewById(R.id.txtTongHangDaSua);
        txtMucTieu =(TextView) findViewById(R.id.txtMucTieu);
        txtTongNangSuatKiem =(TextView) findViewById(R.id.txtTongNangSuatKiem);
        txtDat =(TextView) findViewById(R.id.txtDat);
        txtTongLoi =(TextView) findViewById(R.id.txtTongLoi);

        btnXacNhan =(Button) findViewById(R.id.btnXacNhan);
        btnCapNhat =(Button) findViewById(R.id.btnCapNhat);
        btnDoiHangHang =(Button) findViewById(R.id.btnDoiMaHang);
        btnHangDat =(Button) findViewById(R.id.btnHangDat);
        btnHangKhongDat =(Button) findViewById(R.id.btnHangKhongDat);
        btnHuy =(Button) findViewById(R.id.btnHuy);
        btnMoTV =(Button) findViewById(R.id.btnMoTV);
        btnTatMay =(Button) findViewById(R.id.btnTatMay);
        btnHangDaSua =(Button) findViewById(R.id.btnHangSua);


        btnHangKhongDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButton();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButton();
            }
        });
    }

    public void getButton(){
        btnHuy.setVisibility(View.VISIBLE);
        btnXacNhan.setVisibility(View.VISIBLE);
    }

    public void deleteButton(){
        btnHuy.setVisibility(View.GONE);
        btnXacNhan.setVisibility(View.GONE);
    }
}
