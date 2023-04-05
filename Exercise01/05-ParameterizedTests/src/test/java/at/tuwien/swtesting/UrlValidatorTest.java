package at.tuwien.swtesting;


import org.apache.commons.validator.routines.UrlValidator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.StyledEditorKit.BoldAction;

public class UrlValidatorTest {
    
    private UrlValidator sut = new UrlValidator();

	@ParameterizedTest
    @CsvFileSource(resources = "/testdata.tsv", delimiter = '\t', numLinesToSkip = 1)
	public void valid_urls_are_valid(
        String schema, String subdomain, String domain, String topDomain, 
        String port, String path, String querySeparator, 
        String queryParameter, String fragment, Boolean expectedOutput)
    {
        // (
        // schema , domain , topDomain ,
        // port , path , querySeparator ,
        // queryParameter , fragment
        // ); 
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

        //assertTrue(sut.isValid(url));
        assertEquals(expectedOutput, sut.isValid(url), "current urls: \n" + url + "\n");
	}
}
