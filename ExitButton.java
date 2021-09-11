import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ExitButton extends JButton implements ActionListener {

    public ExitButton(){
        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            this.setFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setText("EXIT");
        setForeground(Color.white);
        setBackground(Color.red);
        setVerticalTextPosition(JButton.CENTER);
        setHorizontalTextPosition(JButton.CENTER);
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder(4,Color.white,Color.yellow));

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setEnabled(false);
        System.exit(0);
    }
}
