package api.Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public Object[][] getAllData() throws IOException {
	    String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
	    XLUtility xl = new XLUtility(path);
	    
	    int rownum = xl.getRowCount("Sheet1");  // Get the number of rows in Sheet1
	    int colcount = xl.getCellCount("Sheet1", 1); // Get the number of columns in the second row
	    
	    Object[][] apidata = new Object[rownum][colcount];
	    
	    for (int i = 1; i <= rownum; i++) {  // Loop over rows
	        for (int j = 0; j < colcount; j++) {  // Loop over columns
	            if (j == 0) { // Assuming the first column is the ID and it's an integer
	                String idString = xl.getCellData("Sheet1", i, j);
	                if (idString == null || idString.trim().isEmpty()) {
	                    // Handle empty or invalid ID. You can either skip the row or assign a default ID.
	                    apidata[i - 1][j] = 0;  // Assign default ID if invalid
	                } else {
	                    apidata[i - 1][j] = Integer.parseInt(idString);  // Parse ID as int
	                }
	            } else {
	                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);  // Store other columns as Strings
	            }
	        }
	    }
	    return apidata;
	}

    
    
    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);
        
        int rownum = xl.getRowCount("Sheet1");  // Get the number of rows in Sheet1
        Object[] apidata = new Object[rownum];  // Adjust to Object[] for flexibility
        
        for (int i = 1; i <= rownum; i++) {  // Loop over rows
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);  // Assuming column 1 is the username
        }
        return apidata;    
    }

}
