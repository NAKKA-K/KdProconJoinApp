package com.example.snakka.kdproconjoinapp;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPSJsonSender {
    private static final String PROTOCOL = "https";
    private static final String HOST = "kdproconjoin.herokuapp.com";
    private static final int PORT = 3000;
    private static final String FILEPATH = "join/api/";

    public static void postJoinToServer(final String postJsonData){
        new AsyncTask<Void, String, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                //http通信でデータを送信して、レスポンスコードを受け取っている
                int resCode = postJsonByHTTPS(postJsonData);

                return new Boolean(isSuccess(resCode));
            }

            /** HTTPのレスポンスコードを受け取って、成功か失敗かを画面に表示しつつ、booleanを返す */
            private boolean isSuccess(int resCode){ //TODO:既存、登録、その他の3種類くらいで、条件分けする予定
                Log.d("ResCode", "HTTP通信で返ってきた値は" + resCode);

                if(resCode == 0){
                    publishProgress("ネットワークエラーです");
                }else if(resCode / 100 == 2){
                    publishProgress("JOINしました");
                    return true;
                }else if(resCode / 100 == 4){
                    publishProgress("アプリケーションエラーです");
                }else if(resCode / 100 == 5){
                    publishProgress("サーバーエラーです");
                }else{
                    publishProgress(resCode + "番のエラーです");
                }
                return false;
            }

            @Override
            protected void onProgressUpdate(String... params){
                Toast.makeText(AppContextMgr.getContext(), params[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(Boolean isResCode) {
                if (isResCode == false) return;

            }

        }.execute();
    }


    /** HTTP通信でPOSTをするときに、JSONのデータを送るメソッド */
    public static int postJsonByHTTPS(String postJsonData){
        PrintStream outputServer = null;
        HttpURLConnection httpConnector = null;
        int resCode = 0;

        try {
            URL url = new URL(PROTOCOL, HOST, PORT, FILEPATH);
            httpConnector = (HttpURLConnection) url.openConnection();
            httpConnector.setRequestMethod("POST");
            httpConnector.setDoOutput(true);
            httpConnector.setRequestProperty("Content-Type", "application/json");
            httpConnector.setRequestProperty("Accept", "application/json");

            httpConnector.connect();

            //JSONをString型で送信
            outputServer = new PrintStream(httpConnector.getOutputStream());
            outputServer.print(postJsonData);
            outputServer.flush();

            resCode = httpConnector.getResponseCode();
        } catch (MalformedURLException e) {
            Log.e("例外発生", "URLが不正です", e);
        } catch (IOException e) {
            Log.e("例外発生", "接続失敗", e);
        } finally {
            if (outputServer != null) outputServer.close();
            if (httpConnector != null) httpConnector.disconnect();
        }

        return resCode;
    }

}
