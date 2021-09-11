import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Fuwari extends JFrame implements Puzzle {
    private final int rows=5;
    private final int columns=5;
    private final FuwariCell[][] theGrid = new FuwariCell[rows][columns];
    private final int[][] sections=new int[6][]; //stores the cells into respective sections


    public Fuwari() {
        setSize(new Dimension(1000,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setTitle("Dosun-Fuwari");
        setResizable(false);
        ImageIcon icon = new ImageIcon("icon1.jpg");
        setIconImage(icon.getImage());

        FuwariBackgroundPanel fuwariBackground = new FuwariBackgroundPanel("fuwaribackground.jpg");

        JPanel table = new JPanel();
        table.setBounds(400,50,500,500);
        table.setBorder(BorderFactory.createLineBorder(Color.darkGray,6,true));
        table.setLayout(new GridLayout(5,5));

        //setting Grid
        int position=1;
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(i==0 && j==1)  theGrid[i][j]=new FuwariCell(true,position); //hard-coding blocked cells
                else if(i==3 && j==2)  theGrid[i][j]=new FuwariCell(true,position);
                else if(i==3 && j==4)  theGrid[i][j]=new FuwariCell(true,position);
                else { theGrid[i][j] = new FuwariCell(position); }
                position++;
            }
        }

        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                table.add(theGrid[i][j]);
            }
        }

        //section 1
        sections[0]=new int[6];
        theGrid[0][0].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,0,3, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[0][0].setSection(1);
        sections[0][0]=theGrid[0][0].getPositionInGrid();

        theGrid[1][0].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[1][0].setSection(1);
        sections[0][1]=theGrid[1][0].getPositionInGrid();

        theGrid[2][0].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[2][0].setSection(1);
        sections[0][2]=theGrid[2][0].getPositionInGrid();

        theGrid[3][0].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[3][0].setSection(1);
        sections[0][3]=theGrid[3][0].getPositionInGrid();

        theGrid[4][0].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,3,0, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[4][0].setSection(1);
        sections[0][4]=theGrid[4][0].getPositionInGrid();

        theGrid[4][1].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,0,3,3, Color.green),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[4][1].setSection(1);
        sections[0][5]=theGrid[4][1].getPositionInGrid();


        //section 2
        sections[1]=new int[3];
        theGrid[1][1].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,0,3, Color.blue),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[1][1].setSection(2);
        sections[1][0]=theGrid[1][1].getPositionInGrid();

        theGrid[2][1].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.blue),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[2][1].setSection(2);
        sections[1][1]=theGrid[2][1].getPositionInGrid();

        theGrid[3][1].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,3,3, Color.blue),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[3][1].setSection(2);
        sections[1][2]=theGrid[3][1].getPositionInGrid();

        //section 3
        sections[2]=new int[3];
        theGrid[0][2].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,0,3, Color.orange),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[0][2].setSection(3);
        sections[2][0]=theGrid[0][2].getPositionInGrid();

        theGrid[1][2].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.orange),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[1][2].setSection(3);
        sections[2][1]=theGrid[1][2].getPositionInGrid();

        theGrid[2][2].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,3,3, Color.orange),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[2][2].setSection(3);
        sections[2][2]=theGrid[2][2].getPositionInGrid();

        //section 4
        sections[3]=new int[3];
        theGrid[1][3].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,0,3, Color.MAGENTA),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[1][3].setSection(4);
        sections[3][0]=theGrid[1][3].getPositionInGrid();

        theGrid[2][3].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, Color.MAGENTA),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[2][3].setSection(4);
        sections[3][1]=theGrid[2][3].getPositionInGrid();

        theGrid[3][3].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,3,3, Color.MAGENTA),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[3][3].setSection(4);
        sections[3][2]=theGrid[3][3].getPositionInGrid();

        //section 5
        sections[4]=new int[4];
        theGrid[0][3].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,3,0, new Color(171, 22, 119)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[0][3].setSection(5);
        sections[4][0]=theGrid[0][3].getPositionInGrid();

        theGrid[0][4].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,0,0,3, new Color(171, 22, 119)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[0][4].setSection(5);
        sections[4][1]=theGrid[0][4].getPositionInGrid();

        theGrid[1][4].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,0,3, new Color(171, 22, 119)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[1][4].setSection(5);
        sections[4][2]=theGrid[1][4].getPositionInGrid();

        theGrid[2][4].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,3,3,3, new Color(171, 22, 119)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[2][4].setSection(5);
        sections[4][3]=theGrid[2][4].getPositionInGrid();

        //section 6
        sections[5]=new int[3];
        theGrid[4][2].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,3,3,0, new Color(181, 178, 25)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[4][2].setSection(6);
        sections[5][0]=theGrid[4][2].getPositionInGrid();

        theGrid[4][3].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,0,3,0, new Color(181, 178, 25)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[4][3].setSection(6);
        sections[5][1]=theGrid[4][3].getPositionInGrid();

        theGrid[4][4].setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3,0,3,3, new Color(181, 178, 25)),
                new LineBorder(Color.lightGray,1,true)));
        theGrid[4][4].setSection(6);
        sections[5][2]=theGrid[4][4].getPositionInGrid();

        //check answer button
        CheckAnswerButton answerButton = new CheckAnswerButton(this,fuwariBackground);
        answerButton.setBounds(440,605,130,50);
        answerButton.setBackground(new Color(8, 128, 44));
        answerButton.setForeground(Color.white);
        answerButton.setBorder(BorderFactory.createLineBorder(Color.black,3,true));

        ShowSolutionButton solutionButton = new ShowSolutionButton(this);
        solutionButton.setBounds(580,605,130,50);
        solutionButton.setBackground(new Color(8, 128, 44));
        solutionButton.setForeground(Color.white);
        solutionButton.setBorder(BorderFactory.createLineBorder(Color.black,3,true));

        JButton menu=new JButton();
        menu.setBounds(720,605,130,50);
        menu.setBackground(new Color(8, 128, 44));
        menu.setForeground(Color.white);
        menu.setFocusable(false);
        menu.setBorder(BorderFactory.createLineBorder(Color.black,3,true));
        menu.addActionListener(new ButtonToMenuListener(menu,this));

        fuwariBackground.add(answerButton);
        fuwariBackground.add(solutionButton);
        fuwariBackground.add(menu);
        fuwariBackground.add(table);

        getContentPane().add(fuwariBackground);

        setVisible(true);
    }

    @Override
    public boolean checkFinalRowConstraints(int row,int column) {
        //in the puzzle's sections, balloon goes to the first cell of the section taking into account only cells not pre-filled
        //iron ball goes to the last cell in the section

        boolean ok=true;

        int individualSection=theGrid[row][column].getSection();
        int position=theGrid[row][column].getPositionInGrid();
        //System.out.print(position + " ");

        //if its the first cell in that section, it shd be a balloon
        if(sections[individualSection-1][0]==position){
            ok= theGrid[row][column].getState() == 1;
        }

        //if its the last cell in the section, it shd be an iron ball
        if(sections[individualSection-1][(sections[individualSection-1].length)-1] == position){
            ok= theGrid[row][column].getState() == 2;
        }

        return ok;
    }

        /* state 0-white
           state 1-balloon
           state 2-iron ball
           state 3-blocked
         */

    @Override
    public boolean checkInitialRowConstraint(int row,int column) {
        boolean ok=true;

        int individualSection=theGrid[row][column].getSection();
        int position=theGrid[row][column].getPositionInGrid();
        //System.out.print(position + " ");

        //if its the first cell in that section, it shd be a balloon
        if(sections[individualSection-1][0]==position){
            ok= theGrid[row][column].getState() == 1;
        }

        //if its the last cell in the section, it shd be an iron ball
        if(sections[individualSection-1][(sections[individualSection-1].length)-1] == position){
            ok= theGrid[row][column].getState() == 2;
        }

        return ok;
    }

    //not needed for this puzzle
    @Override
    public boolean checkFinalColumnConstraints() { return true; }

    //not needed for this puzzle
    @Override
    public boolean checkInitialColumnConstraint(int row,int column) { return true; }

    //checks answer when the grid is not filled completely yet
    @Override
    public boolean checkAnswer() {
        boolean ok=true;

        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                int position=theGrid[i][j].getPositionInGrid();
                int state=theGrid[i][j].getState();

                if(state==1){
                    if(!isTop(position)) return false;
                }
                if(state==2){
                    if(!isBottom(position)) return false;
                }
            }
        }
        return ok;
    }

    //checks final answer when check answer button is pressed by player
    @Override
    public boolean checkFinalAnswer(){
        boolean ok=true;

        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                int position=theGrid[i][j].getPositionInGrid();
                int state=theGrid[i][j].getState();

                if(state==1){
                    if(!isTop(position)) return false;
                }
                if(state==2){
                    if(!isBottom(position)) return false;
                }
                if(state==0){
                    if(isBottom(position) || isTop(position)) return false;
                }
            }
        }
        return ok;
    }

    private boolean isTop(int position){
        boolean ok=false;

        for (int[] section : sections) {
            if (position == section[0]) return true;
        }
        return ok;
    }

    private boolean isBottom(int position){
        boolean ok=false;

        for (int[] section : sections) {
            if (position == section[(section.length) - 1]) return true;
        }
        return ok;
    }

    @Override
    public FuwariCell[][] getGrid() { return theGrid; }

    @Override
    public int[] possibleValues() {
        /* state 0-unshaded
       state 1-balloon
       state 2-iron ball
       state 3-blocked
     */
        return new int[]{0,1,2};
    }

    @Override
    public JFrame getGameFrame() { return this; }

    /*public static void main(String[] args) {
        Fuwari fuwari = new Fuwari();
    }*/
}
