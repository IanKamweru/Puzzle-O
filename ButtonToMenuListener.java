import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonToMenuListener implements ActionListener {
    private JButton myButton;
    JPopupMenu  menu;
    private JFrame frame;

    public ButtonToMenuListener(JButton clicker,JFrame frame) {
        myButton = clicker;
        this.frame=frame;

        Font textFont;
        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            textFont=customFont;
            clicker.setFont(textFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        clicker.setText("MENU");

        // create a popup menu
        menu = new JPopupMenu("Menu");

        // create a menu item
        JMenuItem Hitori = new JMenuItem("Hitori");
        Hitori.setToolTipText("Switch to Hitori");
        Hitori.addActionListener(new StartHitoriListener(frame));
        menu.add(Hitori); // add the menu item to the menu

        JMenuItem Kakurausu = new JMenuItem("Kakurasu");
        Kakurausu.setToolTipText("Switch to Kakurasu");
        Kakurausu.addActionListener(new StartKakurasuListener(frame));
        menu.add(Kakurausu);

        JMenuItem Fuwari = new JMenuItem("Fuwari");
        Fuwari.setToolTipText("Switch to Fuwari");
        Fuwari.addActionListener(new StartFuwariListener(frame));
        menu.add(Fuwari);

        JMenuItem Exit = new JMenuItem("Exit Game");
        Exit.setToolTipText("Exit Game");
        Exit.addActionListener(new ExitButton());
        menu.add(Exit);
    }

    public void actionPerformed(ActionEvent e) {

        menu.show(myButton,myButton.getWidth()/2,myButton.getHeight()/2);
    }
}
