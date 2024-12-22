// import java.util.concurrent.TimeUnit;

// import javafx.application.Application;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Pane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.stage.Stage;

// public class App extends Application{
//     public int amount;
//     public int[] allNumbers;
//     public static void main(String[] args) throws Exception {
//         launch(args); // Starting the JavaFX engine.
        
//     }

//     @Override
//     public void start(Stage primaryStage) throws Exception {
//         //Variables

//         Pane root = new Pane();
//         primaryStage.setTitle("Sortings Algoritms.");
//         Scene scene = new Scene(root, 600, 400);
//         primaryStage.setResizable(false);
       

//         //Whole program in one VBox
//         VBox wholeProgram = new VBox();

//         //Top Navigation bar
//         HBox topNavHBox = new HBox();
        
//         Button start = new Button("Start Sorting");
//         Button bubblesort = new Button("Bubble Sort");
//         Button resetCanvas = new Button("Reset");
//         Button setAmountOfNumber = new Button("Set Amount:");
//         TextField amountOfNumbers = new TextField();
//         amountOfNumbers.setPromptText("Please write an Interger");

//         topNavHBox.getChildren().addAll(start, bubblesort, setAmountOfNumber, amountOfNumbers);
        
//         //Setting the amount of Number from the textField.
//         HBox recHBox = new HBox();
//         recHBox.setAlignment(Pos.BOTTOM_LEFT);
        
//         setAmountOfNumber.setOnAction(e -> {
//             amount = Integer.parseInt(amountOfNumbers.getText());
//             allNumbers =  NumberGenerator.generateNumbers(amount);

//             //For statement to print all numbers
//             // for (int i = 0; i < allNumbers.length; i++) {
//             //     System.out.println(allNumbers[i]);
//             // }
            
//             //Creating rectangle
//             int PostionX = 5;
//             for (int element : allNumbers) {
//                 Rectangle rectangle = new Rectangle(10, element);
//                 rectangle.setFill(Color.rgb(0, 255, 0));
//                 recHBox.getChildren().addAll(rectangle);
//             }
//             root.getChildren().add(recHBox);
//         });
        
        
//         //Setting the action for the starting button
//         start.setOnAction(e -> {

//                 for (int j = 0; j < allNumbers.length; j++) {
//                     for (int i = 0; i < allNumbers.length; i++) {
//                         recHBox.getChildren().clear();
//                         allNumbers = BubbleSort.oneIterationBubbleSort(allNumbers, i);
//                         for (int element : allNumbers) {
//                             Rectangle rectangle = new Rectangle(10, element);
//                             rectangle.setFill(Color.rgb(0, 255, 0));
//                             recHBox.getChildren().addAll(rectangle);
//                             wholeProgram.getChildren().remove(recHBox);
//                             wholeProgram.getChildren().add(recHBox);

//                             // Here is where we want to do the change update to the view.
                        
//                         }
//                         try {
//                             TimeUnit.SECONDS.sleep(1);                           
//                         } catch (Exception k) {
//                             System.out.println("we're fucked (Timeout doing sorting didn't work)");
//                             k.getMessage();
//                         }

//                     }
//                 }
            
//         });
        
//         recHBox.setSpacing(2);
//         wholeProgram.getChildren().addAll(topNavHBox, recHBox);
//         wholeProgram.setSpacing(50.5);
//         root.getChildren().add(wholeProgram);
         
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

    
// }
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        int[] data = {50, 30, 70, 10, 90, 40, 60, 20, 80};

        // Visual representation using rectangles
        HBox hBox = new HBox(5);
        Rectangle[] bars = new Rectangle[data.length];
        for (int i = 0; i < data.length; i++) {
            bars[i] = new Rectangle(30, data[i] * 3); // Height based on value
            bars[i].setFill(Color.BLUE);
            hBox.getChildren().add(bars[i]);
        }

        // Bubble sort in a Task
        Task<Void> bubbleSortTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                bubbleSort(data, bars);
                return null;
            }
        };

        new Thread(bubbleSortTask).start(); // Run sorting in a background thread

        primaryStage.setScene(new Scene(hBox, 400, 300));
        primaryStage.setTitle("Bubble Sort Visualization");
        primaryStage.show();
    }

    // Bubble sort algorithm with live updates
    private void bubbleSort(int[] data, Rectangle[] bars) throws InterruptedException {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    // Swap values
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;

                    // Update rectangles
                    int index = j;
                    Platform.runLater(() -> {
                        bars[index].setHeight(data[index] * 3);
                        bars[index + 1].setHeight(data[index + 1] * 3);
                        bars[index].setFill(Color.RED); // Highlight swapped bars
                        bars[index + 1].setFill(Color.RED);
                    });

                    Thread.sleep(300); // Pause to visualize the iteration

                    // Reset bar colors after swap
                    Platform.runLater(() -> {
                        bars[index].setFill(Color.BLUE);
                        bars[index + 1].setFill(Color.BLUE);
                    });
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
