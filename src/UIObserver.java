import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UIObserver extends JFrame implements IObservable {
    private JLabel Label1;
    private JPanel panel;
    Connection connection;
    public UIObserver(Connection connection)
    {
        Label1=new JLabel();
        Label1.setText("---");
        panel=new JPanel();
        panel.add(Label1);
        setContentPane(panel);
        pack();
        this.connection=connection;
        connection.AddObservable(this);
    }
    @Override
    public void Update(String message) {
        Label1.setText(message);

    }

    public void run(Connection c)
    {
        boolean more = true;
        List<String> in=prueba();
        for (String input:in)
        {
            c.dial(input);
        }
    }
    public List<String> prueba()
    {
        List<String> entradas=new ArrayList<String>();
        entradas.add("1");
        entradas.add("#");
        entradas.add("1");
        entradas.add("#");
        entradas.add("1");
        entradas.add("1");
        return entradas;
    }
}
