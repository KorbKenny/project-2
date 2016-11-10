package com.korbkenny.doodle_1.Singletons;

import android.widget.Toast;

import com.korbkenny.doodle_1.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KorbBookProReturns on 11/7/16.
 */
public class SingletonCart {
    private static SingletonCart cartInstance;
    private ArrayList<ShopItem> mItemsInCart;

    public static SingletonCart getInstance() {
        if(cartInstance == null){
            cartInstance = new SingletonCart();
        }
        return cartInstance;
    }

    private SingletonCart() {
        mItemsInCart = new ArrayList<>();
    }

    public void addToCart(ShopItem itemToAdd) {
        if (!mItemsInCart.contains(itemToAdd)) {
            mItemsInCart.add(itemToAdd);
        }
    }

    public int getTotal(List<ShopItem> list){
        int total = 0;
        for (ShopItem item:list
             ) {
            total += item.getPrice();
        }
        return total;
    }

    public void removeAllFromCart(){
       mItemsInCart.clear();
    }

    public ArrayList<ShopItem> getItemsInCart(){
        return mItemsInCart;
    }
}
