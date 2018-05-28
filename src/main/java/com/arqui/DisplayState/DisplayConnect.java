package com.arqui.DisplayState;

import java.util.ArrayList;

public final class DisplayConnect {
    private static ArrayList<Option> Options=new ArrayList<Option>(){
        {
            add(new Option("Enter mailbox number followed by #", true));

        }
    };
    private static Option Error=new Option("Incorrect mailbox number. Try again!", true);
}
