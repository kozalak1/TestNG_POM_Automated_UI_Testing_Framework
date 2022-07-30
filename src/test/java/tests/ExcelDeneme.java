package tests;

import org.testng.annotations.Test;

import java.io.IOException;

import static utilities.ExcelUtil.writeToExcel;


public class ExcelDeneme {

    @Test
    public void ExcelTestDEneme() throws IOException {

        writeToExcel();



    }
}
