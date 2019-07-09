package bin.test;

import java.util.ArrayList;
import jxl.write.WriteException;

public class EX {

    public static void main(String[] args) {
        // Read existed excel file
        DataRead dr = new DataRead();
        dr.readExcel("D:\\DSKF75.xls");
        System.out.println(dr.getValueFromCell(0, 0));


        // Get designated column with all row data
        ArrayList columnData = dr.getColumnData(0);
        for (Object o : columnData) {
            System.out.println(o);
        }

        // Get total column number
        System.out.println(dr.getColumnNumber());

        // Get designated row with all column data
        ArrayList rowData = dr.getRowNumber(1);
        for (Object o : rowData) {
            System.out.println(o);
        }

        dr.closeFile();

        // Create a new excel file
        try {
            DataWrite dw = new DataWrite();
            dw.createExcel("D:\\DSKF75.xls");
            dw.createSheet("第一表", 0);
            dw.setValueIntoCell(0, 0, "測試資料");
            dw.setValueIntoCell(0, 1, "小明");
            dw.closeFile();
        } catch (WriteException ex) {
            System.out.println(ex);
        }

    }

}