package com.example.app_toko.BackgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.app_toko.Helper.AppConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BackgroundTaskAuth extends AsyncTask<String, String, String> {

    Context context;

    public BackgroundTaskAuth(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        if (type.equals("login")){
            String username = params[1];
            String password = params[2];

            try {
                URL url = new URL(AppConst.LOGIN_URL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                httpURLConnection.setDoInput(true);

                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("password", password);

                OutputStream os = httpURLConnection.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String response = "";
                while ((response = bufferedReader.readLine()) != null){
                    sb.append(response).append("\n");
                }

                response = sb.toString();

                is.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return "gagal";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
