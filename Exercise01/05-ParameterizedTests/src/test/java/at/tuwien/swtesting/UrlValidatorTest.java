/*
 * William Hedlund, 12233006, excercise 1
 */
package at.tuwien.swtesting;


import org.apache.commons.validator.routines.UrlValidator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.StyledEditorKit.BoldAction;

public class UrlValidatorTest {
    
    private String[] schemes = {"http","https","ftp","file"};
    private UrlValidator sut = new UrlValidator(schemes);

	@ParameterizedTest
    @CsvFileSource(resources = "/testdata.tsv", delimiter = '\t', numLinesToSkip = 1)
	public void valid_urls_are_valid(
        String schema, String subdomain, String domain, String topDomain, 
        String port, String path, String querySeparator, 
        String queryParameter, String fragment, Boolean expectedOutput)
    {
        String[] parts = {
            schema , subdomain, domain , topDomain ,
            port , path , querySeparator ,
            queryParameter , fragment
            };
        String url = "";
        for (String s : parts){
            if(s != null){
                url += s;
            }
        };
        assertEquals(expectedOutput, sut.isValid(url), "Failed url: \n" + url + "\n");
	}
}
