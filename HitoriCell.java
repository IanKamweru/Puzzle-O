import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitoriCell extends Spot implements ActionListener {
    private int value;
    private boolean isShaded=false;
    private int state;
    private boolean checkedInConstraintChecker;


    public HitoriCell(int value){
        this.value=value;
        state=0;
        checkedInConstraintChecker=false;
        Font other = new Font("", Font.BOLD, 30);
        setFont(other);
        setForeground(Color.yellow);
        setBackground(new Color(8, 128, 44));
        setText(Integer.toString(value));
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setFocusable(false);
        addActionListener(this);
        setBorder(BorderFactory.createLineBorder(Color.black,1,true));
    }

    public int getValue(){return value;}

    public boolean isShaded(){return isShaded;}

    @Override
    public void setState(int state){
        this.state=state;
        if(state==1) isShaded=true;
        else if(state==0) isShaded=false;
        else if(state==3){
            this.setEnabled(false);
            isShaded=true;
        }
    }

    @Override
    public int getState(){return state;}

    public boolean isCheckedInConstraintChecker() { return checkedInConstraintChecker; }

    public void setCheckedInConstraintChecker(boolean checkedInConstraintChecker) { this.checkedInConstraintChecker = checkedInConstraintChecker; }

    @Override
    public void drawSpot(){
        if(state==1){
            setBackground(Color.black);
        }

        else if(state==0){
            setBackground(new Color(8, 128, 44));
        }
    }

    public void actionPerformed(ActionEvent e){
        if(!isShaded) {
            setBackground(Color.black);
            isShaded = true;
            state=1;
        }
        else if(isShaded){
            setBackground(new Color(8, 128, 44));
            isShaded=false;
            state=0;
        }
    }
}
