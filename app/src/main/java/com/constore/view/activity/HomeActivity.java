package com.constore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.constore.R;
import com.constore.adapter.GridviewAdapter;
import com.constore.layout.ChildAnimationExample;
import com.constore.layout.ExpandableHeightGridView;
import com.constore.layout.SliderLayout;
import com.constore.model.bean.Beanclass;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, Serializable, SearchView.OnQueryTextListener {

    public static final String BEAN_EXTRA = "BeanClass";
    private static final String TAG = HomeActivity.class.getSimpleName();
    BottomNavigationView bottomNavigation;
    SliderLayout mDemoSlider;
    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;
    private int[] IMAGEgrid = {R.drawable.grooming3, R.drawable.grooming6, R.drawable.grooming7, R.drawable.grooming1, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w1};
    private String[] TITLeGgrid = {"Dao cạo râu gỗ", "Cốc sứ", "Túi du lịch", "Nước hoa", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ"};
    private String[] DIscriptiongrid = {"Min 70% off", "Min 50% off", "Min 45% off", "Min 60% off", "Min 70% off", "Min 50% off", "Min 45% off", "Min 60% off", "Min 70% off", "Min 50% off"};
    private String[] Dategrid = {"200,000đ", "350,000đ", "200,000đ", "1,500,000đ", "1,000,000Đ", "350,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ"};

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

//        ********GRIDVIEW***********

        gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);
        beanclassArrayList = new ArrayList<Beanclass>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }
        gridviewAdapter = new GridviewAdapter(HomeActivity.this, beanclassArrayList);
        gridview.setExpanded(true);

        gridview.setAdapter(gridviewAdapter);
        gridview.setFocusable(false);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.drawable.groomingslider1);
        file_maps.put("2", R.drawable.groomingslider2);
        file_maps.put("3", R.drawable.groomingslider3);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    //  .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setVisibility(View.VISIBLE);

//        Open ProductDetailActivity
//        Created by NQManh - 23/12/2108
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), "Click" + position, Toast.LENGTH_LONG).show()


                showProduct(beanclassArrayList.get(position));

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            int pid = android.os.Process.myPid();
            //android.os.Process.killProcess(pid);
            //this.finish();
            System.exit(0);
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Ấn lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void showProduct(Beanclass bean) {
        Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
        intent.putExtra(BEAN_EXTRA, (Serializable) bean);
        // this.startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_titlebar, menu);
        menu.findItem(R.id.button_search).getIcon()
                .setTint(getResources().getColor(R.color.colorWhite));


        MenuItem btnSearch = menu.findItem(R.id.button_search);
        SearchView searchView = (SearchView) btnSearch.getActionView();
        searchView.setOnQueryTextListener(this);


        MenuItemCompat.setOnActionExpandListener(btnSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //Toast.makeText(getBaseContext(), "onMenuItemActionExpand called", Toast.LENGTH_SHORT).show();
                mDemoSlider.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //Toast.makeText(getBaseContext(), "onMenutItemActionCollapse called", Toast.LENGTH_SHORT).show();
                mDemoSlider.setVisibility(View.VISIBLE);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }


    private void initListeners() {
        bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_notification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.button_category:
                startActivity(new Intent(this, CategoryActivity.class));
                break;
            case R.id.button_rate:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.button_contact:
                startActivity(new Intent(this, ContactActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //Toast.makeText(this.getBaseContext(), "Empty", Toast.LENGTH_LONG).show();
            beanclassArrayList.clear();
            for (int i = 0; i < IMAGEgrid.length; i++) {

                Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);

                beanclassArrayList.add(beanclass);

            }
            gridviewAdapter = new GridviewAdapter(HomeActivity.this, beanclassArrayList);
            gridview.setExpanded(true);

            gridview.setAdapter(gridviewAdapter);
        } else {
            // Hiển thị dữ liệu tìm thấy
            beanclassArrayList.clear();
            for (int i = 0; i < IMAGEgrid.length; i++) {

                Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);

                if (HomeActivity.covertStringToURL(TITLeGgrid[i]).toLowerCase().contains(HomeActivity.covertStringToURL(newText).toLowerCase()) == true) {
                    beanclassArrayList.add(beanclass);
                }


                gridviewAdapter = new GridviewAdapter(HomeActivity.this, beanclassArrayList);
                gridview.setExpanded(true);

                gridview.setAdapter(gridviewAdapter);
            }
        }
        return true;
    }

    public static String covertStringToURL(String str) {
        try {
            String temp = Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
