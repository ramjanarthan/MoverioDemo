package com.epson.moverio.moveriodemo;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by ijp_mbp on 23/6/17.
 */
public class CustomPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {

        int pagePosition = (int) page.getTag();

        int pageWidth = page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        if (position <= -1.0f || position >= 1.0f) {
            //Page is not visible
        } else if (position == 0.0f) {
            //Page is being viewed
        } else {

            View image = page.findViewById(R.id.visualAid);

            if (image != null) {

                View title = page.findViewById(R.id.header);
                title.setTranslationX(-pageWidthTimesPosition / 3f);

                image.setAlpha(1.0f - absPosition);
                image.setTranslationX(-pageWidthTimesPosition * 1.5f);
            }

            View description = page.findViewById(R.id.info);

            if (description != null ) {
                description.setTranslationX(-pageWidthTimesPosition / 3f);
                description.setAlpha(1.0f - absPosition);
            }

            if (position < 0) {
                // Create your out animation here
            } else {
                // Create your in animation here
            }
        }
    }

}