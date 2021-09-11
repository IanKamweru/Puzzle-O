import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KakurasuCell extends Spot implements ActionListener {
    private final int verticalValue;
    private final int horizontalValue;
    private boolean isShaded;
    private int state;


    public KakurasuCell(int verticalValue,int horizontalValue){
        this.horizontalValue=horizontalValue;
        this.verticalValue=verticalValue;
        state=0;
        setBackground(Color.yellow);
        setBorder(BorderFactory.createLineBorder(Color.pink,1,true));
        addActionListener(this);
    }

    @Override
    public int getVerticalValue() { return verticalValue; }

    public int getHorizontalValue() { return horizontalValue; }

    public boolean isShaded(){return isShaded;}

    @Override
    public void setState(int state){
        this.state=state;
        if(state==1) isShaded=true;
        if(state==0) isShaded=false;
    }

    @Override
    public int getState(){ return state;}

    @Override
    public void drawSpot(){
        if(state==1) setBackground(Color.black);
        else if(state==0) setBackground(Color.yellow);
    }


    public void actionPerformed(ActionEvent e){
        if(!isShaded) {
            setBackground(Color.black);
            isShaded = true;
            state=1;
        }
        else if(isShaded){
            setBackground(Color.yellow);
            isShaded=false;
            state=0;
        }
    }
}
