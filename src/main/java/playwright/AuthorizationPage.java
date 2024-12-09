package playwright;

import com.google.inject.Inject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

public class AuthorizationPage extends AbstractPage {

    private final PlaywrightManagement playwrightManagement;

   @Inject
    public AuthorizationPage(PlaywrightManagement playwrightManagement) {
        this.playwrightManagement = playwrightManagement;
    }

    public Locator loginField() {
        return getByAriaRole(AriaRole.TEXTBOX, "Username");
    }

    public Locator passwordField() {
        return getByAriaRole(AriaRole.TEXTBOX, "Password");
    }

    public Locator buttonLogin() {
        return getByAriaRole(AriaRole.BUTTON, "Login");
    }

    public Locator errorMessage() {
        return getByAriaRole(AriaRole.HEADING, "Epic sadface: Username and password do not match any user in this service");
    }

    public Locator pageFooter() {
        return playwrightManagement.getPage().locator("#page_wrapper > footer > div");
    }

    @Step("Авторизация")
    public void UserLogin(String name, String pass) {
        Page page = playwrightManagement.getPage();

        page.waitForSelector("#user-name");
        page.waitForSelector("#password");
        page.locator("#user-name").fill(name);
        page.locator("#password").fill(pass);
        page.locator("#login-button").click();
    }

    @Step("Проверка открытия страницы")
    public void loggedIn() {
        pageFooter().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
}