import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CheckAnswerButton extends JButton implements ActionListener{

    private final Puzzle game;
    private final JLabel text = new JLabel();
    private final JPanel gamePanel;

    public CheckAnswerButton(Puzzle puzzle, JPanel gamePanel){
        this.gamePanel=gamePanel;
        this.game=puzzle;
        Font other;
        Font textFont;
        //custom font
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("bold.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            other=customFont;
            setFont(other);
            textFont=other.deriveFont(27f);
            text.setFont(textFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setBackground(Color.cyan);
        setText("Check Answer");
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.blue,3,true));

        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        text.setText(null);
        //System.out.println(game.checkFinalAnswer());
        if(game.checkFinalAnswer()){
            text.setForeground(new Color(8, 128, 44));
            text.setHorizontalTextPosition(JLabel.CENTER);
            text.setText("<html>MISSION <br><br>ACCOMPLISHED !<html>");
        }

        else if(game.checkAnswer()) {
            text.setForeground(new Color(8, 128, 44));
            text.setHorizontalTextPosition(JLabel.CENTER);
            text.setText("SO FAR, SO GOOD !");

        }

        else {
            text.setForeground(new Color(153, 0, 0));
            text.setHorizontalTextPosition(JLabel.CENTER);
            text.setText("<html>SOMETHING DOESN'T <br><br>SEEM RIGHT : (<html>");
        }

        gamePanel.add(text);
        text.setBounds(50,515,300,100);
        text.setVisible(true);
    }
}
