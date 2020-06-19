package com.example.gameproject.option;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gameproject.R;
import com.example.gameproject.gameplay.MainGameplay;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SmallMenuGameplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SmallMenuGameplay extends Fragment {

    Button buttonReset,buttonExitToScreen;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SmallMenuGameplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SmallMenuGameplay.
     */
    // TODO: Rename and change types and number of parameters
    public static SmallMenuGameplay newInstance(String param1, String param2) {
        SmallMenuGameplay fragment = new SmallMenuGameplay();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_small_menu_gameplay, container, false);
        buttonExitToScreen = (Button) view.findViewById(R.id.buttonExitToScreen);
        buttonReset = (Button) view.findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainGameplay.callResetGameplayTable();
            }
        });
        buttonExitToScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainGameplay.goBackToMenu();
            }
        });
        return view;

    }
}