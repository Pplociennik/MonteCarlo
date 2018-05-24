package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    int i = 0;
    int liczba = 1;

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Obliczanie π");
        primaryStage.setResizable(false);

        Stage errorStage = new Stage();
        errorStage.setTitle("Błąd!");
        errorStage.setResizable(false);

        Group errorGroup = new Group();

        Scene errorScene = new Scene(errorGroup, 200, 100);

        Group group = new Group();


        Scene mainScene = new Scene(group, 600, 600, Color.GREY);

        int rectYMIN = (int) (mainScene.getHeight() / 2) - 246;
        int rectYMAX = (int) (mainScene.getHeight() / 2) - 3;

        int rectXMIN = (int) (mainScene.getWidth() / 2) - 121;
        int rectXMAX = (int) (mainScene.getWidth() / 2) + 122;

        MonteCarlo mc = new MonteCarlo();

        Rectangle r = new Rectangle((mainScene.getWidth() / 2) - 125, (mainScene.getHeight() / 2) - 250, 250, 250);
        r.setStroke(Color.BLACK);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(5);


        Circle c = new Circle((mainScene.getWidth() / 2), (mainScene.getHeight() / 2) - 125, 125);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        c.setStrokeWidth(5);

        TextField textField = new TextField("Wpisz ilość punktów");
        textField.setPrefWidth(200);
        textField.setLayoutX((mainScene.getWidth() / 2) - 100);
        textField.setLayoutY(mainScene.getHeight() - 150);

        Label inside = new Label("Wewnątrz: ");
        inside.setLayoutX(125);
        inside.setLayoutY(350);

        Line x_axis = new Line();
        x_axis.setStartX(100);
        x_axis.setEndX(mainScene.getWidth() - 135);
        x_axis.setStartY(325);
        x_axis.setEndY(325);

        Line y_axis = new Line();
        y_axis.setStartX(150);
        y_axis.setEndX(150);
        y_axis.setStartY(335);
        y_axis.setEndY(25);

        Label xAxisName = new Label("X");
        xAxisName.setLayoutX(mainScene.getWidth() - 155);
        xAxisName.setLayoutY(330);

        Label yAxisName = new Label("Y");
        yAxisName.setLayoutX(140);
        yAxisName.setLayoutY(40);


        Label insideValue = new Label("0");
        insideValue.setLayoutX(200);
        insideValue.setLayoutY(350);


        Label pi = new Label("Wartość π: ");
        pi.setLayoutX(125);
        pi.setLayoutY(400);

        Label piValue = new Label("0");
        piValue.setLayoutX(200);
        piValue.setLayoutY(400);

        Label iterationNumber = new Label("Iteracja nr:");
        iterationNumber.setLayoutX(400);
        iterationNumber.setLayoutY(375);

        Label itNR = new Label("0");
        itNR.setLayoutX(475);
        itNR.setLayoutY(375);

        Label error = new Label("Wpisz liczbę >0!");
        error.setLayoutX(errorScene.getWidth() / 2 - (errorScene.getWidth() / 4));
        error.setLayoutY(errorScene.getHeight() / 2);
        errorGroup.getChildren().add(error);

        Button startButton = new Button("Start!");
        startButton.setPrefWidth(100);
        startButton.setLayoutX((mainScene.getWidth() / 2) - 50);
        startButton.setLayoutY(mainScene.getHeight() - 100);

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long now) {

                liczba = Integer.parseInt(textField.getText());
                int x1;
                int y1;

                Line line = new Line();


                x1 = mc.randomPointX(rectXMIN, rectXMAX);
                y1 = mc.randomPointY(rectYMIN, rectYMAX);

                mc.setAll(liczba);

                line.setStartX(x1);
                line.setEndX(x1);

               // System.out.println("Iteration nr: " + i);
                //System.out.println("X line coords: " + line.getStartX() + " " + line.getEndX());

                line.setStartY(y1);
                line.setEndY(y1);
                line.setStroke(Color.RED);


                mc.checkConsistance(x1, y1, line);
               // System.out.println("Points inside circle: " + mc.getLicznik());

                line.setStrokeWidth(3);

               // System.out.println("Y line coords: " + line.getStartY() + " " + line.getEndY());

                Line[] l = new Line[liczba];
                l[i] = line;

                group.getChildren().addAll(l[i]);


                mc.calculatePI(i+1);


                ++i;

                insideValue.setText(new String(String.valueOf(mc.getLicznik())));
                piValue.setText(new String(String.valueOf(mc.getPi())));
                itNR.setText(new String(String.valueOf(i)));

              //  System.out.println("\n");

                if (i == liczba) {
                    this.stop();
                }
            }
        };

        startButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (isInt(textField.getText()) == false || Integer.parseInt(textField.getText()) <= 0) {
                    errorScene.setRoot(errorGroup);
                    errorStage.setScene(errorScene);
                    errorStage.show();
                } else {

                 //   System.out.println("Application started...");
                 //   System.out.println("\n" + "Circle and Rectangle center coordinates: ");
                  //  System.out.println("X: " + c.getCenterX() + ", Y: " + c.getCenterY());

                    i = 0;

                    t.start();

                    group.getChildren().clear();
                    group.getChildren().addAll(textField, startButton, r, c, inside, insideValue, pi, piValue, iterationNumber, itNR, x_axis, y_axis, xAxisName, yAxisName);

                    mc.setLicznik(0);
                    mc.setPi(0);
                    mc.setAll(liczba);
                }


            }
        });

        group.getChildren().addAll(textField, startButton, r, c, inside, insideValue, pi, piValue, iterationNumber, itNR, x_axis, y_axis, xAxisName, yAxisName);
        mainScene.setRoot(group);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
