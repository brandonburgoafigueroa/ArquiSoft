import javax.swing.*;

public class UIObserver extends JFrame implements IObservable {

    private JTextArea TextArea;
    private JPanel panel;
    Connection connection;
    public UIObserver(Connection connection)
    {
        TextArea=new JTextArea();
        TextArea.setText("---");
        TextArea.setEditable(false);
        panel=new JPanel();
        panel.add(TextArea);
        setContentPane(panel);
        pack();
        this.connection=connection;
        connection.AddObservable(this);
        setSize(400,400);
        setVisible(true);
    }
    @Override
    public void Update(String message) {
        TextArea.setText(message);
    }


}
