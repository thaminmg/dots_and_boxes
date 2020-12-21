import java.util.*;
import javafx.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*; 
import javafx.event.*; 

public class CellBox{
    private final ChoiceBox<Pair<String, String>> assetClass = new ChoiceBox<>();
    private final static Pair<String, String> EMPTY_PAIR = new Pair<>("", "");

    private Label label;
    private HBox cellBox;

    CellBox(Label label){
        this.label = label;
        this.cellBox = new HBox(label, assetClass);
       
        assetClass.setPrefWidth(50);
        cellBox.setSpacing( 10.0d );
        cellBox.setAlignment(Pos.CENTER );
        cellBox.setPadding( new Insets(10) );
        //cellBox.setPrefSize(50, 10);
    }
    
    public Pair<String, String> getValue(){
        return assetClass.getValue();
    }

    protected void initChoice() {
        List<Pair<String,String>> assetClasses = new ArrayList<>();
        assetClasses.add( new Pair("2", "2"));
        assetClasses.add( new Pair("3", "3"));
        assetClasses.add( new Pair("4", "4"));
        assetClasses.add( new Pair("5", "5"));
        assetClasses.add( new Pair("6", "6"));
        assetClasses.add( new Pair("7", "7"));
        assetClasses.add( new Pair("8", "8"));
        assetClasses.add( new Pair("9", "9"));
        // assetClasses.add( new Pair("10", "10"));
        
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
    
    public HBox getCellBox(){
        return cellBox;
    }
}