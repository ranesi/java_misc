package com.ranesi;
import java.util.Date;

public class Ticket {
/*
    Name: Ticket
    Purpose: Represent an open item for helpdesk analysis
    Variables:
        • staticTicketIDCounter
            - used to generate primary key for ticket objects
        • priority
            - representative of the issue's importance
            - used for sorting
        • reporter
            - name of user submitting issue
        • description
            - details issue
            - used for searching
        • dateReported
            - when the issue was reported
    Methods:
        • Gets/Sets
        • Overridden toString()
 */
    //Static variable (used to create pk)
    protected static int staticTicketIDCounter = 1;

    protected int priority;
    protected String reporter;
    protected String description;
    protected Date dateReported;


    protected int ticketID;

    //Constructor used when loading from file
    public Ticket(int id, int p, String desc,  String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = id;
    }

    //Constructor used when instantiating new ticket
    public Ticket(int p, String desc, String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    @Override
    public String toString() {
        return("ID: " + this.ticketID + " " + this.description +
                " PRIORITY: " + this.priority + " " + this.dateReported);
    }
    /*
        GETTERS / SETTERS
     */
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public static int getStaticTicketIDCounter() {
        return staticTicketIDCounter;
    }

    public static void setStaticTicketIDCounter(int staticTicketIDCounter) {
        Ticket.staticTicketIDCounter = staticTicketIDCounter;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getTicketID() {
        return this.ticketID;
    }

}
