package com.korbkenny.doodle_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.korbkenny.doodle_1.Database.ShopSQLHelper;

import java.util.List;

public class EquipActivity extends AppCompatActivity {

    ImageView mSave;
    RecyclerView mRecyclerView;
    EquipAdapter mEquipAdapter;
    List<ShopItem> mShopItemList;
    AsyncTask<Void,Void,Void> mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        //////////
        //SETUP
        //////////
        getIntent();
        mSave = (ImageView) findViewById(R.id.ButtonSave);



        //////////
        //RECYCLER VIEW
        //////////

        mAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mShopItemList = ShopSQLHelper.getInstance(EquipActivity.this).getBought();
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                mEquipAdapter = new EquipAdapter(mShopItemList);
                mRecyclerView = (RecyclerView) findViewById(R.id.equipRecyclerView);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(EquipActivity.this, 3);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(mEquipAdapter);
            }
        };
        mAsyncTask.execute();

        //////////
        // SAVE
        //////////
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAsyncTask != null && mAsyncTask.getStatus() == AsyncTask.Status.RUNNING){
                    Toast.makeText(EquipActivity.this, "Hold up.", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });
    }
}
