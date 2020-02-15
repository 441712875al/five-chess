package entity;


import enums.Side;
import javafx.scene.control.Alert;

public class FiveChess{

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getCellLen() {
        return cellLen;
    }

    public void setCellLen(double cellLen) {
        this.cellLen = cellLen;
    }

    /**

     * 维度

     */

    private int n;

    private double width;

    private double height;

    /**
     * 小方格的大小
     */
    private double cellLen;

    /**
     * 当前棋手
     */
    private Side currentSide=Side.BLACK;

    public char getFlag() {
        return flag;
    }

    private char flag=' ';

    private int[][] chess;

    public int[][] getChess() {
        return chess;
    }

    public void setChess(int[][] chess) {
        this.chess = chess;
    }

    public Side getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }



    public FiveChess(double width,double height,double cellLen){
        this.width=width;
        this.height=height;
        this.cellLen=cellLen;
        chess=new int[(int)height][(int)width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                chess[i][j]=' ';
    }



    public void play(int x,int y){

        //将当前的棋子放置到（x,y）
        if(chess[x][y]==' '){
            chess[x][y]=currentSide.getCode();
            changeSide();
        }

    }



    public void  changeSide(){

        //更换下棋方

        setCurrentSide(currentSide==Side.BLACK?Side.WHITE:Side.BLACK);

    }



    public boolean judgeGame(int row, int col, Side chessColor){

        //判断游戏是否结束
        if(rowJudge(row,col,chessColor)&&colJudge(row,col,chessColor)&&mainDiagonalJudge(row,col,chessColor)&&DeputyDiagonalJudge(row,col,chessColor))
            return true;
        return false;


    }

    /**
     * 横向判断五子连线
     * @param row
     * @param col
     * @param chessColor
     * @return
     */
    public boolean rowJudge(int row,int col,Side chessColor){

        int count = 0;
        for(int j = col;j<width;j++){
            if(chess[row][j]!=chessColor.getCode())
                break;
            count++;
        }
        for(int j=col-1;j>=0;j--){
            if(chess[row][j]!=chessColor.getCode())
                    break;
            count++;
        }
        if(count>=5)
                return false;
        return true;
    }

    /**
     * 纵向判断
     * @param row
     * @param col
     * @param chessColor
     * @return
     */
    public boolean colJudge(int row,int col,Side chessColor){

        int count=0;
        for(int i=row;i<height;i++){
            if(chess[i][col]!=chessColor.getCode())
                    break;
            count++;
        }
        for(int i=row-1;i>=0;i--){
            if(chess[i][col]!=chessColor.getCode())
                break;
            count++;
        }
        if(count>=5)
                return false;

        return true;
    }


    /**
     * 主对角线方向
     * @param row
     * @param col
     * @param chessColor
     * @return
     */
    public boolean mainDiagonalJudge(int row,int col,Side chessColor){

        int count=0;

        for(int i=row,j=col;i<height&&j<width;i++,j++){
            if(chess[i][j]!=chessColor.getCode())
                    break;
            count++;
        }


        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if(chess[i][j]!=chessColor.getCode())
                break;
            count++;
        }

        if(count>=5)
            return false;

        return true;
    }


    /**
     * 副对角线方向
     * @param row
     * @param col
     * @param chessColor
     * @return
     */
    public boolean DeputyDiagonalJudge(int row,int col,Side chessColor){

        int count=0;
        for(int i=row,j=col;i>=0&&j<width;i--,j++){
            if(chess[i][j]!=chessColor.getCode())
                break;

            count++;
        }


        for(int i=row+1,j=col-1;i<height&&j>=0;i++,j--){
            if(chess[i][j]!=chessColor.getCode())
                break;

            count++;
        }


        if(count>=5)
            return false;

        return true;
    }
}