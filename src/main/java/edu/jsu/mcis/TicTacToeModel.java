package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(final String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(final String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        for (int i = 0; i < width; i++){
            
            for (int j = 0; j < width; j++){

                board[i][j] = Mark.EMPTY;
            }

        }
        
        // INSERT YOUR CODE HERE
        
    }
	
    public boolean makeMark( int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        if (isValidSquare(row, col) && !isSquareMarked(row, col))  {

            if (isXTurn()) {

                board[row][col] = Mark.X;

                xTurn = false;
            }
            else{

                board[row][col] = Mark.O;
           
                xTurn = true;
            }
            
            return true;

        }
       
        return false; // remove this line later!
        }
    
	
    private boolean isValidSquare( int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        if ((row < width && row > -1) && (col < width && col > -1)) {
           
            return true;
        }

        else{

        
             return false;
        }
    }
    
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        if (!board[row][col].equals(Mark.EMPTY)){
            return true;
        }
        
        else {
            return false;
        }
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];
        
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if (isMarkWin(Mark.X) == true){
            return Result.X;
          }
          if (isMarkWin(Mark.O) == true){
             return Result.O;
          }
          if (isTie() == true){
              return Result.TIE;
          }
          else{
        return Result.NONE;
          }

        
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
          
        int vertCount = 0;
        int horoCount = 0;
        int downRight = 0;
        int upRight = 0;
       
        //rows
       for (int i = 0; i < width; i++){
           if( horoCount != width ){
           horoCount = 0;
       }
           for (int j = 0; j < width; j++){
               if (board[i][j] == mark) {
                   horoCount++;
                }
               
               
           }
       }
       
       //columns
       for (int j = 0; j < width; j++){
           if( vertCount != width ){
           vertCount = 0;
       
           for (int i = 0; i < width; i++){
               if (board[i][j] == mark) {
                   vertCount++;
               }
               
               
           }
         }
       }
       
       //downRight
       for(int i = 0; i < width; i++){
           if (board[i][i] == mark){
               downRight++;
           }
       }
       
       //upRight
       for(int i = 0; i < width; i++){
           if (board[i][width-i-1] == mark){
           upRight++;
           }   
              
       }
       if (horoCount == width ){
           return true;  
       }
       else if (vertCount == width ){
        return true;  
    }
    else if (downRight == width ){
        return true;  
    }
    else if (upRight == width ){
        return true;
    }

       return false; // remove this line later!
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        int emptySpots = 0;
        
        for (int i = 0; i < width; i++){
              for (int j = 0; j < width; j++){
                  
                 if (board[i][j] == Mark.EMPTY){
                     emptySpots++;
                 }
              }
        }
        
        if (emptySpots == 0){
            return true;
        }
        else{
        return false; // remove this line later!
        }
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        final StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE
        for ( int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){

                if (i == 0 && j == 0) {
                    for (int c = 0; c < width; c++){
                        output.append(c);
                    }
                    output.append("\n");
                }
                if (j == 0){
                    output.append(i + " ");
                }
                output.append(board[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
        
    }
    
}
