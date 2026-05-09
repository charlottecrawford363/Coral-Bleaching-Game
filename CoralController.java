import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
import java.net.URL; 
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

/**
 The CoralController class determines how the screen is updated based on what buttons are pressed. It handles the progression of the game utilizing the view and model classes.

 @author: Charlotte Crawford
 @date: 12/2/2024

@input: model, view
@output: button actions
 */

public class CoralController
{
  private CoralModel model;
  private CoralView view;
  private int decisionNum;

  public CoralController(CoralModel model, CoralView view)
  {
    this.model = model;
    this.view = view;

    // starts buttons when pressed
    view.getStartButton().setOnAction(e -> handleStartButton());
    view.getButton1().setOnAction(e -> handleButton1());
    view.getButton2().setOnAction(e -> handleButton2());
    view.getEndButton().setOnAction(e -> handleEndButton());
  }

  public void handleStartButton()
  {
    //resets all values 
    model.initializeValues();
    // call random scenario, and set labels to values of scenario
    model.randomScenario();
    view.getQuestionLabel().setText(model.getCurrentScenario()[0]);
    
    view.getMoneyLabel().setText("Money: $" + model.getMoney());
    view.getHealthLabel().setText("Coral Health: " + model.getCoralHealth());
    view.getTemperatureLabel().setText("Temperature: " + model.getTemperature() + "°F");
    view.getDayLabel().setText("Day: " + model.getDay());


    // display options on buttons in random order
    String[] displayOrder = model.optionDisplayOrder();
    view.getButton1().setText(displayOrder[0]);
    view.getButton2().setText(displayOrder[1]);

    //start game
    view.startGame();

    // set coral color to value of coral health
    int col = model.getColorBG();
    view.getCoralColor().setFill(Color.rgb(255, col, col)); //changes the blue and green values of the coral --> the coral will go between red to white
  }

  public void handleButton1()
  {
    // set decision to 0
    decisionNum = 0;

    updateButtons();
  }

  public void handleButton2()
  {
    // set decision to 1
    decisionNum = 1;

    updateButtons();
  }

  public void updateButtons()
  {
    // checks if game is not over
    if (model.getGameOver() == false)
      {
        // set money and temperature changing values according to decision chosen
        model.playScenario(decisionNum);

        //update label statuses
        view.getHealthLabel().setText("Coral Health: " + model.getCoralHealth());
        view.getMoneyLabel().setText("Money: $" + model.getMoney());
        view.getTemperatureLabel().setText("Temperature: " + model.getTemperature() + "°F");
        view.getDayLabel().setText("Day: " + model.getDay());
        
        // update coral color
        int col = model.getColorBG();
        if (model.getTemperature() < 84)
        {
          view.getCoralColor().setFill(Color.rgb(255, col, col));
        }
        else
        {
          if (col + 20 <= 255)
          {
            view.getCoralColor().setFill(Color.rgb(col+20, col, col-10));
          }
          else
          {
            view.getCoralColor().setFill(Color.rgb(255, col, col-10));
          }
        }

        // call for the next scenario and change buttons to scenario options in random order, and question label to scenario
        model.randomScenario();
        String[] displayOrder = model.optionDisplayOrder();

        view.getButton1().setText(displayOrder[0]);
        view.getButton2().setText(displayOrder[1]);

        view.getQuestionLabel().setText(model.getCurrentScenario()[0]);

      }
      
    //checks if game is over
    else
    {
      // change grid to game over screen and update label
      view.endGame();
      view.getEndingLabel().setText("Game over! \n\nYou made it to \n - Day " + model.getDay() + " \n - $" + model.getMoney() + "\n - A " + model.getCoralHealth() + " coral \n - A final temperature of " + model.getTemperature() + "°F.\n\nWere you able to come up with a strategy to balance how much money you made and the health of the corals? Hard, isn't it? This is similar to the choices that real life business owners and environmentalist need to make everyday! Cleaning up the ocean and helping corals isn't a cheap process, and as you saw in this game, it's hard to give up money to make help the environment. But, in the long-run, it will save us something worth more than a couple of hundred dollars-- our human lives! So the next time you go to drive a car, take a bike instead, or walk!\n\nWould you like to play again?");
    }


  }

  // when end button is pressed, call the same action as when start button is pressed
  public void handleEndButton()
  {
    handleStartButton();
  }  
}
