package playwright;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Selectors;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;
import playwright.PlaywrightManagement;

public class AbstractPage {

    @Inject
    private ShoppingPage shoppingPage;
    @Inject
    private AuthorizationPage authorizationPage;
    @Inject
    private PlaywrightManagement playwrightManagement;
    @Inject
    private Page page;
    @Inject
    private CheckoutPage checkoutPage;

    public Locator getByLocator(String selector) {
        return playwrightManagement.getPage().locator(selector);
    }

    public Locator getByAriaRole(AriaRole role, String selector) {
        return playwrightManagement.getPage().getByRole(role, new Page.GetByRoleOptions().setName(selector));
    }

    public void reloadPage() {
        playwrightManagement.getPage().reload();
    }

    public void click(Locator locator) {
        playwrightManagement.getPage().click(String.valueOf(locator));
    }

    @Step("Закрытие Playwright")
    public void closePlaywright() {
        PlaywrightManagement.close();
    }

    public Locator getByTextContain(String selector) {
        return playwrightManagement.getPage().getByText(selector,
                new Page.GetByTextOptions().setExact(false));
    }

    public Locator getByText(String selector) {
        return playwrightManagement.getPage().getByText(selector);
    }
}
