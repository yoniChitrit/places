package com.example.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class DownloadTask extends AsyncTask< String, Integer, DownloadTask.Result > {
    @SuppressLint("StaticFieldLeak")
    private Context context;
//private FirstFragment callback;

    interface DownloadComplete {
        void onDownloadComplete(String result);
    }

    private final DownloadComplete downloadComplete;

    public DownloadTask(Context context, final DownloadComplete download) {
        downloadComplete = download;
    }
  /*  DownloadTask(FirstFragment callback, DownloadComplete downloadComplete) {
        this.downloadComplete = downloadComplete;
        setCallback(callback);
    }

    void setCallback(FirstFragment callback) {
        Log.e(TAG,"setCallback"+  callback );
        this.callback = callback;

    }*/


    static class Result {

        // private TextView textView;
        public String resultValue;
        public Exception exception;

        public Result(String resultValue) {
            this.resultValue = resultValue;
            //  valueResult(this.resultValue);
          //  Log.e(TAG, "result");
        }

        public Result(Exception exception) {
          //  Log.e(TAG, "exception");
            this.exception = exception;
            //   Log.e(TAG,"result"+  this.exception );
        }

    }


    /**
     * Updates the DownloadCallback with the result.
     */
    @Override
    protected void onPostExecute(Result result) {
        Log.e(TAG, "onPostExecute()  " + downloadComplete);
        if (result != null && downloadComplete != null) {//&& callback != null
            if (result.exception != null) {
                Log.e(TAG, "onPostExecute()+result.exception");
                downloadComplete.onDownloadComplete(result.exception.getMessage());
            } else if (result.resultValue != null) {
                Log.e(TAG, "onPostExecute()+result.resultValue");

                //  callback.onDownloadComplete(result.resultValue);
                downloadComplete.onDownloadComplete(result.resultValue);
            }
            Log.e(TAG, "onPostExecute()+finishDownloading ");
            //  callback.finishDownloading();
            cancel(true);
        }
    }

    @Override
    protected Result doInBackground(String... urls) {

        Result result = null;
        if (!isCancelled() && urls != null && urls.length > 0) {
            String urlString = urls[0];
            // Log.e(TAG,"doInBackground()"+urlString);
            //  urlString="https://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=e";
            try {
                URL url = new URL(urlString);
                String resultString = downloadUrl(url);////////////URL
                //   Log.e(TAG,"doInBackground()"+resultString);


                if (resultString != null) {

                    // Log.e(TAG,"doInBackground()"+resultString);
                    result = new Result(resultString);
                    // callback.onDownloadComplete(resultString);

                } else {
                    Log.e(TAG, "doInBackground()+No response received");
                    throw new IOException("No response received.");

                }
            } catch (Exception e) {
                result = new Result(e);
            }
        }
        return result;


    }

    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     *
     * @param url
     */
    private String downloadUrl(URL url) throws IOException {
        Log.e(TAG, "downloadUrl");
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();
            publishProgress(0);//CONNECT_SUCCESS

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            publishProgress(1, 0);//GET_INPUT_STREAM_SUCCESS
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 111500);
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }


    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream, int maxReadSize)

            throws IOException, UnsupportedEncodingException {
        Log.e(TAG, "readStream");
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }


}
