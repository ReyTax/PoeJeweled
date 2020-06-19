package com.example.gameproject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CommunicationService extends Service {


    PrintWriter send;
    BufferedReader get;
    String message;

    public void setSentObj(PrintWriter send){
        this.send = send;
    }

    public void setGetObj(BufferedReader get){
        this.get = get;
    }
    public final IBinder iBinder= new LocalBinder();

    public class LocalBinder extends Binder {
        public CommunicationService getService() {

            return CommunicationService.this;
        }
    }
    public IBinder onBind(Intent intent) {

        return iBinder;
    }

    public static Thread sendToServerT;
    public static Thread listenToServerT;

    public static ScoreClassData scoreClassData[];
    public static ScoreClassData aux;
    public static int numberOfInstances=0;
    int nrInstances=0;
    public void send(String mesaj){

        this.message = mesaj;
        sendToServerT = new Thread(new Send());
        sendToServerT.start();
    }
    public class ScoreClassData{
        public String name;
        public int score;
        public ScoreClassData(String name,int score){
            this.name=name;
            this.score=score;
        }
    }
    public void listen(){
        scoreClassData=new ScoreClassData[1000];
        numberOfInstances=100;
        nrInstances=0;
        send("Get Game*");
        for(int i=1;i<=105;i++)
        {
            scoreClassData[i]=new ScoreClassData("a",-1);
        }
        aux=new ScoreClassData("0",0);
        listenToServerT = new Thread(new Listen());
        System.out.println("ASCULT");
        listenToServerT.start();


    }
    public class Listen implements Runnable{

        @Override
        public void run() {
                String data;
                try {
                    while ((data = get.readLine()) != null){
                        nrInstances++;
                        //numberOfInstances++;
                        //System.out.println(data);
                        int index=data.indexOf(',');
                        String name=data.substring(0,index);
                        String stringScore=data.substring(index+1,data.length());
                        System.out.println(name+"------------"+stringScore);
                        int score=Integer.parseInt(stringScore);
                        aux.score=score;
                        aux.name=name;
                        System.out.println(aux.name);
                        scoreClassData[nrInstances]=new ScoreClassData(aux.name,aux.score);
                        System.out.println("INSTANCES: "+Integer.toString(numberOfInstances) + "  ");
                        System.out.println("THIS IS "+scoreClassData[nrInstances].name+" "+ Integer.toString(scoreClassData[nrInstances].score));

                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

    }

    public class Send implements Runnable{

        @Override
        public void run() {
            try{
                System.out.println(message);
                send.println(message);
            }catch (Exception e) {
                System.out.println("ERROR LA TRIMITERE");
            }
        }
    }
}