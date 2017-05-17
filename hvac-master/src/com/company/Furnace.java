package com.company;

import java.util.Date;

public class Furnace extends ServiceCall {

    private String type;

    public Furnace(String serviceAddress, String problemDescription, Date date, String type) {

        super(serviceAddress, problemDescription, date);
        this.type = type;

    }

    @Override
    public String toString() {

        String typeString = type.toString();
        String resolvedDateString = (resolvedDate == null) ? "Unresolved" : this.resolvedDate.toString();
        String resolutionString = (this.resolution == null) ? "Unresolved" : this.resolution;
        String feeString = (fee == UNRESOLVED) ? "Unresolved" : "$" + Double.toString(fee);


        return  serviceAddress + " " +
                problemDescription + " " +
                typeString + " " +
                reportedDate + " " +
                resolvedDateString + " " +
                resolutionString + " " +
                feeString;
    }

    /* An enum is a group of constants. Since the furnace's type must be one of these, use
    an Enum to contain the allowed types.  */
    enum FurnaceType {
        FORCED_AIR,
        BOILER,
        GRAVITY
    }
}
