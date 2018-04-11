import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIObserver extends JFrame implements IObservable {

    private JPanel panel;
    private JTextArea Input;
    Connection connection;
    private JTextArea Output;
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

    public UIObserver(Connection connection)
    {
        pack();
        Output.setEditable(false);
        setSize(400,700);
        setContentPane(panel);
        this.connection=connection;
        connection.addObservable(this);
        pressed.setEditable(false);
        pressed.setSize(10,10);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked=(JButton) e.getSource();
                Run(buttonClicked.getText());
                pressed.setText(pressed.getText()+" "+buttonClicked.getText());
            }
        };
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
        buttonH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=Input.getText();
                Input.setText("");
                pressed.setText("");
                Run(text);
                Run("H");
            }
        });
    }

    @Override
    public void Update(String message) {
        Output.setText(message);
    }

    public void Run(String input)
    {
        boolean connectionContinue;
        connectionContinue=connection.executeCommand(input);
        if (connectionContinue==false)
        {
            this.hide();
        }
    }

}
