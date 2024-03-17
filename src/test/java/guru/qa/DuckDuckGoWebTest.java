package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {

    @BeforeEach
    void setUp() {
        open("https://www.duckduckgo.com/");
    }

    @ValueSource(strings = {
            "Selenide", "JUnit 5"
    })

    @ParameterizedTest(name = "The list of search {0} should not be empty")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

//    @CsvSource(value = {
//            "Selenide , https://selenide.org",
//            "JUnit 5 , https://junit.org"
//    })
    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedUrl.csv")

    @ParameterizedTest(name = "The list of search {0} should have {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("The list of search 'junit5' should not be empty")
    void successfulSearchJUnitTest() {
        $("#searchbox_input").setValue("junit 5").pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("The list of search for 'selenide' images should not be empty")
    void successfulSearchPhotoTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();
        $("[data-zci-link='images']").click();
        $$("img.tile--img__img")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}
