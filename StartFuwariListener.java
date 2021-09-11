import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFuwariListener implements ActionListener {
    private JFrame frame;

    public StartFuwariListener(JFrame frame){
        this.frame=frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        Fuwari newGame = new Fuwari();
    }
}
