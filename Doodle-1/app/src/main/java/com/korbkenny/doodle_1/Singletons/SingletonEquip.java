package com.korbkenny.doodle_1.Singletons;

import android.widget.Switch;

import com.korbkenny.doodle_1.ShopItem;

import java.util.ArrayList;

/**
 * Created by KorbBookProReturns on 11/9/16.
 */

public class SingletonEquip {
    private static SingletonEquip eInstance;
    private ArrayList<Integer> eHair = new ArrayList<>();
    private ArrayList<Integer> eHat = new ArrayList<>();
    private ArrayList<Integer> eWeapon = new ArrayList<>();
    private ArrayList<Integer> eShoes = new ArrayList<>();
    private ArrayList<Integer> eElemental = new ArrayList<>();

    public static SingletonEquip getInstance(){
        if(eInstance == null){
            eInstance = new SingletonEquip();
        }
        return eInstance;
    }

    public ArrayList<Integer> getHair() {
        return eHair;
    }

    public ArrayList<Integer> getHat() {
        return eHat;
    }

    public ArrayList<Integer> getWeapon() {
        return eWeapon;
    }

    public ArrayList<Integer> getShoes() {
        return eShoes;
    }

    public ArrayList<Integer> getElemental() {
        return eElemental;
    }

    public void equipSomething(ShopItem item) {
        ArrayList<Integer> upPics = SingletonPictures.getInstance().getUpPics();
        ArrayList<Integer> downPics = SingletonPictures.getInstance().getDownPics();
        String type = item.getType();

        switch (type) {
            case "Hair":
                eHair.clear();
                eHair.add(upPics.get(item.getIconId()));
                eHair.add(downPics.get(item.getIconId()));
                break;
            case "Hat":
                eHat.clear();
                eHat.add(upPics.get(item.getIconId()));
                eHat.add(downPics.get(item.getIconId()));
                break;
            case "Weapon":
                eWeapon.clear();
                eWeapon.add(upPics.get(item.getIconId()));
                eWeapon.add(downPics.get(item.getIconId()));
                break;
            case "Shoes":
            case "Socks":
                eShoes.clear();
                eShoes.add(upPics.get(item.getIconId()));
                eShoes.add(downPics.get(item.getIconId()));
                break;
            case "Elemental":
                eElemental.clear();
                eElemental.add(upPics.get(item.getIconId()));
                eElemental.add(downPics.get(item.getIconId()));
                break;
            default:break;
        }
    }
}
