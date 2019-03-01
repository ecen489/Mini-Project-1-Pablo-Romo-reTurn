package com.example.pablo_mini_project1_return;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class userinput extends Fragment {

    View myView;
    String evaluate;
    EditText inputText;
    Button grabname;
    String[] changinglist = {"","",""};
    private static userinput instance = null;

    public userinput() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_userinput, container, false);

        inputText = (EditText) myView.findViewById(R.id.userin);
        grabname = (Button) myView.findViewById(R.id.grabname);

        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(savedInstanceState != null) {
            changinglist[0] = savedInstanceState.getString("change1");
            changinglist[1] = savedInstanceState.getString("change2");
            changinglist[2] = savedInstanceState.getString("change3");
            listFill(changinglist[0], changinglist[1], changinglist[2], "nothing");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("change1", changinglist[0]);
        outState.putString("change2", changinglist[1]);
        outState.putString("change3", changinglist[2]);
    }

    public static userinput getInstance() {
        return instance;
    }

    public void bootselect(String save1, String save2, String save3) {
        // give the user a listview of what partitions to open
        changinglist[0] = save1;
        changinglist[1] = save2;
        changinglist[2] = save3;

        ListView list = (ListView) myView.findViewById(R.id.mainlist);
        list.setVisibility(View.VISIBLE);
        list.setAdapter(new ArrayAdapter<String>(myView.getContext(), android.R.layout.simple_list_item_1 , changinglist));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    console.getInstance().setConsole("> Starting parition save1");
                    console.getInstance().loadSave("save1");
                }
                if(position == 1) {
                    console.getInstance().setConsole("> Starting parition save2");
                    console.getInstance().loadSave("save2");
                }
                if(position == 2) {
                    console.getInstance().setConsole("> Starting partition save3");
                    console.getInstance().loadSave("save3");
                }
            }
        });
    }

    public void listFill(String c1, String c2, String c3, String eval) {
        // give the user a listview of what partitions to open
        changinglist[0] = c1;
        changinglist[1] = c2;
        changinglist[2] = c3;

        evaluate = eval;

        ListView list = (ListView) myView.findViewById(R.id.mainlist);
        list.setVisibility(View.VISIBLE);
        list.setAdapter(new ArrayAdapter<String>(myView.getContext(), android.R.layout.simple_list_item_1 , changinglist));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    console.getInstance().setConsole(changinglist[0]);
                    if(!evaluate.equals("nothing")){
                        evaluation(evaluate, changinglist[0]);
                    }
                }
                if(position == 1) {
                    console.getInstance().setConsole(changinglist[1]);
                    if(!evaluate.equals("nothing")){
                        evaluation(evaluate, changinglist[1]);
                    }
                }
                if(position == 2) {
                    console.getInstance().setConsole(changinglist[2]);
                    if (!evaluate.equals("nothing")) {
                        evaluation(evaluate, changinglist[2]);
                    }
                }
            }
        });
    }

    public void getsavename() {
        ListView list = (ListView) myView.findViewById(R.id.mainlist);
        list.setVisibility(View.INVISIBLE);

        inputText = (EditText) myView.findViewById(R.id.userin);
        grabname = (Button) myView.findViewById(R.id.grabname);

        inputText.setVisibility(View.VISIBLE);
        grabname.setVisibility(View.VISIBLE);

        grabname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                console.getInstance().setsavename(inputText.getText().toString());
                inputText.setVisibility(View.INVISIBLE);
                grabname.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void evaluation(String eval, String ans) {
        // redirects the input of the user to the correct output string of the story

        switch(eval) {
            case "e1" :
                console.getInstance().chooser("b2", ans);
                break;
            case "e2" :
                console.getInstance().chooser("b3", ans);
            case "e3" :
                console.getInstance().chooser("b4", ans);
            case "e4" :
                console.getInstance().chooser("b5", ans);
            case "e5" :
                console.getInstance().chooser("b6", ans);
            default :
                break;
        }
    }

}
