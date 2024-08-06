import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }
    @Step("Ищем репозиторий")
    public void findRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();
    }
    @Step("Открываем репозитторий")
    public void openRepository(String repo) {
        $(By.linkText(repo)).click();
    }
    @Step("Открываем таб issues")
    public void openTabIssue() {
        $("#issues-tab").click();
    }
    @Step("Проверяем есть ли там Issue for Autotest")
    public void checkResult() {
        $("#issue_2").shouldHave(text("Issue for Autotest"));
    }
}
