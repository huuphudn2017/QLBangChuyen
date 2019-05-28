package com.example.qlbangchuyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qlbangchuyen.models.Line;
import com.example.qlbangchuyen.models.Plan;
import com.example.qlbangchuyen.models.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtChuyen, txtQC, txtToTruong, txtLDHienDien, txtLDVang, txtTime, txtMaHang, txtMau, txtNgay, txtChiTiet, txtTongHangDat, txtTongHangKhongDat, txtTongDaSua, txtMucTieu, txtTongNangSuatKiem, txtDat, txtTongLoi;
    Button btnTatMay, btnCapNhat, btnDoiHangHang, btnMoTV, btnHangDat, btnHangKhongDat, btnHangDaSua, btnHuy, btnXacNhan;

    private Gson gson;
    private GsonBuilder gsonBuilder = new GsonBuilder();

    ArrayList<Plan> plans;
    ArrayList<Line> lines = new ArrayList<Line>();
    ArrayList<Staff> staffsLeader = new ArrayList<Staff>();
    ArrayList<Staff> staffsQC = new ArrayList<Staff>();;

    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        deleteButton();

        updateTime();

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

        //Gọi API
        gson = gsonBuilder.create();

        String URLPLAN = "http://115.73.220.127:89/api/Plans?lineId=d33c7593-4cfe-4ea2-a675-e8d049dc805f";

        RequestQueue requestQueuePlan = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequestPlan = new JsonArrayRequest(
                Request.Method.GET,
                URLPLAN,
                null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String jsonResponse = response.toString();

                Moshi moshi = new Moshi.Builder().build();

                Type type = Types.newParameterizedType(List.class, Plan.class);

                JsonAdapter<ArrayList<Plan>> adapter = moshi.adapter(type);

                try {
                    plans = adapter.fromJson(jsonResponse);

                    getLine();
                    updateUI();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueuePlan.add(jsonArrayRequestPlan);

    }

    public void getLine(){
            String URLLINE = "http://115.73.220.127:89/api/Lines?Id="+plans.get(0).getLineId();
            RequestQueue requestQueueLine = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequestLine = new JsonObjectRequest(
                    Request.Method.GET,
                    URLLINE,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("ok", "ok");

                            String jsonResponse = response.toString();
                            Line line = new Gson().fromJson(jsonResponse, Line.class);
                            /*String jsonResponse = response.toString();
                            Line line = gson.fromJson(jsonResponse, Line.class);*/

                                /*Moshi moshi = new Moshi.Builder().build();

                                Type type = Types.newParameterizedType(Object.class, Line.class);

                                JsonAdapter<Line> adapter = moshi.adapter(type);

                                lines.add(adapter.fromJson(jsonResponse));*/
                            lines.add(line);
                            getStaff();
                            updateUI();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            requestQueueLine.add(jsonObjectRequestLine);
    }
    public void getStaff(){

        if(lines!=null) {

            //Get LEADER
            String URLSTAFFS = "http://115.73.220.127:89/api/Staffs?Id=" + lines.get(0).getLeaderId();

            RequestQueue requestQueueStaffs = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequestStaffs = new JsonObjectRequest(
                    Request.Method.GET,
                    URLSTAFFS,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("staffsLeader", staffsLeader+"");

                            String jsonResponse = response.toString();
                            Staff staff = new Gson().fromJson(jsonResponse, Staff.class);
                            /*String jsonResponse = response.toString();
                            Line line = gson.fromJson(jsonResponse, Line.class);*/

                                /*Moshi moshi = new Moshi.Builder().build();

                                Type type = Types.newParameterizedType(Object.class, Line.class);

                                JsonAdapter<Line> adapter = moshi.adapter(type);

                                lines.add(adapter.fromJson(jsonResponse));*/
                            staffsLeader.add(staff);

                            updateUILeader();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueueStaffs.add(jsonObjectRequestStaffs);

            //Get QC
            String URLSTAFFSQC = "http://115.73.220.127:89/api/Staffs?Id=" + lines.get(0).getQCLeaderId();

            RequestQueue requestQueueStaffsQC = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequestStaffsQC = new JsonObjectRequest(
                    Request.Method.GET,
                    URLSTAFFS,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String jsonResponse = response.toString();
                            Staff staff = new Gson().fromJson(jsonResponse, Staff.class);
                            /*String jsonResponse = response.toString();
                            Line line = gson.fromJson(jsonResponse, Line.class);*/

                                /*Moshi moshi = new Moshi.Builder().build();

                                Type type = Types.newParameterizedType(Object.class, Line.class);

                                JsonAdapter<Line> adapter = moshi.adapter(type);

                                lines.add(adapter.fromJson(jsonResponse));*/
                            staffsQC.add(staff);

                            Log.d("staffsQC", staffsQC+"");

                            updateUILeaderQC();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueueStaffsQC.add(jsonObjectRequestStaffsQC);
        }
    }

    private void updateTime() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //Calendar calendar = Calendar.getInstance();
                        String currentTime = time.format(Calendar.getInstance().getTime());
                        String currentDay = day.format(Calendar.getInstance().getTime());
                        txtTime.setText(currentTime+"");
                        txtNgay.setText(currentDay+"");
                        updateTime();
                    }
                }, 300);
    }

    public void updateUI(){
        txtChuyen.setText(plans.get(0).getLineName()+"");
        txtLDVang.setText(plans.get(0).getTotalStaffOff()+"");
        txtLDHienDien.setText(plans.get(0).getTotalStaff()+"");
        txtMaHang.setText(plans.get(0).getArticalName()+"-"+plans.get(0).getArticalNumber());
        txtMucTieu.setText(plans.get(0).getPlanQuantity()+"");
        txtMau.setText(plans.get(0).getColor()+"");

    }

    public void updateUILeader(){

        txtToTruong.setText(staffsLeader.get(0).getFullName()+"");
    }
    public void updateUILeaderQC(){
        txtQC.setText(staffsQC.get(0).getFullName()+"");
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
