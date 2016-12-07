package com.example.alien.im.widget;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;


import com.example.alien.im.utils.ActivityUtils;

import java.io.FileDescriptor;
import java.io.IOException;

public class VideoFragment extends Fragment implements TextureView.SurfaceTextureListener {
    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    private ActivityUtils activityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textureView=new TextureView(getContext());
        return textureView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer=new MediaPlayer();
        activityUtils=new ActivityUtils(getActivity());
        textureView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        AssetFileDescriptor afd = null;
        try {
            afd = getContext().getAssets().openFd("welcome.mp4");
            FileDescriptor fd=afd.getFileDescriptor();
            mediaPlayer.setDataSource(fd,afd.getStartOffset(),afd.getLength());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Surface sf=new Surface(textureView.getSurfaceTexture());
                    mediaPlayer.setSurface(sf);
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            activityUtils.showToast("视频播放失败");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}