package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaterHeater extends ServiceCall {

    public static final double MINIMUM_FEE = 20;

    WaterHeater(String serviceAddress, String problemDescription, Date date) {
        super(serviceAddress, problemDescription, date);
        this.fee = MINIMUM_FEE;
    }

    @Override
    public String toString() {

        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(this.reportedDate);

        String output = String.format(
                "Water Heater %s %s %s Fee: $%.2f + %.2f = $%.2f",
                this.serviceAddress, formattedDate, this.problemDescription,
                (this.fee - WaterHeater.MINIMUM_FEE),
                WaterHeater.MINIMUM_FEE,
                this.fee
        );
        return output;
    }
}
