package com.constore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.constore.R;
import com.constore.adapter.GridviewAdapter;
import com.constore.customfonts.MyTextView;
import com.constore.layout.ExpandableHeightGridView;
import com.constore.model.bean.Beanclass;
import com.constore.view.activity.CategoryActivity;
import com.constore.view.activity.HomeActivity;
import com.constore.view.activity.ProductDetailActivity;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {


    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;
    private View view;

    private int[] IMAGEgrid = {R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w1,};
    private String[] TITLeGgrid = {"Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ", "Đồng Hồ"};
    private String[] DIscriptiongrid = {"Min 70% off", "Min 50% off", "Min 45% off", "Min 60% off", "Min 70% off", "Min 50% off"};
    private String[] Dategrid = {"1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ", "1,000,000đ"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttab1, container, false);

        gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
        beanclassArrayList = new ArrayList<Beanclass>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }
        gridviewAdapter = new GridviewAdapter(getActivity(), beanclassArrayList);
        gridview.setExpanded(true);

        gridview.setAdapter(gridviewAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra(HomeActivity.BEAN_EXTRA, beanclassArrayList.get(position));
                startActivity(intent);
            }
        });

        return view;

    }
}