package pages;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;

public class SearchPage {

    private Page page;

    private String locator_searchBar = "#searchBar";
    private String locator_hiddenBooks = "li.ui-screen-hidden";
    private String locator_visibleBooks = "li:not(.ui-screen-hidden)";
    private String locator_visibleBookTitles = "li:not(.ui-screen-hidden) h2";


    public SearchPage(Page page){
        this.page = page;
    }

    public void search(String query) {
        clearSearchBar();
        page.fill(locator_searchBar, query);

        var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
        page.waitForSelector(locator_hiddenBooks,expectedState);
    }

    public void clearSearchBar(){
        page.fill(locator_searchBar, "");

        var expectedState = new Page.WaitForSelectorOptions().withState(DETACHED);
        page.waitForSelector(locator_hiddenBooks,expectedState);
    }

    public int getNumberOfVisibleBooks(){
       return page.querySelectorAll(locator_visibleBooks).size();
    }

    public List<String> getVisibleBooks(){
        return page.querySelectorAll(locator_visibleBookTitles)
                .stream()
                .map(e -> e.innerText())
                .collect(Collectors.toList());
    }
}