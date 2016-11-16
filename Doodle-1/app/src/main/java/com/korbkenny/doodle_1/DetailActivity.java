package com.korbkenny.doodle_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.korbkenny.doodle_1.Database.ShopSQLHelper;
import com.korbkenny.doodle_1.Singletons.SingletonCart;
import com.korbkenny.doodle_1.Singletons.SingletonIcons;
import com.korbkenny.doodle_1.Singletons.SingletonPictures;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String ID_KEY = "id_key";
    private TextView mTitle, mType, mColor, mDescription, mPrice;
    private ImageView mIcon, mAddToCart;
    private ArrayList<ShopItem> mItemsInCart;
    private boolean isInCart;
    AsyncTask<Void,Void,Void> mAsyncTask;
    ShopItem mTheItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ///////////////
        //   SETUP
        ///////////////

        mTitle = (TextView)findViewById(R.id.detail_title);
        mType = (TextView)findViewById(R.id.detail_type);
        mColor = (TextView)findViewById(R.id.detail_color);
        mDescription = (TextView)findViewById(R.id.detail_description);
        mPrice = (TextView)findViewById(R.id.detail_price);
        mIcon = (ImageView)findViewById(R.id.detail_image);
        mAddToCart = (ImageView) findViewById(R.id.detail_button);

        final int id = getIntent().getIntExtra(ID_KEY,-1);
        if(id == -1){finish();}

        mAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mTheItem = ShopSQLHelper
                        .getInstance(DetailActivity.this)
                        .getItemByID(id);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(mTheItem == null){finish();}

                String pricePlus = "Price: ";
                String typePlus = "Type: ";
                String colorPlus = "Color: ";
                mTitle.setText(mTheItem.getName());
                mType.setText(typePlus + mTheItem.getType());
                mColor.setText(colorPlus + mTheItem.getColor());
                mDescription.setText(mTheItem.getDescription());
                mPrice.setText(pricePlus + mTheItem.getPrice());

                ArrayList<Integer> icons = SingletonIcons.getInstance().getIcons();
                mIcon.setImageResource(icons.get(id-1));
            }
        };
        mAsyncTask.execute();

        ///////////////
        // ADD TO CART
        ///////////////

        mItemsInCart = SingletonCart.getInstance().getItemsInCart();

        for (ShopItem item:mItemsInCart) {
            if(item.getID()==mTheItem.getID()){
                isInCart = true;
            }
            else {
                isInCart = false;
            }
        }

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInCart) {
                    Toast.makeText(DetailActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    SingletonCart.getInstance().addToCart(mTheItem);
                    Toast.makeText(DetailActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
