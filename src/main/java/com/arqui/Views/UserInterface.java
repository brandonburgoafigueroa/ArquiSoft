package com.arqui.Views;

import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IDisplay;
import com.arqui.Interfaces.IModelView;
import com.arqui.Interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserInterface extends JFrame implements IView {
private List<String> Informations;
private List<JButton> OptionsButtons;
private ActionListener listenerOption;
    public UserInterface(IConnection connection)
    {
       listenerOption = returnActionOfOption();
       hiddenButtons=false;
       changeHideShow();
        pack();
        setConnection(connection);
        setAttributesToComponentsOfUI();
        setActionsToButtons();
        setVisible(true);
        Informations=new ArrayList<>();
        OptionsButtons=new ArrayList<>();
        Options.setLayout(new GridLayout(10,9));

        Cambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Run("00000");
            }
        });
    }

    private ActionListener returnActionOfOption() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked=(JButton) e.getSource();
                String command=buttonClicked.getText();
                String[] parts = command.split(".-");
                command = parts[0];
                Run(command);
            }
        };
        return listener;
    }
    private void changeHideShow()
    {
        if (hiddenButtons)
        {
            showButtons();
            ShowHidde.setText("Ocultar teclado");
            hiddenButtons=false;
        }
        else
        {
            hideButtons();
            ShowHidde.setText("Mostrar teclado");
            hiddenButtons=true;
        }
    }
    private void hideButtons()
    {
        button1.hide();
        button8.hide();
        button3.hide();
        button2.hide();
        button4.hide();
        button5.hide();
        button6.hide();
        button9.hide();
        button7.hide();
        button0.hide();
        buttonB.hide();
    }
    private void showButtons()
    {
        button1.show();
        button8.show();
        button3.show();
        button2.show();
        button4.show();
        button5.show();
        button6.show();
        button9.show();
        button7.show();
        button0.show();
        buttonB.show();
    }
    @Override
    public void setDisplay(IDisplay display) {
        this.display=display;
    }

    @Override
    public void setPersistenceText(String persistenceType) {
        TypeOfPersistence.setText(persistenceType);
    }


    @Override
    public void setInformation(String information) {
        Informations.add(information);
    }

    @Override
    public void setOption(String option) {
        Options.hide();
        int size=OptionsButtons.size()+1;
        JButton button=new JButton(size+".- "+option);
        button.addActionListener(listenerOption);
        OptionsButtons.add(button);
    }

    @Override
    public void showView() {
        Information.setText("");
        Options.removeAll();
        for (String information:Informations) {
            String text=Information.getText()+'\n'+information;
            Information.setText(text);
        }

        for (JButton option:OptionsButtons) {
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
    private void setConnection(IConnection connection) {
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
        ActionListener listenerHide = showHideButtons();
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
        ShowHidde.addActionListener(listenerHide);
    }

    private ActionListener showHideButtons() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              changeHideShow();
            }
        };
        return listener;

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
    private Boolean hiddenButtons;
    private JPanel panel;
    private JTextArea Input;
    private IConnection connection;
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
    private JButton ShowHidde;
    private JLabel TypeOfPersistence;
    private JButton Cambiar;
    private IDisplay display;
}
