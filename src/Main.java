import javafx.application.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        setStage(stage);

        Group group = createGroup();

        stage.setScene(new Scene(group));
    }

    public void setStage(Stage stage) {
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.setTitle("Clicker");
        stage.setResizable(false);
        stage.show();
    }

    public Group createGroup() {
        Button mainClic = new Button("+1 pts");
        final int[] pointsParClic = {1};
        final int[] points = {0};

        Label score = new Label("Points: 0");
        Label pps = new Label("Points/sec: 0");


        mainClic.setScaleX(5);
        mainClic.setScaleY(8);
        mainClic.setTranslateX(470);
        mainClic.setTranslateY(350);

        mainClic.setOnAction(event -> {
            score.setText("Points: " + ++points[0]);
        });

        score.setTranslateX(470);
        score.setTranslateY(35);
        score.setScaleX(3);
        score.setScaleY(3);

        pps.setTranslateX(460);
        pps.setTranslateY(95);
        pps.setScaleX(3);
        pps.setScaleY(3);

        return new Group(mainClic, score, pps);
    }
}
