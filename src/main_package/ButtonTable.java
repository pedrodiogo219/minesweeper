package main_package;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonTable {

    private Matrix m;
    private JButton[][] b;
    private JPanel endPanel;
    private JPanel imagePanel;
    private int opened_buttons;
    private int lin, col, bombs;
    
    public ButtonTable(int lin, int col, int bombs){

        this.lin = lin;
        this.col = col;
        this.bombs = bombs;
        
        
        opened_buttons = 0;
        this.m = new Matrix(lin, col, bombs);
        m.printMatrix();


        b = new JButton[lin+2][col+2];
        for(int i=1; i<=lin; i++) {
            for (int j = 1; j <= col; j++) {
                b[i][j] = new JButton("   ");
            }
        }

        endPanel = new JPanel();
        JLabel imageLabelNeutral = new JLabel(new ImageIcon("img1.png"));
        JLabel imageLabelHappy = new JLabel(new ImageIcon("img3.png"));
        JLabel imageLabelSad = new JLabel(new ImageIcon("img2.png"));
        
        imagePanel = new JPanel();
        imagePanel.add(imageLabelNeutral);

        JLabel youLoseLabel = new JLabel("You lose.");
        JLabel youWinLabel = new JLabel("You win");
        
        for(int i=1; i<=lin; i++){
            for(int j=1; j<=col; j++){
                JButton actualButton = b[i][j];
                String s = String.valueOf(m.getPosition(i,j));

                int r = i;
                int t = j;

                if(s.equals("0")){
                    actualButton.addActionListener(
                            (ActionEvent e) -> {
                                open_button(openZeros(r,t));
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
                                        imagePanel.removeAll();
                                        imagePanel.add(imageLabelSad);
                                        
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
                                open_button(1);
                                if(win()){
                                    for(int w=1; w<=lin; w++){
                                        for(int z=1; z<=col; z++){
                                            b[w][z].setText( m.getPosition(w,z)==9 ? "B" : String.valueOf(m.getPosition(w,z)) );
                                            b[w][z].setEnabled(false);
                                        }                                        
                                    }
                                    endPanel.add(youWinLabel);
                                    imagePanel.removeAll();
                                    imagePanel.add(imageLabelHappy);
                                }
                                
                            }

                    );
                }
            }
        }
    }

    public JButton getButton(int x, int y){
        return b[x][y];
    }

    public int openZeros(int x, int y){
        b[x][y].setEnabled(false);

        int dX[] = {0 , 0,  1, 1, 1, -1, -1, -1};
        int dY[] = {-1, 1, -1, 0, 1, -1,  0,  1};

        int soma=0;
        for(int i=0; i<8; i++){
            if(m.getPosition( x+dX[i], y+dY[i]) == 0 && b[x+dX[i]][y+dY[i]].isEnabled() ){
                soma= soma + openZeros(x+dX[i], y+dY[i]);
            }
        }
        return soma+1;
    }

    public JPanel getEndPanel(){
        return endPanel;
    }
    
    public JPanel getImagePanel(){
        return imagePanel;
    }
    
    public void open_button(int x){
        opened_buttons += x;
    }
    public boolean win(){
        if(lin*col - bombs == opened_buttons){
            return true;
        }
        else return false;
    }
}
