package com.korbkenny.doodle_1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

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
    AsyncTask<Void,Void,Void> mAsyncSetup;
    AsyncTask<Void,Void,Void> mAsyncUpdate;
    AsyncTask<Void,Void,Void> mAsyncSearchUpdate;
    List<ShopItem> mSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ///////////////
        //   SETUP
        ///////////////
        mCurrentCash = (TextView) findViewById(R.id.shopMoney);
        mCurrentCash.setText(Integer.toString(SingletonCurrentCash.getInstance().getCash()));
        mCartButton = (ImageView) findViewById(R.id.checkoutbutton);


        /////////////////
        // RECYCLER VIEW
        /////////////////
        mAsyncSetup = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mShopItemList = ShopSQLHelper.getInstance(ShopActivity.this).getAllAsList();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                mShopAdapter = new ShopAdapter(mShopItemList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopActivity.this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mShopAdapter);
            }
        };
        mAsyncSetup.execute();


        //////////////////
        //  BUTTON TO CART
        //////////////////
        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAsyncSetup != null && mAsyncSetup.getStatus()== AsyncTask.Status.RUNNING){
                    Toast.makeText(ShopActivity.this, "Hold up.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(ShopActivity.this, CartActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });

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
                    mAsyncUpdate = new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            mShopItemList = ShopSQLHelper.getInstance(ShopActivity.this).getAllAsList();
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            mShopAdapter.replaceData(mShopItemList);
                        }
                    };
                    mAsyncUpdate.execute();
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
            mAsyncSearchUpdate = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    mSearchList = ShopSQLHelper.getInstance(ShopActivity.this).searchQuery(mQuery);
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid) {
                    mShopAdapter.replaceData(mSearchList);
                }
            };
            mAsyncSearchUpdate.execute();
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
