import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class FindSolution {

    private Puzzle theGame;
    Spot[][] theGrid;
    private int[] possibleValues;
    private JFrame gameFrame;
    boolean result;

    public FindSolution(Puzzle puzzle) throws FileNotFoundException {
        this.gameFrame= puzzle.getGameFrame();
        gameFrame.setVisible(false);
        theGame=puzzle;
        theGrid=puzzle.getGrid();
        possibleValues=puzzle.possibleValues();
        result=label(theGrid,theGame,possibleValues,0,0);

        for (Spot[] spots : theGrid) {
            for (int j = 0; j < theGrid.length; j++) {
                spots[j].setEnabled(false);
            }
        }
        drawSolution();
    }

    public boolean label(Spot[][] grid,Puzzle game,int[] possibleValues,int row,int column){

        if(row==theGrid.length) { return game.checkFinalColumnConstraints() && game.checkFinalRowConstraints(theGrid.length-1, theGrid[0].length-1); }

        if(theGrid[row][column].getState()==3){

            int newColumn=column+1;
            int newRow=row;

            if(newColumn==theGrid[row].length){
                newColumn=0;
                newRow=row+1;
            }

            label(theGrid,theGame,possibleValues,newRow,newColumn);
        }

        for(int i : possibleValues){
            theGrid[row][column].setState(i);
            //System.out.print(row + " " + column);

            boolean check;

            //initial constraints-total hasn't exceeded yet
            //final constraints-compares against actual values

            if(column==theGrid.length-1) check=theGame.checkFinalRowConstraints(row,column);
            else  check=theGame.checkInitialRowConstraint(row,column) && theGame.checkInitialColumnConstraint(row,column);
            //System.out.println(check);


            if(check){
                //System.out.println(row+ " " + column + " " + theGrid[row][column].getState());
                int newColumn=column+1;
                int newRow=row;

                if(newColumn==theGrid[row].length){
                    newColumn=0;
                    newRow=row+1;
                }

                if(label(theGrid,theGame,possibleValues,newRow,newColumn)) {
                    return true;
                }
            }
        }

        theGrid[row][column].setState(0);
        return false;
    }

    public void printGridStates(){
        for (Spot[] spots : theGrid) {
            for (int j = 0; j < theGrid.length; j++) {
                System.out.print(spots[j].getState() + " ");
            }
        }
    }

    public void drawSolution(){
        for (Spot[] spots : theGrid) {
            for (int j = 0; j < theGrid.length; j++) {
                spots[j].drawSpot();
            }
        }

        gameFrame.setVisible(true);
    }

    /*public static void main(String[] args) throws FileNotFoundException {
        FindSolution test=new FindSolution(new Hitori());
        System.out.println(test.result);
        test.printGridStates();
    }*/
}
