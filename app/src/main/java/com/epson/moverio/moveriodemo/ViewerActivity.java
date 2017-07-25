package com.epson.moverio.moveriodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int layouts;
    private Button btnPrev, btnNext, btnHome, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.container);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnHome = (Button) findViewById(R.id.btn_home);
        btnMenu = (Button) findViewById(R.id.btn_menu);
        btnPrev = (Button) findViewById(R.id.btn_prev);

        btnPrev.setVisibility(View.INVISIBLE);

        // Set an Adapter on the ViewPager
        IntroAdapter introAdapter = new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(introAdapter);
        layouts = introAdapter.getCount();
        // adding bottom dots
        addBottomDots(0);


        // Set a PageTransformer
        viewPager.setPageTransformer(false, new CustomPageTransformer());

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnPrev.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                        btnPrev.setText(getString(R.string.btnPrevious));
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        btnPrev.setText("");
                        break;
                }
                return false;
            }
        });

        btnNext.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                        btnNext.setText(getString(R.string.btnNext));
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        btnNext.setText("");
                        break;
                }
                return false;
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMenuScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchMenuScreen();
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int current = getItem(-1);
                if(current >= 0){
                    viewPager.setCurrentItem(current);
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(".");
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_inactive_screen));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(ContextCompat.getColor(this, R.color.dot_active_screen));
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchMenuScreen() {
        finish();
    }

    private void launchHomeScreen() {
        setResult(RESULT_OK, null);
        finish();
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position != 0) {
                btnPrev.setVisibility(View.VISIBLE);
            } else {
                btnPrev.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * View pager adapter
     */

    private class IntroAdapter extends FragmentPagerAdapter {

        public IntroAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewerFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return ViewerFragment.getCount();
        }

    }
}