package com.wudoumi.batter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class BatterDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Class<?>[] clazzes;

    public BatterDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);

    }

    public BatterDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion,int config) {

        super(context, databaseName, factory, databaseVersion, config);
    }


    public BatterDatabaseHelper(Context context, String databaseName,int databaseVersion,Class<?>[] clazzes){
        super(context, databaseName, null, databaseVersion);
        this.clazzes =clazzes;
    }







    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        if(clazzes!=null){
            try {
                for(Class<?> clazz : clazzes){
                    TableUtils.createTable(connectionSource, clazz);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(clazzes!=null){
            try {
                for(Class<?> clazz : clazzes){
                    TableUtils.dropTable(connectionSource, clazz, true);
                }
                onCreate(database, connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        String daoKey = clazz.getName();
        if(!map.containsKey(daoKey)){
           map.put(daoKey,super.getDao(clazz));
        }
        return (D) map.get(daoKey);
    }


    private Map<String,Dao> map = new HashMap<String,Dao>();
}
