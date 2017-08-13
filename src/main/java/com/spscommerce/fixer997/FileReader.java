package com.spscommerce.fixer997;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by ikornienko on 8/1/2017.
 */
public class FileReader {
    private static final Logger log = Logger.getLogger(FileReader.class); // logger initialization

    /**
     * This method is used to open an EDI file
     *
     * @param datafolder - folder with data
     * @param prodFolder - folder on the prod
     * @param archiveFolder - archive folder
     */
    public void openEDIfile(String datafolder, String prodFolder, String archiveFolder){
        File inFolder = new File(datafolder);
        if (!inFolder.exists()){
            log.error("Provided Error Folder ["+inFolder+"] doesn't  exist");
            System.out.println("Provided Error Folder ["+inFolder+"] doesn't  exist");
            return;
        }
        File prFolder = new File(prodFolder);
        if (!prFolder.exists()){
            log.error("Provided Production Folder ["+prFolder+"] doesn't  exist");
            System.out.println("Provided Production Folder ["+prFolder+"] doesn't  exist");
            return;
        }
        File outFolder = new File(archiveFolder);
        if (!outFolder.exists()){
            log.error("Provided Archive Folder ["+outFolder+"] doesn't  exist");
            System.out.println("Provided Archive Folder ["+outFolder+"] doesn't  exist");
            return;
        }
            File[] listOfFiles = inFolder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) { // Look only in files, not directories
                    try {
                        String content = FileUtils.readFileToString(listOfFiles[i]);
                        UpdateEDI ue = new UpdateEDI();
                        String updatedFile = ue.updateEDI(content);
                        if (!updatedFile.matches("|NA TA1|NA ST|NOT 997")) {
                            log.info("We have found incorrect file with name '" + listOfFiles[i].getName() + "'");
                            File fileNew = new File(listOfFiles[i].getName());
                            FileUtils.writeStringToFile(fileNew, updatedFile);
                            FileUtils.copyFileToDirectory(fileNew, prFolder);
                            log.info("It has been fixed and move to production folder " + prFolder);
                            FileUtils.copyFileToDirectory(listOfFiles[i], outFolder);
                            log.info("Also initial file has been saved to archive folder " + outFolder);
                            FileUtils.forceDelete(listOfFiles[i]);
                            log.info("File deleted from initial folder " + inFolder);
                            System.out.println("We processed file ["+listOfFiles[i]+"]");
                        }
                        else{
                            System.out.println("We didn't touch file ["+listOfFiles[i]+"]");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("IOException is in FileReader class happened " + e);
                    }
                }
        }
    }
}
