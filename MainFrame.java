import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setSize(new Dimension(800,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setTitle("Wizard of Oz");
        setResizable(false);
        ImageIcon icon = new ImageIcon("icon1.jpg");
        setIconImage(icon.getImage());

        StartPanel mainMenu = new StartPanel("background.jpg");

        OptionButton hitoriButton = new OptionButton();
        hitoriButton.setBounds(300,300,200,100);
        hitoriButton.setIcon(new ImageIcon("hitori.jpg"));
        hitoriButton.addActionListener(new StartHitoriListener(this));
        hitoriButton.setToolTipText("Hitori");

        OptionButton kakurasuButton = new OptionButton();
        kakurasuButton.setBounds(300,450,200,100);
        kakurasuButton.setIcon(new ImageIcon("kakurasu.png"));
        kakurasuButton.addActionListener(new StartKakurasuListener(this));
        kakurasuButton.setToolTipText("Kakurasu");

        OptionButton fuwariButton = new OptionButton();
        fuwariButton.setBounds(300,600,200,100);
        fuwariButton.setIcon(new ImageIcon("fuwari.jpg"));
        fuwariButton.addActionListener(new StartFuwariListener(this));
        fuwariButton.setToolTipText("Dosun-Fuwari");

        JLabel exitSign = new JLabel();
        ExitButton close = new ExitButton();
        close.setBounds(650,685,65,30);
        close.setToolTipText("Exit Game");

        mainMenu.add(hitoriButton);
        mainMenu.add(kakurasuButton);
        mainMenu.add(fuwariButton);
        mainMenu.add(close);
        getContentPane().add(mainMenu);

        setVisible(true);
        StdAudio.play("src/audio.wav");


    }

    /*public static void main(String[] args) {
        MainFrame a = new MainFrame();
    }*/
}
