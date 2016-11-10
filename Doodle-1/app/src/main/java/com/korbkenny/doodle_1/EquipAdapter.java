package com.korbkenny.doodle_1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.korbkenny.doodle_1.Singletons.SingletonEquip;
import com.korbkenny.doodle_1.Singletons.SingletonIcons;
import com.korbkenny.doodle_1.Singletons.SingletonPictures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KorbBookProReturns on 11/9/16.
 */

public class EquipAdapter extends RecyclerView.Adapter<EquipViewHolder> {
    List<ShopItem> mShopItemList;
    ArrayList<Integer> mIcons;

    public EquipAdapter(List<ShopItem> shopItemList) {
        mShopItemList = shopItemList;
        mIcons = SingletonIcons.getInstance().getIcons();
    }


    @Override
    public EquipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EquipViewHolder(inflater.inflate(R.layout.equip_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(EquipViewHolder holder, final int position) {

        holder.mImage.setImageResource(mIcons.get(mShopItemList.get(position).getIconId()));
        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonEquip.getInstance().equipSomething(mShopItemList.get(position));
                Toast.makeText(view.getContext(), "Equipped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShopItemList.size();
    }
}
