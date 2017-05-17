package com.ranesi;
import java.util.Date;

public class ResolvedTicket extends Ticket {
/*
    Name: ResolvedTicket
    Purpose: Representative a closed item for helpdesk analysis
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
        • resolvedDate
            - when issue was closed
        • resolution
            - how issue was resolved
    Methods:
        • Gets/Sets
        • Overridden toString()
*/


    private Date resolvedDate;
    private String resolution;

    public ResolvedTicket(int id, int p, String desc, String rep, Date date, Date rd, String res){
        super(id, p, desc, rep, date);
        this.resolvedDate = rd;
        this.resolution = res;
    }

    @Override
    public String toString() {
        return("ID: " + this.ticketID + "\n" + this.description + "\nPriority: " + this.priority + "\nReported by: "
                + this.reporter + "\nReported on: " + this.dateReported);
    }

    public Date getResolvedDate() { return this.resolvedDate; }
    public void setResolvedDate(Date resolvedDate) { this.resolvedDate = resolvedDate; }

    public String getResolution() { return this.resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

}
