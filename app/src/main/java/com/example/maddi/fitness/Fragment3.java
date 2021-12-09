package com.example.maddi.fitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class Fragment3 extends Fragment implements YouTubePlayer.OnInitializedListener {

    private int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayer mPlayer3;
    YouTubePlayerSupportFragment playerFragment3;


    String mVideoId3;

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();

        return fragment;
    }

    public Fragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView3;

        rootView3 = inflater.inflate(R.layout.tab_fragment_3, container, false);
        TextView title3 = (TextView) rootView3.findViewById(R.id.title3);
        title3.setVisibility(View.VISIBLE);

        title3.setText(R.string.tab3Text);
        mVideoId3 = getString(R.string.tab3VideoID);
        Log.d("mVideoId3 is ", mVideoId3);

        playerFragment3 = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.moviePlayer3);
        playerFragment3.setUserVisibleHint(true);

        return rootView3;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isVisibleToUser && mPlayer3 != null) {
            mPlayer3.release();
        }
        if (isVisibleToUser && playerFragment3 != null) {
            playerFragment3.initialize(getString(R.string.google_maps_key), this);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer3, boolean restored) {

        mPlayer3 = youTubePlayer3;
        mPlayer3.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
        mPlayer3.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
        mPlayer3.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

        if (mVideoId3 != null) {
            if (restored) {
                mPlayer3.play();
            } else {
                mPlayer3.loadVideo(mVideoId3);
            }
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else {
            //Handle the failure
            Toast.makeText(getActivity(), "onInitializationFailure", Toast.LENGTH_LONG).show();
        }
    }
}