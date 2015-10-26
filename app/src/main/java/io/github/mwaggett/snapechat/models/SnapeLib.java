package io.github.mwaggett.snapechat;

import java.util.ArrayList;
import java.util.Random;

public class SnapeLib {
    private ArrayList<Integer> mSnapes;

    public SnapeLib() {
        mSnapes = new ArrayList<Integer>();
        mSnapes.add(R.drawable.snape1);
        mSnapes.add(R.drawable.snape2);
        mSnapes.add(R.drawable.snape3);
        mSnapes.add(R.drawable.snape4);
        mSnapes.add(R.drawable.snape5);
        mSnapes.add(R.drawable.snape6);
        mSnapes.add(R.drawable.snape7);
    }

    public ArrayList<Integer> getSnapes() {
        return mSnapes;
    }

    public int nextSnape(int currentSnape) {
        int index = mSnapes.indexOf(currentSnape);
        if (index == mSnapes.size()-1) {
            return mSnapes.get(0);
        } else {
            return mSnapes.get(index+1);
        }
    }

    public int previousSnape(int currentSnape) {
        int index = mSnapes.indexOf(currentSnape);
        if (index == 0) {
            return mSnapes.get(mSnapes.size()-1);
        } else {
            return mSnapes.get(index-1);
        }
    }

    public int randomSnape() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mSnapes.size());
        return mSnapes.get(index);
    }
}
