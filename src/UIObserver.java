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
        setContentPane(panel);
        Output.setEditable(false);
        pack();
        setSize(400,400);
        setVisible(true);
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

    private void SendCommand(String text) {
        CleanInput(text);
        Run(text);
    }
    private void CleanInput(String text)
    {
        if (text.equals("#") || text.equals("h"))
            Input.setText("");
    }

    @Override
    public void Update(String message) {
       Output.setText(message);
    }

    public void Run(String input)
    {
            if (input == null) return;
            if (input.equalsIgnoreCase("H"))
                connection.hangup();
            else if (input.equalsIgnoreCase("Q"))
                ;
            else if (input.length() == 1
                    && "1234567890#".indexOf(input) >= 0)
                connection.dial(input);
            else
                connection.record(input);
    }
}
