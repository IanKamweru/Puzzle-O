import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FuwariBackgroundPanel extends JPanel {
    private final Image img;
    private final ImageIcon board = new ImageIcon("board.png");
    private final ImageIcon signpost= new ImageIcon("post.png");

    public FuwariBackgroundPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public FuwariBackgroundPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(1000,700);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        JLabel first = new JLabel();
        JLabel one = new JLabel();
        JLabel two = new JLabel();
        JLabel three = new JLabel();

        //custom font
        Font font;
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            font=customFont;
            first.setFont(font);
            one.setFont(font.deriveFont(15f));
            two.setFont(font.deriveFont(15f));
            three.setFont(font.deriveFont(15f));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        first.setText("RULES: ");
        first.setHorizontalTextPosition(JLabel.CENTER);
        first.setVerticalTextPosition(JLabel.CENTER);
        first.setBounds(150,127,100,40);

        one.setText("<html>1) Place one balloon and one iron ball <br>in each of the areas surrounded by <br>bold lines.<html>");
        one.setHorizontalTextPosition(JLabel.LEADING);
        one.setVerticalTextPosition(JLabel.CENTER);
        one.setBounds(50,157,400,45);

        two.setText("<html>2) Balloons are light, so they are <br>placed a cell at the top or under <br>other balloons.<html>");
        two.setHorizontalTextPosition(JLabel.LEADING);
        two.setVerticalTextPosition(JLabel.CENTER);
        two.setBounds(50,250,400,45);

        three.setText("<html>3) Iron-weights sink, so they are placed <br>in a cell at the bottom, or over <br>other iron-weights.<html>");
        three.setHorizontalTextPosition(JLabel.LEADING);
        three.setVerticalTextPosition(JLabel.CENTER);
        three.setBounds(50,350,400,45);

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
