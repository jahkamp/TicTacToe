
import java.awt.GridLayout;
import javax.swing.*;


/**
 * Write a description of class TicTacToe here.
 * 
 * @author Brendan
 * @version 13 Jan 2011
 */
public class TicTacToe extends JFrame
{
    // instance variables - replace the example below with your own


    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe()
    {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new TTTPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true); // Show the JFrame.

    }

    public static void main(String[] args) {
        TicTacToe newGame = new TicTacToe();
        //JOptionPane.showMessageDialog(newGame, "This is a test.");
    }
}
