package edu.jsu.mcis;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView();
        
    }

    public void start() {
    
        JFrame win = new JFrame("Tic-Tac-Toe");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        win.add(this.getView());
        win.pack();
        win.setVisible(true);

    }

    public String getMarkAsString (int row, int col) {
        return model.getMark(row,col).toString();
    } 

    public TicTacToeView getView(){
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // INSERT YOUR CODE HERE
        String name = ((JButton) event.getSource()).getName();
        int row, col;
        row = col = -1;
        
        for (int i = 0; i < name.length(); i++){
            char c = name.charAt(i);        
            if (Character.isDigit(c)){
                if (row == -1){
                    row = (c - 48);
                } 
                else {
                    col = (c - 48);
                }
            }
        }
        model.makeMark(row, col);
        view.updateSquares();

        if (model.isGameover()){
            view.showResult((model.getResult()).toString());

            view.disableSquares();
        }

    }

}
