package com.korbkenny.doodle_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.korbkenny.doodle_1.Database.ShopSQLHelper;
import com.korbkenny.doodle_1.Singletons.SingletonCart;
import com.korbkenny.doodle_1.Singletons.SingletonCurrentCash;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView mCash1, mCash2, mCash3;
    ImageView mCheckout;
    RecyclerView mRecyclerView;
    CartAdapter mCartAdapter;
    ArrayList<ShopItem> mShopItems;
    int mTotal, mCurrent, mDifference;
    String current, total, difference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ///////////////
        //SETUP
        ///////////////

        mShopItems = SingletonCart.getInstance().getItemsInCart();

        mCash1 = (TextView) findViewById(R.id.cart_current_cash);
        mCash2 = (TextView) findViewById(R.id.cart_total);
        mCash3 = (TextView) findViewById(R.id.cart_remaining);

        for (int i = 0; i < mShopItems.size(); i++) {
            mTotal += mShopItems.get(i).getPrice();
        }


        ///////////////
        //CURRENT, TOTAL, REMAINING CASH
        ///////////////

        mCurrent = SingletonCurrentCash.getInstance().getCash();
        mDifference = mCurrent - mTotal;

        current = Integer.toString(mCurrent);
        total = Integer.toString(mTotal);
        difference = Integer.toString(mDifference);

        mCash1.setText("Current Cash: " + current);
        mCash2.setText("Total: " + total);
        mCash3.setText("Remaining: " + difference);


        ///////////////
        //RECYCLERVIEW + ADAPTER
        ///////////////

        mRecyclerView = (RecyclerView) findViewById(R.id.cart_recyclerview);
        mCartAdapter = new CartAdapter(mShopItems, mCash2, mCash1, mCash3, mTotal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mCartAdapter);


        ///////////////
        //TO CHECK-OUT
        //////////////

        mCheckout = (ImageView) findViewById(R.id.cart_checkout_button);

        mCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrent - mCartAdapter.mCashTotal < 0) {
                    Toast.makeText(CartActivity.this, "Not enough money!", Toast.LENGTH_SHORT).show();
                } else if (mCartAdapter.mCashTotal == 0) {
                    Toast.makeText(CartActivity.this, "Nothing in cart.", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mShopItems.size(); i++) {
                        mShopItems.get(i).setBought(1);
                    }
                    for (ShopItem item:mShopItems) {
                        ShopSQLHelper.getInstance(CartActivity.this).changeToBought(item);
                    }
                    SingletonCurrentCash.getInstance().spendCash(mCartAdapter.mCashTotal);
                    SingletonCart.getInstance().removeAllFromCart();
                    Toast.makeText(CartActivity.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("key","key");
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }

            }
        });
    }
}


