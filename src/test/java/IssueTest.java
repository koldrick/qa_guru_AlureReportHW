
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueTest {
    private static final String REPOSITORY = "qa-guru/qa_guru_14_10";

    @Test
    void testIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys("qa_guru_14_10");
        $("#query-builder-test").pressEnter();
        $(By.linkText("qa-guru/qa_guru_14_10")).click();
        $("#issues-tab").click();
        $("#issue_2").shouldHave(text("Issue for Autotest"));

    }

    @Test
    void testLambdaIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });
        step("Открываем репозитторий" + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем есть ли там Issue for Autotest", () -> {

            $("#issue_2").shouldHave(text("Issue for Autotest"));
        });

    }

    @Test
    void testAnnotatedStepIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.findRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openTabIssue();
        steps.checkResult();
    }
}
