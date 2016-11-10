package com.korbkenny.doodle_1.Singletons;

import com.korbkenny.doodle_1.R;

import java.util.ArrayList;

/**
 * Created by KorbBookProReturns on 11/2/16.
 */

public class SingletonPictures {
    private static SingletonPictures mInstance;
    private ArrayList<Integer> mUpPics;
    private ArrayList<Integer> mDownPics;

    public static SingletonPictures getInstance(){
        if(mInstance == null){
            mInstance = new SingletonPictures();
        }
        return mInstance;
    }

    private SingletonPictures() {
        mUpPics = new ArrayList<>();
        mDownPics = new ArrayList<>();

        mUpPics.add(R.drawable.up1emo);
        mUpPics.add(R.drawable.up2longhair);
        mUpPics.add(R.drawable.up3ponytail);
        mUpPics.add(R.drawable.up4regularhair);
        mUpPics.add(R.drawable.up5truckergreen);
        mUpPics.add(R.drawable.up6truckerred);
        mUpPics.add(R.drawable.up7skullblack);
        mUpPics.add(R.drawable.up8skullpink);
        mUpPics.add(R.drawable.up9santa);
        mUpPics.add(R.drawable.up10viking);
        mUpPics.add(R.drawable.up11strongarm);
        mUpPics.add(R.drawable.up12sword);
        mUpPics.add(R.drawable.up13bloodsword);
        mUpPics.add(R.drawable.up14magicsword);
        mUpPics.add(R.drawable.up15spagetti);
        mUpPics.add(R.drawable.up16whip);
        mUpPics.add(R.drawable.up17kelp);
        mUpPics.add(R.drawable.up18converseblack);
        mUpPics.add(R.drawable.up19conversepink);
        mUpPics.add(R.drawable.up20soxgreen);
        mUpPics.add(R.drawable.up21soxred);
        mUpPics.add(R.drawable.up22soxblue);
        mUpPics.add(R.drawable.up23highred);
        mUpPics.add(R.drawable.up24highblack);
        mUpPics.add(R.drawable.up25humanfoot);
        mUpPics.add(R.drawable.up26navi);
        mUpPics.add(R.drawable.up27birdbrown);
        mUpPics.add(R.drawable.up28cardinal);
        mUpPics.add(R.drawable.up29halo);

        mDownPics.add(R.drawable.down1emo);
        mDownPics.add(R.drawable.down2longhair);
        mDownPics.add(R.drawable.down3ponytail);
        mDownPics.add(R.drawable.down4regularhair);
        mDownPics.add(R.drawable.down5truckergreen);
        mDownPics.add(R.drawable.down6truckerred);
        mDownPics.add(R.drawable.down7skullblack);
        mDownPics.add(R.drawable.down8skullpink);
        mDownPics.add(R.drawable.down9santa);
        mDownPics.add(R.drawable.down10viking);
        mDownPics.add(R.drawable.down11strongarm);
        mDownPics.add(R.drawable.down12sword);
        mDownPics.add(R.drawable.down13bloodsword);
        mDownPics.add(R.drawable.down14magicsword);
        mDownPics.add(R.drawable.down15spagetti);
        mDownPics.add(R.drawable.down16whip);
        mDownPics.add(R.drawable.down17kelp);
        mDownPics.add(R.drawable.down18converseblack);
        mDownPics.add(R.drawable.down19conversepink);
        mDownPics.add(R.drawable.down20soxgreen);
        mDownPics.add(R.drawable.down21soxred);
        mDownPics.add(R.drawable.down22soxblue);
        mDownPics.add(R.drawable.down23highred);
        mDownPics.add(R.drawable.down24highblack);
        mDownPics.add(R.drawable.down25humanfoot);
        mDownPics.add(R.drawable.down26navi);
        mDownPics.add(R.drawable.down27birdbrown);
        mDownPics.add(R.drawable.down28cardinal);
        mDownPics.add(R.drawable.down29halo);
    }

    public ArrayList<Integer> getUpPics(){
        return mUpPics;
    }

    public ArrayList<Integer> getDownPics(){
        return mDownPics;
    }



}
