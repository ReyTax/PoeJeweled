package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.gameproject.gameplay.GameplayFunctionality;
import com.example.gameproject.gameplay.MainGameplay;
import com.example.gameproject.option.MainOptionsActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame;
    private Button buttonStartGameplayActivity,buttonOptionsMainMenu;
    private Intent intentGameplayActivity,intentOptionsActivity;

    private Socket socket;
    private PrintWriter send;
    private BufferedReader get;
    public static CommunicationService communicationService;
    private Intent intentService;
    @SuppressLint("StaticFieldLeak")
    public void connectToServer() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    System.out.println("++++++++++++++++++++++++++++1");
                    socket = new Socket("192.168.0.192", 8888);
                    send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    get = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("++++++++++++++++++++++++++++2");
                    startCommunication();
                    System.out.println("++++++++++++++++++++++++++++3");

                } catch (Exception ex) {
                    System.out.println("Failed to connect to server");
                }
                return null;
            }
        }.execute();
    }
    public static ServiceConnection connection;
    public void startCommunication(){

        connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className,
                                           IBinder service) {
                System.out.println("SA CREAT SERVICIUL");
                CommunicationService.LocalBinder binder = (CommunicationService.LocalBinder) service;
                binder.getService().setGetObj(get);
                binder.getService().setSentObj(send);
                communicationService = binder.getService();


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        intentService = new Intent(getApplicationContext(), CommunicationService.class);
        try{
            bindService(intentService, connection, Context.BIND_AUTO_CREATE); // unbindService(connection);
        }catch (Exception ex) {
            System.out.println("Failed to create connection");
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        connectToServer();
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.gameframe);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        frame = findViewById(R.id.gameframe);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        buttonStartGameplayActivity = findViewById(R.id.buttonPlay);
        buttonOptionsMainMenu = findViewById(R.id.buttonOptionsMainMenu);

        intentGameplayActivity = new Intent(this, MainGameplay.class);
        intentOptionsActivity = new Intent(this, MainOptionsActivity.class);

        buttonStartGameplayActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int test1[] = new int[2];
                frame.getLocationInWindow(test1);
                System.out.println(test1[0]+" "+test1[1]);
                int height = frame.getMeasuredHeight();
                int width = frame.getHeight();
                System.out.println(height);
                System.out.println(width);
                startActivity(intentGameplayActivity);

                GameplayFunctionality.gameplayTableDimension=width;
                GameplayFunctionality.gameplayPieceDimension=width/8;
                GameplayFunctionality.generateTable();
            }
        });

        buttonOptionsMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentOptionsActivity);
            }
        });


    }




}

