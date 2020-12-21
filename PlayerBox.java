import java.util.*;
import javafx.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

public class PlayerBox{
    private final ChoiceBox<Pair<String, String>> assetClass = new ChoiceBox<>();
    private final static Pair<String, String> EMPTY_PAIR = new Pair<>("", "");
    
    private Label label;
    private HBox playerBox;
    
    PlayerBox(Label label){
        this.label = label;
        this.playerBox = new HBox(label, assetClass);
        
        assetClass.setPrefWidth(100);
        playerBox.setSpacing( 10.0d );
        playerBox.setAlignment(Pos.CENTER );
        playerBox.setPadding( new Insets(10) );
        //playerBox.setPrefSize(400, 10);
    }
    
    public Pair<String, String> getValue(){
        return assetClass.getValue();
    }
   
    protected void initChoice() {
        List<Pair<String,String>> assetClasses = new ArrayList<>();
        assetClasses.add( new Pair("Human", "A"));
        assetClasses.add( new Pair("AI", "B"));

        assetClass.setConverter( new StringConverter<Pair<String,String>>() {
            @Override
            public String toString(Pair<String, String> pair) {
                return pair.getKey();
            }
            @Override
            public Pair<String, String> fromString(String string) {
                return null;
            }
        });
        assetClass.getItems().add( EMPTY_PAIR );
        assetClass.getItems().addAll( assetClasses );
        assetClass.setValue( EMPTY_PAIR );
    }
    
    public HBox getPlayerBox(){
        return playerBox;
    }
    
     public Label getPlayerLabel(){
        return this.label;
    }
}