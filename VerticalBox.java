import java.util.*;
import javafx.util.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*; 
import javafx.scene.text.*;
import javafx.scene.paint.*;

public class VerticalBox {
        static HBox navigationBox, player, playerScore;
        static VBox vbox, vbox1, vbox2, vbox3;
        static Text title, turn ;
        static Label playerTurn, playerAB, aScore , bScore, winner;
        //String name = "Player A";
        VerticalBox(){
            init();
            initGUI();
        }
        
        public static void init(){

            vbox = new VBox();
            vbox1 = new VBox();
            vbox2 = new VBox();
            vbox3 = new VBox();
            
            title = new Text("Navigation Panel: ");

            navigationBox = new HBox();
            
            playerTurn = new Label("        Player ----- ");

            turn = new Text("  turn !");
            
            player = new HBox();

            playerAB = new Label("   Player A               Player B");
            
            aScore = new Label("0");
            bScore = new Label("0");
            winner = new Label("");
            playerScore = new HBox();
        }
        
        public void initGUI(){
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(30);
            vbox.setPrefSize(300, 500);
            vbox.setStyle("-fx-background-color: #E6E6FA;");//Color.LAVENDER
            title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
            
            //navigationBox.setPadding(new Insets(15, 12, 15, 12));
            navigationBox.setPadding(new Insets(30));
            navigationBox.setStyle("-fx-background-color: #f0f0f0;");
            navigationBox.setPrefSize(200, 50);
            
            playerTurn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            //playerTurn.setPadding(new Insets(15, 12, 15, 12));
            //playerTurn.setStyle("-fx-background-color: #f0f0f0;");
            
            turn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            
            navigationBox.getChildren().addAll(playerTurn, turn);
            

            player.setPadding(new Insets(15, 12, 15, 12));
            player.setSpacing(10);
            player.setStyle("-fx-background-color: #E6E6FA;");
            player.setPrefSize(200, 50);
            
            playerAB.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            player.getChildren().addAll(playerAB);

            playerScore.setPadding(new Insets(15, 12, 15, 12));
            playerScore.setSpacing(10);
            playerScore.setStyle("-fx-background-color: #E6E6FA;");
            playerScore.setPrefSize(200, 50);
            
            aScore.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            aScore.setTextFill(Color.ROYALBLUE);
            
            bScore.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            bScore.setTextFill(Color.CRIMSON);
            
            vbox1.setPadding(new Insets(-50, 30, 30, 30));
            vbox1.setSpacing(8);
            vbox1.setPrefSize(30, 30);
            vbox1.setStyle("-fx-background-color: #E6E6FA;");
            vbox1.getChildren().addAll(aScore);
            
            vbox2.setPadding(new Insets(-50, 30, 30, 30));
            vbox2.setSpacing(8);
            vbox2.setPrefSize(30, 30);
            vbox2.setStyle("-fx-background-color: #E6E6FA;");
            vbox2.getChildren().addAll(new Text("       "));
            
            vbox3.setPadding(new Insets(-50, 30, 30, 30));
            vbox3.setSpacing(8);
            vbox3.setPrefSize(30, 30);
            vbox3.setStyle("-fx-background-color: #E6E6FA;");
            vbox3.getChildren().addAll(bScore);
            
            playerScore.getChildren().addAll(vbox1, vbox2, vbox3);
            winner.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            winner.setPadding(new Insets(0, 0, 0, 50));
            vbox.getChildren().addAll(title, navigationBox, player, playerScore, winner);
        }
        
        public static void setPlayerDefault(){
            playerTurn.setText("        Player ----- ");
            playerTurn.setTextFill(Color.BLACK);
        }
        
        public static void setWinnerDefault(){
            winner.setText("");
            winner.setTextFill(Color.BLACK);
        }
        
        public static void setTurn(boolean turn){
            if (turn){
                playerTurn.setText("           Player A");
                playerTurn.setTextFill(Color.BLUE);
            }
            else{
                playerTurn.setText("           Player B");
                playerTurn.setTextFill(Color.CRIMSON);
            }
        }
        
        public static void setAScore(int a){
            aScore.setText(a + " ");
        }
        
        public static void setBScore(int b){
            bScore.setText(b + " ");
        }
        
        public VBox getVBox(){
            return vbox;
        }      

        public static void setWinner(int win){
            if (win == 1){
                winner.setText("Player A wins !!!");
                winner.setTextFill(Color.ROYALBLUE);
            }
            else if (win == 2){
                winner.setText("Player B wins !!!");
                winner.setTextFill(Color.CRIMSON);
            }
            else{
                winner.setText("   Draw   !");
                winner.setTextFill(Color.BLACK);
            }
        }
   }