package com.ranesi;
import java.text.ParseException;
import java.util.*;

public class TicketManager {
/*
    Name: TicketManager
    Purpose: House central logic for ticket program
    Variables: None
    Methods:
        • mainMenu
        • searchByIssue
        • deleteTicketByIssue
        • deleteTicket
            - gets ID, deletes ticket
        • deleteTicket
            - overloaded, takes integer ticketID as parameter
        • addTickets
            - creates tickets, adds them to ticketQueue (mainMenu)
        • addTicketInPriorityOrder
            - sorts tickets
        • printAllTickets
        • printAllResolvedTickets

 */
    private void mainMenu() throws ParseException {
        // Presents, selects applicable menu options
        // Instantiates container objects used throughout program

        LinkedList<Ticket> ticketQueue = FileIO.readOpenTickets();
        LinkedList<ResolvedTicket> resolvedTickets = new LinkedList<>();

        while(true) {
            System.out.println(
                    "1. Enter Ticket\n2. Delete Ticket by ID\n3. Delete by Issue" +
                            "\n4. Search by Issue\n5. Display All Tickets\n" +
                            "6. Display All Resolved\n7. Quit");
            int task = Input.getPositiveIntInput("Enter your selection");

            if (task == 1) {
                addTickets(ticketQueue);
            } else if (task == 2 ) {
                deleteTicket(ticketQueue, resolvedTickets);
            } else if (task == 7) {
                System.out.println("Quitting program.");
                break;
            } else if (task == 3) {
                deleteTicketByIssue(ticketQueue, resolvedTickets);
            } else if (task == 4) {
                searchByIssue(ticketQueue);
            } else if (task == 6) {
                printAllResolvedTickets(resolvedTickets);
            } else {
                printAllTickets(ticketQueue);
            }
        }
        FileIO.writeOpenTickets(ticketQueue);
        FileIO.writeResolvedTickets(resolvedTickets);

    }

    protected void searchByIssue(LinkedList<Ticket> ticketQueue) {
        //Searches all ticket descriptions for substring "search", submitted by user

        String search = Input.getStringInput("Enter search term(s)");
        System.out.println("--------All Tickets--------");
        for (Ticket t : ticketQueue) {
            if (t.getDescription().contains(search)) {
                System.out.println(t);
            }
        }
        System.out.println("-------End of List---------");
    }

    protected void deleteTicketByIssue(LinkedList<Ticket> ticketQueue, LinkedList<ResolvedTicket> resolvedTickets) {
        //Calls searchByIssue method, prompts for TicketID, deletes issue by ID

        searchByIssue(ticketQueue);
        int ticketID = Input.getPositiveIntInput("Enter ID of ticket to delete");
        deleteTicket(ticketQueue, resolvedTickets, ticketID);
    }

    protected void deleteTicket(LinkedList<Ticket> ticketQueue, LinkedList<ResolvedTicket> resolvedTickets) {
        //Requests ticketID from user, searches ticketQueue to delete specified ticket
        //Creates ResolvedTicket from deleted ticket

        printAllTickets(ticketQueue);

        if(ticketQueue.isEmpty()) {
            System.out.println("You cannot delete that which you do not have.");
        } else {
            while (true) {
                int deleteID = Input.getPositiveIntInput("Enter ID of ticket to delete");

                boolean found = false;

                for (Ticket ticket : ticketQueue) {
                    if (ticket.getTicketID() == deleteID) {

                        found = true;

                        String resolution = Input.getStringInput("Enter resolution");

                        resolvedTickets.add(new ResolvedTicket(ticket.getTicketID(), ticket.getPriority(),
                                ticket.getDescription(), ticket.getReporter(), ticket.getDateReported(),
                                new Date(), resolution));

                        ticketQueue.remove(ticket);

                        System.out.println(String.format("Ticket %d deleted", deleteID));
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Ticket ID not found, no ticket deleted");
                } else {
                    break;
                }
            }
        }
        printAllResolvedTickets(resolvedTickets);
    }

    protected void deleteTicket(LinkedList<Ticket> ticketQueue,
                                LinkedList<ResolvedTicket> resolvedTickets, int ticketID) {
        // Overloaded deleteTicket
        // passing ticketID allows for much more succinct code
        if (ticketQueue.isEmpty()) {
            System.out.println("ALAS no tickets.");
        } else {
            for (Ticket ticket : ticketQueue) {
                if (ticket.getTicketID() == ticketID) {

                    String resolution = Input.getStringInput("Enter resolution");

                    resolvedTickets.add(new ResolvedTicket(ticket.getTicketID(), ticket.getPriority(),
                            ticket.getDescription(), ticket.getReporter(), ticket.getDateReported(),
                            new Date(), resolution));

                    ticketQueue.remove(ticket);

                    System.out.println(String.format("Ticket %d deleted", ticketID));
                    break;
                }
            }
        }
        printAllResolvedTickets(resolvedTickets);
    }

    protected void addTickets(LinkedList<Ticket> ticketQueue) {
        //Create new ticket, add to queue

        Date dateReported = new Date();

        while (true) {

            String description = Input.getStringInput("Enter problem");
            String reporter = Input.getStringInput("Who reported this problem?");
            int priority = Input.getPositiveIntInput("Enter priority of " + description);

            Ticket t = new Ticket(priority, description, reporter, dateReported);

            addTicketInPriorityOrder(ticketQueue, t);

            printAllTickets(ticketQueue);

            String more = Input.getStringInput("More tickets to add?");
            if (more.equalsIgnoreCase("N")) {
                return;
            }
        }
    }

    protected void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket) {
        //Adds tickets by priority to circumvent creating a Comparator

        if (tickets.isEmpty()) {
            tickets.add(newTicket);
            return;
        }

        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size(); x++) {
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }

        tickets.addLast(newTicket);
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        // Output all open tickets to console

        System.out.println("--------All Open Tickets--------");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
        System.out.println("-----------End of List----------");
    }

    protected static void printAllResolvedTickets(LinkedList<ResolvedTicket> tickets) {
        // Output all resolved tickets to console

        System.out.println("--------All Resolved Tickets--------");
        for (ResolvedTicket t : tickets) {
            System.out.println(t);
        }
        System.out.println("------------End of List-------------");
    }

    public static void main(String[] args) throws ParseException {
        TicketManager mgmt = new TicketManager();
        mgmt.mainMenu();
    }
}
