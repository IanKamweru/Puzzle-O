import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuwariCell extends Spot implements ActionListener {
    private int state;
    private int positionInGrid;
    private final ImageIcon balloon = new ImageIcon("balloon.png");
    private final ImageIcon ironBall = new ImageIcon("ironball.png");
    private final ImageIcon box = new ImageIcon("box.png");
    private ImageIcon icon;
    private JLabel label;
    private int section;
    private boolean isCheckedInConstraintChecker;

    /* state 0-white
       state 1-balloon
       state 2-iron ball
       state 3-blocked
     */


    public FuwariCell(int positionInGrid){
        this.positionInGrid=positionInGrid;
        isCheckedInConstraintChecker=false;
        state=0;
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
        addActionListener(this);
    }

    public FuwariCell(boolean blocked,int positionInGrid){
        if(blocked) {
            state = 3;
            section=6;
            this.positionInGrid=positionInGrid;
            setBackground(Color.white);
            setBorder(BorderFactory.createLineBorder(Color.lightGray, 1, true));
            icon = box;
            label = new JLabel();
            label.setIcon(icon);
            this.add(label);
            label.setBounds(0, 0, 100, 100);
            label.setVisible(true);
            setEnabled(false);
        }
    }

    @Override
    public int getState() { return state; }

    @Override
    public void setState(int state){this.state=state;}

    public void setSection(int section){this.section=section;}

    public int getSection(){return section;}

    public int getPositionInGrid(){return positionInGrid;}

    @Override
    public void drawSpot(){
        if(state==0){
            setBackground(Color.white);
        }
        if(state==1){
            JLabel loon=new JLabel();
            loon.setIcon(balloon);
            add(loon);
            loon.setBounds(5,5,100,100);
        }
        if(state==2){
            JLabel loon=new JLabel();
            loon.setIcon(ironBall);
            add(loon);
            loon.setBounds(5,5,100,100);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == 0) {
            icon=balloon;
            label = new JLabel();
            label.setIcon(icon);
            this.add(label);
            label.setBounds(5,5,100,100);
            state=1;
        } else if (state == 1) {
            label.setIcon(null);
            icon=ironBall;
            label = new JLabel();
            label.setIcon(icon);
            this.add(label);
            state=2;
        } else if (state == 2) {
            label.setIcon(null);
            state=0;
        }
    }
}
