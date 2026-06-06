package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.XPage;
import org.testng.annotations.Test;

public class XTest extends BaseTest {
    @Test
    public void xTestMenu() {
        XPage xPage = new XPage(DriverManager.getDriver());

        xPage.clickViewMenu();
    }
}