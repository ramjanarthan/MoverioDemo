package com.epson.moverio.moveriodemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by ijp_mbp on 23/6/17.
 */

public class TwoDFragment extends Fragment {

    private static final String PAGE = "page";

    private int mPage;

    public static TwoDFragment newInstance(int page) {
        TwoDFragment frag = new TwoDFragment();
        Bundle b = new Bundle();
        b.putInt(PAGE, page);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getArguments().containsKey(PAGE))
            throw new RuntimeException("Fragment must contain a \"" + PAGE + "\" argument!");
        mPage = getArguments().getInt(PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            // Select a layout based on the current page
            int layoutResId;
            View view;
            VideoView video;
            ImageView imageView;
            GlideDrawableImageViewTarget imageViewTarget;
            String uripath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.twod_football;
            Uri uri = Uri.parse(uripath);
            switch (mPage) {
                case 0:
                    layoutResId = R.layout.fragment_twod1;
                    view = setupView(container, layoutResId);
                    break;
                case 1:
                    layoutResId = R.layout.fragment_twod2;
                    view = setupView(container, layoutResId);
                    imageView = (ImageView) view.findViewById(R.id.fullImage);
                    imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                    Glide.with(this).load(R.drawable.twod_homescreen).into(imageViewTarget);
                    break;
                case 2:
                    layoutResId = R.layout.fragment_twod3;
                    view = setupView(container, layoutResId);
                    imageView = (ImageView) view.findViewById(R.id.fullImage);
                    imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                    Glide.with(this).load(R.drawable.twod_photos).into(imageViewTarget);
                    break;
                case 3:
                    layoutResId = R.layout.fragment_twod4;
                    view = setupView(container, layoutResId);
                    break;
                case 4:
                    layoutResId = R.layout.fragment_twod5;
                    view = setupView(container, layoutResId);

                    video = (VideoView) view.findViewById(R.id.movie);
                    video.setVideoURI(uri);
                    video.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
                    video.start();
                    break;
                case 5:
                    layoutResId = R.layout.fragment_twod6;
                    view = setupView(container, layoutResId);
                    break;
                case 6:
                    layoutResId = R.layout.fragment_twod7;
                    view = setupView(container, layoutResId);
                    imageView = (ImageView) view.findViewById(R.id.fullImage);
                    imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                    Glide.with(this).load(R.drawable.twod_brightness).into(imageViewTarget);
                    break;
                case 7:
                    layoutResId = R.layout.fragment_twod8;
                    view = setupView(container, layoutResId);
                    break;
                default:
                    layoutResId = R.layout.fragment_twod5;
                    view = setupView(container, layoutResId);
                    video = (VideoView) view.findViewById(R.id.movie);
                    video.setVideoURI(uri);
                    video.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
                    video.start();

                    break;

            }
            return view;
        } catch (Exception e){
            return null;
        }
    }


    @NonNull
    private View setupView(ViewGroup container, int layoutResId) {
        // Inflate the layout resource file
        View view = getActivity().getLayoutInflater().inflate(layoutResId, container, false);
        // Set the current page index as the View's tag (useful in the PageTransformer)
        view.setTag(mPage);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
