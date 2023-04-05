package at.tuwien.swtesting;


import org.apache.commons.validator.routines.UrlValidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;

public class UrlValidatorTest {
    
    private UrlValidator sut = new UrlValidator();

    @Test
    public void sainty_check(){
        assertTrue(sut.isValid("https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html#isValid-java.lang.String-"));
        assertTrue(sut.isValid("http://192.168.0.1"));
        assertTrue(sut.isValid("http://192.abc.0.1"));
    }

	@ParameterizedTest
    @CsvFileSource(resources = "/testdata.tsv", delimiter = '\t')
	public void valid_urls_are_valid(
        String schema, String domain, String topDomain, 
        String port, String path, String querySeparator, 
        String queryParameter, String fragment)
    {
        // (
        // schema , domain , topDomain ,
        // port , path , querySeparator ,
        // queryParameter , fragment
        // ); 
        String[] parts = {
            schema , domain , topDomain ,
            port , path , querySeparator ,
            queryParameter , fragment
            };
        String url = "";
        for (String s : parts){
            if(s != null){
                url += s;
            }
        };
        assertEquals(true, sut.isValid(url), url);
	}
}
