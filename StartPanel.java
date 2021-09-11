import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {
    private Image img;
    private ImageIcon exit = new ImageIcon("exit.png");
    public static Font myFont;

    public StartPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public StartPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(800,800);
        setPreferredSize(size);
        setMinimumSize(size);
            setMaximumSize(size);
        setSize(size);
        setLayout(null);

        ImageIcon post = new ImageIcon("poster.png");
        JLabel poster = new JLabel();
        poster.setIcon(post);

        poster.setBounds(75,50,650,186);
        JLabel one = new JLabel();
        JLabel two = new JLabel();

        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            one.setFont(customFont);
            two.setFont(customFont);
            myFont=customFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        poster.setLayout(null);

        one.setText("To start, choose a quest below !");
        one.setHorizontalTextPosition(JLabel.CENTER);
        one.setVerticalTextPosition(JLabel.CENTER);
        one.setBounds(160,60,600,80);

        two.setText("Good Luck !");
        two.setHorizontalTextPosition(JLabel.CENTER);
        two.setVerticalTextPosition(JLabel.CENTER);
        two.setBounds(260,100,600,80);

        poster.add(one);
        poster.add(two);

        this.add(poster);

    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(img, 0, 0, null);
        g.drawImage(exit.getImage(), 600,655,null);
    }
}
