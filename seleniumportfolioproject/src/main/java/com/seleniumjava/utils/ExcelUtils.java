package com.seleniumjava.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.Arrays;

public class ExcelUtils {
    // Toma los datos de la matriz en el archivo excel.
    public static Object[][] getData(String filePath, String sheetName) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                workbook.close();
                throw new RuntimeException("La hoja '" + sheetName + "' no existe en el archivo Excel.");
            }

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            if (rowCount <= 0 || colCount <= 0) {
                workbook.close();
                throw new RuntimeException("La hoja '" + sheetName + "' no tiene datos.");
            }

            Object[][] data = new Object[rowCount][colCount];

            for (int i = 1; i <= rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    // Verificar si la celda es null antes de llamar a toString()
                    if (sheet.getRow(i) == null || sheet.getRow(i).getCell(j) == null) {
                        data[i - 1][j] = ""; // Asignar una cadena vacía en lugar de null
                    } else {
                        data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                    }
                }
            }

            workbook.close();
            file.close();

            // Imprime los valores obtenidos para depuración
            System.out.println("Datos extraídos del Excel:");
            for (Object[] row : data) {
                System.out.println(Arrays.toString(row));
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el archivo Excel: " + filePath, e);
        }
    }
}