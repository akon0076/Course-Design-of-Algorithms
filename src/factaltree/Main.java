package factaltree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        LinePane linePane = new LinePane(Math.PI / 12, Math.PI / 8, 650, 700, 100, 8);

        Scene scene = new Scene(linePane, 1200, 800);
        primaryStage.setTitle("分形树");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
