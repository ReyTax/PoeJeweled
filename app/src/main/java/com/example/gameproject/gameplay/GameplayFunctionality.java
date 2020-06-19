package com.example.gameproject.gameplay;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

import java.util.Random;

public class GameplayFunctionality extends AppCompatActivity {

    public static int gameScore=0;
    public static int savedScore=0;
    public static int gameScoreIncreaseCurrent = 100;
    public static int gameScoreIncreaseDefault[]={0,10,25,100,150,200,300,400,500};
    public static int gameScoreIncreaseExtraCombinationDefault[]={0,0,0,0,50,75,100,150,200};
    public static float gameScoreMultiplierCurrent = 1;
    public static float gameScoreMultiplierDefault[] = {0,0.1f,0.5f,1,1.5f,2,3,5};
    public static float gameScoreMultiplierIncreaseCurrent = 0.2f;
    public static float gameScoreMultiplierIncreaseDefault[]={0,0,0,0.1f,0.3f,0.5f,0.7f,1};
    public static int delayForAnimations=5;
    public static int gameDurationDefault[]={1200,2400,4800};
    public static int gameDurationScoreDivision[]={1,2,4};
    public static int gameDurationOption=1;
    public static int gameDurationCurrent=1200;
    public static int gameMoreTimeCharges=0;
    public static int gameMoreTimeChargesLimit=4;
    public static int gameDurationPerCharge=50;
    public static int combinationsNeededForOneCharge=4;

    public static int gameplayTableDimension=0;
    public static int gameplayPieceDimension=0;
    public static int gameplayTable[][]=new int[9][9];
    public static int gameplayVerifTable[][]=new int[11][11];
    public static int pieceMovementSpeed=15;
    public static int pieceDropSpeed=35;
    public static int pieceTypesDefault=4;
    public static int pieceTypes=4;
    public static int combinationLimit=4;
    public static int pieceTypeOrder[]=new int[9];

    //optiuni pentru mainOptions
    public static int selectOptionPiece[]={1,1,1,1,0,0,0,0,0};

    public static void generatePiecesNumber(){
        pieceTypes=0;

        for(int i=0;i<=8;i++)
        {
            if(selectOptionPiece[i]==1){
                pieceTypeOrder[pieceTypes]=i;
                pieceTypes++;
            }
        }
    }

    public static void calculateScoreIncrement(){
        gameScoreIncreaseCurrent=gameScoreIncreaseDefault[pieceTypes-1];
        gameScoreMultiplierCurrent=gameScoreMultiplierDefault[combinationLimit-1];
        gameScoreMultiplierIncreaseCurrent=gameScoreMultiplierIncreaseDefault[combinationLimit-1];
        gameDurationCurrent=gameDurationDefault[gameDurationOption-1];
        gameScore=0;
    }
    public static void generateTable(){
        int i,j;
        Random random=new Random();
        for(i=0;i<=9;i++){
            for(j=0;j<=9;j++){
                gameplayVerifTable[i][j]=-1;
            }
        }

        generatePiecesNumber();
        calculateScoreIncrement();

        for(i=1;i<=8;i++){
            for(j=1;j<=8;j++){
                gameplayTable[i][j]=random.nextInt(pieceTypes) + 1;
                gameplayVerifTable[i][j]=0;
            }
        }
        if(getCombination()!=null && pieceTypes>=3 && combinationLimit>=3){
            generateTable();
        }
    }

