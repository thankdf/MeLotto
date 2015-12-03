package com.example.kd.melotto;

import java.io.File;

/**
 * Created by kd on 11/30/15.
 */
public class Ticket
{
    private boolean win;
    private File photo;
    private String date;
    private double amountPaid, amountWon;
    private int[][] numbers;

    public Ticket(String d, double paid, int[][] number, File i)
    {
        date = d;
        amountPaid = paid;
        numbers = number;
        photo = i;
    }

    public void setWin(String d)
    {
        int[] winningNumbers = retrieveWinningNumbers(d);
        int regnumber = 0;
        int mega = 0;
        for(int i = 0; i < numbers.length; i++)
        {
            for(int j = 0; j < numbers[0].length; j++)
            {
                if(j < 5)
                {

                }
            }
        }
    }

    public int[] retrieveWinningNumbers(String d)
    {
        int[] winnings = new int[6];
        return winnings;
    }

    public File viewTicket()
    {
        return photo;
    }

    public boolean isWinning() { return win; }

    public double getAmountPaid() { return amountPaid; }

    public double getAmountWon() { return amountWon; }

}
