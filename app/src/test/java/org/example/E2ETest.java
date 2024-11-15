package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.regex.Pattern.compile;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class E2ETest {
    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }
    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = browser.newPage();
    }
    @Test
    void testGreeting(){
        page.navigate("http://localhost:%s/greeting".formatted(port));
        page.waitForURL("http://localhost:%s/greeting".formatted(port));
        String actualText = page.locator("//p[@id='greeting']").textContent();
        String expectedString = "Hello, World!";
        Assertions.assertEquals(expectedString, actualText);
    }

    @AfterEach
    void tearDown(){
        context.close();
    }

    @AfterAll
    static void closeBrowser(){
        playwright.close();
    }

}
