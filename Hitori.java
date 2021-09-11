import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Hitori extends JFrame implements Puzzle{
    private final int rows=5;
    private final int columns=5;
    private final HitoriCell[][] theGrid = new HitoriCell[rows][columns];
    private final File file=new File("hitori.txt");
    private final Scanner sc=new Scanner(file);
    private ArrayList<Integer> rowValues = new ArrayList<>();
    private ArrayList<Integer> columnValues = new ArrayList<>();


    public Hitori() throws FileNotFoundException {
        setSize(new Dimension(1000,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setTitle("Hitori");
        setResizable(false);
        ImageIcon icon = new ImageIcon("icon1.jpg");
        setIconImage(icon.getImage());

        HitoriBackgroundPanel hitoriBackground = new HitoriBackgroundPanel("hitoribackground.jpg");

        JPanel table = new JPanel();
        table.setBounds(400,50,500,500);
        table.setBorder(BorderFactory.createLineBorder(Color.gray,6,true));
        table.setLayout(new GridLayout(5,5));

        //setting Grid
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                assert sc != null;
                theGrid[i][j] = new HitoriCell(sc.nextInt());
            }
        }

        for (HitoriCell[] button : theGrid) {
            for (HitoriCell hitoriCell : button) {
                table.add(hitoriCell);
            }
        }

        //check answer button
        CheckAnswerButton answerButton = new CheckAnswerButton(this, hitoriBackground);
        answerButton.setBounds(440,580,130,50);
        ShowSolutionButton solutionButton = new ShowSolutionButton(this);
        solutionButton.setBounds(580,580,130,50);

        //menu
        JButton menu=new JButton();
        menu.setBounds(720,580,130,50);
        menu.setBackground(Color.cyan);
        menu.setFocusable(false);
        menu.setBorder(BorderFactory.createLineBorder(Color.blue,3,true));
        menu.addActionListener(new ButtonToMenuListener(menu,this));

        hitoriBackground.add(answerButton);
        hitoriBackground.add(solutionButton);
        hitoriBackground.add(menu);
        hitoriBackground.add(table);

        getContentPane().add(hitoriBackground);

        setVisible(true);
    }

    @Override
    public boolean checkInitialRowConstraint(int row,int column) {
        //method checks whether a value has a duplicate in the row. If so, the first instance is shaded unless
        //the shadedNeighbors condition is not met
        boolean ok=true;

        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid[i].length;j++) {
                int value;
                rowValues.clear();

                //checks if the value of button pressed exists in another cell in respective row or column. If false, then pressed wrong button since
                //it isn't a duplicate in both the row or the column
                if (theGrid[i][j].isShaded()) {
                    value = theGrid[i][j].getValue();
                    theGrid[i][j].setCheckedInConstraintChecker(true);

                    for (int k = 0; k < theGrid[i].length; k++) {
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    if(!rowValues.contains(value) && !columnValues.contains(value)) ok=false;
                }

                theGrid[i][j].setCheckedInConstraintChecker(false); //resetting
            }
        }

        //if cell[row][column] has a duplicate and is the first instance, it shd be shaded unless shadedNeighbor condition is not met
        boolean hasDuplicate=hasDuplicate(row,column);


        if(hasDuplicate){

            boolean hasShadedNeighbor=hasShadedNeighbor(row,column);
            //System.out.println(row + " " + column + " " + hasShadedNeighbor);

            if(!hasShadedNeighbor) {
                if (theGrid[row][column].getState() == 1) return true;
                else return false;
            }
            else {
                if(theGrid[row][column].getState()==0) return true;
                else return false;
            }
        }


        //checks whether the unshaded buttons connect either horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                boolean hasNeighbours=hasNeighbors(i,j);
                //System.out.println(i + " " + j + " " + hasNeighbours);
                if(!hasNeighbours){
                    return false;
                }
            }
        }

        return ok;
    }



    @Override
    public boolean checkInitialColumnConstraint(int row,int column) {
        //checks for adjacent shaded cells horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    boolean hasShadedNeighbor=hasShadedNeighbor(i,j);
                    if(hasShadedNeighbor){
                        return false;
                    }
                }
            }
        }

        return true;
    }



    @Override
    public boolean checkFinalRowConstraints(int row,int column) {
        boolean ok=true;

        //checks for adjacent shaded cells horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    boolean hasShadedNeighbor=hasShadedNeighbor(i,j);
                    if(hasShadedNeighbor){
                        return false;
                    }
                }
            }
        }

        //checking for duplicates at the end of the row
        for(int j=0;j<theGrid[row].length;j++){

            if(!theGrid[row][j].isShaded()){
                boolean hasDuplicate=hasDuplicate(row,j);

                if(hasDuplicate) ok=false;
            }
        }

        return ok;
    }

    @Override
    public boolean checkFinalColumnConstraints() {
        boolean ok=true;

        //checks for duplicates in the row
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid[i].length;j++) {
                int value;
                rowValues.clear();
                columnValues.clear();

                //checks if the value of button pressed exists in another button in that row or column. If value doesn't
                // exist in both row and column, then pressed wrong button since it isn't a duplicate
                if (!theGrid[i][j].isShaded() && theGrid[i][j].getState() != 3) {
                    value = theGrid[i][j].getValue();
                    theGrid[i][j].setCheckedInConstraintChecker(true);

                    for (int k = 0; k < theGrid[i].length; k++) {
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    for (int m = 0; m < theGrid.length; m++) {
                        if (!theGrid[m][j].isShaded() && !theGrid[m][j].isCheckedInConstraintChecker())
                            columnValues.add(theGrid[m][j].getValue());
                    }

                    if (rowValues.contains(value) || columnValues.contains(value)) ok = false;
                    theGrid[i][j].setCheckedInConstraintChecker(false); //resetting

                }
            }

        }

        //checks for adjacent shaded cells horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    boolean hasShadedNeighbor=hasShadedNeighbor(i,j);
                    if(hasShadedNeighbor){
                        return false;
                    }
                }
            }
        }

        //checks whether the unshaded buttons connect either horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                boolean hasNeighbours=hasNeighbors(i,j);
                //System.out.println(i + " " + j + " " + hasNeighbours);
                if(!hasNeighbours){
                    return false;
                }
            }
        }

        return ok;
    }

    //checks answer when the grid is not filled completely yet
    @Override
    public boolean checkAnswer() {

        boolean ok=true;

        //checks for duplicates in the row
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid[i].length;j++) {
                int value;
                rowValues.clear();
                columnValues.clear();

                //checks if the value of button pressed exists in another button in that row or column. If false, then pressed wrong button since
                //it isn't a duplicate
                if (theGrid[i][j].isShaded() && theGrid[i][j].getState() != 3) {
                    value = theGrid[i][j].getValue();
                    theGrid[i][j].setCheckedInConstraintChecker(true);

                    for (int k = 0; k < theGrid[i].length; k++) {
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    for (int m = 0; m < theGrid.length; m++) {
                        if (!theGrid[m][j].isShaded() && !theGrid[m][j].isCheckedInConstraintChecker())
                            columnValues.add(theGrid[m][j].getValue());
                    }

                    if(!rowValues.contains(value) && !columnValues.contains(value)) ok=false;
                }

                theGrid[i][j].setCheckedInConstraintChecker(false); //resetting
            }
        }

        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    rowValues.clear();
                    columnValues.clear();


                    int value=theGrid[i][j].getValue();

                    for(int k=0;k<theGrid[i].length;k++){
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    for (int m = 0; m < theGrid.length; m++) {
                        if (!theGrid[m][j].isShaded() && !theGrid[m][j].isCheckedInConstraintChecker())
                            columnValues.add(theGrid[m][j].getValue());
                    }

                    if(!rowValues.contains(value) && !columnValues.contains(value)){
                        return false;
                    }
                }
            }
        }

        ////checks for adjacent shaded cells horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    boolean hasShadedNeighbor=hasShadedNeighbor(i,j);
                    //System.out.println(i + " " + j + " " + hasShadedNeighbor);
                    if(hasShadedNeighbor){
                        return false;
                    }
                }
            }
        }

        return ok;
    }

    //checks final answer when check answer button is pressed by player
    @Override
    public boolean checkFinalAnswer(){
        boolean ok=true;

        //checks for duplicates in the row
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid[i].length;j++) {
                int value;
                rowValues.clear();
                columnValues.clear();

                //checks if the value of button pressed exists in another button in that row or column. If value doesn't
                // exist in both row and column, then pressed wrong button since it isn't a duplicate
                if (!theGrid[i][j].isShaded()) {
                    value = theGrid[i][j].getValue();
                    theGrid[i][j].setCheckedInConstraintChecker(true);

                    for (int k = 0; k < theGrid[i].length; k++) {
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    for (int m = 0; m < theGrid.length; m++) {
                        if (!theGrid[m][j].isShaded() && !theGrid[m][j].isCheckedInConstraintChecker())
                            columnValues.add(theGrid[m][j].getValue());
                    }

                    if (rowValues.contains(value) || columnValues.contains(value)) ok = false;
                    theGrid[i][j].setCheckedInConstraintChecker(false); //resetting

                }
            }

        }

        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()){
                    rowValues.clear();
                    columnValues.clear();


                    int value=theGrid[i][j].getValue();

                    for(int k=0;k<theGrid[i].length;k++){
                        if (!theGrid[i][k].isShaded() && !theGrid[i][k].isCheckedInConstraintChecker())
                            rowValues.add(theGrid[i][k].getValue());
                    }

                    for (int m = 0; m < theGrid.length; m++) {
                        if (!theGrid[m][j].isShaded() && !theGrid[m][j].isCheckedInConstraintChecker())
                            columnValues.add(theGrid[m][j].getValue());
                    }

                    if(!rowValues.contains(value) && !columnValues.contains(value)){
                        return false;
                    }
                }
            }
        }

        ///checks for adjacent shaded cells horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded() && theGrid[i][j].getState() != 3){
                    boolean hasShadedNeighbor=hasShadedNeighbor(i,j);
                    if(hasShadedNeighbor){
                        return false;
                    }
                }
            }
        }


        //checks whether the unshaded buttons connect either horizontally or vertically
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(!theGrid[i][j].isShaded() && theGrid[i][j].getState() != 3) {
                    boolean hasNeighbours = hasNeighbors(i, j);
                    //System.out.println(i + " " + j + " " + hasNeighbours);
                    if (!hasNeighbours) {
                        return false;
                    }
                }
            }
        }



        return ok;
    }

    //checking whether a shaded cell has a neighboring shaded cell vertically or horizontally
    private boolean hasShadedNeighbor(int row,int column){
        boolean ok=false;

            if (row == 0) {
                if (column == 0) {
                    if (theGrid[row][column + 1].isShaded() || theGrid[row + 1][column].isShaded()) ok = true;
                }
                else if (column == theGrid.length - 1) {
                    if (theGrid[row + 1][column].isShaded() || theGrid[row][column - 1].isShaded()) ok = true;
                }

                else if (theGrid[row + 1][column].isShaded() || theGrid[row][column-1].isShaded() ||
                        theGrid[row][column+1].isShaded()) ok = true;
            }

            else if (row == theGrid.length - 1) {
                if (column == 0) {
                    if (theGrid[row - 1][column].isShaded() || theGrid[row][column + 1].isShaded()) ok = true;
                }
                else if (column == theGrid.length - 1) {
                    if (theGrid[row - 1][column].isShaded() || theGrid[row][column - 1].isShaded()) ok = true;
                }
                else if (theGrid[row - 1][column].isShaded() || theGrid[row][column-1].isShaded() ||
                        theGrid[row][column+1].isShaded()) ok = true;
            }

            else {
                if (column == 0) {
                    if (theGrid[row][column + 1].isShaded() || theGrid[row - 1][column].isShaded() ||
                            theGrid[row + 1][column].isShaded()) ok = true;
                } else if (column == theGrid.length - 1) {
                    if (theGrid[row][column - 1].isShaded() || theGrid[row + 1][column].isShaded() ||
                            theGrid[row - 1][column].isShaded()) ok = true;
                } else if (theGrid[row - 1][column].isShaded() || theGrid[row + 1][column].isShaded() ||
                        theGrid[row][column - 1].isShaded() || theGrid[row][column + 1].isShaded()) ok = true;
            }


        return ok;
    }

    //checking whether unshaded cells have neighbors/form a single continuous area
    private boolean hasNeighbors(int row,int column){
        //has a problem
        boolean ok=false;

        if(!theGrid[row][column].isShaded()){
            if(row==0){
                if(column==0){
                    if(!theGrid[row][column+1].isShaded() || !theGrid[row+1][column].isShaded()) ok=true;
                }
                if(column==theGrid.length-1){
                    if(!theGrid[row+1][column].isShaded() || !theGrid[row][column-1].isShaded()) ok=true;
                }

                else if(!theGrid[row+1][column].isShaded()) ok=true;
            }

            else if(row==theGrid.length-1){
                if(column==0){
                    if(!theGrid[row-1][column].isShaded() || !theGrid[row][column+1].isShaded()) ok=true;
                }
                if(column==theGrid.length-1){
                    if(!theGrid[row-1][column].isShaded() || !theGrid[row][column-1].isShaded()) ok=true;
                }

                else if(!theGrid[row-1][column].isShaded()) ok=true;
            }

            else{
                if(column==0){
                    if(!theGrid[row][column+1].isShaded() || !theGrid[row-1][column].isShaded() ||
                        !theGrid[row+1][column].isShaded()) ok=true;
                }

                else if(column==theGrid.length-1){
                    if(!theGrid[row][column-1].isShaded() || !theGrid[row+1][column].isShaded() ||
                            !theGrid[row-1][column].isShaded()) ok=true;
                }

                else if(!theGrid[row-1][column].isShaded() || !theGrid[row+1][column].isShaded() || !theGrid[row][column-1].isShaded() || !theGrid[row][column+1].isShaded()) ok=true;
            }

        }

        else if(theGrid[row][column].isShaded()) ok=true;

        return ok;
    }

    private boolean hasDuplicate(int row,int column){

        rowValues.clear();

        int value=theGrid[row][column].getValue();
        theGrid[row][column].setCheckedInConstraintChecker(true);

        for(int j=0;j<theGrid[row].length;j++){
            if(!theGrid[row][j].isShaded() && !theGrid[row][j].isCheckedInConstraintChecker()){
                rowValues.add(theGrid[row][j].getValue());
            }
        }

        return rowValues.contains(value);
    }

    @Override
    public HitoriCell[][] getGrid(){ return theGrid; }

    @Override
    public int[] possibleValues() {
        //0-not shaded
        //1-shaded
        return new int[]{0,1};
    }

    @Override
    public JFrame getGameFrame() { return this; }

    /*public static void main(String[] args) throws FileNotFoundException {
        Hitori a = new Hitori();
    }*/

}
