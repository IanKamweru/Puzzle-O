

https://user-images.githubusercontent.com/90436611/157787545-8efed272-b92e-4815-b2f0-3f2701e06c28.mp4

# Puzzle-O
The Puzzle interface is implemented by the three puzzles and contains a set of methods to check the row and column constraints. There’s also a set of methods (checkAnswer and checkFinalAnswer) which are a different set of constraints called when the user presses the Check Answer button as they continue filling the puzzle. Puzzle also contains a method to get the grid, get possible values and get the game frame.

-The Spot class extends JButton and all the in-puzzle buttons for each puzzle extend this class.

-The Cell classes are the puzzle buttons that extend Spot.

-The CheckAnswerButton, ShowSolutionButton and ExitButton classes extend JButton and set up the corresponding function buttons. Each has a listener class separately.

-The OptionButton class set up the buttons in the main menu in which one chooses a puzzle. The classes StartHitoriListener, StartKakurasuListener and StartFuwariListener implement action listener and are added to the OptionButton when necessary.

-The Hitori, Kakurasu and Fuwari BackgroundPanel and StartPanel classes are for setting up the background graphics on the respective panels.

-The MainFrame class sets up the main menu where the user initially chooses between the puzzles.

-The StartGame class is the main program - it starts the game.

Within each puzzle, one can switch to a different one with a menu pop-up on the bottom right.

How does the user make a move? The user clicks the buttons to switch between options.

	       -Hitori and Kakurasu-shaded and not-shaded
         
	       -Fuwari-empty, balloon and iron ball
         
What commands can the user do?
-In each puzzle, the user can check their answer as they continue solving the puzzle by pressing the Check Answer button.

-The user can check the solution by pressing the Show Solution button which calls the FindSolution class/method.

-In the Menu pop-up, the user can switch to a different new puzzle and exit the game.

