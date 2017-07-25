package com.epson.moverio.moveriodemo;

import android.support.v4.view.ViewPager;
import android.view.View;

public class CustomPageTransformer implements ViewPager.PageTransformer {

    //Transformer that allows custom animation to views during transitions
    @Override
    public void transformPage(View page, float position) {

        int pagePosition = (int) page.getTag();

        int pageWidth = page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        if (position != 0.0f && (position >= -1.0f && position <= 1.0f)) {
            View image = page.findViewById(R.id.fullImage);
            if (image != null) {
                image.setTranslationX(pageWidthTimesPosition * 1.5f);
            }
        }
    }

}