package main_package;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonTable {

    private Matrix m;
    private JButton[][] b;
    private JPanel endPanel;
    public ButtonTable(int lin, int col, int bombs){

        this.m = new Matrix(lin, col, bombs);
        m.printMatrix();


        b = new JButton[lin+2][col+2];
        for(int i=1; i<=lin; i++) {
            for (int j = 1; j <= col; j++) {
                b[i][j] = new JButton("   ");
            }
        }

        endPanel = new JPanel();

        JLabel youLoseLabel = new JLabel("You lose.");

        for(int i=1; i<=lin; i++){
            for(int j=1; j<=col; j++){
                JButton actualButton = b[i][j];
                String s = String.valueOf(m.getPosition(i,j));

                int r = i;
                int t = j;

                if(s.equals("0")){
                    actualButton.addActionListener(
                            (ActionEvent e) -> {
                                openZeros(r,t);
                            }
                    );
                }
                else if(s.equals("9")){
                    actualButton.addActionListener(
                            (ActionEvent e) -> {

                                for(int w=1; w<=lin; w++){
                                    for(int z=1; z<=col; z++){
                                        b[w][z].setText( m.getPosition(w,z)==9 ? "B" : String.valueOf(m.getPosition(w,z)) );
                                        b[w][z].setEnabled(false);
                                        endPanel.add(youLoseLabel);
                                    }
                                }

                            }
                    );
                }
                else {
                    actualButton.addActionListener(
                            (ActionEvent e) -> {
                                actualButton.setText(s);
                                actualButton.setEnabled(false);
                            }

                    );
                }
            }
        }
    }

    public JButton getButton(int x, int y){
        return b[x][y];
    }

    public void openZeros(int x, int y){
        b[x][y].setEnabled(false);

        int dX[] = {0 , 0,  1, 1, 1, -1, -1, -1};
        int dY[] = {-1, 1, -1, 0, 1, -1,  0,  1};

        for(int i=0; i<8; i++){
            if(m.getPosition( x+dX[i], y+dY[i]) == 0 && b[x+dX[i]][y+dY[i]].isEnabled() ){
                openZeros(x+dX[i], y+dY[i]);
            }
        }
    }

    public JPanel getEndPanel(){
        return endPanel;
    }

}
