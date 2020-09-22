// I worked on the homework assignment alone, using only course materials.

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.shape.Line;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;
//import java.util.ArrayList;
import javafx.scene.Node;

/**
 * This class develops a JavaFX application that serves as a review page for my CS 1331 Restaurant.
 * @author Athrey Gonella
 * @version 1.0
 */
public class CafeReviewPage extends Application {
    private VBox reviewSection;
    private TextField nameInput;
    private TextField feedbackInput;
    private TextField colorInput;
    private Slider slider;

    private Image starSymbol = new Image("star.png");

    //private ArrayList<Text> unsortedTexts = new ArrayList<>();
    //private ArrayList<Integer> unsortedStars = new ArrayList<>();
    //private ArrayList<Text> sortedTexts = new ArrayList<>();
    //private ArrayList<Integer> sortedStars = new ArrayList<>();

    int oneStarCount = 0;
    int twoStarCount = 0;
    int threeStarCount = 0;
    int fourStarCount = 0;
    int fiveStarCount = 0;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        // Creates VBox for review section
        reviewSection = new VBox(10);  // sets spacing to 10
        reviewSection.setPrefHeight(450);
        reviewSection.setPrefWidth(489);
        reviewSection.setPadding(new Insets(5, 10, 20, 12)); // Sets offset between border and text within
        reviewSection.setBorder(new Border(
                new BorderStroke(Color.GAINSBORO, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT,
                        new Insets(15, 0, 15, 20))));
        Label review_header = new Label("Reviews");
        review_header.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        reviewSection.getChildren().add(review_header);
        reviewSection.setAlignment(Pos.TOP_CENTER);


        // Sets up scrollbar
        ScrollPane sp = new ScrollPane(reviewSection);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.fitToWidthProperty().set(true);
        sp.fitToHeightProperty().set(true);
        sp.setPadding(new Insets(2));


        // Creates HBox for input section
        HBox inputs = new HBox(5);
        inputs.setPadding(new Insets(15, 7, 5, 7));
        inputs.setAlignment(Pos.BOTTOM_CENTER);
        inputs.setSpacing(10);

        nameInput = new TextField();
        nameInput.setPromptText("Name");
        feedbackInput = new TextField();
        feedbackInput.setPromptText("Feedback");
        colorInput = new TextField();
        colorInput.setPromptText(("Color"));

        Button postButton = new Button("Post!");
        postButton.setOnAction(new PostHandler());

