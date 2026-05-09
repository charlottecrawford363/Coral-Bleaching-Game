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

/**
 The CoralModel class is all the logic code that creates the status variables, updates them, creates scenarios and options, and includes the factor of natural disaster.

 @author: Charlotte Crawford
 @date: 12/2/2024


 */


public class CoralModel
{  
  private int colorBG = 116; // the initial starting shade of red that the coral is at 
  private double temperature;
  private double tempChangeNum = 0; //keeps track of how much the temperature is changing by
  private String coralHealth;
  private int day;
  private double money;
  
  // CREATE A LIST OF SCENARIOS BELOW!! 
  private String[][] scenarioList = {
    {"You're business is booming! You find that you may need to build a headquarters somewhere but the only land avaliable near you is a forest.", "Don't Build Headquarters", "Build Headquarters"}, //1

    {"There is a growing demand for a certain type of fish in your business.", "Don't catch more fish than we need", "Catch them all!"}, //2

    {"You have a lot of excess oil from your company productions.", "Use the expensive but proper disposal methods", "Dispose oil in ocean"}, //3

    {"Your boats pump out a lot of CO2 into the ocean.", "Switch to the expensive boat that reduces carbon footprint", "Who cares!"}, //4

    {"You found a company that offers to replace your lights with solar-powered lights. It might be inefficient and expensive but it would reduce your company's carbon footprint a lot!", "Use solar-powered energy.", "Who has the money for that!"}, //5

    {"You notice that the employees at your company have a lot of food wastes, so you ponder on the idea of having a composting station.", "Compost all the food wastes", "Isn't that just trash?"}, //6
    
    {"Your company relies on AC a lot, as you live coasty and temperatures are high.", "Switch to a more efficient AC", "Leave the AC on!"}, //7

    {"The company's fisher seem to go on the boats with a lot more things than they come back with. In terms of, plastic wastes. So you suspect that they are throwing plastic into the ocean.", "Host a company-wide information session about Plastics & The Environment", "Leave them be."}, //8

    {"Your company buys paper from another company that has recently been found to be unsustainably producing their products.", "Switch to a local company that produces paper.", "The original paper company is cheaper!"}, //9

    {"Your company's buildings is a 10 minute drive from your home, but you drive there everyday anyway. (Your car is very carbon-inefficient.", "Switch to a bike", "I like to drive!"} //10

  }; 
  // each scenario is its own array {scenario, option1, option2} (Note: option1 will decrease temp and money effects, option2 will increase temp and money effects)
  
  private String[] currentScenario;
  
  // {temp effect, money effect}; each array of points is for the corresponding scenario array (based off index)
  private int[][] scenarioPoints = {
    {1, 100}, //1
    {2, 200}, //2
    {1, 100}, //3
    {2, 150}, //4
    {1, 150}, //5
    {1, 100}, //6
    {2, 150}, //7
    {1, 50}, //8
    {2, 100}, //9
    {1, 50} //10
  }; //CORRESPONDING POINTS FOR EACH SCENARIO

  //same as scenario and scenario points but for when natural disaster is called
  private String[][] disasterList = {
    {"A hurricane has just hit the area and devastated your business and the ocean.", "Donate money to help with the ocean", "Work on rebuilding your company."},
    
    {"There is a drought in the area. Water levels are falling and hurting the coral reefs. Your business is losing money.", "Donate money to help with the ocean", "Work on rebuilding your company."},

    {"An earthquake shakes up your entire area, and there are several damages done not only to people and your company but also the enviornment. It lead to a lot of sedimentation near the shore.", "Initiate a company clean-up event.", "Focus on your employees' health."}
  };
  private int[] disasterPoints = {3, 400};
  private boolean naturalDisaster = false; 
  
  private int currentScenarioIndex; // value set to the index of the current scenario (works for both disaster and regular random)
  
  private String[] choicesDisplayOrder;
  
  private boolean gameOver = false;
  

  // sets all beginning values
  public void initializeValues()
  {
    temperature = 78.0;
    money = 1000;
    changeCoralHealth();
    day = 0;    
    colorBG = 116;
    gameOver = false;
  }
  
  //getters
  public double getTemperature()
  {
    return temperature;
  }
  
  public int getColorBG()
    {
      return colorBG;
    }
  
  public String getCoralHealth()
  {
    return coralHealth;
  }

  public int getDay()
  {
    return day;
  }

  public double getMoney()
  {
    return money;
  }

  public String[] getCurrentScenario()
  {
    return currentScenario;
  }

  public int getCurrentScenarioIndex()
  {
    return currentScenarioIndex;
  }

  public boolean getGameOver()
  {
    return gameOver;
  }

  // allows game to change the temperature after each user decision
  public void changeTemperature(double tempChange)
  {
    tempChangeNum = tempChange; //records how much the temperature is changing by
    if (temperature + tempChange >= 73.0)
    {
      temperature += tempChange;
      changeCoralHealth();
      changeCoralColor();
    }
    else //if temperature changes to below 73, then make sure it doesn't go below 73
    {
      temperature = 73.0;
    }
    
  }

  // allows game to change coral health status based on temperature
  public void changeCoralHealth()
  {
    // dead coral >104 non-inclusive
    if (temperature >= 104.0)
    {
      coralHealth = "Dead";
    }
    // bleach coral 84-104 non-inclusive
    else if (temperature > 84.0)
    {
      coralHealth = "Bleached";
    }
    // healthy coral 73-84 inclusive
    else
    {
      coralHealth = "Healthy";
    }
  }

