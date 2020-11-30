package Mastermind;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GUI {
    public int[] nums;
    int index;
    int guessNum;
    Game game;
    int[] code;

    GUI() {
        nums = new int[] {-1, -1, -1, -1};
        index = 13;
        game = new Game();
        code = game.codeToInts(game.generateSecretCode());
        guessNum = 1;
    }
    public void createWelcomeScreen() {
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        AnchorPane welcomeScreen = new AnchorPane();
        Scene scene = new Scene(welcomeScreen, 520, 550);
        Label rules = new Label();
        welcomeScreen.setTopAnchor(rules, 15.0);
        welcomeScreen.setLeftAnchor(rules, 80.0);
        rules.setText("Welcome to Mastermind.\n" +
                "\n" +
                "The objective of the game is to correctly guess a secret code consisting\n" +
                "of 4 colored pegs.\n\n" +
                "Each peg will be of one of 6 colors – Blue, Green, Orange, Purple, Red,\nand Yellow.\n" +
                "More than one peg in the secret code could be of the same color.\nYou must guess the correct color " +
                "and order of the code.\n\n" +
                "You will have 10 chances to correctly guess the code.\n\nAfter every guess, the computer " +
                "will provide you feedback in the\nform of 0 to 4 colored pegs. A black peg indicates " +
                "that a peg in your guess is\nof the correct color and is in the correct position.\n" +
                "A white peg indicates that a peg in your guess is of the correct color\n" +
                "but is not in the correct position.\n\n" +
                "NOTE: The order of the black and white squares does not matter just the color " +
                "\n");
        welcomeScreen.getChildren().add(rules);

        Button play = new Button("Start Game");
        welcomeScreen.setTopAnchor(play, 420.0);
        welcomeScreen.setLeftAnchor(play, 80.0);
        play.setPrefHeight(50.0);
        play.setPrefWidth(120.0);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                createGameScreen();

            }
        });
        Button quit = new Button("Quit");
        welcomeScreen.setTopAnchor(quit, 420.0);
        welcomeScreen.setLeftAnchor(quit, 330.0);
        quit.setPrefWidth(120.0);
        quit.setPrefHeight(50.0);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                System.exit(0);
            }
        });
        welcomeScreen.getChildren().addAll(play,quit);

        stage.setScene(scene);
        stage.show();
    }

    public void createGameScreen() {
        Stage stage = new Stage();
        stage.setTitle("Mastermind");
        AnchorPane gameScreen = new AnchorPane();
        Scene scene = new Scene(gameScreen, 1000, 650);
        HBox options = new HBox();
        options.setSpacing(30.0);

        for (int i=0; i < 4; i++) {
            int x = i;
            Circle c = new Circle(20.0);
            options.getChildren().add(c);
            c.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    toggleColor(c, x);
                }
            });
        }
        gameScreen.setTopAnchor(options, 30.0);
        gameScreen.setLeftAnchor(options, 270.0);
        Button submit = new Button("Submit Guess");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (index>3) {

                    createGuessCircles(gameScreen, stage);
                }
                else {
                    playAgainScreen(false, stage);

                    for (int i=0; i < 4; i++) {
                        System.out.print(code[i]);
                    }
                }

            }
        });
        submit.setPrefHeight(50.0);
        submit.setPrefWidth(120.0);
        gameScreen.setTopAnchor(submit, 25.0);
        gameScreen.setLeftAnchor(submit, 560.0);

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        exit.setPrefHeight(50.0);
        exit.setPrefWidth(120.0);
        gameScreen.setTopAnchor(exit, 25.0);
        gameScreen.setLeftAnchor(exit, 780.0);

        gameScreen.getChildren().addAll(options, submit, exit);
        stage.setScene(scene);
        stage.show();

    }

    public void toggleColor(Circle c, int index) {
        if (nums[index]==5) {
            nums[index]=0;
        }
        else {
            nums[index]++;
        }
        changeColor(c, index);
    }

    public void changeColor(Shape c, int index)  {
        switch (nums[index]) {
            case 0: {
                c.setFill(Color.BLUE); break;
            }
            case 1: {
                c.setFill(Color.GREEN); break;
            }
            case 2: {
                c.setFill(Color.ORANGE); break;
            }
            case 3: {
                c.setFill(Color.PURPLE); break;
            }
            case 4: {
                c.setFill(Color.RED); break;
            }
            case 5: {
                c.setFill(Color.YELLOW); break;
            }
        }
    }

    public void createGuessCircles(AnchorPane a, Stage stage) {
        HBox guess = new HBox();
        guess.setSpacing(30.0);
        Label whichGuess = new Label();
        whichGuess.setText("" + guessNum);
        guessNum++;
        guess.getChildren().add(whichGuess);

        for (int i=0; i<4; i++) {
            Circle c = new Circle(20.0);
            changeColor(c, i);
            guess.getChildren().add(c);
        }

        Separator s = new Separator();
        s.setOrientation(Orientation.VERTICAL);
        guess.getChildren().add(s);

        int[] feedback = game.getFeedback(code, nums);

        if (feedback[0]==4) {
            playAgainScreen(true, stage);
        }
        for (int i=0; i < 4; i++) {
            Rectangle r = new Rectangle(30.0, 30.0);
            guess.getChildren().add(r);
            if (feedback[0] > 0) {
                r.setFill(Color.BLACK);
                feedback[0]--;
            }
            else if (feedback[1] > 0) {
                r.setFill(Color.WHITE);
                feedback[1]--;
            }
            else {
                r.setFill(Color.PINK);
            }
        }

        if (guessNum!=11) {
            a.setLeftAnchor(guess, 220.0);
        }
        else {
            a.setLeftAnchor(guess, 214.0);
        }

        index--;
        a.setTopAnchor(guess, index * 50.0);

        a.getChildren().add(guess);

    }

    public void playAgainScreen(boolean won, Stage s) {
        Stage stage = new Stage();
        AnchorPane playAgain = new AnchorPane();
        Scene scene = new Scene(playAgain, 300, 100);

        Label message = new Label();
        playAgain.setTopAnchor(message, 10.0);
        playAgain.setLeftAnchor(message, 105.0);
        Label showCode = new Label();
        showCode.setVisible(false);
        if (won) {
            stage.setTitle("You Win!");
            message.setText("Congrats! You Win!");
        }
        else {
            stage.setTitle("You Lose!");
            message.setText("Sorry! You lose!\n");
            showCode.setText("The code was " + game.codeForPlayAgain(code));
            showCode.setVisible(true);
            playAgain.setTopAnchor(showCode, 28.0);
            playAgain.setLeftAnchor(showCode, 40.0);

        }
        Button play = new Button("Play Again");
        playAgain.setTopAnchor(play, 60.0);
        playAgain.setLeftAnchor(play, 40.0);
        play.setPrefHeight(15.0);
        play.setPrefWidth(80.0);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                s.close();
                stage.close();
                (new GUI()).createGameScreen();
            }
        });

        Button quit = new Button("Quit");
        playAgain.setTopAnchor(quit, 60.0);
        playAgain.setLeftAnchor(quit, 180.0);
        quit.setPrefHeight(15.0);
        quit.setPrefWidth(80.0);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        playAgain.getChildren().addAll(message, showCode, play, quit);

        stage.setScene(scene);
        stage.show();
    }
}