        // Creates slider for number of stars
        slider = new Slider();
        slider.setMin(0);
        slider.setMax(5);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);

        Label sliderLabel = new Label("Number of Stars: ");
        sliderLabel.setFont(Font.font("Garamond", FontWeight.SEMI_BOLD, 14));
        sliderLabel.setTextFill(Color.GOLDENROD);


        inputs.getChildren().add(nameInput);
        inputs.getChildren().add(feedbackInput);
        inputs.getChildren().add(colorInput);
        inputs.getChildren().add(sliderLabel);
        inputs.getChildren().add(slider);
        inputs.getChildren().add(postButton);


        // Creates title (in its own Pane)
        HBox titlePane = new HBox();
        Text title = new Text("Thanks for eating at the Cafe1331! Drop us a review and we hope to see you again!");
        title.setFont(Font.font("Times New Roman", 20));
        title.setFill(Color.FIREBRICK);

        titlePane.getChildren().add(title);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(10, 20, 12, 25));



        /*Button sortButton = new Button("Sort reviews by stars");
        sortButton.setOnAction(new sortHandler());
        rightPane.getChildren().add(sortButton);
        rightPane.setAlignment(Pos.CENTER_LEFT);*/

        // Creates another pane for counting stars
        /*VBox rightPane = new VBox();
        Label[] starCounts = new Label[5];
        for (int s = 1; s <= 5; s++) {
            starCounts[s] = new Label("Number of " + s + "-star reviews: ");
        }

        rightPane.getChildren().add(starCounts[1]);*/




        // Places nodes into BorderPane
        pane.setLeft(sp); // ScrollPane to the left
        pane.setCenter(reviewSection); // Review Section in the center
        pane.setBottom(inputs); // Inputs in the bottom
        pane.setTop(titlePane); // Title at the top
        //pane.setRight(rightPane); // Rightpane on the right
        pane.setPrefWidth(inputs.getPrefWidth());





        Scene scene = new Scene(pane);
        primaryStage.setTitle("Cafe1331 Reviews");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Posts a review upon the "Post!" button's activation.
     * @param name      TextField object that represents the reviewer's name
     * @param feedback  TextField object that represents the reviewer's feedback
     * @param color     TextField object that represents the reviewer's selected color
     */
    public void postReview(TextField name, TextField feedback, TextField color) {

        String feedbackGiven = feedback.getText();
        String nameGiven = name.getText();
        String colorString = color.getText();

        if (!feedbackGiven.equals("")) {
            Text out;
            if (!nameGiven.equals("")) {
                out = new Text(nameGiven + ":\n\t" + feedbackGiven);
            } else {
                out = new Text("Anonymous:\n\t" + feedbackGiven);
            }
            out.setFont(Font.font("Verdana"));

            Color col;
            try {
                col = Color.web(colorString);
            } catch (Exception e) {
                col = Color.BLACK;
            }

            out.setFill(col);

            // Enters rating (number of stars) prior to the TextFlow
            HBox stars = new HBox();
            int rating = (int) slider.getValue();


            for (int i = 0; i < rating; i++) {
                ImageView iv = new ImageView(starSymbol);
                iv.setFitWidth(15);
                iv.setFitHeight(15);
                stars.getChildren().add(iv);
            }

            reviewSection.getChildren().add(stars);
            reviewSection.getChildren().add(new TextFlow(out)); // Enables text to go to next line after passing border

            Line separationLine = new Line();
            separationLine.setStroke(Color.GAINSBORO);
            separationLine.setStartX(reviewSection.getLayoutX());
            separationLine.setEndX(separationLine.getStartX() + reviewSection.getPrefWidth());
            reviewSection.getChildren().add(separationLine);

            /*switch (rating) {
                case 1:
                    oneStarCount++;
                    break;
                case 2:
                    twoStarCount++;
                    break;
                case 3:
                    threeStarCount++;
                    break;
                case 4:
                    fourStarCount++;
                    break;
                case 5:
                    fiveStarCount++;
                    break;
            }*/


            //unsortedStars.add(rating);
            //unsortedTexts.add(out);



        }

    }


    /**
     * Defines a helper class that manages the handler object for the "Post" button.
     */
    class PostHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            postReview(nameInput, feedbackInput, colorInput);
            if (!feedbackInput.getText().equals("")) {
                nameInput.setText("");
                feedbackInput.setText("");
                colorInput.setText("");
                slider.setValue(0);
            }

        }
    }

    /*class sortHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent e) {
            sortedStars = unsortedStars;
            sortedTexts = unsortedTexts;

            sortByStars();

            for (int x = reviewSection.getChildren().size() - 1; x >= 0; x--) {
                reviewSection.getChildren().remove(x);
            }



            for (int i = 0; i < sortedStars.size() - 1; i++) {

                HBox stars = new HBox();
                for (int j = 0; j < sortedStars.get(j); j++) {
                    ImageView iv = new ImageView(starSymbol);
                    iv.setFitWidth(15);
                    iv.setFitHeight(15);
                    stars.getChildren().add(iv);
                }
                reviewSection.getChildren().add(stars);

                reviewSection.getChildren().add(new TextFlow(sortedTexts.get(i)));
                separationLine = new Line();
                separationLine.setStroke(Color.GAINSBORO);
                separationLine.setStartX(reviewSection.getLayoutX());
                separationLine.setEndX(separationLine.getStartX() + reviewSection.getPrefWidth());
                reviewSection.getChildren().add(separationLine);
            }

        }
    }

    public void sortByStars() {
        // Runs selection sort algorithm
        for (int outer = 0; outer < sortedStars.size(); outer++) {
            int min = outer;
            for (int inner = outer + 1; inner < sortedStars.size(); inner++) {
                if (sortedStars.get(min) > sortedStars.get(inner)) {
                    min = inner;
                }
            }
            int temp = sortedStars.get(outer);
            Text temp2 = sortedTexts.get(outer);

            sortedStars.set(outer, sortedStars.get(min));
            sortedTexts.set(outer, sortedTexts.get(min));

            sortedStars.set(min, temp);
            sortedTexts.set(min, temp2);
        }
    }*/

    /**
     * Main method required for the program to compile and run (not really linked to the application itself).
     * @param args  String array used for command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /*for (String x : Font.getFontNames()) {
            System.out.println(x);
        }*/

    }

}

