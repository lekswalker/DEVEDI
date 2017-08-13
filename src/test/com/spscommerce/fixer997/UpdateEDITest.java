package com.spscommerce.fixer997;

import org.junit.*;

/**
 * Created by ikornienko on 8/9/2017.
 */
public class UpdateEDITest {

    UpdateEDI ue = null;
    @Before
    public void setUp() throws Exception {
        ue = new UpdateEDI();
    }

    @After
    public void tearDown() throws Exception {
        ue = null;
    }

    @Test
    public void testUpdateEDI() throws Exception {
            String initial = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005TA1*000019369*170727*1620*A*000\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*997*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
            String estimated = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*997*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
            Assert.assertEquals(estimated,ue.updateEDI(initial));
    }

    @Test
    public void testUpdateEDI2() throws Exception {
        String initial = "";
        String estimated = "";
        Assert.assertEquals(estimated,ue.updateEDI(initial));
    }

    @Test
    public void testUpdateEDI3() throws Exception {
        String initial = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005TA1*000019369*170727*1620*A*000\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*850*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
        String estimated = "NOT 997";
        Assert.assertEquals(estimated,ue.updateEDI(initial));
    }

    @Test
    public void testUpdateEDI4() throws Exception {
        String initial = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005TA1*000019369*170727*1620*A*000\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005";
        String estimated = "NA ST";
        Assert.assertEquals(estimated,ue.updateEDI(initial));
    }

    @Test
    public void testUpdateEDI5() throws Exception {
        String initial = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*997*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
        String estimated = "NA TA1";
        Assert.assertEquals(estimated,ue.updateEDI(initial));
    }

   // @Ignore
    @Test
    public void testUpdateEDIFail() throws Exception {
        String initial = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005TA1*000019369*170727*1620*A*000\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*997*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
        String estimated = "ISA*00*          *00*          *08*5163497100     *12*7033100361WIM  *170727*1806*ÿ*00403*000002951*0*P*>\u0005TA1*000019369*170727*1620*A*000\u0005GS*FA*5163497100*7033100361WIM*20170727*1806*1052*X*004010\u0005ST*997*000001052\u0005AK1*IN*19369\u0005AK9*A*2*2*2\u0005SE*4*000001052\u0005GE*1*1052\u0005IEA*1*000002951\u0005";
        Assert.assertEquals(estimated,ue.updateEDI(initial));
    }
}