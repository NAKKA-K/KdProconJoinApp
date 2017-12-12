package com.example.snakka.kdproconjoinapp;


import android.content.Context;

/**
 * シングルトンで実装した、ApplicationContextをどこからでも取得できるクラス
 */
public class AppContextMgr {
    private static AppContextMgr instance = null;
    private Context applicationContext;

    //生成
    static void onCreateApplication(Context applicationContext){
        instance = new AppContextMgr(applicationContext);
    }

    private AppContextMgr(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    public static AppContextMgr getInstance(){
        if(instance == null){
            throw new RuntimeException("Initialize AppContextMgr!");
        }
        return instance;
    }



    public static Context getContext(){
        return instance.getApplicationContext();
    }

    public Context getApplicationContext(){
        return applicationContext;
    }
}
