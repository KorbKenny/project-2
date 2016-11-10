package com.korbkenny.doodle_1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.korbkenny.doodle_1.Database.ShopSQLHelper;
import com.korbkenny.doodle_1.Singletons.SingletonCart;
import com.korbkenny.doodle_1.Singletons.SingletonCurrentCash;
import com.korbkenny.doodle_1.Singletons.SingletonIcons;
import com.korbkenny.doodle_1.Singletons.SingletonPictures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KorbBookProReturns on 11/7/16.
 */

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    List<ShopItem> mCartItemList;
    TextView mTextTotal, mTextCurrent, mTextRemaining;
    int mCashTotal, mCashRemaining;
    ArrayList<Integer> mIcons;

    public CartAdapter(List<ShopItem> itemList, TextView total, TextView current, TextView remaining, int cashTotal){
        mCartItemList = itemList;
        mTextTotal = total;
        mTextCurrent = current;
        mTextRemaining = remaining;
        mCashTotal = cashTotal;
        mIcons = SingletonIcons.getInstance().getIcons();
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CartViewHolder(inflater.inflate(R.layout.shop_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {

        holder.mIcon.setImageResource(mIcons.get(mCartItemList.get(position).getIconId()));
        holder.mName.setText(mCartItemList.get(position).getName());
        holder.mPrice.setText(String.valueOf(mCartItemList.get(position).getPrice()));


        ///////////////
        //REMOVE AN ITEM FROM LIST
        ///////////////

        holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                int currentCash = SingletonCurrentCash.getInstance().getCash();
                mCashTotal -= mCartItemList.get(position).getPrice();
                mCashRemaining = currentCash - mCashTotal;

                mTextCurrent.setText("Current Cash: " + currentCash);
                mTextTotal.setText("Total: " + mCashTotal);
                mTextRemaining.setText("Remaining: " + mCashRemaining);
                mCartItemList.remove(position);
                SingletonCart.getInstance().getTotal(mCartItemList);
                SingletonCurrentCash.getInstance().getCash();
                notifyDataSetChanged();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCartItemList.size();
    }

    public void replaceData(List<ShopItem> newList){
        mCartItemList = newList;
        notifyDataSetChanged();
    }
}


