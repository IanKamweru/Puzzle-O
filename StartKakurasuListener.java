import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartKakurasuListener implements ActionListener {
    private final JFrame frame;

    public StartKakurasuListener(JFrame frame){
        this.frame=frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        Kakurasu newGame = new Kakurasu();
    }
}
