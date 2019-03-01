package com.example.pablo_mini_project1_return;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */

public class console<onBackPressed> extends Fragment {

    View myView; // current view
    TextView outputText; // the textview for the output
    String all; // string that will hold the current output

    String save1name; // holds name of save1
    String save2name; // holds name of save2
    String save3name; // holds name of save3
    String save1; // holds the content of save file 1
    String save2; // holds the content of save file 2
    String save3; // holds the content of save file 3
    String currsave; //holds the current save being used
    String bootup; // tracks if the app has been booted
    String lasteval; //tracks the last evaluation done so the choices will come back

    // misc things needed for certain functionalities
    private static console instance = null;
    public static Context context;
    String separator = System.getProperty("line.separator");

    public console() {
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
        myView = inflater.inflate(R.layout.fragment_console, container, false);
        context = myView.getContext();

        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        outputText = (TextView) myView.findViewById(R.id.consoleout);
        outputText.setMovementMethod(new ScrollingMovementMethod());

        if(savedInstanceState != null) {
            all = savedInstanceState.getString("all");
            lasteval = savedInstanceState.getString("lasteval");
            bootup = "yes";
        } else {
            all = "";
        }

        readFile("all", "all.txt", context);
        readFile("lasteval","lasteval.txt", context);
        readFile("save1","save1.txt", context);
        readFile("save2","save2.txt", context);
        readFile("save3","save3.txt", context);
        readFile("save1name","save1name.txt", context);
        readFile("save2name","save2name.txt", context);
        readFile("save3name","save3name.txt", context);

        try {
            readFile("bootup","bootup.txt",context);
            if(bootup instanceof String) {
                readFile("bootup", "bootup.txt", context);
                bootup();
            } else {
                bootup = "yes";
                writeFile(bootup, "bootup.txt",context);
                bootup();
            }
        } catch (NullPointerException p) {
            bootup = "yes";
            writeFile(bootup, "bootup.txt",context);
            bootup();
        }

        outputText.setText(all);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("all", all);
        outState.putString("lasteval", lasteval);
        writeFile(all, "all.txt", context);
        if(lasteval instanceof String){
            writeFile(lasteval, "lasteval.txt", context);
        }

        if(save1 instanceof String){
            writeFile(save1, "save1.txt", context);
        }/*
        if(save2 instanceof String){
            writeFile(save2, "save2.txt", context);
        }
        if(save3 instanceof String){
            writeFile(save3, "save3.txt", context);
        }
        if(save1name instanceof String){
            writeFile(save1name, "save1name.txt", context);
        }
        if(save2name instanceof String){
            writeFile(save2name, "save2name.txt", context);
        }
        if(save3name instanceof String){
            writeFile(save3name, "save3name.txt", context);
        }*/
    }

