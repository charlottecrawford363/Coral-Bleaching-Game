import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.*; 
import java.io.*;
import java.util.*;
import java.net.URL; 
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

/**
 The CoralView class sets all the grid, button, label, image parts on the the start screen, game screen, and ending screen. 
 
 @author: Charlotte Crawford
 @date: 12/2/2024

 @see
 ocean background
https://static.vecteezy.com/system/resources/previews/000/273/915/non_2x/ocean-background-vector.jpg
 
factory
https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUwmUMbZZtk6DgF9njvIWdl50JMM7lq_oEaQ&s

you lose picture
https://thumbs.dreamstime.com/b/you-lose-red-rubber-stamp-over-white-background-86701681.jpg

coral reef
https://www.jacadatravel.com/wp-content/uploads/fly-images/451966/AdobeStock_444355340-coral-reef-1600x700-cc.jpg
 */

public class CoralView
{
  // instance variables
  private GridPane gridPane; //grid
  
  private Button startButton = new Button("Start"); //start button
  private Label introLabel = new Label("Welcome to the Coral Reef Simulator!\n\n\nYou are a wealthy citizen living \non the coast side, in a beach house that has a \nclear view of the atlantic and it's beautiful ecosystem. \n\nYou own a large but local organization \nthat manages fishing and other activities. \nHowever, with the power of wealth, you have the responsibility \nof making crucial decisions everyday. \n\nIs it possible to minimize the amount of profit \nyou have sustainably? How do your choices affect the\nhealth of coral reefs? \n\nPress the start button to begin!"); //intro message
  
  private Button button1 = new Button("Option 1"); //option button
  private Button button2 = new Button("Option 2"); //option button
  private Label questionLabel = new Label("Question"); //question
  private Label moneyLabel = new Label("Money: $"); //money status
  private Label healthLabel = new Label("Coral Health: "); //health status
  private Label temperatureLabel = new Label("Temperature: "); // temp status
  private Label dayLabel = new Label("Day: "); // day status

  //images
  private Image factoryImage;
  private Image coralImage;
  private Image openingCoral;
  private Image youLose;
  //controls color of coral
  Rectangle rect = new Rectangle(5, 5, 200, 140); 

  //end screen
  private Label endingLabel = new Label("Game Over!");
  private Button endButton = new Button("Start Over");
  
  public void start(Stage primaryStage)
  {  
    // creating grid and setting the grid alignment
    gridPane = new GridPane(); 

    //Setting size for the pane  
    gridPane.setMinSize(600, 500); 

    //Setting the padding  
    gridPane.setPadding(new Insets(10, 10, 10, 10)); 

    //Setting the vertical and horizontal gaps between the columns 
    gridPane.setVgap(5); 
    gridPane.setHgap(5); 

    gridPane.setAlignment(Pos.TOP_LEFT);

    // add buttons to gridpane
    gridPane.add(startButton, 20, 6);
    startButton.setWrapText(true);

    // add introduction/instructions
    gridPane.add(introLabel, 20, 5);
    introLabel.setWrapText(true);

    // catching exceptions if the image isn't available
    try
    {
      // assigning Image openingCoral to the image at the corresponding link
      openingCoral = new Image(new URL("https://www.jacadatravel.com/wp-content/uploads/fly-images/451966/AdobeStock_444355340-coral-reef-1600x700-cc.jpg").openStream());
    }
    catch (IOException e)
    {
      // sets variable to null if the image is not available
      openingCoral = null;
    }

    // adding image to imageview
    ImageView imageView2 = new ImageView(openingCoral);

    // setting the fit height and width of the image view 
    imageView2.setFitHeight(220); 
    imageView2.setFitWidth(300); 

    // setting the preserve ratio of the image view 
    imageView2.setPreserveRatio(true); 

    // adding imageview to gridpane
    gridPane.add(imageView2, 20, 10);
    
    Scene scene = new Scene(gridPane); //create scene from gridpane
    primaryStage.setTitle("Coral Bleaching Game"); //set stage title
    primaryStage.setScene(scene); //place stage on scene
    primaryStage.show(); //display stage       
  }

