package com.example.gameproject.option;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gameproject.R;
import com.example.gameproject.gameplay.GameplayFunctionality;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainOptionsActivity extends AppCompatActivity {



    private Resources resources;
    private ImageView imageViewPiece[];
    private  Drawable drawablePieceActive[];
    private  Drawable drawablePieceInactive[];
    private TextView textViewSelectedPieces, textViewWarningPieceNumber,textViewDefaultInfo,textViewSelectedPiecesScore,textViewCombinationLimit,textViewMultiplier,textViewMultiplierIncrease,textViewTimeLimit,textViewTimeLimitDivider;
    private int previousColor;
    private Button buttonGoBack,buttonResetToDefault,buttonRightCombinationLimit,buttonLeftCombinationLimit,buttonRightTime,buttonLeftTime;
    private AnimationSet animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
        resources=getResources();

        imageViewPiece= new ImageView[]{findViewById(R.id.piece1),findViewById(R.id.piece2),findViewById(R.id.piece3),findViewById(R.id.piece4),findViewById(R.id.piece5),findViewById(R.id.piece6),findViewById(R.id.piece7),findViewById(R.id.piece8),findViewById(R.id.piece9)};
        drawablePieceActive= new Drawable[]{resources.getDrawable(R.drawable.piece1),resources.getDrawable(R.drawable.piece2),resources.getDrawable(R.drawable.piece3),resources.getDrawable(R.drawable.piece4),resources.getDrawable(R.drawable.piece5),resources.getDrawable(R.drawable.piece6),resources.getDrawable(R.drawable.piece7),resources.getDrawable(R.drawable.piece8),resources.getDrawable(R.drawable.piece9)};
        drawablePieceInactive= new Drawable[]{resources.getDrawable(R.drawable.piece1inactive),resources.getDrawable(R.drawable.piece2inactive),resources.getDrawable(R.drawable.piece3inactive),resources.getDrawable(R.drawable.piece4inactive),resources.getDrawable(R.drawable.piece5inactive),resources.getDrawable(R.drawable.piece6inactive),resources.getDrawable(R.drawable.piece7inactive),resources.getDrawable(R.drawable.piece8inactive),resources.getDrawable(R.drawable.piece9inactive)};
        textViewSelectedPieces= findViewById(R.id.textViewSelectedPieces);
        textViewWarningPieceNumber =findViewById(R.id.textViewWarningInfo1);
        textViewSelectedPiecesScore= findViewById(R.id.textViewInfoScorePiece);
        buttonRightCombinationLimit=findViewById(R.id.buttonRightCombination);
        buttonLeftCombinationLimit=findViewById(R.id.buttonLeftCombination);
        textViewCombinationLimit=findViewById(R.id.textViewCombinationLimit);
        textViewMultiplier=findViewById(R.id.textViewMultiplier);
        textViewMultiplierIncrease=findViewById(R.id.textViewMultiplierIncrease);
        textViewTimeLimit = findViewById(R.id.textViewTimeLimit);
        buttonRightTime=findViewById(R.id.buttonRightTime);
        buttonLeftTime=findViewById(R.id.buttonLeftTime);
        textViewTimeLimitDivider=findViewById(R.id.textViewTimeLimitDivider);
        previousColor = textViewSelectedPieces.getCurrentTextColor();

        for(int i=0;i<=8;i++){
            if(GameplayFunctionality.selectOptionPiece[i]==1)
                imageViewPiece[i].setImageDrawable(drawablePieceActive[i]);
            else
                imageViewPiece[i].setImageDrawable(drawablePieceInactive[i]);
        }

        buttonGoBack=findViewById(R.id.buttonGoBack);
        buttonResetToDefault=findViewById(R.id.buttonResetToDefault);

        textViewDefaultInfo=findViewById(R.id.textViewDefaultInfo);
        textViewDefaultInfo.setVisibility(View.INVISIBLE);

        textViewSelectedPieces.setText(Integer.toString(GameplayFunctionality.pieceTypes));
        textViewSelectedPiecesScore.setText(Integer.toString(GameplayFunctionality.gameScoreIncreaseDefault[GameplayFunctionality.pieceTypes-1]));
        textViewCombinationLimit.setText(Integer.toString(GameplayFunctionality.combinationLimit));
        textViewMultiplier.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit-1]));
        textViewMultiplierIncrease.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierIncreaseDefault[GameplayFunctionality.combinationLimit-1]));
        GameplayFunctionality.gameDurationCurrent=GameplayFunctionality.gameDurationDefault[GameplayFunctionality.gameDurationOption-1];
        if((GameplayFunctionality.gameDurationCurrent/10)%60>10)
            textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
        else
            textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":0"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
        textViewTimeLimitDivider.setText(Integer.toString(GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption-1]));



        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonResetToDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultButtonAnimation();
                System.out.println("Button DEFAULT");
                textViewDefaultInfo.setVisibility(View.VISIBLE);
                textViewDefaultInfo.setAnimation(animation);
                if(animation.hasEnded())
                    textViewDefaultInfo.clearAnimation();
                textViewDefaultInfo.setVisibility(View.INVISIBLE);
                for(int i=0;i<9;i++){
                    if(i<4){
                        GameplayFunctionality.selectOptionPiece[i]=1;
                        imageViewPiece[i].setImageDrawable(drawablePieceActive[i]);
                    }
                    else
                    {
                        GameplayFunctionality.selectOptionPiece[i]=0;
                        imageViewPiece[i].setImageDrawable(drawablePieceInactive[i]);
                    }

                }

                GameplayFunctionality.generatePiecesNumber();
                textViewSelectedPieces.setTextColor(previousColor);
                textViewWarningPieceNumber.setVisibility(View.INVISIBLE);
                textViewSelectedPieces.setText(Integer.toString(GameplayFunctionality.pieceTypes));
                textViewSelectedPiecesScore.setText(Integer.toString(GameplayFunctionality.gameScoreIncreaseDefault[GameplayFunctionality.pieceTypes-1]));
                GameplayFunctionality.combinationLimit=4;
                textViewCombinationLimit.setText(Integer.toString(GameplayFunctionality.combinationLimit));
                textViewMultiplier.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit-1]));
                textViewMultiplierIncrease.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierIncreaseDefault[GameplayFunctionality.combinationLimit-1]));
                GameplayFunctionality.gameDurationOption=1;
                GameplayFunctionality.gameDurationCurrent=GameplayFunctionality.gameDurationDefault[GameplayFunctionality.gameDurationOption-1];

                if((GameplayFunctionality.gameDurationCurrent/10)%60>10)
                    textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                else
                    textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":0"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                textViewTimeLimitDivider.setText(Integer.toString(GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption-1]));
            }
        });

        buttonRightCombinationLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameplayFunctionality.combinationLimit<8){
                    GameplayFunctionality.combinationLimit++;
                    textViewCombinationLimit.setText(Integer.toString(GameplayFunctionality.combinationLimit));
                    textViewMultiplier.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit-1]));
                    textViewMultiplierIncrease.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierIncreaseDefault[GameplayFunctionality.combinationLimit-1]));
                }
            }
        });
        buttonLeftCombinationLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameplayFunctionality.combinationLimit>1){
                    GameplayFunctionality.combinationLimit--;
                    textViewCombinationLimit.setText(Integer.toString(GameplayFunctionality.combinationLimit));
                    textViewMultiplier.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierDefault[GameplayFunctionality.combinationLimit-1]));
                    textViewMultiplierIncrease.setText(Float.toString(GameplayFunctionality.gameScoreMultiplierIncreaseDefault[GameplayFunctionality.combinationLimit-1]));
                }
            }
        });

        buttonRightTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameplayFunctionality.gameDurationOption<3){
                    GameplayFunctionality.gameDurationOption++;
                    GameplayFunctionality.gameDurationCurrent=GameplayFunctionality.gameDurationDefault[GameplayFunctionality.gameDurationOption-1];
                    if((GameplayFunctionality.gameDurationCurrent/10)%60>10)
                        textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                    else
                        textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":0"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                    textViewTimeLimitDivider.setText(Integer.toString(GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption-1]));

                }
            }
        });
        buttonLeftTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameplayFunctionality.gameDurationOption>1){
                    GameplayFunctionality.gameDurationOption--;
                    GameplayFunctionality.gameDurationCurrent=GameplayFunctionality.gameDurationDefault[GameplayFunctionality.gameDurationOption-1];
                    if((GameplayFunctionality.gameDurationCurrent/10)%60>10)
                        textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                    else
                        textViewTimeLimit.setText(Integer.toString((GameplayFunctionality.gameDurationCurrent/10)/60)+":0"+Integer.toString((GameplayFunctionality.gameDurationCurrent/10)%60));
                    textViewTimeLimitDivider.setText(Integer.toString(GameplayFunctionality.gameDurationScoreDivision[GameplayFunctionality.gameDurationOption-1]));
                }
            }
        });

        imageViewPiece[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(0);
            }
        });
        imageViewPiece[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(1);
            }
        });
        imageViewPiece[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(2);
            }
        });
        imageViewPiece[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(3);
            }
        });
        imageViewPiece[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(4);
            }
        });
        imageViewPiece[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(5);
            }
        });
        imageViewPiece[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(6);
            }
        });
        imageViewPiece[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(7);
            }
        });
        imageViewPiece[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionPiece(8);
            }
        });

    }

    private void defaultButtonAnimation(){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);

    }

    private void selectedOptionPiece(int piece){
        textViewSelectedPieces.setTextColor(previousColor);
        textViewWarningPieceNumber.setVisibility(View.INVISIBLE);
        System.out.println("PIECE : " + piece +" "+ GameplayFunctionality.selectOptionPiece[piece]);
        if(GameplayFunctionality.selectOptionPiece[piece]==0){
            GameplayFunctionality.selectOptionPiece[piece]=1;
            imageViewPiece[piece].setImageDrawable(drawablePieceActive[piece]);
            GameplayFunctionality.generatePiecesNumber();
            textViewSelectedPieces.setText(Integer.toString(GameplayFunctionality.pieceTypes));
            textViewSelectedPiecesScore.setText(Integer.toString(GameplayFunctionality.gameScoreIncreaseDefault[GameplayFunctionality.pieceTypes-1]));
        }
        else
        {
            if(GameplayFunctionality.pieceTypes>1){
                GameplayFunctionality.selectOptionPiece[piece]=0;
                imageViewPiece[piece].setImageDrawable(drawablePieceInactive[piece]);
                GameplayFunctionality.generatePiecesNumber();
                textViewSelectedPieces.setText(Integer.toString(GameplayFunctionality.pieceTypes));
                textViewSelectedPiecesScore.setText(Integer.toString(GameplayFunctionality.gameScoreIncreaseDefault[GameplayFunctionality.pieceTypes-1]));
            }
            else
            if(GameplayFunctionality.pieceTypes==1)
            {
                textViewWarningPieceNumber.setVisibility(View.VISIBLE);
                textViewSelectedPieces.setTextColor(Color.RED);
                textViewSelectedPieces.setText(Integer.toString(GameplayFunctionality.pieceTypes));
                textViewSelectedPiecesScore.setText(Integer.toString(GameplayFunctionality.gameScoreIncreaseDefault[GameplayFunctionality.pieceTypes-1]));
            }
            System.out.println("Count " + GameplayFunctionality.pieceTypes);

        }

    }
}