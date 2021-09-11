
public class StartGame {
    public static void main(String[] args) {
        try{
            javax.swing.UIManager.setLookAndFeel(
                    new javax.swing.plaf.metal.
                            MetalLookAndFeel());
        }
        catch(Exception e){}

        MainFrame newGame=new MainFrame();
    }
}
