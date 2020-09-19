package com.phonevenue.india;

import android.app.Application;
import android.content.Intent;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

public class MainApp extends Application {

    public static LeastRecentlyUsedCacheEvictor leastRecentlyUsedCacheEvictor;
    public static ExoDatabaseProvider exoDatabaseProvider;
    public static SimpleCache simpleCache;
    public static DataSource.Factory dataSourceFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this,StreamService.class);
        startService(intent);

        if(leastRecentlyUsedCacheEvictor == null) {
            leastRecentlyUsedCacheEvictor = new LeastRecentlyUsedCacheEvictor(90*1024*1024);
        }

        if(exoDatabaseProvider == null) {
            exoDatabaseProvider = new ExoDatabaseProvider(this);
        }

        if(simpleCache == null) {
            simpleCache = new SimpleCache(getCacheDir(),leastRecentlyUsedCacheEvictor,exoDatabaseProvider);
        }

    }


}
