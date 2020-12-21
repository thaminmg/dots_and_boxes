import java.util.*;
import javafx.application.Application;
import javafx.util.*;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
    HorizontalBox hbox;
    VerticalBox vbox;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        Pair<String, String> pairM, pairN;
        int m, n ;
        BorderPane border = new BorderPane();

        HorizontalBox hbox = new HorizontalBox();
        border.setTop(hbox.getHBox());
        
        VerticalBox vbox = new VerticalBox();
        border.setRight(vbox.getVBox());
        
        Board board = new Board(0,0);
        border.setCenter(board.getBoard());
       
        Separator sep = new Separator();
        border.setLeft(sep);
        
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dot and Box Game");
        stage.show();

        Button start = hbox.getStartBtn();
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                    border.setCenter(board.setBoard(Integer.parseInt(hbox.getM().toString().charAt(0)+""),  Integer.parseInt(hbox.getN().toString().charAt(0)+""))
                    );
                    VerticalBox.setTurn(true);
                    VerticalBox.setWinnerDefault();
                }
        });
        
        Button reset = hbox.getResetBtn();
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                    VerticalBox.setWinnerDefault();
                    border.setCenter(board.setBoard(0,0));
                    board.init();
                    VerticalBox.init();

            }
        });
        
        Button exit = hbox.getExitBtn();
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                    VerticalBox.setWinner(board.getTempWinner());
            }
        });
    }
}