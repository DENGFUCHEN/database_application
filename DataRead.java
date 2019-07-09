package bin.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataRead {

    private Workbook wbook;
    private Sheet sheet;
    private String strSheetName;

    public void readExcel(String path) {
        try {
            wbook = Workbook.getWorkbook(new File(path));
            int numberOfSheet = wbook.getNumberOfSheets();
            if (numberOfSheet != 1) {
                strSheetName = JOptionPane.showInputDialog("Whih sheet do you want to read?");
                sheet = wbook.getSheet(strSheetName);

            } else {
                sheet = wbook.getSheet(0);
                strSheetName = sheet.getName();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getValueFromCell(int iColumnNumber, int iRowNumber) {
        String result
                = sheet.getCell(iColumnNumber, iRowNumber).getContents();
        return result;
    }

    public ArrayList getColumnData(int columnNumber) {
        ArrayList cellResult = new ArrayList();
        Cell[] cellList = sheet.getColumn(columnNumber);

        for (Cell cell : cellList) {
            if (cell.getType() == CellType.LABEL) {
                LabelCell lbc = (LabelCell) cell;
                cellResult.add(lbc.getString());
            } else if (cell.getType() == CellType.NUMBER) {
                NumberCell nc = (NumberCell) cell;
                cellResult.add(nc.getValue());
            }

        }

        return cellResult;
    }

    public int getColumnNumber() {
        return sheet.getColumns();
    }

    public ArrayList getRowNumber(int rowNumber) {
        ArrayList cellResult = new ArrayList();
        Cell[] cellList = sheet.getRow(rowNumber);

        for (Cell cell : cellList) {
            if (cell.getType() == CellType.LABEL) {
                LabelCell lbc = (LabelCell) cell;
                cellResult.add(lbc.getString());
            } else if (cell.getType() == CellType.NUMBER) {
                NumberCell nc = (NumberCell) cell;
                cellResult.add(nc.getValue());
            }

        }

        return cellResult;
    }

    public void closeFile() {
        wbook.close();
    }

}
