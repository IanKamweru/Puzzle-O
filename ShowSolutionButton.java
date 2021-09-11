import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowSolutionButton extends JButton implements ActionListener {
    private Puzzle puzzle;
    private JFrame frame;

    public ShowSolutionButton(Puzzle puzzle){
        this.puzzle=puzzle;

        Font other;
        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            other=customFont;
            setFont(other);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        setBackground(Color.cyan);
        setText("Show Solution");
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.blue,3,true));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FindSolution solution=new FindSolution(puzzle);
            solution.drawSolution();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
