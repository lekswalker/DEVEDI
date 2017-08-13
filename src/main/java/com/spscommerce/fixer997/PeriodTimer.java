package com.spscommerce.fixer997;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by ikornienko on 8/2/2017.
 */
public class PeriodTimer extends TimerTask {
    String dataFolder = "D:/IDEA/fixer997";
    String prodFolder = "D:/IDEA/fixer997/prod";
    String archiveFolder = "D:/IDEA/fixer997/archive";
    Date now;
    public void run() {
        FileReader pars = new FileReader();
        pars.openEDIfile(dataFolder, prodFolder, archiveFolder);
        now = new Date();
        //System.out.println("Time is :" + now); // Display current time
    }
}
