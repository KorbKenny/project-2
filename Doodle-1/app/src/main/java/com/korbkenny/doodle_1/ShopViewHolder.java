package com.korbkenny.doodle_1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by KorbBookProReturns on 10/31/16.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder{
    ImageView mIcon, mMoney;
    TextView mName, mPrice;
    LinearLayout mLayout;

    public ShopViewHolder(View itemView) {
        super(itemView);

        mIcon = (ImageView)itemView.findViewById(R.id.vhIcon);
        mName = (TextView)itemView.findViewById(R.id.vhName);
        mPrice = (TextView)itemView.findViewById(R.id.vhPrice);
        mMoney = (ImageView)itemView.findViewById(R.id.vhMoneySign);

        mLayout = (LinearLayout)itemView.findViewById(R.id.clickable_item);


    }


}
