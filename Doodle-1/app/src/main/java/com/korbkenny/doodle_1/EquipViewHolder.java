package com.korbkenny.doodle_1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by KorbBookProReturns on 11/9/16.
 */

public class EquipViewHolder extends RecyclerView.ViewHolder {
    ImageView mImage;

    public EquipViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView)itemView.findViewById(R.id.equipIcon);
    }
}
