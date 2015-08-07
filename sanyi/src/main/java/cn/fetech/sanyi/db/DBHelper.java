package cn.fetech.sanyi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.fetech.sanyi.R;

/**
 * Created by qianjujun on 2015/7/14 0014 13:39.
 * qianjujun@163.com
 */
public class DBHelper {
    public static final String DB_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/三益/";
    public static final String DB_NAME = "regions.db";
    private AndroidConnectionSource connectionSource;
    private static DBHelper dbHelper;

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            throw new RuntimeException("请在application中调用init");
        }
        return dbHelper;
    }


    public static void init(Context applicationContext){
        if(dbHelper == null){
            synchronized (DBHelper.class){
                if(dbHelper==null){
                    dbHelper = new DBHelper(applicationContext);
                }
            }
        }
    }

    private DBHelper(Context context) {
        File dir = new File(DB_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, DB_NAME);
        if (!file.exists()) {
            try {
                loadFile(context, file, R.raw.regions);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getPath(), null,
                SQLiteDatabase.OPEN_READWRITE);
        connectionSource = new AndroidConnectionSource(db);
    }

    /**
     * 下在文件到指定目录
     *
     * @param context
     * @param file
     *            sd卡中的文件
     * @param id
     *            raw中的文件id
     * @throws IOException
     */
    public static void loadFile(Context context, File file, int id)
            throws IOException {
        InputStream is = context.getResources().openRawResource(id);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        is.close();
    }

    /**
     * 获取dao
     *
     * @param clazz
     * @return
     */
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws Exception {
        if (connectionSource != null) {
            return DaoManager.createDao(connectionSource, clazz);
        }
        return null;
    }
}