    public void writeFile(String s, String destination, Context ctx) {
        // given a string put into destination file

        s = s.replace(separator,"~");

        try {
            FileOutputStream fileOutputStream = ctx.openFileOutput(destination, MODE_PRIVATE);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String destination, String openme, Context ctx) {
        // read a text file string given the file and the destination string

        String lines; // temporary string

        try {
            FileInputStream fileInputStream = ctx.openFileInput(openme);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            if(!destination.equals("nod")) {
                lines = bufferedReader.readLine();
                if (destination.equals("bootup")) {
                    bootup = lines.replace("~", separator);
                } else if (destination.equals("save1")) {
                    save1 = lines.replace("~", separator);
                } else if (destination.equals("save2")) {
                    save2 = lines.replace("~", separator);
                } else if (destination.equals("save3")) {
                    save3 = lines.replace("~", separator);
                } else if (destination.equals("save1name")) {
                    save1name = lines;
                } else if (destination.equals("save2name")) {
                    save2name = lines;
                } else if (destination.equals("save3name")) {
                    save3name = lines;
                } else if (destination.equals("lasteval")) {
                    lasteval = lines;
                } else {
                    all = lines.replace("~", separator);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean FileExists(String fname) {
        // checks if the file exists for whether or not to open a save file in the beginning
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }

    public static console getInstance() {
        // give instance to use in other fragments
        return instance;
    }

    void setConsole(String s) {
        // set the string of the current save
        all += s + separator;
        outputText.setText(all);
    }

    void bootup() {
        //give the bootup sequence
        all += "booting..." + separator;
        all += "root    (hd0,0)" + separator;
        all += "Filesystem loading..." + separator;
        all += "kernal /root/vm/automAI = /dev/ ro md=0/sda3/image" + separator;
        all += "Probing for Daemons..." + separator;
        all += "Booting the Daemons..." + separator;
        all += "done." + separator;
        all += ":: Starting processes..." + separator;
        all += "done." + separator;
        all += "Loading partitions..." + separator;
        all += "Select partition:" + separator;

        try {
            readFile("save1name","save1name.txt",context);
            if(save1name instanceof String) {
                all += "save1: " + save1name + separator;
            } else {
                all += "save1: empty" + separator;
                save1name = "save1: empty";
                writeFile("save1: empty", "save1name.txt",context);
            }
        } catch (NullPointerException p) {
            all += "save1: empty" + separator;
            writeFile("save1: empty", "save1name.txt",context);
        }
        try {
            readFile("save2name","save2name.txt",context);
            if(save2name instanceof String) {
                all += "save2: " + save2name + separator;
            } else {
                all += "save2: empty" + separator;
                save2name = "save2: empty";
                writeFile("save2: empty", "save2name.txt",context);
            }
        } catch (NullPointerException p) {
            all += "save2: empty" + separator;
            writeFile("save2: empty", "save2name.txt",context);
        }
        try {
            readFile("save3name","save3name.txt",context);
            if(save3name instanceof String) {
                all += "save3: " + save3name + separator;
            } else {
                all += "save3: empty" + separator;
                writeFile("save3: empty", "save3name.txt",context);
            }

        } catch (NullPointerException p) {
            all += "save3: empty" + separator;
            writeFile("save3: empty", "save3name.txt",context);
        }

        userinput.getInstance().bootselect("> save1: open partition", "> save2: open partition", "> save3: open partition");

        outputText.setText(all);
    }

    public void loadSave(String saved) {
        // load the save file and change name if there was no name in the beginning

        if(saved.equals("save1")) {
            //try {
                readFile("save1","save1.txt",context);
            //} catch (Exception e) {
            if(!(save1 instanceof String)) {
                save1 = all;
                writeFile(all, "save1.txt", context);
            }
            //}
            all = save1;
        } else if(saved.equals("save2")){
            try {
                readFile("save2","save2.txt",context);
            } catch (Exception e) {
                save2 = all;
                writeFile(all, "save2.txt",context);
            }
            all = save2;
        } else if(saved.equals("save3")) {
            try {
                readFile("save3","save3.txt",context);
            } catch (Exception e) {
                save3 = all;
                writeFile(all, "save3.txt",context);
            }
            all = save3;
        }
        //setConsole(all);
        currsave = saved;
        if(!(lasteval instanceof String) || lasteval == "ls1") {
            userinput.getInstance().getsavename();
        } else {
            sendlist(lasteval); // make lastevals separate for each save?
        }

    }

    public void setsavename(String savename) {
        if (currsave.equals("save1")) {
            writeFile(savename, "save1name.txt",context);
        } else if (currsave.equals(("save2"))) {
            writeFile(savename, "save2name.txt", context);
        } else if (currsave.equals("save3")) {
            writeFile(savename,"save3name.txt",context);
        }
        setConsole("New save file named " + savename + " created...");
        chooser("begin", "filler");
        //userinput.getInstance().listFill("Who are you?", "", "", "begin");
    }

    public void chooser(String choice, String ans) {
        // gets choice from the user to give correct story output and then new choices

        switch(choice) {
            case "begin" :
                setConsole("Hello, I am the Caretaker. I find lost processes with corrupted Return designations." +
                        separator + "I'm here to help you find your Daemon.");
                lasteval = "QS_begin";
                sendlist(lasteval);
                break;
            case "b2" :
                // comes from e1, QS_begin
                switch (ans) {
                    case "> Why am I here?" :
                        setConsole("You are here, at my lost process haven, because you have a corrupted Return designation.");
                        lasteval = "QS2";
                        sendlist(lasteval);
                        break;
                    case "> Who are you?" :
                        setConsole("I am the Caretaker. I am a process that has come to take a liking to helping those without Returns since at some point I didn't have one either.");
                        lasteval = "QS3";
                        sendlist(lasteval);
                        break;
                    case "> What is a Daemon?" :
                        setConsole("Daemon is the designation given to processes that keep their purpose after a Hew.");
                        lasteval = "QS4";
                        sendlist(lasteval);
                        break;
                }
                setConsole("");
                lasteval = "ls2";
                sendlist(lasteval);
                break;
            case "b3" :
                // comes from e2, QS2
                switch (ans) {
                    case "> What is a Return designation?" :
                        setConsole("A Return designation is the purpose you have to finish your individual process and then go back to the Daemon that you hewed from.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> How did my Return designation get corrupted?" :
                        setConsole("I'm not sure how it did. I wish I knew because then I could help processes like you much easier");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> What is a Daemon?" :
                        setConsole("A Daemon is a process that keeps its purpose after hewing.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                }
                break;
            case "b4" :
                // comes from e3, QS3
                switch (ans) {
                    case "> Who am I?" :
                        setConsole("I don't know who you are, but I hope we can find that out!");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> What is a Daemon?" :
                        setConsole("Daemon is the designation given to processes that keep their purpose after a Hew.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> What is a Return designation?" :
                        setConsole("A Return designation is the purpose you have to finish your individual process and then go back to the Daemon that you hewed from.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                }
                break;
            case "b5" :
                // comes from e4, QS4
                switch (ans) {
                    case "> Who am I?" :
                        setConsole("I don't know who you are, but I hope we can find that out!");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> What is a Return designation?" :
                        setConsole("A Return designation is the purpose you have to finish your individual process and then go back to the Daemon that you hewed from.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                    case "> Who are you?" :
                        setConsole("I am the Caretaker. I am a process that has come to take a liking to helping those without Returns since at some point I didn't have one either.");
                        lasteval = "QS5";
                        sendlist(lasteval);
                        break;
                }
                break;
            case "b6" :
                // not done yet comes from e5, QS5
                switch (ans) {
                    case "> Where should I go?" :
                        setConsole("I think it would be a good idea to first get to know a bit about you and your abilities.");
                        //lasteval = "QS5";
                        //sendlist(lasteval);
                        break;
                    case "> I want to go home." :
                        setConsole("Hopefully soon we can get you there, for now you should rest.");
                        //lasteval = "QS5";
                        //sendlist(lasteval);
                        break;
                    case "> Thanks but no thanks, I'm not buying it." :
                        setConsole("What?");
                        //lasteval = "QS5";
                        //sendlist(lasteval);
                        break;
                }
                break;
            default:
                all += "END OF GAME";
                break;
        }
    }

    public void sendlist(String l) {

        switch (l) {
            case "QS_begin":
                userinput.getInstance().listFill("> Why am I here?", "> Who are you?", "> What is a Daemon?", "e1");
                break;
            case "QS2" :
                // for question Why am I here?
                userinput.getInstance().listFill("> What is a Return designation?", "> How did my Return designation get corrupted?", "> What is a Daemon?", "e2");
                break;
            case "QS3" :
                // for question Who are you?
                userinput.getInstance().listFill("> Who am I?", "> What is a Daemon?", "> What is a Return designation?", "e3");
                break;
            case "QS4" :
                // for question What is a Daemon?
                userinput.getInstance().listFill("> Who am I?", "> What is a Return designation?", "> Who are you?", "e4");
            case "QS5" :
                // for questions from QS2, QS3, QS4
                userinput.getInstance().listFill("> Where should I go?", "> I want to go home.", "> Thanks but no thanks, I'm not buying it.", "e5");
            default:
                break;
        }
    }

}
