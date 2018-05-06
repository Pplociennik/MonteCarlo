package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Obliczanie π");
        primaryStage.setResizable(false);

        Group group = new Group();

        Scene mainScene = new Scene(group, 600, 600, Color.GREY);

        int rectYMIN = (int) (mainScene.getHeight() / 2) - 250;
        int rectYMAX = (int) (mainScene.getHeight() / 2);

        int rectXMIN = (int) (mainScene.getWidth() / 2) - 125;
        int rectXMAX = (int) (mainScene.getWidth() / 2) + 125;

        MonteCarlo mc = new MonteCarlo();

        Rectangle r = new Rectangle((mainScene.getWidth() / 2) - 125, (mainScene.getHeight() / 2) - 250, 250, 250);
        r.setStroke(Color.BLACK);
        r.setFill(Color.WHITE);

        Circle c = new Circle((mainScene.getWidth() / 2), (mainScene.getHeight() / 2) - 125, 125);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);

        TextField textField = new TextField("Wpisz ilość punktów");
        textField.setPrefWidth(200);
        textField.setLayoutX((mainScene.getWidth() / 2) - 100);
        textField.setLayoutY(mainScene.getHeight() - 150);

        Label inside = new Label("Wewnątrz: ");
        inside.setLayoutX(125);
        inside.setLayoutY(350);

        Label insideValue = new Label("0");
        insideValue.setLayoutX(200);
        insideValue.setLayoutY(350);

        Label pi = new Label("Wartość π: ");
        pi.setLayoutX(125);
        pi.setLayoutY(400);

        Label piValue = new Label("0");
        piValue.setLayoutX(200);
        piValue.setLayoutY(400);

        Button startButton = new Button("Start!");
        startButton.setPrefWidth(100);
        startButton.setLayoutX((mainScene.getWidth() / 2) - 50);
        startButton.setLayoutY(mainScene.getHeight() - 100);


        startButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Siema");
                System.out.println("X: " + c.getCenterX() + ", Y: " + c.getCenterY());

                int liczba = Integer.parseInt(textField.getText());
                int x1;
                int y1;
                group.getChildren().clear();
                group.getChildren().addAll(textField, startButton, r, c, inside, insideValue, pi, piValue);

                mc.setLicznik(0);
                mc.setPi(0);
                mc.setAll(liczba);


                for (int i = 0; i < liczba; i++) {
                    Line line = new Line();


                    x1 = mc.randomPointX(rectXMIN, rectXMAX);
                    y1 = mc.randomPointY(rectYMIN, rectYMAX);

                    line.setStartX(x1);
                    line.setEndX(x1);

                    System.out.println(line.getStartX() + " " + line.getEndX());

                    line.setStartY(y1);
                    line.setEndY(y1);

                    mc.checkConsistance(x1, y1);
                    System.out.println(mc.getLicznik());

                    line.setStrokeWidth(7);

                    System.out.println(line.getStartY() + " " + line.getEndY());

                    Line[] l = new Line[liczba];
                    l[i] = line;

                    group.getChildren().addAll(l[i]);
                    mc.calculatePI();

                    insideValue.setText(new String(String.valueOf(mc.getLicznik())));
                    piValue.setText(new String(String.valueOf(mc.getPi())));

                }
            }
        });

        group.getChildren().addAll(textField, startButton, r, c, inside, insideValue, pi, piValue);

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
