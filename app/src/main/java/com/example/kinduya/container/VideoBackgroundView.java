package com.example.kinduya.container;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.kinduya.R;

public class VideoBackgroundView extends ViewGroup implements SurfaceHolder.Callback,
        MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnInfoListener{

    MediaPlayer mMediaPlayer;
    SurfaceView mSurfaceView;
    Context mContext;


    public VideoBackgroundView(Context context) {
        this(context, null);
    }

    public VideoBackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoBackgroundView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public void instantiateView() {
        releaseView();

        mSurfaceView = new SurfaceView(mContext);
        mSurfaceView.getHolder().addCallback(this);
        addView(mSurfaceView);
    }

    public void releaseView() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        if (mSurfaceView != null) {
            mSurfaceView.getHolder().removeCallback(this);
            removeView(mSurfaceView);
            mSurfaceView = null;
        }
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestLayout();
                }
            }, 100);
            return true;
        }
        return false;
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer = MediaPlayer.create(getContext(), R.drawable.bacground);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setDisplay(holder);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int w, int h) {
        if (mMediaPlayer != null) {
            float widthRatio = (float)w/mMediaPlayer.getVideoWidth();
            float heightRatio = (float)h/mMediaPlayer.getVideoHeight();
            if (widthRatio > heightRatio) {
                float outWidth = mMediaPlayer.getVideoWidth() * widthRatio;
                float outHeight = mMediaPlayer.getVideoHeight() * widthRatio;
                float topOffset = (h-outHeight)/2;
                mSurfaceView.layout(0, (int) topOffset, (int)outWidth, (int)(outHeight+topOffset));
            } else {
                float outWidth = mMediaPlayer.getVideoWidth() * heightRatio;
                float outHeight = mMediaPlayer.getVideoHeight() * heightRatio;
                float leftOffset = (w-outWidth)/2;
                mSurfaceView.layout((int) leftOffset, 0, (int)(outWidth+leftOffset), (int) (outHeight));
            }
        } else {
            if (mSurfaceView != null) {
                mSurfaceView.layout(0, 0, 1, 1);
            }
        }
    }
}
