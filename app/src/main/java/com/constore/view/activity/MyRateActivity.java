package com.constore.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.constore.R;
import com.constore.adapter.RateRecycleViewAdapter;
import com.constore.model.bean.Beanclass;

import java.util.ArrayList;

public class MyRateActivity extends AppCompatActivity {

    private ArrayList<Beanclass> beanclassArrayList;
    private RateRecycleViewAdapter recyclerViewAdapter;

    private int[] IMAGEgrid = {R.drawable.grooming7, R.drawable.grooming3};
    private String[] TITLeGgrid = {"Túi du lịch", "Dao cạo râu gỗ"};
    private String[] DIscriptiongrid = {"Min 30% off", "Min 70% off"};
    private String[] Dategrid = {"200,000đ", "200,000đ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rate);
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

        //display list of product
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RateRecycleViewAdapter(MyRateActivity.this, beanclassArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


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
}
