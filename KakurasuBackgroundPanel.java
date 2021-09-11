import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KakurasuBackgroundPanel extends JPanel {
    private final Image img;
    private final ImageIcon board = new ImageIcon("board.png");
    private final ImageIcon signpost= new ImageIcon("post.png");
    private final int[] rowValues={1,15,2,4,7};
    private final int[] columnValues={3,5,7,11,2};
    public static Font font;

    public KakurasuBackgroundPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public KakurasuBackgroundPanel(Image img) {
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
        JLabel indicators = new JLabel();

        //custom font
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

        one.setText("<html>1) The numbers at the top and at the <br>left are the values for each of the <br>squares in the " +
                "rows and columns.<html>");
        one.setHorizontalTextPosition(JLabel.LEADING);
        one.setVerticalTextPosition(JLabel.CENTER);
        one.setBounds(50,157,400,45);

        two.setText("<html>2) The numbers at the bottom and at <br>the right equal the row and column <br>totals for the colored squares.<html>");
        two.setHorizontalTextPosition(JLabel.LEADING);
        two.setVerticalTextPosition(JLabel.CENTER);
        two.setBounds(50,250,400,45);

        three.setText("<html>3) Marking a square will add that <br>square's value to both the row's total <br>and the column's total.<html>");
        three.setHorizontalTextPosition(JLabel.LEADING);
        three.setVerticalTextPosition(JLabel.CENTER);
        three.setBounds(50,350,400,45);

        int x=360;
        int y=60;

        int[] tags = {1, 2, 3, 4, 5};
        for(int i : tags){
            indicators.setFont(font.deriveFont(30f));
            indicators.setForeground(Color.white);
            indicators.setText(Integer.toString(i));
            add(indicators);
            indicators.setBounds(x,y,40,40);
            indicators=new JLabel();
            y+=100;
        }

        int x2=425;
        int y2=10;

        for(int i : tags){
            indicators.setFont(font.deriveFont(30f));
            indicators.setForeground(Color.white);
            indicators.setText(Integer.toString(i));
            add(indicators);
            indicators.setBounds(x2,y2,40,40);
            indicators=new JLabel();
            x2+=100;
        }

        int x3=930;
        int y3=60;

        for(int i : rowValues){
            indicators.setFont(font.deriveFont(30f));
            indicators.setForeground(Color.green);
            indicators.setText(Integer.toString(i));
            add(indicators);
            indicators.setBounds(x3,y3,40,40);
            indicators=new JLabel();
            y3+=100;
        }


        int x4=435;
        int y4=562;

        for(int i : columnValues){
            indicators.setFont(font.deriveFont(30f));
            indicators.setForeground(Color.green);
            indicators.setText(Integer.toString(i));
            add(indicators);
            indicators.setBounds(x4,y4,40,40);
            indicators=new JLabel();
            x4+=100;
        }

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
