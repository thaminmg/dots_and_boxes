import java.util.*;
import javafx.util.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*; 
import javafx.scene.text.Text;

public class HorizontalBox {
        HBox hbox;
        VBox vbox1, vbox2, vbox3;
        PlayerBox playerBoxA, playerBoxB;
        CellBox cellBoxH, cellBoxV;
        Button startBtn, exitBtn, resetBtn;
        ComboBox cbM, cbN;
        String mm, nn;
        String numbers[] = { "2", "3", "4", "5", "6", "7", "8", "9"}; 
        HorizontalBox(){
            init();
            initGUI();
        }
        
        public void init(){
            hbox = new HBox();
            vbox1 = new VBox();
            vbox2 = new VBox();
            vbox3 = new VBox();
            
            playerBoxA = new PlayerBox(new Label("Player A:"));  
            playerBoxB = new PlayerBox(new Label("Player B:"));        
        
            cellBoxH = new CellBox(new Label("Horizontal cells:"));
            cellBoxV = new CellBox(new Label("Verticle cells    :"));
            
            startBtn = new Button("Start");
            exitBtn = new Button("Exit");
            resetBtn = new Button("Reset");
              
            playerBoxA.initChoice();
            playerBoxB.initChoice();
            cellBoxH.initChoice();
            cellBoxV.initChoice();
        }
        
        public void initGUI(){
            hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
            hbox.setStyle("-fx-background-color: #cdcdcd;");
            hbox.setPrefSize(800, 100);
        
            vbox1.setPadding(new Insets(10));
            vbox1.setSpacing(8);
            vbox1.setPrefSize(300, 20);
            vbox1.getChildren().addAll(playerBoxA.getPlayerBox(), playerBoxB.getPlayerBox());
    
            vbox2.setPadding(new Insets(10));
            vbox2.setSpacing(8);
            vbox2.setPrefSize(300, 20);
            vbox2.getChildren().addAll(cellBoxH.getCellBox(), cellBoxV.getCellBox());
            //vbox2.getChildren().addAll(cbM, cbN);
            
            startBtn.setPrefSize(300, 20);
            exitBtn.setPrefSize(300, 20);
            resetBtn.setPrefSize(300, 20);
            
            vbox3.setPadding(new Insets(10));
            vbox3.setSpacing(8);
            vbox3.setPrefSize(300, 20);
            vbox3.getChildren().addAll(startBtn, exitBtn, resetBtn);
        
            hbox.getChildren().addAll(vbox1, vbox2, vbox3);
 
        }
        
        public HBox getHBox(){
            return hbox;
        }
      
        public Pair<String, String> getM(){      
            return cellBoxH.getValue();
        }
        
        public Pair<String, String> getN(){
            return cellBoxV.getValue();
        }
        
        public Button getStartBtn(){
            return startBtn;
        }
        
        public Button getExitBtn(){
            return exitBtn;
        }
        
        public Button getResetBtn(){
            return resetBtn;
        }
        
        public void startBtnOnPressed(){
         startBtn.setOnAction(
            (evt) -> 
            System.out.println("Start Btn pressed: " + cellBoxH.getValue())
            );
        }

        public void exitBtnOnPressed(){
         exitBtn.setOnAction(
            (evt) -> System.out.println("Exit Btn pressed: ")
            );
        }
        
        public void resetBtnOnPressed(){
          resetBtn.setOnAction(
            (evt) -> System.out.println("Reset Btn pressed: " + playerBoxA.getValue())
            );
        }
    }