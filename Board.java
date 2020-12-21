import java.util.*;
import javafx.util.*;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*; 
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Board {
        VBox vbox;
        GridPane gp;
        Label l;
        Rectangle[][] dot, boX, hRect, vRect;
        static boolean turn = true; // for Player A = ture; for Player B = false;
        private int m, n;
        static int A_Score, B_Score;

        final static boolean BLANK = false;
        final static int PLAYER_A = 1;
        final static int PLAYER_B = 2;
        
        static boolean[][] horizontalEdge;
        static boolean[][] verticalEdge;
        private int[][] box;

        
        Board(int m, int n){
            this.m = m;
            this.n = n;

        }
        
        private void initValueH(boolean[][] matrix, boolean val, int m, int n) {
            for(int i=0; i<m+1; i++)
                for(int j=0; j<n; j++)
                    matrix[i][j]= val;
        }
        
         private void initValueV(boolean[][] matrix, boolean val, int m, int n) {
            for(int i=0; i<m; i++)
                for(int j=0; j<n+1; j++)
                    matrix[i][j]= val;
        }
        
        
        public GridPane setBoard(int m, int n){
            addBoard(m, n);
            gp.setAlignment( Pos.CENTER );

            return gp;
        }
        
        public VBox getBoard(){
            return vbox;
        } 
        
        public void setPlayerAScore(){
            A_Score++;
        }
        public static int getPlayerAScore() {
            return A_Score;
        }
        public static int getTempWinner(){
            if (A_Score > B_Score)
                return PLAYER_A;
            else if (A_Score < B_Score)
                return PLAYER_B;
            else
                return 0;
        }
        public void setPlayerBScore(){
            B_Score++;
        }
        public static int getPlayerBScore() {
            return B_Score;
        }
        
        public int getScore(int avatar) {
            if(avatar == PLAYER_A) 
                return A_Score;
            else if (avatar == PLAYER_B) 
                return B_Score;
                
            return 0;
        }
    
        public boolean endgame() {
            if (A_Score + B_Score == (m) * (n))
                return true;
            else
                return false;
        }
    
        public int getWinner() {
            if(A_Score > B_Score) 
                return PLAYER_A;
            else if(A_Score < B_Score) 
                return PLAYER_B;
            else 
                return 0;
        }
        
        public void addBoard(int m, int n){
            this.m = m;
            this.n = n;
            
            horizontalEdge = new boolean[m+1][n];
            verticalEdge = new boolean[m][n+1];
            
            box = new int[m][n];
            
            initValueH(horizontalEdge, BLANK, m, n);
            initValueV(verticalEdge, BLANK, m, n);
            init();
            initGUI();
        }
       
        
        public void init(){
            vbox = new VBox();
            gp = new GridPane();
            l = new Label("Dot&Box by LZ");
            turn = true; // for Player A = ture; for Player B = false;
            A_Score = 0;
            B_Score = 0;
            VerticalBox.setAScore(0);
            VerticalBox.setBScore(0);
            VerticalBox.setPlayerDefault();
            GridPane.setColumnSpan(l, (2*n)+1); 
            GridPane.setRowSpan(l, (2*m)+1);
        }
        
        public void initGUI(){

            vbox.setAlignment( Pos.CENTER );
            vbox.getChildren().addAll(l, gp);

            Rectangle[][] dot = new Rectangle[m+1][n+1];
            for (int i = 0; i < m+1; i++){   
                for (int j = 0; j < n+1; j++){
                    dot[i][j] = new Rectangle(8,8);
                    dot[i][j].setFill(Color.GRAY);
                }
            }
            Rectangle[][] boX = new Rectangle[m][n];
            for (int i = 0; i < m; i++){   
                for (int j = 0; j < n; j++){
                    boX[i][j] = new Rectangle(40,40);
                    boX[i][j].setFill(Color.WHITE);
                    
                }
            }
            Rectangle[][] hRect = new Rectangle[m+1][n];
            for (int i = 0; i < m+1; i++){   
                for (int j = 0; j < n; j++){
                    hRect[i][j] = new Rectangle(40,8);
                    hRect[i][j].setId(i + "" + j);
                    hRect[i][j].setFill(Color.WHITE);
                    installEventHandler(hRect[i][j]);  
                 }
            }
            Rectangle[][] vRect = new Rectangle[m][n+1];
            for (int i = 0; i < m; i++){   
                for (int j = 0; j < n+1; j++){
                    vRect[i][j] = new Rectangle(8,40);
                    vRect[i][j].setId(i + "" + j);
                    vRect[i][j].setFill(Color.WHITE);
                    installEventHandler(vRect[i][j]);
                }
            }
            
            int k = 1,z=0;
            //adding the gui to the pane
            for (int row = 0; row < (2*m)+1; row++){
                if (row%2 == 0){
                    int coll;
                    int roww = row -z;
                    for (int col = coll = 0; col < n+1; col++){
                        gp.add(dot[roww][col], coll, row);
                        coll++;
                        if (col == n) continue;
                        gp.add(hRect[roww][col], coll, row);
                        coll++;
                    }
                    z++;
                }
                else
                {   int coll;
                    int roww = row - k;
                    for (int col = coll = 0; col < n+1; col++){
                        gp.add(vRect[roww][col], coll, row);
                        coll++;
                        if (col == n) continue;
                        gp.add(boX[roww][col], coll, row);
                        coll++;
                    }
                    k++;
                }
            }       

              
        }
        
        
        public void installEventHandler(final Rectangle mouseEnteredNode) {
                final EventHandler<MouseEvent> mouseEnteredEventHandler = new EventHandler<MouseEvent>() {
                public void handle(final MouseEvent mouseEvent) {
                    handleMouseEntered(mouseEvent, mouseEnteredNode);
                }
                };
                final EventHandler<MouseEvent> mouseClickedEventHandler = new EventHandler<MouseEvent>() {
                public void handle(final MouseEvent mouseEvent) {      
                    handleMouseClicked(mouseEvent, mouseEnteredNode);
                }
                };
                mouseEnteredNode.setOnMouseEntered(mouseEnteredEventHandler);
                mouseEnteredNode.setOnMouseExited(mouseEnteredEventHandler);
                mouseEnteredNode.setOnMouseClicked(mouseClickedEventHandler);
                
        }
        public void handleMouseEntered(MouseEvent mouseEvent, Rectangle mouseEnteredNode){
                //System.out.println("mouse click detected! " + mouseEvent.getSource());
                if (mouseEnteredNode.getWidth() == 40){    
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED &&
                    horizontalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                    [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")]==false){
                        if (turn) 
                            mouseEnteredNode.setFill(Color.BLUE);
                        else
                            mouseEnteredNode.setFill(Color.RED);
                    }
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED &&
                    horizontalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                    [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")]==false){
                            mouseEnteredNode.setFill(Color.WHITE);
                    }
                }
                else if (mouseEnteredNode.getWidth() == 8){    
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED &&
                    verticalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                    [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")]==false){
                        if (turn) 
                            mouseEnteredNode.setFill(Color.BLUE);
                        else
                            mouseEnteredNode.setFill(Color.RED);
                    }
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED &&
                    verticalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                    [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")]==false){
                            mouseEnteredNode.setFill(Color.WHITE);
                    }
                }
        }
        
        public void handleMouseClicked(MouseEvent mouseEvent, Rectangle mouseEnteredNode){
                int a, b;
                a = A_Score;
                b = B_Score;
                
                if (turn){
                    System.out.println(mouseEnteredNode.getId());
                    mouseEnteredNode.setFill(Color.BLUE);
                    if(mouseEnteredNode.getWidth() == 40)
                        horizontalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                        [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")] = true;
                    if(mouseEnteredNode.getWidth() == 8)
                        verticalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                        [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")] = true;
                    //mouseEnteredNode.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseClickedEventHandler);
                }
                else{
                    System.out.println(mouseEnteredNode.getId());
                    mouseEnteredNode.setFill(Color.RED);
                    if(mouseEnteredNode.getWidth() == 40)
                        horizontalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                        [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")] = true;
                    if(mouseEnteredNode.getWidth() == 8)
                        verticalEdge[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")]
                        [Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")] = true;
                       //mouseEnteredNode.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseClickedEventHandler);
                }  
                //if (box[Integer.parseInt(mouseEnteredNode.getId().charAt(0)+"")][Integer.parseInt(mouseEnteredNode.getId().charAt(1)+"")]!=0){
                    logic(mouseEnteredNode.getWidth(), mouseEnteredNode.getId(), turn);
                //}
                System.out.println("A score : " + A_Score);
                System.out.println("B score : " + B_Score);
                if (A_Score - a == 0 && B_Score - b == 0){
                    VerticalBox.setAScore(A_Score);   
                    VerticalBox.setBScore(B_Score);
                    if(turn == true){
                        VerticalBox.setTurn(false);
                    }
                    else
                        VerticalBox.setTurn(true);
                    
                    turn = !turn;
                    
                }
                else{
                    VerticalBox.setAScore(A_Score);   
                    VerticalBox.setBScore(B_Score);
                    //System.out.println("A score : " + A_Score);
                    //System.out.println("B score : " + B_Score);
                }
                if (endgame()){
                    if(A_Score > B_Score){
                        System.out.println("A wins !!!");
                        VerticalBox.setWinner(PLAYER_A);
                    }
                    else if (B_Score > A_Score){
                        System.out.println("B wins !!!");
                        VerticalBox.setWinner(PLAYER_B);
                    }
                    else{
                        System.out.println("Game Draw !");
                        VerticalBox.setWinner(0);
                    }
                    //init();
                    //initGUI();
                }
        }
        
        public void logic(double width, String str, boolean turn){
            int mm = Integer.parseInt(str.charAt(0)+"");
            int nn = Integer.parseInt(str.charAt(1)+"");
        
            if (width == 40){
                 if (mm == 0){
                     if ((horizontalEdge[mm+1][nn] && verticalEdge[mm][nn] && verticalEdge[mm][nn+1]) && box[mm][nn] == 0){
                         if (turn){
                            box[mm][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn].setFill(Color.RED);
                        }
                     }
                       
                 }
                 else if (mm == this.m){
                     if ((horizontalEdge[mm-1][nn] && verticalEdge[mm-1][nn] && verticalEdge[mm-1][nn+1]) && box[mm-1][nn] == 0){

                         if (turn){
                            box[mm-1][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm-1][nn].setFill(Color.BLUE);
                        }
                        else{
                            box[mm-1][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm-1][nn].setFill(Color.RED);
                        }
                     }
                 }
                 else {
                     if ((horizontalEdge[mm+1][nn] && verticalEdge[mm][nn] && verticalEdge[mm][nn+1]) && box[mm][nn] == 0){
                         if (turn){
                            box[mm][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn].setFill(Color.RED);
                        }
                     }
                     
                     if ((horizontalEdge[mm-1][nn] && verticalEdge[mm-1][nn] && verticalEdge[mm-1][nn+1]) && box[mm-1][nn] == 0){
                       if (turn){
                            box[mm-1][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm-1][nn].setFill(Color.BLUE);
                    
                        }
                        else{
                            box[mm-1][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm-1][nn].setFill(Color.RED);
                        }
                     }
                 }
                 //System.out.println("A score : " + A_Score);
                 //System.out.println("B score : " + B_Score);
                 
            }
            
            if (width == 8){
                if (nn == 0 ){
                     if ((horizontalEdge[mm][nn] && horizontalEdge[mm+1][nn] && verticalEdge[mm][nn+1]) && box[mm][nn] == 0){
                         if (turn){
                            box[mm][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn].setFill(Color.RED);
                        }
                     }
                       
                 }
                 else if (nn == this.n){
                     if ((horizontalEdge[mm][nn-1] && horizontalEdge[mm+1][nn-1] && verticalEdge[mm][nn-1]) && box[mm][nn-1] == 0){
                         if (turn){
                            box[mm][nn-1] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn-1].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn-1] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn-1].setFill(Color.RED);
                        }
                     }
                 }
                 else {
                     if ((horizontalEdge[mm][nn] && horizontalEdge[mm+1][nn] && verticalEdge[mm][nn+1]) && box[mm][nn] == 0){
                         if (turn){
                            box[mm][nn] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn].setFill(Color.RED);
                        }
                     }
                     
                     if ((horizontalEdge[mm][nn-1] && horizontalEdge[mm+1][nn-1] && verticalEdge[mm][nn-1]) && box[mm][nn-1] == 0){
                         if (turn){
                            box[mm][nn-1] = PLAYER_A;
                            A_Score++;
                            //boX[mm][nn-1].setFill(Color.BLUE);
                        }
                        else{
                            box[mm][nn-1] = PLAYER_B;
                            B_Score++;
                            //boX[mm][nn-1].setFill(Color.RED);
                        }
                     }
                 }
                 //System.out.println("A score : " + A_Score);
                    //System.out.println("B score : " + B_Score);
            }    
        }   
        

        public void updateUI(){

            Rectangle[][] dot = new Rectangle[m+1][n+1];
            for (int i = 0; i < m+1; i++){   
                for (int j = 0; j < n+1; j++){
                    dot[i][j] = new Rectangle(8,8);
                    dot[i][j].setFill(Color.GRAY);
                }
            }
            
            Rectangle[][] boX = new Rectangle[m][n];
            for (int i = 0; i < m; i++){   
                for (int j = 0; j < n; j++){
                    boX[i][j] = new Rectangle(40,40);
                    boX[i][j].setFill(Color.WHITE);

                }
            }

            Rectangle[][] hRect = new Rectangle[m+1][n];
            for (int i = 0; i < m+1; i++){   
                for (int j = 0; j < n; j++){
                    hRect[i][j] = new Rectangle(40,8);
                    //hRect[i][j].setFill(Color.WHITE);
                    installEventHandler(hRect[i][j]);
                    
                 }
            }
            
            Rectangle[][] vRect = new Rectangle[m][n+1];
            for (int i = 0; i < m; i++){   
                for (int j = 0; j < n+1; j++){
                    vRect[i][j] = new Rectangle(8,40);
                    //vRect[i][j].setFill(Color.WHITE);
                    installEventHandler(vRect[i][j]);
                }
            }
            
            int k = 1,z=0;
            
            //adding the gui to the pane
            for (int row = 0; row < (2*m)+1; row++){
                if (row%2 == 0){
                    int coll;
                    int roww = row -z;
                    for (int col = coll = 0; col < n+1; col++){
                        gp.add(dot[roww][col], coll, row);
                        coll++;
                        if (col == n) continue;
                        gp.add(hRect[roww][col], coll, row);
                        coll++;
                    }
                    z++;
                }
                else
                {   int coll;
                    int roww = row - k;
                    for (int col = coll = 0; col < n+1; col++){
                        gp.add(vRect[roww][col], coll, row);
                        coll++;
                        if (col == n) continue;
                        gp.add(boX[roww][col], coll, row);
                        coll++;
                    }
                    k++;
                }
            }
        }
    }