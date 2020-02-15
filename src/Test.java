import controller.PlayAction;
import entity.FiveChess;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.ChessPane;

import javax.print.attribute.standard.Fidelity;
import java.io.*;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        FiveChess fiveChess = new FiveChess(20,20,28.0);
        ChessPane chessPane=new ChessPane(fiveChess);

        /*记录下棋盘信息*/
        try(FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/记录.txt"),true)){
            out.write(new String("棋局开始，黑子先行\n").getBytes());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*注册时间处理器*/
        chessPane.setOnMouseClicked(new PlayAction(fiveChess,chessPane));

        /*显示舞台*/
        Scene scene=new Scene(chessPane,800,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("五子棋游戏");
        primaryStage.show();

    }
}
