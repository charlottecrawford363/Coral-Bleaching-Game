# Coral Bleaching Game

A JavaFX simulation game exploring how business decisions affect coral reef health and climate change.

## About

This game was created as a final project for an introductory Java course (senior year). It's meant to illustrate how the everyday choices of businesses contribute to climate change — and how that, in turn, affects coral bleaching. Natural disasters also occur randomly to show that nature itself plays a role alongside human activity.

The project prioritizes backend logic over UI: it mainly focuses on modeling temperature, coral health, and economic tradeoffs.

## How to Play

You play as a wealthy coastal business owner who must make a series of decisions, balancing profit against environmental impact. Every choice shifts the ocean temperature and your available money. If the coral dies or you go bankrupt, you lose.

The generalized temperature range for coral health in the game is as follows:
- **Healthy coral**: water temperature 73–84°F
- **Bleached coral**: 84–104°F
- **Dead coral**: above 104°F

which is based on the information in "In what types of water do corals live?" by the NOAA (https://oceanservice.noaa.gov/facts/coralwaters.html).

Note: There is a 1-in-15 chance each round that a natural disaster strikes

## Project Structure
This project was designed with the MVC (Model-View-Controller) pattern.

| File | Role |
|---|---|
| `CoralGame.java` | Entry point; launches the application |
| `CoralModel.java` | All game logic — temperature, coral health, money, scenarios, disasters |
| `CoralView.java` | UI layout using JavaFX GridPane, labels, buttons, and images |
| `CoralController.java` | Connects the model and view; handles all button actions |

## Running the Game

Make sure you have JavaFX set up, then run:

```
CoralGame.java
```

The `main` method in `CoralGame` will invoke the other files automatically.

> **Note:** The game loads a few images from the web and expects a local `image.png` (coral graphic) in the project directory. If these are unavailable, the game will still run — the image slots will just be empty.


## Dependencies

- Java (JDK 11+)
- JavaFX
