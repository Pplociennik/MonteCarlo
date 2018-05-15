package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

public class MonteCarlo {

    private int licznik = 0;
    private int all;
    private double pi;

    public MonteCarlo() {

    }

    public int getLicznik() {
        return licznik;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public double getPi() {
        return pi;
    }

    public void setLicznik(int licznik) {
        this.licznik = licznik;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public int randomPointX(int minWidth, int maxWidth) {
        int X = 0;

        Random random = new Random();
        X = random.nextInt(maxWidth - minWidth) + minWidth;

        return X;
    }

    public int randomPointY(int minHeight, int maxHeight) {
        int Y = 0;

        Random random = new Random();
        Y = random.nextInt(maxHeight - minHeight) + minHeight;

        return Y;
    }

    public void checkConsistance(int x, int y, Line l) {
        int result = ((int) Math.pow((double) (x - 300), 2) + (int) Math.pow((double) (y - 175), 2));

        if (result <= (int) Math.pow(125, 2)) {
            licznik++;
            l.setStroke(Color.BLUE);
        }
    }

    public void calculatePI() {
        double result = 4.0*((double)licznik / (double)all);
        pi = result;

        System.out.println("Value of PI: " + result);
    }
}
