import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
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

        primaryStage.setTitle("Sortings Algoritms.");
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Top Navigation bar
        HBox topNavHBox = new HBox();
        
        Button start = new Button("Start Sorting");
        Button bubblesort = new Button("Bubble Sort");
        Button resetCanvas = new Button("Reset");
        Button setAmountOfNumber = new Button("Set Amount:");
        TextField amountOfNumbers = new TextField();
        amountOfNumbers.setPromptText("Please write an Interger");

        topNavHBox.getChildren().addAll(start, bubblesort, setAmountOfNumber, amountOfNumbers);
        root.getChildren().add(topNavHBox);
        
        //Setting the amount of Number from the textField.
        HBox recHBox = new HBox();
        setAmountOfNumber.setOnAction(e -> {
            amount = Integer.parseInt(amountOfNumbers.getText());
            allNumbers =  NumberGenerator.generateNumbers(amount);

            for (int i = 0; i < allNumbers.length; i++) {
                System.out.println(allNumbers[i]);
            }
            //Creating rectangle
            int PostionX = 5;
            for (int element : allNumbers) {
                int PostionY = ((int)scene.getHeight())+element;
                Rectangle rectangle = new Rectangle(PostionX, PostionY, 5, element);
                rectangle.setFill(Color.rgb(0, 255, 0));
                PostionX += 20;
                recHBox.getChildren().addAll(rectangle);
            }
            root.getChildren().add(recHBox);
        });

        Rectangle rectangle = new Rectangle(5, ((int)scene.getHeight()-20));


        
        

    }
}
