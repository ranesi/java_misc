package com.ranesi;
import java.io.*;
import java.util.*;
import java.text.*;

public class FileIO {
    /*
        Class: FileIO
        Purpose: All file input/operations take place within this class
        Variables: None
        Methods:
            • readOpenTickets
                - retrieves current open tickets from open_tickets.txt
            • writeOpenTickets
                - takes LinkedList<Ticket>
                - writes all open tickets to a file for later retrieval
            • writeResolvedTickets
                - creates log storing all completed tickets
                TODO check for prior existence of file with same name (prevent overwriting previous logs)
     */

    public static LinkedList<Ticket> readOpenTickets() throws ParseException {

        LinkedList<Ticket> ticketQueue = new LinkedList<>();
        String path = "open_tickets.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {

                line = line.replace("\n", "");
                String[] ticket = line.split(";");

                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                String dateString = ticket[4];

                ticketQueue.add(new Ticket(
                        Integer.parseInt(ticket[0]),
                        Integer.parseInt(ticket[1]),
                        ticket[2], ticket[3],
                        formatter.parse(dateString)
                ));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
        //ensure that the static ticketID IS NOT ONE on program startup...
        Ticket.setStaticTicketIDCounter(ticketQueue.size() + 1);
        return ticketQueue;
    }

    public static void writeOpenTickets(LinkedList<Ticket> tickets) {
        String path = "open_tickets.txt";
        String output = "";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Ticket t : tickets) {
                output += t.getTicketID() + ";" + t.getPriority() + ";" +
                        t.getDescription() + ";" + t.getReporter() + ";" +
                        t.getDateReported() + "\n";
            }
            bw.write(output);
            bw.close();
        } catch (IOException e) {
            System.out.println("Error! Couldn't find " + path);
        }

    }

    public static void writeResolvedTickets(LinkedList<ResolvedTicket> tickets){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MMM_yyyy");
        String todayFormatted = format.format(today);
        String path = "Resolved_tickets_as_of_" + todayFormatted;
        String output = "";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (ResolvedTicket t : tickets) {
                output += t.getTicketID() + ";" + t.getPriority() + ";" +
                        t.getDescription() + ";" + t.getReporter() + ";" +
                        t.getDateReported() + ";" + t.getResolvedDate() + ";" +
                        t.getResolution() + "\n";
            }
            bw.write(output);
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Error! Couldn't find " + path);
        }
    }
}
