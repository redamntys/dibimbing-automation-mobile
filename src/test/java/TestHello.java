import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

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
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    //@Test
    public void swipe(){
        Dimension size = driver.manage().window().getSize();

        System.out.println("Dimension:" + size);

        int startX = size.getWidth() /2;
        System.out.println("startX:" + startX);

        int startY = size.getHeight()/ 2;
        System.out.println("startY:" + startY);

        int endY = (int) (size.getHeight() * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(0),
                        PointerInput.Origin.viewport(),startX,startY
                )
        );
        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );
        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),startX,endY
                )
        );
        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );
        driver.perform(Collections.singleton(swipe));

        // Demo propose only
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //@Test
    public void tapByCoordinate(){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ofSeconds(0),
        PointerInput.Origin.viewport(),
          1001, 191
            )
        );
        tap.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
                )

        );
        tap.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()
                )
        );
        driver.perform(Arrays.asList(tap));
        // Demo propose only
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //@Test
    public void swipeUsingUIAutomator() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().text(\"Sauce Labs Onesie\"))"
                )
        );

        // Demo propose only
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSample() {
        assert driver.getSessionId() != null;
        System.out.println("Success create appium session");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100));

        WebElement ViewMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("View menu")
                )
        );

        ViewMenu.click();

        WebElement wvMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"WebView\")"
                        )
                )
        );

        wvMenu.click();

        driver.findElement(AppiumBy.accessibilityId("View menu")).click();
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"WebView\")"
                )
        ).click();
        driver.findElement(
                AppiumBy.id(
                        "com.saucelabs.mydemoapp.android:id/urlET"
                )
        ).sendKeys("dikacore.dev");
        driver.findElement(
                AppiumBy.accessibilityId("Tap to view content of given url")
        ).click();

// Demo propose only
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<String> contexts = driver.getContextHandles();

        for (String context : contexts) {
            if (context.contains("WEBVIEW_")) {
                System.out.println("That webview");
                driver.context(context);
                System.out.println("Success switch to webview");
            } else {
                System.out.println("That native");
                System.out.println("next loops");
            }
        }

        String title = driver.getTitle();
        System.out.println("Title: " + title);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Session closed");
        }
    }
}