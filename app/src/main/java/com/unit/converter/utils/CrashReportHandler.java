package com.unit.converter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class CrashReportHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Activity activity;

    public static void attach(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashReportHandler(
                context));
    }

    public CrashReportHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String str = "";

                Log.d("AAA", Log.getStackTraceString(ex));
//                Log.getStackTraceString(ex);

                try {

                    String platform = "android";
                    String os = Integer.toString(android.os.Build.VERSION.SDK_INT);
                    String app = "minecraftapps";

                    String appVersion;
                    try {
                        appVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                    } catch (PackageManager.NameNotFoundException e) {
                        appVersion = "1";
                    }
                    String packageApp = context.getPackageName();
                    String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    String deviceType = "";
                    String deviceName = android.os.Build.MODEL;
                    String deviceMan = android.os.Build.MANUFACTURER;

                    byte[] result = null;

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://sdk.adecosystems.su:8888/app/stacktrace");
                    Log.d("CrashReportHandler", "try to launch it ->" + str);

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("platform", platform));
                    nameValuePairs.add(new BasicNameValuePair("os", os));
                    nameValuePairs.add(new BasicNameValuePair("app", app));
                    nameValuePairs.add(new BasicNameValuePair("app_version", appVersion));
                    nameValuePairs.add(new BasicNameValuePair("package", packageApp));
                    nameValuePairs.add(new BasicNameValuePair("device_id", deviceId));
                    nameValuePairs.add(new BasicNameValuePair("device_type", deviceType));
                    nameValuePairs.add(new BasicNameValuePair("device_manufacture", deviceMan));
                    nameValuePairs.add(new BasicNameValuePair("device_model", deviceName));
                    nameValuePairs.add(new BasicNameValuePair("message", ex.getMessage()));
                    nameValuePairs.add(new BasicNameValuePair("stacktrace", Log.getStackTraceString(ex)));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                    HttpResponse response = httpclient.execute(httppost);
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
                        result = EntityUtils.toByteArray(response.getEntity());
                        str = new String(result, "UTF-8");
                    }

                } catch (ClientProtocolException e) {
                    Log.getStackTraceString(e);
                } catch (IOException e) {
                    Log.getStackTraceString(e);
                } catch (Exception e) {
                    Log.getStackTraceString(e);
                }

                Log.d("CrashReportHandler", "str ->" + str);
                android.os.Process.killProcess(android.os.Process.myPid());
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            }
        };
        task.execute();
    }
}