package com.phonevenue.india;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheWriter;

import java.io.IOException;

public class StreamService extends IntentService implements CacheWriter.ProgressListener {

    private DataSpec dataSpec;
    private CacheDataSource cacheDataSource;
    private String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

    public StreamService() {
        super("StreamService");
        dataSpec = new DataSpec(Uri.parse(videoUrl),0,1024*400L);
        cacheDataSource = new CacheDataSource(MainApp.simpleCache, new DefaultHttpDataSource());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CacheWriter cacheWriter = new CacheWriter(cacheDataSource,dataSpec,false,null,this);
        try {
            cacheWriter.cache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProgress(long requestLength, long bytesCached, long newBytesCached) {
        Log.i("GoogleIO","requestLength: "+requestLength);
        Log.i("GoogleIO","byteCached: "+bytesCached);
        Log.i("GoogleIO","newBytesCached: "+newBytesCached);
    }
}
