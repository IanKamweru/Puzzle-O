import javax.swing.*;
import java.awt.*;

public class Kakurasu extends JFrame implements Puzzle {
    private final int rows=5;
    private final int columns=5;
    private final KakurasuCell[][] theGrid = new KakurasuCell[rows][columns];

    private final int[] rowValues={1,15,2,4,7};
    private final int[] columnValues={3,5,7,11,2};
    private int[] rowTotals;
    private int[] columnTotals;


    public Kakurasu() {
        setSize(new Dimension(1000,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setTitle("Kakurasu");
        setResizable(false);
        ImageIcon icon = new ImageIcon("icon1.jpg");
        setIconImage(icon.getImage());

        KakurasuBackgroundPanel kakurasuBackground = new KakurasuBackgroundPanel("kakurasubackground.jpg");

        JPanel table = new JPanel();
        table.setBounds(400,50,500,500);
        table.setBorder(BorderFactory.createLineBorder(Color.orange,6,true));
        table.setLayout(new GridLayout(5,5));

        //setting theGrid
        for(int i=0;i<theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                theGrid[i][j] = new KakurasuCell(i+1,j+1);
            }
        }


        for (KakurasuCell[] button : theGrid) {
            for (KakurasuCell kakurasuCell : button) {
                table.add(kakurasuCell);
            }
        }

        //check answer button
        CheckAnswerButton answerButton = new CheckAnswerButton(this,kakurasuBackground);
        answerButton.setBounds(440,605,130,50);
        answerButton.setBackground(new Color(8, 128, 44));
        answerButton.setForeground(Color.white);
        answerButton.setBorder(BorderFactory.createLineBorder(Color.black,3,true));


        ShowSolutionButton solutionButton = new ShowSolutionButton(this);
        solutionButton.setBounds(580,605,130,50);
        solutionButton.setBackground(new Color(8, 128, 44));
        solutionButton.setForeground(Color.white);
        solutionButton.setBorder(BorderFactory.createLineBorder(Color.black,3,true));

        //menu
        JButton menu=new JButton();
        menu.setBounds(720,605,130,50);
        menu.setBackground(new Color(8, 128, 44));
        menu.setForeground(Color.white);
        menu.setFocusable(false);
        menu.setBorder(BorderFactory.createLineBorder(Color.black,3,true));
        menu.addActionListener(new ButtonToMenuListener(menu,this));

        kakurasuBackground.add(answerButton);
        kakurasuBackground.add(solutionButton);
        kakurasuBackground.add(menu);
        kakurasuBackground.add(table);

        getContentPane().add(kakurasuBackground);

        setVisible(true);
    }

    public KakurasuCell[][] getGrid(){ return theGrid; }

    @Override
    public int[] possibleValues() {
        //0-not shaded
        //1-shaded

        return new int[]{0,1};
    }

    @Override
    public JFrame getGameFrame() { return this; }

    @Override
    public boolean checkFinalRowConstraints(int row,int column) {
        //vertical values add up to the column totals
        //horizontal values add up to the row totals
        boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            assert rowTotals != null;
            if(rowTotals[i] != 0 ) {
                if (rowTotals[i] != rowValues[i]) {
                    ok = false;
                    break;
                }
            }
        }

        return ok;
    }

    @Override
    public boolean checkFinalColumnConstraints() {
        boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if (columnTotals[i] != columnValues[i]) {
                ok = false;
                break;
            }
        }

        return ok;
    }

    @Override
    public boolean checkInitialRowConstraint(int row,int column) {
        boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if((rowTotals[i] != 0)) {
                if(rowTotals[i] > rowValues[i]) ok=false; //
            }
        }

        return ok;
    }

    @Override
    public boolean checkInitialColumnConstraint(int row,int column) {
        boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if((columnTotals[i] != 0)) {
                if(columnTotals[i] > columnValues[i]) ok=false; //
            }
        }

        return ok;
    }

    //checks answer when the grid is not filled completely yet
    @Override
    public boolean checkAnswer() {
        boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if((rowTotals[i] != 0)) {
                if(rowTotals[i] > rowValues[i]) ok=false; //
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if((columnTotals[i] != 0)) {
                if(columnTotals[i] > columnValues[i]) ok=false; //
            }
        }

        return ok;
    }

    //checks final answer when check answer button is pressed by player
    @Override
    public boolean checkFinalAnswer(){boolean ok=true;
        rowTotals=new int[rows];
        columnTotals=new int[columns];

        for(int i=0;i< theGrid.length;i++){
            for(int j=0;j<theGrid[i].length;j++){
                if(theGrid[i][j].isShaded()) {
                    rowTotals[i]+=theGrid[i][j].getHorizontalValue();
                    columnTotals[j]+=theGrid[i][j].getVerticalValue();
                }
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if((rowTotals[i] != 0)) {
                if(rowTotals[i] > rowValues[i]) ok=false; //
            }
        }

        for(int i=0;i< theGrid.length;i++){
            if (columnTotals[i] != columnValues[i]) {
                ok = false;
                break;
            }
        }

        return ok;
    }

    /*public static void main(String[] args) {
        Kakurasu a = new Kakurasu();
    }*/

}
