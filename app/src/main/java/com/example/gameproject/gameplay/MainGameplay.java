package com.example.gameproject.gameplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gameproject.CommunicationService;
import com.example.gameproject.MainActivity;
import com.example.gameproject.R;
import com.example.gameproject.option.SmallMenuGameplay;
import com.example.gameproject.option.SmallScoreMenu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainGameplay extends AppCompatActivity implements View.OnTouchListener {

    private boolean devMode = false; // cordonatele la mouse

    private FrameLayout gameframe;
    private ConstraintLayout smallmenulayout;
    private ImageView pieces[][] = new ImageView[9][9];
    private ImageView selectedpieceaux[] = new ImageView[3];
    private ImageView selectedpiece[] = new ImageView[3];
    private ImageView imageViewMoreTimeCharges;
    private Button buttonCreateTable, buttonOptionMenu;
    private TextView textViewScore, textViewMultiplier, textViewGameDuration;
    private int combinationCharger = 0;
    private boolean select1 = false, select2 = false;
    private boolean isClicked = false;
    private boolean animationIsGoing = false;
    private int animationDelay;
    private boolean gameCreated = false;
    private boolean gamePause = false;
    private static boolean resetGameplay = false;
    private static boolean goBackToMainScreen = false;
    private boolean countdownStop = true;
    private boolean gameEnd=false;
    private Timer countdownGameDuration;
    private Resources resources;
    private Drawable imageViewMoreTimeChargesDrawable[] = new Drawable[GameplayFunctionality.gameMoreTimeChargesLimit];
    private FragmentManager fragmentManager;
    private final SmallMenuGameplay smallMenuGameplay = new SmallMenuGameplay();
    private final SmallScoreMenu smallScoreMenu = new SmallScoreMenu();
    LinearLayout.LayoutParams layoutParams;
    private TextView viewCordX, viewCordY;
    private float cordX, cordY;
    private Timer timerGameplay = new Timer();
    private Handler handler = new Handler();
    private static Context context;

    /*private Socket socket;
    private PrintWriter send;
    private BufferedReader get;
    private static CommunicationService communicationService;
    private Intent intentService;*/

    private static boolean closeScoreWindow=false;
    /*@SuppressLint("StaticFieldLeak")
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
    private ServiceConnection connection;
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

    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_gameplay);
        //connectToServer();
        gameEnd=false;
        layoutParams = new LinearLayout.LayoutParams(GameplayFunctionality.gameplayPieceDimension, GameplayFunctionality.gameplayPieceDimension);
        smallmenulayout = findViewById(R.id.smallmenulayout);
        gameframe = findViewById(R.id.gameframe);
        resources = getResources();
        imageViewMoreTimeChargesDrawable[0] = resources.getDrawable(R.drawable.moretimeassetone);
        imageViewMoreTimeChargesDrawable[1] = resources.getDrawable(R.drawable.moretimeassettwo);
        imageViewMoreTimeChargesDrawable[2] = resources.getDrawable(R.drawable.moretimeassetthree);
        imageViewMoreTimeChargesDrawable[3] = resources.getDrawable(R.drawable.moretimeassetfour);


        imageViewMoreTimeCharges = findViewById(R.id.imageViewMoreTime);
        imageViewMoreTimeCharges.setImageDrawable(imageViewMoreTimeChargesDrawable[0]);

        GameplayFunctionality.gameMoreTimeCharges = 0;
        combinationCharger = 0;
        textViewScore = findViewById(R.id.textViewScore);
        textViewMultiplier = findViewById(R.id.textViewMultiplier);
        textViewGameDuration = findViewById(R.id.textViewGameDuration);

        textViewScore.setText("0");
        textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));

        if ((GameplayFunctionality.gameDurationCurrent / 10) % 60 > 9)
            textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
        else
            textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":0" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));

        context = this;

        fragmentManager = getSupportFragmentManager();

        viewCordX = findViewById(R.id.cordX);
        viewCordY = findViewById(R.id.cordY);

        if (devMode == false) {
            viewCordX.setVisibility(View.INVISIBLE);
            viewCordY.setVisibility(View.INVISIBLE);
        }

        buttonCreateTable = findViewById(R.id.buttonCreateTable);
        buttonOptionMenu = findViewById(R.id.buttonOptionsMenu);

        buttonCreateTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countdownStop = false;
                gameCreated = true;
                gamePause = false;
                animationIsGoing = false;
                animationDelay = 0;
                gameDurationContdown();
                generateGameplayTable();
                buttonCreateTable.setVisibility(View.INVISIBLE);
                if ((GameplayFunctionality.gameDurationCurrent / 10) % 60 > 9)
                    textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
                else
                    textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":0" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
            }
        });

        buttonOptionMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                if (gamePause == false || gameEnd==true) {
                    fragmentManager.beginTransaction().replace(smallmenulayout.getId(), smallMenuGameplay, smallMenuGameplay.getTag()).commit();
                    buttonCreateTable.setVisibility(View.INVISIBLE);
                    gamePause = true;
                    countdownStop = true;
                    gameEnd=false;

                } else {
                    //communicationService.send("Save Game*"+"Timee1ei,"+"1234,");
                    countdownStop = false;
                    fragmentManager.beginTransaction().remove(smallMenuGameplay).commit();
                    if (gameCreated == false && gameEnd!=true) {
                        buttonCreateTable.setVisibility(View.VISIBLE);
                        countdownStop = true;
                    }
                    gamePause = false;
                    if (gameCreated == true) {
                        //piecesCombinationElimination();
                        GameplayFunctionality.gameDurationCurrent += 1;
                    }

                }

            }
        });

        findViewById(R.id.gameframe).setOnTouchListener(this);

        imageViewMoreTimeCharges.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (gameCreated == true && gamePause == false) {
                    if (GameplayFunctionality.gameMoreTimeCharges > 0) {
                        //System.out.println("Number of charges : " +GameplayFunctionality.gameMoreTimeCharges);
                        GameplayFunctionality.gameMoreTimeCharges--;
                        imageViewMoreTimeCharges.setImageDrawable(imageViewMoreTimeChargesDrawable[GameplayFunctionality.gameMoreTimeCharges]);
                        GameplayFunctionality.gameDurationCurrent += GameplayFunctionality.gameDurationPerCharge;
                        if ((GameplayFunctionality.gameDurationCurrent / 10) % 60 > 9)
                            textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
                        else
                            textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":0" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
                    }
                }
            }
        });


        timerGameplay.schedule(new TimerTask() {

            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (devMode == true) {

                            viewCordX.setText(String.valueOf(cordX));
                            viewCordY.setText(String.valueOf(cordY));
                        }
                        if(closeScoreWindow==true){
                            fragmentManager.beginTransaction().remove(smallScoreMenu).commit();
                            closeScoreWindow=false;
                            buttonCreateTable.setVisibility(View.VISIBLE);
                        }
                        isClicked();
                    }
                });
            }
        }, 0, 20);
    }
private static boolean ok=false;
    private static boolean synchronizationCondition=false;
    public static void sendDataToServer(String nume, View view){
        try{
            MainActivity.communicationService.send("Save Game*"+nume+","+ Integer.toString(GameplayFunctionality.savedScore));
        }catch (Exception e){
            System.out.println("Send Failed");
        }

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        final Timer waitToSend= new Timer();
        final Handler waitToSendHandler=new Handler();
        synchronizationCondition=false;
        waitToSend.schedule(new TimerTask() {

            @Override
            public void run() {
                waitToSendHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(synchronizationCondition==false){
                                try {
                                    MainActivity.communicationService.listen();
                                }catch (Exception e){
                                      System.out.println("Send Failed");
                                }
                            synchronizationCondition=true;
                        }
                        else
                        {
                            System.out.println("END+++++++++++++++++++++-------------------1");
                            /*for(int i=1;i<=CommunicationService.numberOfInstances-1;i++){
                                if(CommunicationService.scoreClassData[i].score==-1){
                                for(int j=i+1;j<=CommunicationService.numberOfInstances;j++) {
                                    CommunicationService.scoreClassData[i]=CommunicationService.scoreClassData[j];
                                }
                                CommunicationService.numberOfInstances--;}
                            }
                            for(int i=1;i<=CommunicationService.numberOfInstances-1;i++)
                                System.out.println("value: 1 - "+ CommunicationService.scoreClassData[i].name);*/

                            for(int i=1;i<=CommunicationService.numberOfInstances-1;i++){
                                System.out.println(CommunicationService.scoreClassData[i].name+" "+ Integer.toString(CommunicationService.scoreClassData[i].score));
                                System.out.println("NUMBER OF INSTANCES " + CommunicationService.numberOfInstances);
                                for(int j=i+1;j<=CommunicationService.numberOfInstances;j++){

                                    if(CommunicationService.scoreClassData[i].score<CommunicationService.scoreClassData[j].score){
                                        CommunicationService.aux=CommunicationService.scoreClassData[i];
                                        CommunicationService.scoreClassData[i]=CommunicationService.scoreClassData[j];
                                        CommunicationService.scoreClassData[j]=CommunicationService.aux;
                                    }
                                }
                            }
                            System.out.println("END++++++++++++++++++++-------------------2");
                            for(int i=CommunicationService.numberOfInstances;i>=1;i--){
                                if(CommunicationService.scoreClassData[i].score==-1){
                                    CommunicationService.numberOfInstances--;
                                }
                            }
                            System.out.println("END++++++++++++++++++++-------------------3 INSTANCES: "  + CommunicationService.numberOfInstances);
                            waitToSend.cancel();
                            waitToSend.purge();
                        }

                    }
                });
            }
        }, 1000, 2000);
    }

    public static void callResetGameplayTable() {
        System.out.println("CALL RESET GAMEPLAY");
        resetGameplay = true;
    }
    public static void closeScoreWindow(){
        closeScoreWindow=true;
    }
    public static void goBackToMenu() {
        System.out.println("CALL BACK TO MENU");
        goBackToMainScreen = true;
    }

    public boolean onTouch(View v, MotionEvent event) {
        cordX = event.getX();
        cordY = event.getY();
        if (event.getAction() == event.ACTION_MOVE || event.getAction() == event.ACTION_DOWN) {
            if (v.getId() == R.id.gameframe) {
                isClicked = true;
            }
        } else
            isClicked = false;
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasBackPressed", true);
        setResult(Activity.RESULT_OK, returnIntent);
        goBackToMainScreen = false;
        gameCreated = false;
        if (countdownGameDuration != null) {
            countdownGameDuration.cancel();
            countdownGameDuration.purge();
        }
        try{
            unbindService(MainActivity.connection); // unbindService(connection);
        }catch (Exception e){
            System.out.println("undindError");
        }

        context=null;
        timerGameplay.purge();
        timerGameplay.cancel();
        finish();
    }

    private void isClicked() {

        if (goBackToMainScreen == true) {
            onBackPressed();
        }
        if(closeScoreWindow==true){
            fragmentManager.beginTransaction().remove(smallScoreMenu).commit();
            closeScoreWindow=false;
            buttonCreateTable.setVisibility(View.VISIBLE);
        }
        if (resetGameplay == true) {
            if (gameCreated == true) {
                fragmentManager.beginTransaction().remove(smallMenuGameplay).commit();

                resetGameplayTable();
                buttonCreateTable.setVisibility(View.VISIBLE);
            } else {
                fragmentManager.beginTransaction().remove(smallMenuGameplay).commit();
                buttonCreateTable.setVisibility(View.VISIBLE);
                resetGameplay = false;
            }

        }
        if (isClicked == true && animationIsGoing == false && cordX < GameplayFunctionality.gameplayTableDimension && cordY <= GameplayFunctionality.gameplayTableDimension && cordY >= 0 && gameCreated == true && gamePause == false) {
            selectedPiece(cordX, cordY, gameframe, selectedpiece);
            switchRelativePieces();

        }

    }

    public void selectedPiece(float x, float y, ViewGroup viewGroup, View view[]) {
        if (select1 == false || select2 == false) {
            int pozX, pozY, pozVerifX, pozVerifY;
            pozX = (int) x / GameplayFunctionality.gameplayPieceDimension * GameplayFunctionality.gameplayPieceDimension;
            pozY = (int) y / GameplayFunctionality.gameplayPieceDimension * GameplayFunctionality.gameplayPieceDimension;
            pozVerifY = (int) x / GameplayFunctionality.gameplayPieceDimension + 1;
            pozVerifX = (int) y / GameplayFunctionality.gameplayPieceDimension + 1;
            //System.out.println("Check table:" + GameplayFunctionality.gameplayVerifTable[pozVerifX+1][pozVerifY-1]);

            if (select1 == false && GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY] != 1 && checkCornerValidation(pozVerifX, pozVerifY)) {
                if (pieces[pozVerifX][pozVerifY] != null) {
                    view[1].setY(pozY);
                    view[1].setX(pozX);
                    view[1].requestLayout();
                    view[1].setLayoutParams(layoutParams);
                    viewGroup.addView(view[1]);
                    select1 = true;
                    GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY] = 1;

                    selectedpieceaux[1] = pieces[pozVerifX][pozVerifY];
                    //System.out.println((pozVerifX-1)*8+pozVerifY);
                    //System.out.println(pozVerifX+"  "+pozVerifY);
                }

            } else {
                if (select2 == false && GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY] != 1 && checkCornerValidation(pozVerifX, pozVerifY)) {
                    if (GameplayFunctionality.gameplayVerifTable[pozVerifX - 1][pozVerifY] == 1 || GameplayFunctionality.gameplayVerifTable[pozVerifX + 1][pozVerifY] == 1 || GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY - 1] == 1 || GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY + 1] == 1) {
                        if (pieces[pozVerifX][pozVerifY] != null) {
                            view[2].setY(pozY);
                            view[2].setX(pozX);
                            view[2].requestLayout();
                            view[2].setLayoutParams(layoutParams);
                            viewGroup.addView(view[2]);
                            select2 = true;
                            GameplayFunctionality.gameplayVerifTable[pozVerifX][pozVerifY] = 1;

                            selectedpieceaux[2] = pieces[pozVerifX][pozVerifY];
                            //System.out.println((pozVerifX-1)*8+pozVerifY);
                            //System.out.println(pozVerifX+"  "+pozVerifY);
                        }

                    }

                }
            }
        }
        //System.out.println("IMAGE VIEW EXIST  "+ selectedpiece[1].getDrawable());
    }

    private void switchRelativePieces() {
        //System.out.println("[switchRelativePieces] Method accessed, animation: "+ animationIsGoing);
        if (selectedpieceaux[1] != null && selectedpieceaux[2] != null)
            if (select1 == true && select2 == true && animationIsGoing == false) {
                animationDelay = GameplayFunctionality.delayForAnimations;
                animationTimeRequest();
                System.out.println("[switchRelativePieces] IF ACCESSED AnimationRequest: " + animationIsGoing);
                int pozVerifY1, pozVerifX1, pozVerifY2, pozVerifX2;
                int moveAnimation = 0;
                float x1, y1, x2, y2;
                int auxMat;
                ImageView imgAux;

                x1 = selectedpieceaux[1].getX();
                y1 = selectedpieceaux[1].getY();
                x2 = selectedpieceaux[2].getX();
                y2 = selectedpieceaux[2].getY();

                System.out.println("moveRelativePieces :");
                System.out.println("Cordonate p1: " + y1 + "   " + y2);
                System.out.println("Cordonate p2: " + x1 + "   " + x2);

                pozVerifY1 = (int) x1 / GameplayFunctionality.gameplayPieceDimension + 1;
                pozVerifX1 = (int) y1 / GameplayFunctionality.gameplayPieceDimension + 1;
                pozVerifY2 = (int) x2 / GameplayFunctionality.gameplayPieceDimension + 1;
                pozVerifX2 = (int) y2 / GameplayFunctionality.gameplayPieceDimension + 1;

                System.out.println("CordonateMat p1: " + pozVerifY1 + "   " + pozVerifX1);
                System.out.println("CordonateMat p2: " + pozVerifY2 + "   " + pozVerifX2);


                if (y1 == y2) {

                    if (x1 > x2) {
                        moveAnimation = 1;
                    } else if (x1 < x2) {
                        moveAnimation = 2;
                    }
                } else if (x1 == x2) {
                    if (y1 > y2) {
                        moveAnimation = 3;
                    } else if (y1 < y2) {
                        moveAnimation = 4;
                    }
                }
                switch (moveAnimation) {
                    case 1: {
                        System.out.println("Move case: 1");

                        GameplayFunctionality.gameplayVerifTable[pozVerifX1][pozVerifY1] = 0;
                        GameplayFunctionality.gameplayVerifTable[pozVerifX2][pozVerifY2] = 0;
                        auxMat = GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1];
                        GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1] = GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2];
                        GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2] = auxMat;
                        imgAux = pieces[pozVerifX1][pozVerifY1];
                        pieces[pozVerifX1][pozVerifY1] = pieces[pozVerifX2][pozVerifY2];
                        pieces[pozVerifX2][pozVerifY2] = imgAux;
                        moveSelectedAnimation(x1, x2, moveAnimation);
                        break;
                    }
                    case 2: {
                        System.out.println("Move case: 2");

                        GameplayFunctionality.gameplayVerifTable[pozVerifX1][pozVerifY1] = 0;
                        GameplayFunctionality.gameplayVerifTable[pozVerifX2][pozVerifY2] = 0;
                        auxMat = GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1];
                        GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1] = GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2];
                        GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2] = auxMat;
                        imgAux = pieces[pozVerifX1][pozVerifY1];
                        pieces[pozVerifX1][pozVerifY1] = pieces[pozVerifX2][pozVerifY2];
                        pieces[pozVerifX2][pozVerifY2] = imgAux;
                        moveSelectedAnimation(x1, x2, moveAnimation);
                        break;
                    }
                    case 3: {
                        System.out.println("Move case: 3");

                        GameplayFunctionality.gameplayVerifTable[pozVerifX1][pozVerifY1] = 0;
                        GameplayFunctionality.gameplayVerifTable[pozVerifX2][pozVerifY2] = 0;
                        auxMat = GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1];
                        GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1] = GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2];
                        GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2] = auxMat;
                        imgAux = pieces[pozVerifX1][pozVerifY1];
                        pieces[pozVerifX1][pozVerifY1] = pieces[pozVerifX2][pozVerifY2];
                        pieces[pozVerifX2][pozVerifY2] = imgAux;
                        moveSelectedAnimation(y1, y2, moveAnimation);
                        break;
                    }
                    case 4: {
                        System.out.println("Move case: 4");

                        GameplayFunctionality.gameplayVerifTable[pozVerifX1][pozVerifY1] = 0;
                        GameplayFunctionality.gameplayVerifTable[pozVerifX2][pozVerifY2] = 0;
                        auxMat = GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1];
                        GameplayFunctionality.gameplayTable[pozVerifX1][pozVerifY1] = GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2];
                        GameplayFunctionality.gameplayTable[pozVerifX2][pozVerifY2] = auxMat;
                        imgAux = pieces[pozVerifX1][pozVerifY1];
                        pieces[pozVerifX1][pozVerifY1] = pieces[pozVerifX2][pozVerifY2];
                        pieces[pozVerifX2][pozVerifY2] = imgAux;
                        moveSelectedAnimation(y1, y2, moveAnimation);
                        break;
                    }
                }


            }

    }

    private void moveSelectedAnimation(final float val1, final float val2, final int moveAnimation) {
        final Timer moveSelectedTimer = new Timer();
        final Handler moveSelectedHandler = new Handler();
        moveSelectedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveSelectedHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(selectedpiece[1].getParent()!=null && selectedpiece[2].getParent()!=null)
                        switch (moveAnimation) {
                            case 1: {
                                //System.out.println("THREAD ACTIVE");
                                selectedpieceaux[1].setX(selectedpieceaux[1].getX() - GameplayFunctionality.pieceMovementSpeed);
                                selectedpieceaux[2].setX(selectedpieceaux[2].getX() + GameplayFunctionality.pieceMovementSpeed);
                                if (selectedpieceaux[1].getX() - GameplayFunctionality.pieceMovementSpeed <= val2) {
                                    selectedpieceaux[1].setX(val2);
                                    selectedpieceaux[2].setX(val1);
                                    //posibil probleme

                                    ((ViewManager) selectedpiece[1].getParent()).removeView(selectedpiece[1]);

                                    ((ViewManager) selectedpiece[2].getParent()).removeView(selectedpiece[2]);
                                    selectInitialization();
                                    select1 = false;
                                    select2 = false;
                                    if (GameplayFunctionality.getCombination() != null) {
                                        piecesCombinationElimination();
                                    } else {
                                        GameplayFunctionality.gameScoreMultiplierCurrent = GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit - 1];
                                        textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));
                                    }

                                    moveSelectedTimer.cancel();
                                    moveSelectedTimer.purge();

                                }
                                break;
                            }
                            case 2: {
                                //System.out.println("THREAD ACTIVE");
                                selectedpieceaux[1].setX(selectedpieceaux[1].getX() + GameplayFunctionality.pieceMovementSpeed);
                                selectedpieceaux[2].setX(selectedpieceaux[2].getX() - GameplayFunctionality.pieceMovementSpeed);
                                if (selectedpieceaux[1].getX() + GameplayFunctionality.pieceMovementSpeed >= val2) {
                                    selectedpieceaux[1].setX(val2);
                                    selectedpieceaux[2].setX(val1);
                                    ((ViewManager) selectedpiece[1].getParent()).removeView(selectedpiece[1]);
                                    ((ViewManager) selectedpiece[2].getParent()).removeView(selectedpiece[2]);
                                    selectInitialization();
                                    select1 = false;
                                    select2 = false;
                                    if (GameplayFunctionality.getCombination() != null) {
                                        piecesCombinationElimination();
                                    } else {
                                        GameplayFunctionality.gameScoreMultiplierCurrent = GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit - 1];
                                        textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));
                                    }
                                    moveSelectedTimer.cancel();
                                    moveSelectedTimer.purge();
                                }
                                break;
                            }
                            case 3: {
                                //System.out.println("THREAD ACTIVE");
                                selectedpieceaux[1].setY(selectedpieceaux[1].getY() - GameplayFunctionality.pieceMovementSpeed);
                                selectedpieceaux[2].setY(selectedpieceaux[2].getY() + GameplayFunctionality.pieceMovementSpeed);
                                if (selectedpieceaux[1].getY() - GameplayFunctionality.pieceMovementSpeed <= val2) {
                                    selectedpieceaux[1].setY(val2);
                                    selectedpieceaux[2].setY(val1);
                                    ((ViewManager) selectedpiece[1].getParent()).removeView(selectedpiece[1]);
                                    ((ViewManager) selectedpiece[2].getParent()).removeView(selectedpiece[2]);
                                    selectInitialization();
                                    select1 = false;
                                    select2 = false;
                                    if (GameplayFunctionality.getCombination() != null) {
                                        piecesCombinationElimination();
                                    } else {
                                        GameplayFunctionality.gameScoreMultiplierCurrent = GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit - 1];
                                        textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));
                                    }
                                    moveSelectedTimer.cancel();
                                    moveSelectedTimer.purge();
                                }
                                break;
                            }
                            case 4: {
                                //System.out.println("THREAD ACTIVE");
                                selectedpieceaux[1].setY(selectedpieceaux[1].getY() + GameplayFunctionality.pieceMovementSpeed);
                                selectedpieceaux[2].setY(selectedpieceaux[2].getY() - GameplayFunctionality.pieceMovementSpeed);
                                if (selectedpieceaux[1].getY() + GameplayFunctionality.pieceMovementSpeed >= val2) {
                                    selectedpieceaux[1].setY(val2);
                                    selectedpieceaux[2].setY(val1);
                                    ((ViewManager) selectedpiece[1].getParent()).removeView(selectedpiece[1]);
                                    ((ViewManager) selectedpiece[2].getParent()).removeView(selectedpiece[2]);
                                    selectInitialization();
                                    select1 = false;
                                    select2 = false;
                                    if (GameplayFunctionality.getCombination() != null) {
                                        piecesCombinationElimination();
                                    } else {
                                        GameplayFunctionality.gameScoreMultiplierCurrent = GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit - 1];
                                        textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));
                                    }
                                    moveSelectedTimer.cancel();
                                    moveSelectedTimer.purge();
                                }
                                break;
                            }
                        }

                    }
                });
            }
        }, 0, 20);

    }

    private void piecesCombinationElimination() {

        final Timer waitForAnimation = new Timer();
        final Handler waitHandler = new Handler();
        if (gamePause == false)
            waitForAnimation.schedule(new TimerTask() {
                @Override
                public void run() {
                    waitHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (animationIsGoing == false && gamePause == false) ;
                            {
                                //System.out.println("THREAD ACTIVE");
                                if (GameplayFunctionality.getCombination() != null) {

                                    int a[] = GameplayFunctionality.getCombination();
                                    System.out.println("-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                                    System.out.println("FirstI : " + a[1] + " | FirstJ : " + a[2]);
                                    System.out.println("LastI : " + a[3] + " | LastJ : " + a[4]);
                                    System.out.println("MaxCombination : " + a[5] + " | Orientation : " + a[6]);
                                    System.out.println("-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                                    GameplayFunctionality.gameScore += GameplayFunctionality.gameScoreIncreaseCurrent * GameplayFunctionality.gameScoreMultiplierCurrent / GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption - 1];
                                    if (a[5] > GameplayFunctionality.combinationLimit && a[5] > 4) {
                                        GameplayFunctionality.gameScore += GameplayFunctionality.gameScoreIncreaseExtraCombinationDefault[a[5] - 1] / GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption - 1];
                                    }
                                    textViewScore.setText("" + GameplayFunctionality.gameScore);
                                    GameplayFunctionality.gameScoreMultiplierCurrent += GameplayFunctionality.gameScoreMultiplierIncreaseCurrent;


                                    if (GameplayFunctionality.gameMoreTimeCharges < GameplayFunctionality.gameMoreTimeChargesLimit - 1) {
                                        combinationCharger++;
                                        if (combinationCharger == GameplayFunctionality.combinationsNeededForOneCharge) {
                                            GameplayFunctionality.gameMoreTimeCharges++;

                                            imageViewMoreTimeCharges.setImageDrawable(imageViewMoreTimeChargesDrawable[GameplayFunctionality.gameMoreTimeCharges]);
                                            combinationCharger = 0;
                                        }
                                    }
                                    System.out.println("How many charges are : " + combinationCharger + " " + GameplayFunctionality.gameMoreTimeCharges);
                                    textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));
                                    System.out.println("CURRENT SCORE : " + GameplayFunctionality.gameScore + " | CURRENT MULTIPLIER : " + GameplayFunctionality.gameScoreMultiplierCurrent);
                                    int i, j = 0;
                                    if (a[6] == 1) {
                                        for (i = a[2]; i <= a[4]; i++) {
                                            System.out.println(i);
                                            ((ViewGroup) pieces[a[1]][i].getParent()).removeView(pieces[a[1]][i]);
                                            pieces[a[1]][i] = null;
                                        }
                                        for (i = a[2]; i <= a[4]; i++) {
                                            GameplayFunctionality.gameplayTable[a[1]][i] = 0;
                                        }
                                    } else if (a[6] == 2) {
                                        for (i = a[1]; i <= a[3]; i++) {
                                            ((ViewGroup) pieces[i][a[2]].getParent()).removeView(pieces[i][a[2]]);
                                            pieces[i][a[2]] = null;
                                        }
                                        for (i = a[1]; i <= a[3]; i++) {
                                            GameplayFunctionality.gameplayTable[i][a[2]] = 0;
                                        }
                                    }
                                }
                                waitForAnimation.purge();
                                waitForAnimation.cancel();
                                if (GameplayFunctionality.getCombination() != null && gameCreated == true)
                                    piecesCombinationElimination(); // Interesting interaction.
                                dropPieces();
                            }
                        }
                    });
                }
            }, 100, 500);

    }

    public void dropPieces() {
        int i, j, k, piecesMissing = 0, contPiecesToDrop;
        int piecesToDrop[] = new int[9];
        for (i = 8; i >= 1; i--) {
            for (j = 1; j <= 8; j++) {
                if (GameplayFunctionality.gameplayTable[i][j] == 0) {
                    piecesMissing = 0;
                    contPiecesToDrop = 0;
                    int contor = 0;
                    for (k = i; k >= 1; k--) {
                        if (GameplayFunctionality.gameplayTable[k][j] != 0) {
                            contPiecesToDrop++;
                            piecesToDrop[contPiecesToDrop] = k;
                        } else
                            piecesMissing++;
                        //System.out.println("Pieces Missing : " + piecesMissing + " cont " + contPiecesToDrop);

                    }

                    //System.out.println("-------------------------");
                    for (k = 1; k <= contPiecesToDrop; k++) {
                        //System.out.println("pieces to drop : " + piecesToDrop[k] + " " + j);
                        int aux = i - k + 1;
                        //System.out.println("where to drop : " + aux + " " + j);
                        GameplayFunctionality.gameplayTable[i - k + 1][j] = GameplayFunctionality.gameplayTable[piecesToDrop[k]][j];
                        GameplayFunctionality.gameplayTable[piecesToDrop[k]][j] = 0;
                        contor++;
                        //System.out.println(contor);
                        pieces[i - k + 1][j] = pieces[piecesToDrop[k]][j];
                        pieces[piecesToDrop[k]][j] = null;
                        /*pieces[whereToDrop[1]-k+1][whereToDrop[2]].setY(GameplayFunctionality.gameplayPieceDimension*(whereToDrop[1]-k+1-1));
                        pieces[whereToDrop[1]-k+1][whereToDrop[2]].setX(GameplayFunctionality.gameplayPieceDimension*(whereToDrop[2]-1));*/
                        dropPieceAnimation(i - k + 1, j, GameplayFunctionality.gameplayPieceDimension * (i - k + 1 - 1));

                    }

                    for (k = 1; k <= piecesMissing; k++) {

                        //System.out.println(i-(contPiecesToDrop+k-1)+" "+j);
                        pieces[i - (contPiecesToDrop + k - 1)][j] = new ImageView(this);
                        GameplayFunctionality.generatePieceGraphics(gameframe, resources, pieces, i - (contPiecesToDrop + k - 1), j, 0 - (GameplayFunctionality.gameplayPieceDimension * k), GameplayFunctionality.gameplayPieceDimension * (j - 1), context);

                        dropPieceAnimation(i - (contPiecesToDrop + k - 1), j, GameplayFunctionality.gameplayPieceDimension * (i - k - contPiecesToDrop));
                    }
                    //GameplayFunctionality.showLogTable();

                }
            }
        }

    }

    private void dropPieceAnimation(final int starti, final int startj, final int finishi) {
        final Timer dropAnimation = new Timer();
        final Handler dropAnimationHandler = new Handler();

        dropAnimation.schedule(new TimerTask() {
            @Override
            public void run() {
                dropAnimationHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        animationDelay = GameplayFunctionality.delayForAnimations;
                        if (animationIsGoing == false) ;
                        {

                            pieces[starti][startj].setY(pieces[starti][startj].getY() + GameplayFunctionality.pieceDropSpeed);
                            if (pieces[starti][startj].getY() + GameplayFunctionality.pieceDropSpeed > finishi) {
                                pieces[starti][startj].setY(finishi);
                                dropAnimation.purge();
                                dropAnimation.cancel();
                                //piecesCombinationElimination(); // posibil sa cauzeze errori
                            }

                        }
                    }
                });
            }
        }, 0, 10);
    }


    private void piecesInitialization() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++)
                pieces[i][j] = new ImageView(this);
        }
    }

    private void selectInitialization() {
        for (int i = 0; i <= 2; i++) {
            selectedpiece[i] = new ImageView(this);
            selectedpiece[i].setImageDrawable(resources.getDrawable(R.drawable.selected));
        }
    }

    private void generateGameplayTable() {
        GameplayFunctionality.gameScore = 0;
        gameCreated = true;
        piecesInitialization();
        selectInitialization();
        GameplayFunctionality.generateTable();
        GameplayFunctionality.showLogTable();
        GameplayFunctionality.generateTableGraphics(gameframe, resources, pieces);
        if (gamePause == false)
            if (GameplayFunctionality.getCombination() != null) {
                animationTimeRequest();
                piecesCombinationElimination();
            }
    }


    private void resetGameplayTable() {
        if (gameCreated == true) {

            countdownGameDuration.cancel();
            countdownGameDuration.purge();

            gameCreated = false;


            GameplayFunctionality.calculateScoreIncrement();
            textViewScore.setText(Integer.toString(GameplayFunctionality.gameScore));
            if ((GameplayFunctionality.gameDurationCurrent / 10) % 60 > 9)
                textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
            else
                textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":0" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
            textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.gameScoreMultiplierCurrent));

            GameplayFunctionality.gameMoreTimeCharges = 0;
            imageViewMoreTimeCharges.setImageDrawable(imageViewMoreTimeChargesDrawable[0]);
            deleteGameplayTable();
        }
        if(gameEnd==true){
            buttonCreateTable.setVisibility(View.INVISIBLE);
            fragmentManager.beginTransaction().replace(smallmenulayout.getId(), smallScoreMenu, smallScoreMenu.getTag()).commit();
        }
        else
            buttonCreateTable.setVisibility(View.VISIBLE);
    }

    private void deleteGameplayTable() {
        GameplayFunctionality.resetTable(pieces, context);
        if (select1 == true) {
            ((ViewManager) selectedpiece[1].getParent()).removeView(selectedpiece[1]);
            if (select2 == true)
                ((ViewManager) selectedpiece[2].getParent()).removeView(selectedpiece[2]);
            selectInitialization();
            select1 = false;
            select2 = false;
        }

    }

    private void gameDurationContdown() {
        countdownGameDuration = new Timer();
        final Handler countdownGameDurationHandler = new Handler();
        countdownGameDuration.schedule(new TimerTask() {
            @Override
            public void run() {
                countdownGameDurationHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (countdownStop == false && GameplayFunctionality.gameDurationCurrent > 0)
                            GameplayFunctionality.gameDurationCurrent--;
                        if (GameplayFunctionality.gameDurationCurrent % 1 == 0 && GameplayFunctionality.gameDurationCurrent > 0) {
                            if ((GameplayFunctionality.gameDurationCurrent / 10) % 60 > 9)
                                textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
                            else
                                textViewGameDuration.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) / 60) + ":0" + Integer.toString((GameplayFunctionality.gameDurationCurrent / 10) % 60));
                        }
                        //System.out.println("Animation : " + animationIsGoing + " " + animationDelay);
                        if (GameplayFunctionality.gameDurationCurrent <= 0 && animationIsGoing == false && gamePause == false) {
                            gamePause = true;
                            animationIsGoing = false;
                            animationDelay = 0;
                            buttonCreateTable.setVisibility(View.INVISIBLE);

                            gameEnd=true;
                            GameplayFunctionality.savedScore=GameplayFunctionality.gameScore;

                            resetGameplayTable();
                            gameCreated = false;
                            countdownGameDuration.cancel();
                            countdownGameDuration.purge();
                        }
                    }
                });
            }
        }, 0, 100);
    }



    private void animationTimeRequest() {
        animationIsGoing = true;
        final Timer countdownAnimation = new Timer();
        final Handler countdownHandler = new Handler();
        countdownAnimation.schedule(new TimerTask() {
            @Override
            public void run() {
                countdownHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        animationDelay--;
                        //System.out.println("ANIMATION DELAY THREAD : " + animationDelay +" "+ animationIsGoing);
                        if (animationDelay <= 0) {
                            animationIsGoing = false;
                            //System.out.println("ANIMATION DELAY THREAD ACCESED : " + animationDelay+" "+ animationIsGoing);
                            countdownAnimation.cancel();
                            countdownAnimation.purge();
                            if (GameplayFunctionality.getCombination() != null && gamePause == false && gameCreated == true) {
                                piecesCombinationElimination();
                                animationIsGoing = true;
                                animationDelay = GameplayFunctionality.delayForAnimations;
                                animationTimeRequest();
                            }

                        }
                    }
                });
            }
        }, 500, 50);
    }

    private boolean checkCornerValidation(int x, int y) {
        if (GameplayFunctionality.gameplayVerifTable[x][y] != -1)
            if (GameplayFunctionality.gameplayVerifTable[x - 1][y - 1] != 1 && GameplayFunctionality.gameplayVerifTable[x + 1][y + 1] != 1 && GameplayFunctionality.gameplayVerifTable[x - 1][y + 1] != 1 && GameplayFunctionality.gameplayVerifTable[x + 1][y - 1] != 1)
                return true;
        return false;
    }
}

