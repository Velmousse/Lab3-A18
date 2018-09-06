import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Main extends Application{
    private final int[] pointsParClic = {1},
            points = {0},
            pointsParSec = {0},
            prixBuy = {10, 25, 100, 1000, 10000};

    private Button mainClic = new Button(":)");

    private Button[] buy = {
            new Button("+1 pt/clic"),
            new Button("+1 pt/sec"),
            new Button("+2 pts/sec"),
            new Button("+5 pts/sec"),
            new Button("+15 pts/sec")
    };

    private Label score = new Label("Points: " + points[0]),
            pps = new Label("Points/sec: " + pointsParSec[0]),
            pointParClic = new Label("Points/clic: " + pointsParClic[0]);

    private Label[] labBuy = {
            new Label("Prix: " + prixBuy[0]),
            new Label("Prix: " + prixBuy[1]),
            new Label("Prix: " + prixBuy[2]),
            new Label("Prix: " + prixBuy[3]),
            new Label("Prix: " + prixBuy[4])
    };

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        setStage(stage);

        Group group = createGroup();

        stage.setScene(new Scene(group));
    }

    public void setStage(Stage stage) {
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("Clicker");
        stage.setResizable(false);
        stage.show();
    }

    public Group createGroup() {
        {  //Labels
            score.setTranslateX(170);
            score.setTranslateY(35);
            score.setScaleX(3);
            score.setScaleY(3);

            pps.setTranslateX(160);
            pps.setTranslateY(95);
            pps.setScaleX(3);
            pps.setScaleY(3);

            pointParClic.setTranslateX(160);
            pointParClic.setTranslateY(155);
            pointParClic.setScaleX(3);
            pointParClic.setScaleY(3);

            setLabBuy();
        }

        {  //Boutons
            mainClic.setScaleX(8);
            mainClic.setScaleY(8);
            mainClic.setTranslateX(170);
            mainClic.setTranslateY(350);
            mainClic.setOnAction(event -> {
                points[0] += pointsParClic[0];
                score.setTextFill(Color.GREEN);
                score.setText("Points: " + points[0]);
            });

            setBoutonBuy();

            buy[0].setOnAction(event -> buy(0));
            buy[1].setOnAction(event -> buy(1));
            buy[2].setOnAction(event -> buy(2));
            buy[3].setOnAction(event -> buy(3));
            buy[4].setOnAction(event -> buy(4));
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            if (pointsParSec[0] > 0) {
                points[0] += pointsParSec[0];
                score.setTextFill(Color.GREEN);
                score.setText("Points: " + points[0]);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Group labels = new Group(score, pps, labBuy[0], labBuy[1], labBuy[2], labBuy[3], labBuy[4], pointParClic);
        Group buttons = new Group(mainClic, buy[0], buy[1], buy[2], buy[3], buy[4]);
        return new Group(buttons, labels);
    }

    final public void buy(int bouton) {
        if (points[0] >= prixBuy[bouton]) {
            points[0] -= prixBuy[bouton];
            prixBuy[bouton] *= 2;
            score.setTextFill(Color.INDIANRED);
            score.setText("Points: " + points[0]);

            switch (bouton) {
                case 0: pointsParClic[0]++; break;
                case 1: pointsParSec[0]++; break;
                case 2: pointsParSec[0] += 2; break;
                case 3: pointsParSec[0] += 5; break;
                case 4: pointsParSec[0] += 15;
            }

            labBuy[bouton].setText("Prix: " + prixBuy[bouton]);
            pps.setText("Points/sec: " + pointsParSec[0]);
            pointParClic.setText("Points/clic: " + pointsParClic[0]);
        }
    }

    public void setLabBuy() {
        int y = 75;

        for (Label label : labBuy) {
            label.setScaleX(2);
            label.setScaleY(2);
            label.setTranslateX(650);
            label.setTranslateY(y);

            y += 100;
        }
    }

    public void setBoutonBuy() {
        int y = 75;

        for (Button buy : buy) {
            buy.setScaleX(3);
            buy.setScaleY(3);
            buy.setTranslateX(430);
            buy.setTranslateY(y);

            y += 100;
        }
    }
}
