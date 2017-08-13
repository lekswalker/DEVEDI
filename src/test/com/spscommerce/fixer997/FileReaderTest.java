package com.spscommerce.fixer997;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by ikornienko on 8/3/2017.
 */
public class FileReaderTest {

    private FileReader fr;
    @Before
    public void setUp() throws Exception {
        fr = new FileReader();
    }

    @After
    public void tearDown() throws Exception {
        fr  = null;
    }

    @Test
    public void testOpenEDIfile() throws Exception {
        String errFolder="";
        String prodFolder="";
        String arcFolder="";
        fr.openEDIfile(errFolder,prodFolder,arcFolder);
        //assertNull(fr);
    }

    @Test
    public void testOpenEDIfile1() throws Exception {
        String errFolder="D:/IDEA/fixer997/errors";
        String prodFolder="";
        String arcFolder="";
        fr.openEDIfile(errFolder,prodFolder,arcFolder);
    }

    @Test
    public void testOpenEDIfile2() throws Exception {
        String errFolder="D:/IDEA/fixer997/errors";
        String prodFolder="D:/IDEA/fixer997/prod";
        String arcFolder="";
        fr.openEDIfile(errFolder,prodFolder,arcFolder);
    }
}