package main_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Settings {
    public Settings(){

        JFrame window = new JFrame("Settings");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LINES-------------------------------------------------------
        JTextField linField = new JTextField("5", 3);
        JLabel linLabel = new JLabel("Number of lines:       ");
        JPanel linPanel = new JPanel();
        linPanel.add(linLabel); linPanel.add(linField);

        //COLUMNS-----------------------------------------------------
        JTextField colField = new JTextField("5", 3);
        JLabel colLabel = new JLabel("Number of columns:  ");
        JPanel colPanel = new JPanel();
        colPanel.add(colLabel); colPanel.add(colField);

        //BOMBS-------------------------------------------------------
        JTextField bombsField = new JTextField("5", 3);
        JLabel bombsLabel = new JLabel("Number of bombs:     ");
        JPanel bombsPanel = new JPanel();
        bombsPanel.add(bombsLabel); bombsPanel.add(bombsField);

        

        JPanel settingLayout = new JPanel(new GridLayout(3,1));
        settingLayout.add(linPanel);
        settingLayout.add(colPanel);
        settingLayout.add(bombsPanel);


        JButton okButton = new JButton("Start game");
        okButton.addActionListener(
                (ActionEvent e) -> {
                    int lin = Integer.parseInt(linField.getText());
                    int col = Integer.parseInt(colField.getText());
                    int bombs = Integer.parseInt(bombsField.getText());

                    window.setVisible(false);
                    new Game(lin, col, bombs);
                }
        );

        JPanel windowLayout = new JPanel(new BorderLayout());
        windowLayout.add(settingLayout, BorderLayout.CENTER);
        windowLayout.add(okButton, BorderLayout.SOUTH);

        window.add(windowLayout);
        window.pack();
        window.setVisible(true);
    }

}

