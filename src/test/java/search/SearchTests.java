package search;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.SearchPage;

import java.util.List;

import static org.testng.Assert.*;

public class SearchTests extends BaseTests {

    @Test
    public void searchForExactTitle(){
        String title = "Agile Testing";
        searchPage.search(title);
        assertEquals(searchPage.getNumberOfVisibleBooks(), 1, "Number of visible books");
        assertTrue(searchPage.getVisibleBooks().contains(title), "Title of visible book");
     }

     @Test
    public void searchForPartialTitle(){
        searchPage.search("Test");

        List<String> expectedBooks = List.of(
                "Test Automation in the Real World",
                "Experiences of Test Automation",
                "Agile Testing",
                "How Google Tests Software",
                "Java For Testers"
        );

         assertEquals(searchPage.getNumberOfVisibleBooks(), expectedBooks.size(), "Number of visible books");
         assertEquals(expectedBooks, searchPage.getVisibleBooks(), "Titles of visible books");
     }
}