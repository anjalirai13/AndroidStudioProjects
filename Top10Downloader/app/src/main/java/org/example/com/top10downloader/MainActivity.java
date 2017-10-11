package org.example.com.top10downloader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button parseButton;
    private ListView listView;
    private String mFileContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseButton = (Button) findViewById(R.id.btnParse);
        listView = (ListView) findViewById(R.id.listView);
        DownloadData dd = new DownloadData();
        dd.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml");
        Log.d("mainActivity","mFileContents-"+mFileContents);

        parseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseXmlData pp = new ParseXmlData(mFileContents);
                pp.process();
                ArrayAdapter<Application> arrayAdapter = new ArrayAdapter<Application>(
                        MainActivity.this, R.layout.list_item, pp.getApplications()
                );
                listView.setAdapter(arrayAdapter);
            }
        });
    }

    public class DownloadData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            mFileContents = downloadXmlFile(params[0]);
            if(mFileContents == null){
                Log.d("Download Data", "Error in downloading data");
            }
            Log.d("Download data", "returnFileContents"+mFileContents);
            return mFileContents;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("Download data", "result"+result);
        }

        private String downloadXmlFile(String urlPath){
            StringBuilder tempBuffer = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                Log.d("DownloadData", "Response code: "+responseCode);
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[5000];
                while(true){
                    charRead = isr.read(inputBuffer);
                    if(charRead <= 0){
                        break;
                    }
                    tempBuffer.append(String.copyValueOf(inputBuffer,0,charRead));
                }
                Log.d("mainActivity","tempBuffer"+tempBuffer.toString());
                return tempBuffer.toString();
            } catch (IOException e){
                Log.d("Download Data", "Exception.."+e.getMessage());
            } catch(SecurityException e){
                Log.d("Download Data", "Security Exception"+e.getMessage());
            }
            return null;
        }
    }
}