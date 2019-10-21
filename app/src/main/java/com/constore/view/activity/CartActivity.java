package com.constore.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.constore.R;
import com.constore.adapter.RecyclerViewAdapter;
import com.constore.customfonts.MyTextView;
import com.constore.model.bean.Beanclass;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ArrayList<Beanclass> beanclassArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;

    private int[] IMAGEgrid = {R.drawable.grooming3, R.drawable.grooming6, R.drawable.grooming7, R.drawable.grooming1};
    private String[] TITLeGgrid = {"Dao cạo râu gỗ","Cốc sứ", "Túi du lịch", "Nước hoa"};
    private String[] DIscriptiongrid = {"Min 70% off", "Min 50% off", "Min 45% off", "Min 60% off"};
    private String[] Dategrid = {"200,000đ","350,000đ", "200,000đ", "1,500,000đ"};
    MyTextView buynow;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        buynow = (MyTextView) findViewById(R.id.clear_cart);
        total = (TextView) findViewById(R.id.total);


        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanclassArrayList.size() != 0) {
                    openBuyNowActivity(beanclassArrayList.get(0));

                } else {

                    Toast.makeText(v.getContext(), "Chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);
        beanclassArrayList = new ArrayList<Beanclass>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }
        if (beanclassArrayList.size() != 0) {
            total.setText(beanclassArrayList.get(0).getDate1());
        } else {
            total.setText("0đ");
        }

        //display list of product
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(CartActivity.this, beanclassArrayList, total);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    private void openBuyNowActivity(Beanclass product) {

        Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
        intent.putExtra(ProductDetailActivity.BEAN, product);
        this.startActivityForResult(intent, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // end the activity
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setPrice() {
        total = (TextView) findViewById(R.id.total);
        total.setText("0đ");
    }
}
