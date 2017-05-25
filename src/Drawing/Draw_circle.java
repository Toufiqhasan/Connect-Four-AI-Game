package Drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

public class Draw_circle extends JFrame implements ActionListener {
    
    int cnt = 0;
    int row, col;
    //int indx = 0;
    boolean first = false;

    JLabel l1 = new JLabel();
    JLabel l3 = new JLabel();
    JLabel l2 = new JLabel();

    JPanel full_panel = new JPanel();
    JPanel jp = new JPanel(new GridLayout(1, 7));
    JPanel jp1 = new JPanel(new GridLayout(1, 4));
    JPanel jp2 = new JPanel();

    JButton[] buttons = new JButton[10];
    JButton start = new JButton("Restart");
    JButton drawframe = new JButton("Draw Frame");
    JButton rules = new JButton("Rules");
    public static boolean[][] color = new boolean[900][900];
    public static int[][] value = new int[900][900];
    public static int[] total_value = new int[10];
    boolean man_pressed = false;
    boolean it_is_ai_move = false;
    public static boolean ai_win = false;

    
    public Draw_circle() {
        

        
        setTitle("Connect Four Game");
        setLayout(null);
        setSize(500, 520);
        setLocationRelativeTo(null);

        /// first panel
        jp.setLocation(1, 5);
        jp.setSize(getWidth(), 50);
        ///jp.setBackground(Color.BLACK);

        start.setPreferredSize(new Dimension(40, 40));
        start.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22));
        start.setBackground(new java.awt.Color(204, 255, 204));
        start.setForeground(Color.BLUE);

        rules.setPreferredSize(new Dimension(40, 40));
        rules.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22));
        rules.setBackground(new java.awt.Color(204, 255, 204));
        rules.setForeground(Color.BLUE);

        drawframe.setPreferredSize(new Dimension(40, 60));
        drawframe.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22));
        drawframe.setBackground(new java.awt.Color(204, 255, 204));
        drawframe.setForeground(Color.RED);

        jp.add(start);
        jp.add(drawframe);
       // jp.add(l2);
        jp.add(rules);
        add(jp);

        /// second panel
        jp1.setLocation(0, 60);
        jp1.setSize(getWidth(), 40);
        ///jp1.setBackground(Color.PINK);

        for (int i = 1; i <= 7; i++) {
            if (i == 1) {
                buttons[i] = new JButton("1");
            }
            if (i == 2) {
                buttons[i] = new JButton("2");
            }
            if (i == 3) {
                buttons[i] = new JButton("3");
            }
            if (i == 4) {
                buttons[i] = new JButton("4");
            }
            if (i == 5) {
                buttons[i] = new JButton("5");
            }
            if (i == 6) {
                buttons[i] = new JButton("6");
            }
            if (i == 7) {
                buttons[i] = new JButton("7");
            }
        }
        for (int i = 1; i <= 7; i++) {
            buttons[i].setPreferredSize(new Dimension(50, 30));
            buttons[i].setFont(new java.awt.Font("Times New Roman", 0, 25));
            buttons[i].setBackground(Color.ORANGE);
            buttons[i].setForeground(Color.BLUE);
            jp1.add(buttons[i]);
        }
        add(jp1);

        /// third panel
        jp2.setLocation(0, 110);
        jp2.setSize(getWidth(), 460);
        jp2.setBackground(Color.white);
        jp2.getGraphics();

        add(jp2);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 1; i <= 7; i++) {
            buttons[i].addActionListener(this);
        }
        start.addActionListener(this);
        rules.addActionListener(this);
        drawframe.addActionListener(this);

        for (int i = 10; i <= 430; i += 70) {

            for (int j = 10; j <= 310; j += 60) {
                ///System.out.println("" + i + " " + j);

                value[i][j] = 0; /// value 0 for indicating that this is a empty circle
                if (j == 310) {

                    color[i][j] = false;

                } else {
                    color[i][j] = true;
                }
            }
        }

    }

    public void AiMove() {
        
        ai_win=false;

        AiMoveCalculate aic = new AiMoveCalculate();
        aic.vertical_search();
        aic.horizontal_search();

        int best_move = aic.move();

        System.out.println("the best move is " + best_move + " " + total_value[best_move]);

        row = best_move;
        row = (row - 1) * 70 + 10;
        ///System.out.println("row " + row);

        for (int j = 10; j <= 310; j += 60) {

            if (color[row][j] == false) {
                Graphics g = jp2.getGraphics();
                g.setColor(Color.red);
                g.fillOval(row, j, 51, 51);
                if(j-60>=10)
                {
                    color[row][j - 60] = false;
                }
                
                color[row][j] = true;
                value[row][j] = 2;
                break;
            }
        }

        // ai makes a move that is winning move 
        boolean aw = false;
        if (ai_win == true) {
            aw = true;
            JOptionPane.showMessageDialog(null, "Artificial Intelligence Win !!!!Game over");
            it_is_ai_move = false;
            man_pressed = false;
            for (int j = 1; j <= 7; j++) {
                buttons[j].setEnabled(false);
            }
            ai_win = false;
           
        }

        /// AI move finish
        if (aw == false) {

            int u = 0;

            for (int i = 10; i <= 435; i += 70) {

                for (int j = 10; j <= 310; j += 60) {

                    if (color[i][j] == true) {
                        u++;
                    }

                }
            }
            if (u == 42) {
                JOptionPane.showMessageDialog(null, "Its a Draw !!!!!!!");
               
                for (int j = 1; j <= 7; j++) {
                    buttons[j].setEnabled(false);
                }
            }
        }

    }

    public boolean HumanMove(int n) {
        
        boolean mn_wn=false;

        boolean is_coloum_is_empty = false;

        row = (n - 1) * 70 + 10;
        //System.out.println("row " + row);

        for (int j = 10; j <= 310; j += 60) {

            if (color[row][j] == false) {
                
           
                Graphics g = jp2.getGraphics();
                is_coloum_is_empty = true;
                g.setColor(Color.GREEN);
                g.fillOval(row, j, 51, 51);
                if(j-60>=10)
                {
                    color[row][j - 60] = false;
                }
                
                color[row][j] = true;
                value[row][j] = 1; /// value is 1 for the color of man
                break;
            }
        }
        
        ///System.out.println("HUMAN");

        if (is_coloum_is_empty == true) {

            for (int j = 1; j <= 7; j++) {
                buttons[j].setEnabled(false);
            }
            include_thread ith = new include_thread();

            ith.start();

            try {
                ith.join();
            } catch (Exception e) {
            }

            for (int j = 1; j <= 7; j++) {
                buttons[j].setEnabled(true);
            }

            /// search winning condition for man
            man_winnging_condition mwc = new man_winnging_condition();

            boolean man_win = false;

            man_win = mwc.mmm();
            

            if (man_win == true) {
                mn_wn=true;

                JOptionPane.showMessageDialog(null, "Human Wins!!!! Game Over");

                for (int j = 1; j <= 7; j++) {
                    buttons[j].setEnabled(false);
                }

            }

        } else if (is_coloum_is_empty == false) {
            JOptionPane.showMessageDialog(null, "The column is not empty. try another column");
            mn_wn=true;

        }
        
        return mn_wn;
    }

    public void actionPerformed(ActionEvent ae) {

        try {

            for (int i = 1; i <= 7; i++) {
                if (ae.getSource() == buttons[i]) {
                    boolean jj=HumanMove(i);
                    if(jj==false)
                    {
                        ///System.out.println("no exception");
                        AiMove();
                    }
                    
                    break;
                }
            }

            if (ae.getSource() == start) {

                Draw_circle dc1 = new Draw_circle();
                dc1.setVisible(true);
                dispose();
            }

            if (ae.getSource() == drawframe) {

                Graphics g = jp2.getGraphics();

                for (int i = 10; i <= 430; i += 70) {
                    ;
                    for (int j = 10; j <= 310; j += 60) {
                        g.drawOval(i, j, 50, 50);

                    }

                }
                
                drawframe.setEnabled(false);
            }

            if (ae.getSource() == rules) {
                JOptionPane.showMessageDialog(null, "The game plays with alternative turn.\nFirst Human plays a turn from the Grid then\nComputer thinks its best move and then play\nWho can make first four of its own color \nHorizontaly or vertically is the winner of the game");

            }

        } catch (Exception e) {
            System.out.println("exception");
        }
    }

    public static void main(String args[]) {

        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Times New Roman", Font.BOLD, 18)));
        UIManager.put("OptionPane.background", Color.ORANGE);
        UIManager.put("Panel.background", Color.LIGHT_GRAY);

        Draw_circle dc = new Draw_circle();

    }
}
