package com.example.kd.melotto;

import java.util.ArrayList;

/**
 * Created by kd on 11/30/15.
 */
public class UserData
{
    private String email, password, recovery;
    private int phoneNumber, ticketsSpent, ticketsWon, totalSpent, totalWon;
    private ArrayList<Ticket> tickets;

    public UserData(String user, String pass, int phone, String recover)
    {
        email = user;
        password = pass;
        phoneNumber = phone;
        recovery = recover;
        tickets = new ArrayList<Ticket>();
        totalSpent = 0;
        totalWon = 0;

    }
    public void changeUsername(String newEmail) { email = newEmail; }

    public void changePassword(String newPassword) { password = newPassword; }

    public void changeRecovery(String newRecovery) { recovery = newRecovery; }

    public boolean verifyEmail(String input) { return email.equals(input); }

    public boolean verifyPassword(String input) { return password.equals(input); }

    public boolean verifyPhoneNumber(int input) { return phoneNumber == input; }

    public boolean verifyRecovery(String input) { return recovery.equals(input); }

    public void addTicket(Ticket t)
    {
        tickets.add(t);
        ticketsSpent++;
        updateTotalSpent(t.getAmountPaid());
        if(t.isWinning())
        {
            ticketsWon++;
            updateTotalWon(t.getAmountWon());
        }
    }

    public void updateTotalSpent(double amount) { totalSpent += amount; }

    public void updateTotalWon(double amount) { totalWon += amount; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public int getPhoneNumber() { return phoneNumber; }

    public String getRecovery() { return recovery; }

    public ArrayList<Ticket> getTickets() { return tickets; }

    public ArrayList<Ticket> getTicketsWon()
    {
        ArrayList<Ticket> ticketsWon = new ArrayList<Ticket>();
        for(Ticket t: tickets)
        {
            if(t.isWinning())
            {
                ticketsWon.add(t);
            }
        }
        return ticketsWon;
    }

    public int getTotalSpent() { return totalSpent; }

    public int getTotalWon() { return totalWon; }
}