  // change color of the of the coral based on coral health 
  public void changeCoralColor()
  {
    if (temperature < 84)
    {
      int newColor = colorBG + (int)(tempChangeNum*(255/(84-73))); //color shade of new coral (max shade #/(bounds for healthy) = shade #/temperature degree)

      // red if coral becomes red or is at 73 degrees
      if (temperature == 73.0 || newColor == 0)
      {
        colorBG = 0;
      }
      // change to new color if coral is still healthy but temperature is greater tha73 and new color doesn't exceed color hue # of 255
      else if (coralHealth == "Healthy" && temperature>73 && newColor<=255)
      {
        colorBG = newColor;
      }
      // if above conditions are not met, then coral is white
      else
      {
        colorBG = 255;
      }
    }
    else
    {
      int newColor = colorBG - (int)(tempChangeNum*(155/(104-84)));

      if (temperature == 84)
      {
        colorBG = 255;
      }
      else
      {
        colorBG = newColor;
      }
    } 
  }

  // increments the day # after each scenario
  public void incrementDays()
  {
    day++;
  }

  // allows game to change the money after each user decision
  public void changeMoney(double moneyChange)
  {
    // makes sure that the money doesn't go below 0
    if (money + moneyChange >= 0)
    {
      money += moneyChange;
    }
    else
    {
      money = 0;
    }
  }

  // Randomly selects a scenario and assigns it to the instance variable currentScenario
  public void randomScenario()
  {
    // calls the natural disaster method to see if there is a natural disaster
    callND();
    // if there is no natural disaster, then the scenario is randomly selected from the scenarioList
    if (naturalDisaster == false)
    {
      currentScenarioIndex = (int)(Math.random() * scenarioList.length);
      currentScenario = scenarioList[currentScenarioIndex];
    }
    // if there is a natural disaster, then the scenario is randomly selected from the disasterList
    else
    {
      currentScenarioIndex = (int)(Math.random() * disasterList.length);
      currentScenario = disasterList[currentScenarioIndex];
    }
       
  }

  // randomizes which option is first or second so that there is no pattern to which thing (the user's business or the coral) the option is beneficial to
  public String[] optionDisplayOrder()
  {
    String tempChoice1 = currentScenario[1];
    String tempChoice2 = currentScenario[2];

    choicesDisplayOrder = new String[2];

    int randomIndex = (int)(Math.random() * 2);
    if (randomIndex == 0)
    {
      choicesDisplayOrder[0] = tempChoice2;
      choicesDisplayOrder[1] = tempChoice1;
    }
    else
    {
      choicesDisplayOrder[0] = tempChoice1;
      choicesDisplayOrder[1] = tempChoice2;
    }

    return choicesDisplayOrder;
  }

  public void playScenario(int decisionNumber)
  {
    // runs through the options in currentScenario and finds the index of the option the user choice, assigning it to userChoiceIndex
    int userChoiceIndex = 0;
    for (int i = 1; i <= 2; i++)
      {
          if (choicesDisplayOrder[decisionNumber] == currentScenario[i])
          {
            userChoiceIndex = i;
          }
      }
    
    // checks if this scenario is a natural disaster or not
    // if it is not a natural disaster, the current scenario points are added or subtracted from the user's money and temperature
    if (naturalDisaster == false)
    {
      // if the user chose the first option relative to the current scenario array, then money and temperature are decreased w/ scenario points
      if (userChoiceIndex == 1)
      {
        changeTemperature(-scenarioPoints[currentScenarioIndex][0]);
        changeMoney(-scenarioPoints[currentScenarioIndex][1]);
      }
      // if the user chose the second option relative to the current scenario array, then money and temperature are increased w/ scenario points
      else if (userChoiceIndex == 2)
      {
        changeTemperature(scenarioPoints[currentScenarioIndex][0]);
        changeMoney(scenarioPoints[currentScenarioIndex][1]);
      }
    }
    // if it is a natural disaster, the disaster points are added or subtracted from the user's money and temperature
    else
    {
      // if the user chose the first option relative to the current scenario array, then money and temperature are decreased w/ disaster points
      if (userChoiceIndex == 1)
      {
        changeTemperature(-disasterPoints[0]);
        changeMoney(-disasterPoints[1]);
      }
      // if the user chose the second option relative to the current scenario array, then money and temperature are increased w/ disaster points
      else if (userChoiceIndex == 2)
      {
        changeTemperature(disasterPoints[0]);
        changeMoney(disasterPoints[1]);
      }
    }

    // checks if the game is over
    if (money <= 0 || coralHealth.equals("Dead"))
    {
      // if user meets the above conditions, the game ends
      endGame();
    }
    else
    {
      // if the user does not meet the end game conditions, the game continues and the number of days goes up by 1
      incrementDays();
    }
  }
  
  // method to call natural disasters
  public void callND()
  {
    // generates a random integer between 0 and 14
    int num = (int)(Math.random() * 15);
    
    // makes a 1/15 chance that a natural disaster will occur
    if (num == 1)
    {
      naturalDisaster = true;
    }
    else
    {
      naturalDisaster = false;
    }
      
  }

  // if called, then set gameOver to be true
  public void endGame()
  {
    gameOver = true;
  }
}

