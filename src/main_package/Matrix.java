package main_package;

import java.util.Random;

public class Matrix {
    private int[][] m;
    private int lines, columns;
    public Matrix(int lin, int col, int bombs){
        this.lines = lin;
        this.columns = col;
        this.m = new int[lin+2][col+2];

        Random generator = new Random();
        for(int n=0; n<bombs; n++){
            int x = generator.nextInt(lin)+1;
            int y = generator.nextInt(col)+1;

            if(m[x][y] == 9){
                n--;
                continue;
            }

            m[x][y] = 9;

            int dX[] = {0 , 0,  1, 1, 1, -1, -1, -1};
            int dY[] = {-1, 1, -1, 0, 1, -1,  0,  1};

            for(int i=0; i<8;i++){
                if( m[ x+dX[i] ][ y+dY[i] ] < 9)
                    m[ x+dX[i] ][ y+dY[i] ]+=1;
            }

        }
    }

    public int getPosition(int x, int y){
        if(x>=1 && x<=lines && y>=1 && y<=columns)
            return m[x][y];
        else
            return -999999;
    }

    public void printMatrix(){
        for(int i =1; i <= lines; i++){
            for(int j=1; j<=columns; j++){
                System.out.print(String.valueOf(m[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
