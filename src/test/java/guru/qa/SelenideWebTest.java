package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {

    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectText(Language language) {
        open("https://ru.selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }

    static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.EN,
                        List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")
                ),
                Arguments.of(
                        Language.RU,
                        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")
                )
        );
    }

    @MethodSource
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }
}
