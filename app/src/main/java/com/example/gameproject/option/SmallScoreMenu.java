package com.example.gameproject.option;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gameproject.CommunicationService;
import com.example.gameproject.R;
import com.example.gameproject.gameplay.GameplayFunctionality;
import com.example.gameproject.gameplay.MainGameplay;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SmallScoreMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SmallScoreMenu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textViewInfoScore,textViewInfoLoading;
    private EditText editTextPersonName;
    private Button buttonReset,buttonSend;
    private ListView listScores;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SmallScoreMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SmallScoreMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static SmallScoreMenu newInstance(String param1, String param2) {
        SmallScoreMenu fragment = new SmallScoreMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private Context context;
    private boolean ok=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_small_score_menu, container, false);
        context=view.getContext();
        textViewInfoScore= (TextView) view.findViewById(R.id.textViewInfoScore);
        textViewInfoScore.setText(Integer.toString(GameplayFunctionality.savedScore));
        buttonSend = (Button) view.findViewById(R.id.buttonSend);
        buttonReset = (Button) view.findViewById(R.id.buttonBack);
        editTextPersonName = (EditText) view.findViewById(R.id.editTextPersonName);
        listScores=view.findViewById(R.id.listScores);
        textViewInfoLoading=view.findViewById(R.id.textViewInfoLoading);
        textViewInfoLoading.setVisibility(View.INVISIBLE);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPersonName.getText().toString().equals("")==false && editTextPersonName.getText().length()>4)
                {
                    listScores.setVisibility(View.VISIBLE);
                    System.out.println("IF 1");
                    final String username=editTextPersonName.getText().toString();
                    MainGameplay.sendDataToServer(username,editTextPersonName    );
                    editTextPersonName.setVisibility(View.INVISIBLE);
                    buttonSend.setVisibility(View.INVISIBLE);
                    textViewInfoScore.setVisibility(View.INVISIBLE);
                    view.findViewById(R.id.textViewInfo1).setVisibility(View.INVISIBLE);
                    editTextPersonName.setText("");
                    textViewInfoLoading.setVisibility(View.VISIBLE);
                    textViewInfoLoading.setText("Loading");
                    new CountDownTimer(3500, 1000) {

                        public void onTick(long millisUntilFinished) {
                            System.out.println("seconds remaining: " + millisUntilFinished / 1000);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            int rembemberId=1;
                            if(CommunicationService.numberOfInstances>0){
                                textViewInfoLoading.setVisibility(View.INVISIBLE);
                                ArrayList<String> listItems=new ArrayList<String>();
                                for(int i=1;i<=CommunicationService.numberOfInstances;i++){
                                    System.out.println(CommunicationService.scoreClassData[i].name+" "+ Integer.toString(CommunicationService.scoreClassData[i].score));
                                    listItems.add(Integer.toString(i)+". "+CommunicationService.scoreClassData[i].name);
                                    listItems.add("Score: "+CommunicationService.scoreClassData[i].score);
                                    if(username.equals(CommunicationService.scoreClassData[i].name)){
                                        rembemberId=i;
                                    }
                                }
                                //simple_list_item_1
                                ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.list_view_score, listItems);
                                listScores.setAdapter(adapter);
                                listScores.setSelection((rembemberId-1)*2);
                                System.out.println("done!");
                            }
                            else
                                textViewInfoLoading.setText("Failed to connect to server \n (restart needed)");





                        }
                        ;
                    }.start();

                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainGameplay.closeScoreWindow();
            }
        });
        return view;

    }
}