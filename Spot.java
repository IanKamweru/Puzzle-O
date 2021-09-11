import javax.swing.*;

public class Spot extends JButton {
    private int state;
    private int value;

    public void setState(int state){this.state=state;}
    public int getState(){return state;}
    public int getVerticalValue(){return value;};
    public void drawSpot(){};
}
