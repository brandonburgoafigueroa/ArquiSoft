package com.arqui.Views;

import com.arqui.Core.Connection;
import com.arqui.DisplayState.IDisplay;
import com.arqui.IView;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserInterface extends JFrame implements IView {
private List<String> Informations;
private List<JButton> OptionsButtons;
    public UserInterface(Connection connection)
    {

        pack();
        setConnection(connection);
        setAttributesToComponentsOfUI();
        setActionsToButtons();
        setVisible(true);
        Informations=new ArrayList<>();
        OptionsButtons=new ArrayList<>();
        Options.setLayout(new GridLayout(10,9));
    }

    @Override
    public void setDisplay(IDisplay display) {
        this.display=display;
    }

//sector presenters
    @Override
    public void setInformation(String information) {
        Informations.add(information);
    }

    @Override
    public void setOption(String option) {
        Options.hide();
        int size=OptionsButtons.size()+1;
        OptionsButtons.add(new JButton(size+" - "+option));
    }

    @Override
    public void showView() {
        Information.setText("");
        Options.removeAll();
        for (String information:Informations) {
            String text=Information.getText()+'\n'+information;
            Information.setText(text);
        }

        for (JButton option:OptionsButtons) { ;
            Options.add(option);
        }
        Informations.clear();
        OptionsButtons.clear();
        Options.show();
    }

    //end presenters
    private void Run(String input)
    {
        boolean connectionContinue;
        String textarea=Input.getText();
        if (textarea=="")
        {
            connection.executeCommand(input);
        }
        else
        {
            connection.executeCommand(textarea);
            connection.executeCommand(input);
        }
    }
    private void setConnection(Connection connection) {
        //connection.addObservable(this);
        this.connection=connection;
    }

    private void setAttributesToComponentsOfUI() {

        setContentPane(panel);
        setSize(400,700);

        pressed.setEditable(false);
        pressed.setSize(10,10);
    }

    private void setActionsToButtons() {
        ActionListener listener = returnActionOfButton();
        button1.addActionListener(listener);
        button3.addActionListener(listener);
        button2.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button7.addActionListener(listener);
        button8.addActionListener(listener);
        button9.addActionListener(listener);
        button0.addActionListener(listener);
        buttonB.addActionListener(listener);
    }

    private ActionListener returnActionOfButton() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked=(JButton) e.getSource();
                Run(buttonClicked.getText());
                pressed.setText(pressed.getText()+" "+buttonClicked.getText());
            }
        };

        buttonH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text=Input.getText();
                Input.setText("");
                pressed.setText("");
                Run(text);
                Run("H");
            }
        });
        return listener;
    }

    private JPanel panel;
    private JTextArea Input;
    private Connection connection;
    private JButton button1;
    private JButton button8;
    private JButton buttonH;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button9;
    private JButton button7;
    private JButton button0;
    private JButton buttonB;
    private JTextArea pressed;
    private JLabel Information;
    private JLabel Info;
    private JPanel Options;
    private IDisplay display;
}
