package com.korbkenny.doodle_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.korbkenny.doodle_1.Database.ShopSQLHelper;

import java.util.List;

public class EquipActivity extends AppCompatActivity {

    Button mSave;
    RecyclerView mRecyclerView;
    EquipAdapter mEquipAdapter;
    List<ShopItem> mShopItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        //////////
        //SETUP
        //////////
        getIntent();
        mSave = (Button) findViewById(R.id.ButtonSave);
        mShopItemList = ShopSQLHelper.getInstance(this).getBought();

        //////////
        //RECYCLER VIEW
        //////////
        mEquipAdapter = new EquipAdapter(mShopItemList);
        mRecyclerView = (RecyclerView) findViewById(R.id.equipRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mEquipAdapter);

        //////////
        // SAVE
        //////////
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
