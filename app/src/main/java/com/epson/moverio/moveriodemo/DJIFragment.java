package com.epson.moverio.moveriodemo;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


public class DJIFragment extends Fragment {

    private static final String PAGE = "page";

    private int mPage;

    public static DJIFragment newInstance(int page) {
        DJIFragment frag = new DJIFragment();
        Bundle b = new Bundle();
        b.putInt(PAGE, page);
        frag.setArguments(b);
        return frag;
    }

    public static int getCount() {
        return 6;
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

        // Select a layout based on the current page
        int layoutResId;
        View view;
        VideoView video;
        ImageView imageView;
        GlideDrawableImageViewTarget imageViewTarget;
        switch (mPage) {
            case 0:
                layoutResId = R.layout.fragment_bigimage;
                view = setupView(container, layoutResId);
                imageView = (ImageView) view.findViewById(R.id.fullImage);
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.drone_intro));
                break;
            case 1:
                layoutResId = R.layout.fragment_bigimage;
                view = setupView(container, layoutResId);
                imageView = (ImageView) view.findViewById(R.id.fullImage);
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.drone_poweron));
                break;
            case 2:
                layoutResId = R.layout.fragment_bigimage;
                view = setupView(container, layoutResId);
                imageView = (ImageView) view.findViewById(R.id.fullImage);
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.drone_connect));
                break;
            case 3:
                layoutResId = R.layout.fragment_bigimage;
                view = setupView(container, layoutResId);
                imageView = (ImageView) view.findViewById(R.id.fullImage);
                imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                Glide.with(this).load(R.drawable.drone_iconpress).into(imageViewTarget);
                break;

            case 4:
                layoutResId = R.layout.fragment_smallimage;
                view = setupView(container, layoutResId);
                imageView = (ImageView) view.findViewById(R.id.fullImage);
                imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                Glide.with(this).load(R.drawable.drone_gofly).into(imageViewTarget);
                break;
            default:
                layoutResId = R.layout.fragment_bigmovie;
                view = setupView(container, layoutResId);
                video = (VideoView) view.findViewById(R.id.movie);
                String uripath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.drone_experience;
                Uri uri = Uri.parse(uripath);
                video.setVideoURI(uri);
                video.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });
                video.start();

        }

        return view;
    }

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

