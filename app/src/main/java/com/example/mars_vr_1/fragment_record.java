package com.example.mars_vr_1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mars1.R;

public class fragment_record extends Fragment {

    VideoView vv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_lisence, null);
//        final VideoView videoview = (VideoView) view.findViewById(R.id.videoView);
//        Uri videoUri = Uri.parse("https://youtu.be/5x9IV7GuIQo");
//        videoview.setVideoURI(videoUri);
//
//        videoview.setMediaController(new MediaController(getActivity()));

        return inflater.inflate(R.layout.fragment_record, container, false);
    }
}