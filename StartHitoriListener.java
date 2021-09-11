import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class StartHitoriListener implements ActionListener {
    private JFrame frame;

    public StartHitoriListener(JFrame frame){
        this.frame=frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        try {
            Hitori newGame = new Hitori();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
