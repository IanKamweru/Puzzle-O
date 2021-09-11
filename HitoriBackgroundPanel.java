import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HitoriBackgroundPanel extends JPanel {
    private final Image img;
    private final ImageIcon board = new ImageIcon("board.png");
    private final ImageIcon signpost= new ImageIcon("post.png");

    public HitoriBackgroundPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public HitoriBackgroundPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(1000,700);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        Font other;
        JLabel first = new JLabel();
        JLabel one = new JLabel();
        JLabel two = new JLabel();
        JLabel three = new JLabel();

        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            other=customFont;
            first.setFont(other);
            one.setFont(other.deriveFont(15f));
            two.setFont(other.deriveFont(15f));
            three.setFont(other.deriveFont(15f));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        first.setText("RULES: ");
        first.setHorizontalTextPosition(JLabel.CENTER);
        first.setVerticalTextPosition(JLabel.CENTER);
        first.setBounds(150,130,100,40);

        one.setText("<html>1) No number appears in a row or <br>column more than once.<html>");
        one.setHorizontalTextPosition(JLabel.LEADING);
        one.setVerticalTextPosition(JLabel.CENTER);
        one.setBounds(50,166,400,40);

        two.setText("<html>2) Shaded squares should not touch each <br> other vertically or horizontally.<html>");
        two.setHorizontalTextPosition(JLabel.LEADING);
        two.setVerticalTextPosition(JLabel.CENTER);
        two.setBounds(50,250,400,40);

        three.setText("<html>3) When completed, all un-shaded <br>squares should create a single <br>continuous area.<html>");
        three.setHorizontalTextPosition(JLabel.LEADING);
        three.setVerticalTextPosition(JLabel.CENTER);
        three.setBounds(50,350,400,40);

        add(first);
        add(one);
        add(two);
        add(three);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        g.drawImage(board.getImage(), 20,130,null);
        g.drawImage(signpost.getImage(),20,480,null);

    }
}