    public static void showLogTable(){
        int i,j;
        for(i=1;i<=8;i++){
            for(j=1;j<=8;j++){
                System.out.print(gameplayTable[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void showLogVerifTable(){
        int i,j;
        for(i=1;i<=8;i++){
            for(j=1;j<=8;j++){
                System.out.print(gameplayVerifTable[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void generateTableGraphics(ViewGroup viewGroup,Resources resources, ImageView view[][]){

        Drawable piecesAssets[]={resources.getDrawable(R.drawable.piece1),resources.getDrawable(R.drawable.piece2),resources.getDrawable(R.drawable.piece3),resources.getDrawable(R.drawable.piece4),resources.getDrawable(R.drawable.piece5),resources.getDrawable(R.drawable.piece6),resources.getDrawable(R.drawable.piece7),resources.getDrawable(R.drawable.piece8),resources.getDrawable(R.drawable.piece9)};
        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams(GameplayFunctionality.gameplayPieceDimension, GameplayFunctionality.gameplayPieceDimension);

        int i,j,vali=-1,valj;


        for(i=1;i<=8;i++){
            vali++;
            valj=-1;
            for(j=1;j<=8;j++){
                valj++;
                view[i][j].setImageDrawable(piecesAssets[pieceTypeOrder[gameplayTable[i][j]-1]]);
                view[i][j].setY(gameplayPieceDimension*vali);
                view[i][j].setX(gameplayPieceDimension*valj);
                view[i][j].requestLayout();
                view[i][j].setLayoutParams(layoutParams);
                viewGroup.addView( view[i][j]);
            }
        }
    }
    public static void generatePieceGraphics(ViewGroup viewGroup,Resources resources, ImageView view[][],int i,int j,int locationi,int locationj,Context context){

        Drawable piecesAssets[]={resources.getDrawable(R.drawable.piece1),resources.getDrawable(R.drawable.piece2),resources.getDrawable(R.drawable.piece3),resources.getDrawable(R.drawable.piece4),resources.getDrawable(R.drawable.piece5),resources.getDrawable(R.drawable.piece6),resources.getDrawable(R.drawable.piece7),resources.getDrawable(R.drawable.piece8),resources.getDrawable(R.drawable.piece9)};
        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams(GameplayFunctionality.gameplayPieceDimension, GameplayFunctionality.gameplayPieceDimension);

        Random random=new Random();
        gameplayTable[i][j]=random.nextInt(pieceTypes) + 1;
        System.out.println(gameplayTable[i][j] + " " + i + " " + j);

        view[i][j].setImageDrawable(piecesAssets[pieceTypeOrder[gameplayTable[i][j]-1]]);
        view[i][j].setY(locationi);
        view[i][j].setX(locationj);
        view[i][j].requestLayout();
        view[i][j].setLayoutParams(layoutParams);
        viewGroup.addView( view[i][j]);
    }




    public static void resetTable(ImageView view[][], Context context){
        int i,j;
        for(i=1;i<=8;i++){
            for(j=1;j<=8;j++){
                if(view[i][j]!=null)
                ((ViewManager) view[i][j].getParent()).removeView(view[i][j]);
            }
        }
        for(i=1;i<=8;i++){
            for(j=1;j<=8;j++)
                view[i][j] = new ImageView(context);
        }
    }
    public static int[] getCombination(){
        int i,j,contor,maxcontor=0,firsti=0,firstj=0,lasti=0,lastj=0,orientation=0,color=1;
        boolean combFound=false;
        for(i=1;i<=8&&combFound==false;i++){
            contor=0;

            color=gameplayTable[i][1];
            for(j=1;j<=8&&combFound==false;j++){
                if(color==gameplayTable[i][j] && color!=0){
                    contor++;
                    if(contor>=combinationLimit && maxcontor<contor){
                        maxcontor=contor;
                        lasti=i;
                        lastj=j;
                        orientation=1;//orizontala
                    }
                }
                else
                {
                    if(contor>=combinationLimit)
                        combFound=true;
                    contor=1;

                    color=gameplayTable[i][j];
                }

            }
            if(contor>=combinationLimit)
                combFound=true;
        }
        if(combFound==false)
        for(j=1;j<=8&&combFound==false;j++){
            contor=0;

            color=gameplayTable[1][j];
            for(i=1;i<=8&&combFound==false;i++){
                if(color==gameplayTable[i][j] && color!=0){
                    contor++;
                    if(contor>=combinationLimit && maxcontor<contor){
                        maxcontor=contor;
                        lasti=i;
                        lastj=j;
                        orientation=2;//orizontala
                    }
                }
                else
                {
                    if(contor>=combinationLimit)
                        combFound=true;
                    contor=1;

                    color=gameplayTable[i][j];
                }

            }
            if(contor>=combinationLimit)
                combFound=true;
        }
        if(combFound==true){
            int combinatie[]= new int[7];
            if(orientation==1){
                firstj=lastj-maxcontor+1;
                firsti=lasti;
            }
            else{
                firsti=lasti-maxcontor+1;
                firstj=lastj;
            }


            combinatie[1] = firsti;
            combinatie[2] = firstj;
            combinatie[3] = lasti;
            combinatie[4] = lastj;
            combinatie[5] = maxcontor;
            combinatie[6] = orientation;

            return combinatie;
        }
            return null;
    }

}

