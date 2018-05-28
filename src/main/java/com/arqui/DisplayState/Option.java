package com.arqui.DisplayState;

public class Option {
    private String Text;
    private boolean IsReadOnly;

    public Option(String Text, boolean IsReadOnly) {
     this.Text=Text;
     this.IsReadOnly=IsReadOnly;
    }

    public String getText() {
        return Text;
    }

    public boolean isReadOnly() {
        return IsReadOnly;
    }
}
