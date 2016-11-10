package com.korbkenny.doodle_1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.korbkenny.doodle_1.Database.DBAssetHelper;
import com.korbkenny.doodle_1.Database.ShopSQLHelper;
import com.korbkenny.doodle_1.Singletons.SingletonCurrentCash;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    final int REQUEST_CODE = 12345;

    ImageView mCartButton;
    TextView mCurrentCash;
    RecyclerView mRecyclerView;
    ShopAdapter mShopAdapter;
    List<ShopItem> mShopItemList;
    String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //////////////////
        // DATABASE SETUP
        //////////////////
        DBAssetHelper dbSetup = new DBAssetHelper(ShopActivity.this);
        dbSetup.getReadableDatabase();


        ///////////////
        //   SETUP
        ///////////////
        mShopItemList = ShopSQLHelper.getInstance(this).getAllAsList();
        mCurrentCash = (TextView)findViewById(R.id.shopMoney);
        mCurrentCash.setText(Integer.toString(SingletonCurrentCash.getInstance().getCash()));
        mCartButton = (ImageView)findViewById(R.id.checkoutbutton);


        //////////////////
        //  BUTTON TO CART
        //////////////////
        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this,CartActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });


        /////////////////
        // RECYCLER VIEW
        /////////////////
        mShopAdapter = new ShopAdapter(mShopItemList);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShopAdapter);
    }


    //////////////////////////////////////////////
    // REMOVES ITEMS FROM LIST WHEN THEY'RE BOUGHT
    //////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String resultData = data.getStringExtra("key");
                if (resultData.equals("key")) {
                    mShopItemList = ShopSQLHelper.getInstance(ShopActivity.this).getAllAsList();
                    mShopAdapter.replaceData(mShopItemList);
                }
            }
        }
    }

    ///////////////
    // SEARCH BAR
    ///////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this,ShopActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        //REPOPULATES DATA WHEN YOU EXIT THE SEARCH
        final ImageView searchExit = (ImageView) searchView.findViewById(R.id.search_close_btn);
        searchExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)findViewById(R.id.search_src_text);
                searchView.onActionViewCollapsed();
                mShopAdapter.replaceData(mShopItemList);
            }
        });
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mQuery = intent.getStringExtra(SearchManager.QUERY);
            List<ShopItem> searchList = ShopSQLHelper.getInstance(this).searchQuery(mQuery);
            if (intent.getAction().equals("")) {
                mShopAdapter.replaceData(mShopItemList);
            } else {
                mShopAdapter.replaceData(searchList);
            }
        }
    }

    //////////////////////////////
    // REFRESHES $$ AFTER BOUGHT
    //////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        mCurrentCash = (TextView)findViewById(R.id.shopMoney);
        mCurrentCash.setText(Integer.toString(SingletonCurrentCash.getInstance().getCash()));
    }



}
