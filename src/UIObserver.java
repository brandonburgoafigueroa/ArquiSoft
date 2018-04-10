import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIObserver extends JFrame implements IObservable {

    private JPanel panel;
    private JTextArea Input;
    Connection connection;
    private JButton Send;
    private JTextArea Output;

    public UIObserver(Connection connection)
    {

        Output.setEditable(false);
        pack();
        setSize(400,400);
        setVisible(true);
        setContentPane(panel);
        this.connection=connection;
        connection.AddObservable(this);

        Input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode()==KeyEvent.getExtendedKeyCodeForChar('\n'))
                {
                    String input=Input.getText();
                    String [] lineas = input.split("\n");
                    String command=lineas[lineas.length-1];
                    SendCommand(command);
                }
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

    private void SendCommand(String text) {
        CleanInput(text);
        Run(text);
    }
    private void CleanInput(String text)
    {
        if (text.equals("#") || text.equals("h"))
            Input.setText("");
    }


}
