package main_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {

    private ButtonTable b;
    public Game(int lin, int col, int bombs){



        b = new ButtonTable(lin, col, bombs);





        JFrame window = new JFrame("Minesweeper");

        JPanel windowLayout = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Good Luck");
        JPanel buttonPanel = new JPanel(new GridLayout(lin, col));

        for(int i=1; i<=lin; i++){
            for(int j=1; j<=col; j++){
                buttonPanel.add(b.getButton(i,j));
            }
        }

        windowLayout.add(title, BorderLayout.NORTH);
        windowLayout.add(buttonPanel, BorderLayout.CENTER);
        windowLayout.add(b.getEndPanel(), BorderLayout.SOUTH);

        window.getContentPane().add(windowLayout);

        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    public static void main(String[] args) {
        Settings s = new Settings();
    }
}
