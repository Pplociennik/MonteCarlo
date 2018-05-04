package sample;

import java.util.Random;

public class MonteCarlo {

    private int licznik = 0;
    private int all;

    public MonteCarlo() {

    }

    public int getLicznik() {
        return licznik;
    }

    public void setAll(int all)
    {
        this.all = all;
    }


    public  int randomPointX(int minWidth, int maxWidth)
    {
        int X = 0;

        Random random = new Random();
        X = random.nextInt(maxWidth-minWidth)+minWidth;

        return X;
    }

    public int randomPointY(int minHeight, int maxHeight)
    {
        int Y = 0;

        Random random = new Random();
        Y = random.nextInt(maxHeight-minHeight)+minHeight;

        return Y;
    }

    public void checkConsistance(int x, int y)
    {
        int result = ((int) Math.pow((double) (x - 300), 2) + (int) Math.pow((double) (y - 175), 2));

        if(result <= (int) Math.pow(125, 2))
        {
            licznik++;
        }
    }

    public void calculatePI()
    {
        double result = (double) 4*licznik/all;

        System.out.println("Wartość PI: " + result);
    }
}
