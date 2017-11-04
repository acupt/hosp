package com.acupt.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;

/**
 * Created by liujie on 2017/11/4.
 */
public class ExcelUtil {

    public static String[][] readFromExcel(File file, Integer rows, Integer columns) throws Exception {
        Workbook rwb = null;
        String[][] table = null;
        try {
            rwb = Workbook.getWorkbook(file);
            Sheet sheet = rwb.getSheet(0);
            int readRows = sheet.getRows();
            int readColumns = sheet.getColumns();
            if (rows != null) {
                readRows = Math.min(readRows, rows);
            } else {
                rows = readRows;
            }
            if (columns != null) {
                readColumns = Math.min(readColumns, columns);
            } else {
                columns = readColumns;
            }
            table = new String[rows][columns];
            for (int i = 0; i < readRows; i++) {
                for (int j = 0; j < readColumns; j++) {
                    Cell cell = sheet.getCell(j, i);
                    table[i][j] = cell.getContents();
                }
            }
        } finally {
            if (rwb != null) {
                rwb.close();
            }
        }
        return table;
    }
}
