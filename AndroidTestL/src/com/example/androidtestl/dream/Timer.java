package com.example.androidtestl.dream;

import com.example.androidtestl.R;

import android.service.dreams.DreamService;

public class Timer extends DreamService {
    
    @Override
    public void onAttachedToWindow() {
        setContentView(R.layout.layout_timer);
        // TODO Auto-generated method stub
        super.onAttachedToWindow();
    }
    
    @Override
    public void onDetachedFromWindow() {
        // TODO Auto-generated method stub
        super.onDetachedFromWindow();
    }
    
    @Override
    public void onDreamingStarted() {
        // TODO Auto-generated method stub
        super.onDreamingStarted();
    }
    
}
