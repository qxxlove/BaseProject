package com.example.dell.baseproject.https;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);
        initData();

    }

    private void initData() {
       /* SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            try {
                sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {


        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
        try {
            HttpsURLConnection conn = (HttpsURLConnection)new URL("").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = SSLHttpsUtils.getNewHttpClient();
                HttpGet get = new HttpGet("https://www.baodu.com");
                try {
                    final StringBuffer buffer = null;
                    HttpResponse response = client.execute(get);
                    int code = response.getStatusLine().getStatusCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        InputStream is = response.getEntity().getContent();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader reader = new BufferedReader(isr);
                        String line = reader.readLine();
                        while (line != null) {
                            buffer.append(line);
                            line = reader.readLine();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                BaseUtils.toast(HttpsActivity.this,buffer.toString());
                            }
                        });

                    }
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
