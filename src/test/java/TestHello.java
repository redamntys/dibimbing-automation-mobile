import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestHello {
    private AndroidDriver driver;

    @BeforeClass
    public void setup() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("RR8W205Z6AE")
                .setApp("/Users/reda/IdeaProjects/Java/automation-appium/apk/mda-2.2.0-25.apk");

        try {
            URL appiumServer = new URL("http://127.0.0.1:4723");
            driver = new AndroidDriver(appiumServer, options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSample() {
        assert driver.getSessionId() != null;
        System.out.println("Success create appium session");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Session closed");
        }
    }
}