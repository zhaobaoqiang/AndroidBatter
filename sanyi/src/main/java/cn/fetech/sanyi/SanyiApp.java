package cn.fetech.sanyi;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wudoumi.batter.net.NetInterface;
import com.wudoumi.batter.net.VolleyNet;

import java.io.File;

import cn.fetech.sanyi.db.DBHelper;

/**
 * Created by qianjujun on 2015/7/14 0014 14:33.
 * qianjujun@163.com
 */
public class SanyiApp extends Application{
    private NetInterface netInterface;

    private static SanyiApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        DBHelper.init(getApplicationContext());

        initImageLoader(this);

        netInterface = VolleyNet.getInstance(this);

        instance = this;
    }


    public static SanyiApp getInstance(){
        if(instance==null){
            throw new RuntimeException("Application未启动");
        }

        return instance;
    }



    public  NetInterface getNetInterface(){
        return netInterface;
    }




    private void initImageLoader(Context context) {


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new LimitedAgeDiskCache(getCacheFile(), 30 * 24 * 60 * 60))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    private File getCacheFile(){
        File file = Environment.getExternalStorageDirectory();
        File cache = new File(file, "三益/cache_images");
        if(!cache.exists()){
            cache.mkdirs();
        }

        return cache;
    }
}
