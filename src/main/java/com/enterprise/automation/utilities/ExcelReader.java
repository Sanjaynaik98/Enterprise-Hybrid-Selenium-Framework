
package com.enterprise.automation.utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReader {

    private ExcelReader() {
    }

    public static Object[][] readSheet(
            String resourcePath,
            String sheetName) {

        List<Object[]> records = new ArrayList<>();

        try (InputStream input = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "Excel resource not found: " + resourcePath);
            }

            try (Workbook workbook = WorkbookFactory.create(input)) {

                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    throw new IllegalArgumentException(
                            "Sheet not found: " + sheetName);
                }

                Row header = sheet.getRow(0);

                if (header == null || header.getLastCellNum() <= 0) {
                    throw new IllegalArgumentException(
                            "Excel sheet has no header row");
                }

                int columnCount = header.getLastCellNum();
                DataFormatter formatter = new DataFormatter();

                for (int rowIndex = 1;
                     rowIndex <= sheet.getLastRowNum();
                     rowIndex++) {

                    Row row = sheet.getRow(rowIndex);

                    if (row == null) {
                        continue;
                    }

                    Object[] values = new Object[columnCount];
                    boolean hasData = false;

                    for (int columnIndex = 0;
                         columnIndex < columnCount;
                         columnIndex++) {

                        Cell cell = row.getCell(
                                columnIndex,
                                Row.MissingCellPolicy
                                        .CREATE_NULL_AS_BLANK
                        );

                        String value =
                                formatter.formatCellValue(cell).trim();

                        values[columnIndex] = value;

                        if (!value.isEmpty()) {
                            hasData = true;
                        }
                    }

                    if (hasData) {
                        records.add(values);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read Excel test data: " + resourcePath, e);
        }

        return records.toArray(new Object[0][]);
    }
}
