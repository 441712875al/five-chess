package controller;

import entity.FiveChess;
import enums.Side;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import view.ChessPane;

import java.io.*;

/**
 * 时间处理类，可以使用lamda表达式替代
 */
public class PlayAction implements EventHandler<MouseEvent> {

    /*fiveChess表示五子棋游戏模型*/

    private FiveChess fiveChess;

    /*chessPane表示五子棋显示面板*/

    private ChessPane chessPane;



    public PlayAction(FiveChess fiveChess,ChessPane chessPane){
        this.chessPane=chessPane;

        this.fiveChess = fiveChess;

    }

    @Override
    public void handle(MouseEvent event){

        //获取棋盘的小方格的大小
        double cell = fiveChess.getCellLen();


        //获取鼠标点击坐标
        double x = event.getX();
        double y = event.getY();

        /*映射到数组中的坐标*/
        int i = (int)((x-100+cell/2)/cell);
        int j = (int)((y-100+cell/2)/cell);


        /*记录下落子位置*/
        try (FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/记录.txt"),true)) {
            out.write(new String(fiveChess.getCurrentSide().getDesc()+"("+j+","+i+")\n").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*改变棋盘状态*/
        fiveChess.play(i,j);

        /*重新渲染棋子*/
        chessPane.drawChess(cell);

        /*判断是否结束*/
        if(!fiveChess.judgeGame(i,j,fiveChess.getCurrentSide()== Side.BLACK?Side.WHITE:Side.BLACK)){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("五子棋游戏");
            alert.setHeaderText("提示信息");

            String msg = (fiveChess.getCurrentSide()==Side.BLACK?Side.WHITE:Side.BLACK).getDesc()+"取得胜利！";
            alert.setContentText(msg);

            try (FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/记录.txt"),true)) {
                out.write(msg.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            alert.showAndWait();
        }

    }

}