  public void startGame()
  {
    gridPane.getChildren().clear(); // clears the grid pane
    
    // add buttons and labels to grid pane
    gridPane.add(button1, 0, 3);
    button1.setWrapText(true);
    button1.setMinSize(200.0, 65.0);
    
    gridPane.add(button2, 0, 6);
    button2.setWrapText(true);
    button2.setMinSize(200.0, 65.0);

    gridPane.add(questionLabel, 0, 0);

    questionLabel.setMinSize(400.0, 75.0);
    questionLabel.setWrapText(true);
    gridPane.add(moneyLabel, 2, 7);
    gridPane.add(healthLabel, 2, 8);
    gridPane.add(temperatureLabel, 2, 9);
    gridPane.add(dayLabel, 2, 10);

    // calls the makeImage function to add images to the gridpane
    makeImage();
  }

  public void makeImage() 
  {
    // catching exceptions if the image isn't available
    try
    {
      // assigning Image openingCoral to the image at the corresponding link
      factoryImage = new Image(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUwmUMbZZtk6DgF9njvIWdl50JMM7lq_oEaQ&s").openStream());
    }
    catch (IOException e)
    {
      // sets variable to null if the image is not available
      factoryImage = null;
    }
    
    //adding image
    ImageView imageView = new ImageView(factoryImage);

    //setting the fit height and width of the image view 
    imageView.setFitHeight(135); 
    imageView.setFitWidth(150); 

    //Setting the preserve ratio of the image view 
    imageView.setPreserveRatio(true);  

    // adding imageview to gridpane
    gridPane.add(imageView, 0, 11);

    // catching exceptions if the image isn't available
    try
      {
        // assigning Image openingCoral to the image at the corresponding link
        coralImage = new Image(new FileInputStream("image.png"));
      }
      catch (IOException e)
      {
        // sets variable to null if the image is not available
        coralImage = null;
      }

    //adding image
    ImageView imageView1 = new ImageView(coralImage);

    //setting the fit height and width of the image view 
    imageView1.setFitHeight(180); 
    imageView1.setFitWidth(200); 

    //Setting the preserve ratio of the image view 
    imageView1.setPreserveRatio(true);  

    // fills the rectangle with the given color (red)
    rect.setFill(Color.rgb(200, 0, 0));

    //adding the rectangle and imageview1 to the gridpane
    gridPane.add(rect, 2, 11);    
    gridPane.add(imageView1, 2, 11);

  }
  
  // getter for the rectangle color
  public Rectangle getCoralColor()
  {
    return rect;
  }
  
  // getters for buttons and labels
  public Button getStartButton()
  {
    return startButton;
  }
  
  public Button getButton1()
  {
    return button1;
  }

  public Button getButton2()
  {
    return button2;
  }

  public Label getQuestionLabel()
  {
    return questionLabel;
  }

  public Label getMoneyLabel()
  {
    return moneyLabel;
  }

  public Label getHealthLabel()
  {
    return healthLabel;
  }

  public Label getTemperatureLabel()
  {
    return temperatureLabel;
  }

  public Label getDayLabel()
  {
    return dayLabel;
  }

  
  public Image getFactoryImage()
  {
    return factoryImage;
  }

  public Label getEndingLabel()
  {
    return endingLabel;
  }

  public Button getEndButton()
    {
      return endButton;
    }
  

  // sets up the user display once the game ends
  public void endGame()
    {
      gridPane.getChildren().clear(); // clears the grid pane
      
      // add ending button and label to grid pane
      gridPane.add(endingLabel, 0, 2);
      endingLabel.setWrapText(true);
      
      gridPane.add(endButton, 0, 3);

      // catching exceptions if the image isn't available
      try
      {
        youLose = new Image(new URL("https://thumbs.dreamstime.com/b/you-lose-red-rubber-stamp-over-white-background-86701681.jpg").openStream());
      }
      catch (IOException e)
      {
        youLose = null;
      }

      //adding image
      ImageView imageView3 = new ImageView(youLose);

      //setting the fit height and width of the image view 
      imageView3.setFitHeight(180); 
      imageView3.setFitWidth(200); 

      //Setting the preserve ratio of the image view 
      imageView3.setPreserveRatio(true);  

      // adding imageview to gridpane
      gridPane.add(imageView3, 0, 5);
    }  
}