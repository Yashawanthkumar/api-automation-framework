package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExternalDataReader {
	
	//File_path can be taken from test case also.  
    private static final String JSON_FILE_PATH = "src/test/resources/testData.json";
    private static final String EXCEL_FILE_PATH = "src/test/resources/testData.xlsx";


    @DataProvider(name = "userJsonDataProvider")
	public static Object[][] getJsonUserData() {
        List<Object[]> testData = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(JSON_FILE_PATH));
            JsonNode usersArray = rootNode.get("users");

            for (JsonNode user : usersArray) {
                String name = user.get("name").asText();
                String job = user.get("job").asText();
                testData.add(new Object[]{name, job});
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage());
        }

        return testData.toArray(new Object[0][0]);
    }
    
    @DataProvider(name = "userExcelDataProvider")
    public static Object[][] getUserData() {
        Object[][] testData = null;

        try (FileInputStream file = new FileInputStream(new File(EXCEL_FILE_PATH));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            testData = new Object[rowCount - 1][colCount]; // Skip header row

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row

            int rowIndex = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int colIndex = 0; colIndex < colCount; colIndex++) {
                    testData[rowIndex][colIndex] = row.getCell(colIndex).toString();
                }
                rowIndex++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage());
        }

        return testData;
    }
}
