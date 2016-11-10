package com.korbkenny.doodle_1.Singletons;

import com.korbkenny.doodle_1.R;

import java.util.ArrayList;

/**
 * Created by KorbBookProReturns on 11/10/16.
 */

public class SingletonIcons {
    private static SingletonIcons iInstance;
    private ArrayList<Integer> mIcons;

    public static SingletonIcons getInstance(){
        if(iInstance == null){
            iInstance = new SingletonIcons();
        }
        return iInstance;
    }

    private SingletonIcons(){
        mIcons = new ArrayList<>();

        mIcons.add(R.drawable.icon1emo);
        mIcons.add(R.drawable.icon2long);
        mIcons.add(R.drawable.icon3ponytail);
        mIcons.add(R.drawable.icon4regularhair);
        mIcons.add(R.drawable.icon5truckergreen);
        mIcons.add(R.drawable.icon6truckerred);
        mIcons.add(R.drawable.icon7skullblack);
        mIcons.add(R.drawable.icon8skullpink);
        mIcons.add(R.drawable.icon9santa);
        mIcons.add(R.drawable.icon10viking);
        mIcons.add(R.drawable.icon11strongarm);
        mIcons.add(R.drawable.icon12sword);
        mIcons.add(R.drawable.icon13bloodsword);
        mIcons.add(R.drawable.icon14magicsword);
        mIcons.add(R.drawable.icon15spag);
        mIcons.add(R.drawable.icon16whip);
        mIcons.add(R.drawable.icon17kelp);
        mIcons.add(R.drawable.icon18converse);
        mIcons.add(R.drawable.icon19conversepink);
        mIcons.add(R.drawable.icon20stripesoxgrn);
        mIcons.add(R.drawable.icon21stripesoxred);
        mIcons.add(R.drawable.icon22stripesoxblue);
        mIcons.add(R.drawable.icon23highred);
        mIcons.add(R.drawable.icon24highblack);
        mIcons.add(R.drawable.icon25humanfeet);
        mIcons.add(R.drawable.icon26navi);
        mIcons.add(R.drawable.icon27birdbrown);
        mIcons.add(R.drawable.icon28cardinal);
        mIcons.add(R.drawable.icon29halo);
    }

    public ArrayList<Integer> getIcons(){
        return mIcons;
    }

}
