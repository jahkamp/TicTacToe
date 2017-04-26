
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Write a description of class TTTPanel here.
 *
 * @author Brendan
 * @version Jan 21
 */
public class TTTPanel extends JPanel {

    // instance variables - replace the example below with your own
    private int size = 300;
    //private TicTacToe tttGame;
    private Random rand;
    // TTT board data:
    private char curPlayer;
    private char[][] board;
    private boolean computerMovePlayed;
    /**
     * Constructor for objects of class MondrianFrame
     */
    public TTTPanel() {
        setPreferredSize(new Dimension(size, size));
        addMouseListener(new TTTMouseListener());

        // initialize the board:
        board = new char[3][3]; // initialize board
        rand = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' '; // blank char
            }
        }
        computerMovePlayed = false;
        curPlayer = 'X';
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.drawLine(0, 100, 300, 100);
        g.drawLine(0, 200, 300, 200);
        g.drawLine(100, 0, 100, 300);
        g.drawLine(200, 0, 200, 300);

        Font f = new Font("Times", Font.PLAIN, 50);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        int a = fm.getAscent();
        int h = fm.getHeight();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char curSquare = getSquare(i, j);
                String curSqStr = Character.toString(curSquare);
                int w = fm.stringWidth(curSqStr);
                g.drawString(curSqStr, 100 * i + 50 - w / 2, 100 * j + 50 + a - h / 2);
            }
        }
    }

    // INNER CLASS for a Mouse events:
    private class TTTMouseListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            //System.out.println("mouse entered");
        }

        public void mouseExited(MouseEvent e) {
            //System.out.println("mouse exited");
        }

        public void mouseClicked(MouseEvent e) {
            int x = e.getX() / 100;
            int y = e.getY() / 100;
            if (getSquare(x, y) == ' ') {
                setSquare(x, y, getCurPlayer());
                repaint();
                switchPlayer();
                checkForGameEnd();
            }
            if(curPlayer == 'O')
            {
                computerMovePlayed = false;
                computersTurn();
                repaint();
                switchPlayer();
                checkForGameEnd();
            }
        }
    }
    private void computersTurn()
    {
        boolean turnOver = false;
        //examine current state of board.        
        for(int i = 0; i < 3; i++)
        {   
            for(int j = 0; j < 3; j++)
            {      
                if(getSquare(i,j) == 'X' && !computerMovePlayed)
                {
                   turnOver = blockMove(i,j);
                }
                else if(getSquare(i,j) == 'O' && !computerMovePlayed)
                {
                   turnOver = winMove(i,j);
                }    
            }              
        }                
        while(!turnOver)
        {            
            turnOver = randMove();
        }
    }
    private boolean blockMove(int i, int j)
    {
        boolean canBlock = false;
        switch(i)
        {
            case 0: 
                if(getSquare(i+1, j) == 'X' && getSquare(i+2, j) == ' ')
                {
                    setSquare(i+2, j, 'O');
                    canBlock = true;
                }
                else if(getSquare(i+2, j) == 'X' && getSquare(i+1, j) == ' ')
                {
                    setSquare(i+1, j, 'O');
                    canBlock = true;
                }
                break;
            case 1: 
                if(getSquare(i-1, j) == 'X' && getSquare(i+1, j) == ' ')
                {
                    setSquare(i+1, j, 'O');
                    canBlock = true;
                }
                else if(getSquare(i+1, j) == 'X' && getSquare(i-1, j) == ' ')
                {
                    setSquare(i-1, j, 'O');
                    canBlock = true;
                }
                break;
            case 2: 
                if(getSquare(i-1, j) == 'X' && getSquare(i-2, j) == ' ')
                {
                    setSquare(i-2, j, 'O');
                    canBlock = true;
                }
                else if(getSquare(i-2, j) == 'X' && getSquare(i-1, j) == ' ')
                {
                    setSquare(i-1, j, 'O');
                    canBlock = true;
                }
                break;
        }
        switch(j)
        {
            case 0: 
                if(getSquare(i, j+1) == 'X' && getSquare(i, j+2) == ' ')
                {
                    setSquare(i, j+2, 'O');
                    canBlock = true;
                }
                else if(getSquare(i, j+2) == 'X' && getSquare(i, j+1) == ' ')
                {
                    setSquare(i, j+1, 'O');
                    canBlock = true;
                }
                break;
            case 1: 
                if(getSquare(i, j-1) == 'X' && getSquare(i, j+1) == ' ')
                {
                    setSquare(i, j+1, 'O');
                    canBlock = true;
                }
                else if(getSquare(i, j+1) == 'X' && getSquare(i, j-1) == ' ')
                {
                    setSquare(i, j-1, 'O');
                    canBlock = true;
                }
                break;
            case 2: 
                if(getSquare(i, j-1) == 'X' && getSquare(i, j-2) == ' ')
                {
                    setSquare(i, j-2, 'O');
                    canBlock = true;
                }
                else if(getSquare(i, j-2) == 'X' && getSquare(i, j-1) == ' ')
                {
                    setSquare(i, j-1, 'O');
                    canBlock = true;
                }
                break;
        }
        return canBlock;
    }
    private boolean winMove(int i, int j)
    {
        boolean canWin = false;
        switch(i)
        {
            case 0: 
                if(getSquare(i+1, j) == 'O' && getSquare(i+2, j) == ' ')
                {
                    setSquare(i+2, j, 'O');
                    canWin = true;
                }
                else if(getSquare(i+2, j) == 'O' && getSquare(i+1, j) == ' ')
                {
                    setSquare(i+1, j, 'O');
                    canWin = true;
                }
                break;
            case 1: 
                if(getSquare(i-1, j) == 'O' && getSquare(i+1, j) == ' ')
                {
                    setSquare(i+1, j, 'O');
                    canWin = true;
                }
                else if(getSquare(i+1, j) == 'O' && getSquare(i-1, j) == ' ')
                {
                    setSquare(i-1, j, 'O');
                    canWin = true;
                }
                break;
            case 2: 
                if(getSquare(i-1, j) == 'O' && getSquare(i-2, j) == ' ')
                {
                    setSquare(i-2, j, 'O');
                    canWin = true;
                }
                else if(getSquare(i-2, j) == 'O' && getSquare(i-1, j) == ' ')
                {
                    setSquare(i-1, j, 'O');
                    canWin = true;
                }
                break;
        }
        switch(j)
        {
            case 0: 
                if(getSquare(i, j+1) == 'O' && getSquare(i, j+2) == ' ')
                {
                    setSquare(i, j+2, 'O');
                    canWin = true;
                }
                else if(getSquare(i, j+2) == 'O' && getSquare(i, j+1) == ' ')
                {
                    setSquare(i, j+1, 'O');
                    canWin = true;
                }
                break;
            case 1: 
                if(getSquare(i, j-1) == 'O' && getSquare(i, j+1) == ' ')
                {
                    setSquare(i, j+1, 'O');
                    canWin = true;
                }
                else if(getSquare(i, j+1) == 'O' && getSquare(i, j-1) == ' ')
                {
                    setSquare(i, j-1, 'O');
                    canWin = true;
                }
                break;
            case 2: 
                if(getSquare(i, j-1) == 'O' && getSquare(i, j-2) == ' ')
                {
                    setSquare(i, j-2, 'O');
                    canWin = true;
                }
                else if(getSquare(i, j-2) == 'O' && getSquare(i, j-1) == ' ')
                {
                    setSquare(i, j-1, 'O');
                    canWin = true;
                }
                break;
        }
        return canWin;
    }
    private boolean randMove()
    {
        boolean randPickMade = false;
        int i = rand.nextInt(2);
        int j = rand.nextInt(2);
        if(getSquare(i,j) == ' ')
        {
            setSquare(i,j, 'O');
            randPickMade = true;
        }
        return randPickMade;
    }

    public TTTPanel(Random rand, char curPlayer, char[][] board) {
        this.rand = rand;
        this.curPlayer = curPlayer;
        this.board = board;
    }
    public char getCurPlayer() {
        return curPlayer;
    }


    public void switchPlayer() {
        if (curPlayer == 'X')
            curPlayer = 'O';
        else
            curPlayer = 'X';
    }

    public void setSquare(int x, int y, char c) 
    {
        board[x][y] = c;
        if(curPlayer == 'O')
            computerMovePlayed = true;
    }

    public char getSquare(int x, int y) 
    {
        return board[x][y];
    }

    public void printBoard() 
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    private boolean checkForWin(char p) {

        boolean win = false;
        // check row wins:
        for (int i = 0; i < 3; i++) {
            win = win || (board[i][0] == p && board[i][1] == p && board[i][2] == p);
        }
        // check column wins:
        for (int j = 0; j < 3; j++) {
            win = win || (board[0][j] == p && board[1][j] == p && board[2][j] == p);
        }
        // check diagonal wins:
        win = win || (board[0][0] == p && board[1][1] == p && board[2][2] == p);
        win = win || (board[0][2] == p && board[1][1] == p && board[2][0] == p);

        return win;
    }

    private boolean checkFullBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void checkForGameEnd() {
        if (checkForWin('X')) {
            JOptionPane.showMessageDialog(this, "X wins!!!!");
            System.exit(0);
        } else if (checkForWin('O')) {
            JOptionPane.showMessageDialog(this, "O wins!!!!");
            System.exit(0);
        } else if (checkFullBoard()) {
            JOptionPane.showMessageDialog(this, "Game over, draw.");
            System.exit(0);
        }

    }
    
}
