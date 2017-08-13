package com.spscommerce.fixer997;

/**
 * Created by ikornienko on 8/1/2017.
 */
public class AppRunner {
    public static void main (String[] args) {
        String dataFolder = "D:/IDEA/fixer997";
        String prodFolder = "D:/IDEA/fixer997/prod";
        String archiveFolder = "D:/IDEA/fixer997/archive";
        if(args.length>2){
            dataFolder = args[0];
            prodFolder = args[1];
            archiveFolder = args[2];
        }
        FileReader pars = new FileReader();
        pars.openEDIfile(dataFolder, prodFolder, archiveFolder);
    }
}
