import javax.swing.*;
import java.io.FileNotFoundException;

public interface Puzzle{

    boolean checkFinalRowConstraints(int row,int column);
    boolean checkFinalColumnConstraints();
    boolean checkInitialRowConstraint(int row,int column);
    boolean checkInitialColumnConstraint(int row,int column);
    boolean checkAnswer();
    boolean checkFinalAnswer();
    Spot[][] getGrid() throws FileNotFoundException;
    int[] possibleValues();
    JFrame getGameFrame();

}
