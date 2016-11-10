package com.korbkenny.doodle_1.Singletons;

/**
 * Created by KorbBookProReturns on 11/7/16.
 */

public class SingletonCurrentCash {
    private static SingletonCurrentCash cCashInstance;
    private int mCash;

    public static SingletonCurrentCash getInstance(){
        if(cCashInstance == null){
            cCashInstance = new SingletonCurrentCash();
        }
        return cCashInstance;
    }

    private SingletonCurrentCash(){
        mCash = 40;
    }

    public int getCash(){
        return mCash;
    }

    public void restartCash(){mCash = 40;}

    //WILL BE USED FOR BATTLE MODE
    public void earnCash(int earned){
        mCash += earned;
    }

    public void spendCash(int spent){
        mCash -= spent;
    }

}
