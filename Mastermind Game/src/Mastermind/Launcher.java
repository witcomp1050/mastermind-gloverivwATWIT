package Mastermind;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Launcher {
    public static class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            (new GUI()).createWelcomeScreen();
        }


        public static void main(String[] args) {
            launch(args);
        }
        

    }
}
