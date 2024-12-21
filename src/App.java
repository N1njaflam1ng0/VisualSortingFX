import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application{
    public int amount;
    public int[] allNumbers;
    public static void main(String[] args) throws Exception {
        launch(args); // Starting the JavaFX engine.
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Variables

        Pane root = new Pane();
        primaryStage.setTitle("Sortings Algoritms.");
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setResizable(false);
       

        //Whole program in one VBox
        VBox wholeProgram = new VBox();

        //Top Navigation bar
        HBox topNavHBox = new HBox();
        
        Button start = new Button("Start Sorting");
        Button bubblesort = new Button("Bubble Sort");
        Button resetCanvas = new Button("Reset");
        Button setAmountOfNumber = new Button("Set Amount:");
        TextField amountOfNumbers = new TextField();
        amountOfNumbers.setPromptText("Please write an Interger");

        topNavHBox.getChildren().addAll(start, bubblesort, setAmountOfNumber, amountOfNumbers);
        
        //Setting the amount of Number from the textField.
        HBox recHBox = new HBox();
        recHBox.setAlignment(Pos.BOTTOM_LEFT);
        
        setAmountOfNumber.setOnAction(e -> {
            amount = Integer.parseInt(amountOfNumbers.getText());
            allNumbers =  NumberGenerator.generateNumbers(amount);

            //For statement to print all numbers
            // for (int i = 0; i < allNumbers.length; i++) {
            //     System.out.println(allNumbers[i]);
            // }
            
            //Creating rectangle
            int PostionX = 5;
            for (int element : allNumbers) {
                Rectangle rectangle = new Rectangle(10, element);
                rectangle.setFill(Color.rgb(0, 255, 0));
                recHBox.getChildren().addAll(rectangle);
            }
            root.getChildren().add(recHBox);
        });
        
        
        //Setting the action for the starting button
        start.setOnAction(e -> {

                for (int j = 0; j < allNumbers.length; j++) {
                    for (int i = 0; i < allNumbers.length; i++) {
                        recHBox.getChildren().clear();
                        allNumbers = BubbleSort.oneIterationBubbleSort(allNumbers, i);
                        for (int element : allNumbers) {
                            Rectangle rectangle = new Rectangle(10, element);
                            rectangle.setFill(Color.rgb(0, 255, 0));
                            recHBox.getChildren().addAll(rectangle);
                            wholeProgram.getChildren().remove(recHBox);
                            wholeProgram.getChildren().add(recHBox);

                            // Here is where we want to do the change update to the view.
                        
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);                           
                        } catch (Exception k) {
                            System.out.println("we're fucked (Timeout doing sorting didn't work)");
                            k.getMessage();
                        }

                    }
                }
            
        });
        
        recHBox.setSpacing(2);
        wholeProgram.getChildren().addAll(topNavHBox, recHBox);
        wholeProgram.setSpacing(50.5);
        root.getChildren().add(wholeProgram);
         
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
