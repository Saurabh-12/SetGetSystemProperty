package com.saurabh.example.sysprop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView showPropTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPropTextView = (TextView)findViewById(R.id.show_prop_textview);
    }

    public void removeSystemProp(View view) {
        removeSystemProperty("MyKey", "NoValue");
    }

    public void getSystemProp(View view) {
        String value = readSystemProperty("MyKey");
        showPropTextView.setText("Key:  MyKey "+value);
    }

    public void setSystemProp(View view) {
        setSystemProperty("MyKey", "MyValue");
    }

    public void setSystemProperty(String Key, String value){
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            Process proc = Runtime.getRuntime().exec("/system/bin/setprop "+Key+" "+value);
            in = new InputStreamReader(proc.getInputStream());
            reader = new BufferedReader(in);

            String line = null;
            Log.d("Saurabh Shell" ,"<OUTPUT>");
            while ( (line = reader.readLine()) != null)
                Log.d("Shell" , line);
            Log.d("Saurabh Shell", "</OUTPUT>");
            int exitVal = proc.waitFor();
            Log.d("Saurabh Shell","Process exitValue: " + exitVal);

        } catch (IOException e) {
           e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(in);
            closeQuietly(reader);
        }
    }

    public void removeSystemProperty(String Key, String value){
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            Process proc = Runtime.getRuntime().exec("/system/bin/setprop "+Key+" "+value);
            in = new InputStreamReader(proc.getInputStream());
            reader = new BufferedReader(in);

            String line = null;
            Log.d("Saurabh Shell" ,"<OUTPUT>");
            while ( (line = reader.readLine()) != null)
                Log.d("Shell" , line);
            Log.d("Saurabh Shell", "</OUTPUT>");
            int exitVal = proc.waitFor();
            Log.d("Saurabh Shell","Process exitValue: " + exitVal);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(in);
            closeQuietly(reader);
        }
    }

    public  String readSystemProperty(String name) {
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"/system/bin/getprop", name});
            in = new InputStreamReader(proc.getInputStream());
            reader = new BufferedReader(in);
            return reader.readLine();
        } catch (IOException e) {
            return null;
        } finally {
            closeQuietly(in);
            closeQuietly(reader);
        }
    }

    public  void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }
}
