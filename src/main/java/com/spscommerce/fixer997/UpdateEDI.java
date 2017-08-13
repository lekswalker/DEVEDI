package com.spscommerce.fixer997;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by ikornienko on 8/1/2017.
 */
public class UpdateEDI {
    private static final Logger log = Logger.getLogger(UpdateEDI.class);

    /**
     * This method is used to delete TA1 segment from 997
     *
     * @param fileData - EDI document
     * @return - updated file
     */
    public String updateEDI(String fileData){
        String updateFileText = "";
        try{
            if(fileData.length()>106) { // if no, it is not EDI file
                String textToDelete = "";
                String segDel = fileData.substring(105, 106);
                String elDel = fileData.substring(3, 4);
                String[] segs = fileData.split(segDel);
                Optional<String> st = Arrays.stream(segs).filter(c -> c.substring(0, 2).equals("ST")).findAny();
               if (!st.isPresent()) {
                    return "NA ST";
                }
                if (elDel.equals("*")) elDel = "\\*"; // exception without this
                String[] fields = st.get().split(elDel);
                String firstEl = fields[1];
                if (!firstEl.equals("997")) {
                    return "NOT 997";
                } else {
                    Optional<String> ta1 = Arrays.stream(segs).filter(c -> c.substring(0, 3).equals("TA1")).findAny();
                    if (!ta1.isPresent()) {
                        return "NA TA1";
                    } else {
                        for (int i = 0; i < segs.length; i++) {
                            if ( segs[i].substring(0, 3).equals("TA1")) {
                                String ta1info = segs[i];
                                textToDelete = segDel + ta1info;
                                updateFileText = fileData.replace(textToDelete, "");
                            }
                        }
                    }
                }
            }
        }
        catch(IndexOutOfBoundsException e){
            log.warn("We can not read file correctly. Looks like file has strange Non EDI format "+e);
        }

        catch(Exception e){
            log.warn("We can not read file correctly. Who knows why "+e);
        }
        return updateFileText;
    }
}
