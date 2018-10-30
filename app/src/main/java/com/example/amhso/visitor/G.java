package com.example.amhso.visitor;



import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class G extends Application {

    public static Context context;
    public static Activity activity;


    public static String urlserver="http://darmalena.com/android/";
    public static String ServerImg="http://darmalena.com/uploads/";
    public static String urlwebview="http://darmalena.com/";



    public static final String MyPREFERENCES = "MyPrefs";
    public static final String id_user = "id_user";





















    @Override
    public void onCreate() {

        context = getApplicationContext();
        super.onCreate();




    }









    public static boolean checknet() {
        ConnectivityManager conMgr;
        conMgr = (ConnectivityManager) G.activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            return   true;

        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }



}