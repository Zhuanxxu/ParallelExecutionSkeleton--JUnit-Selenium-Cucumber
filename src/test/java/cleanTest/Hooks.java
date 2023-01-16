package cleanTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BooksPage;

import java.util.ArrayList;
import java.util.List;


public class Hooks {
    private static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();
    public static ThreadLocal<Scenario> scenarios = new ThreadLocal<>();

    /**
     * drivers.set(driver); guarda en un ThreadLocal un RemoteWebDriver
     * Printea el Thread en el que esta y su Driver
     * @param driver
     */
    public static void addDriver(RemoteWebDriver driver) {

        drivers.set(driver);
        System.out.printf(
                "[Thread %2d] Created and Added Driver: [%s]%n",
                Thread.currentThread().getId(), Hooks.getDriver());
    }
    public static RemoteWebDriver getDriver() {
        return drivers.get();
    }

    /**
     * Ejecuta npm run report luego de los test para generar y abrir el reporte
     */
    @AfterAll
    public static void after_all() {
        try {
            String[] cmd = {"cmd.exe", "/c", "npm run report"};
            Runtime.getRuntime().exec(cmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Luego de cada scenario, tomo el driver de cada thread y lo cierro
     * Si fallo le saco una screenshoot
     * @param scenario
     * @throws Exception
     */
    @After
    public void cleanup(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        RemoteWebDriver driver = getDriver();
        driver.quit();
    }

    /**
     *  WebDriverManager.chromedriver().setup(); inicia el WebDriverManger para poder hacer new Chromedriver sin tener el archivo
     *  chrome.exe y sin indicarle el path al archivo
     *  ChromeOptions para indicarle que inicie maximizado
     *  Hooks.addDriver(new ChromeDriver(options)); inicializa el chorme driver con la opcion ventana maximizada y la agrega a la lista
     *  de drivers
     * @throws Exception
     */
    @Before
    public void setUp(Scenario scenario) throws Exception {
        scenarios.set(scenario);
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions().addArguments("start-maximized");
        Hooks.addDriver(new ChromeDriver(options));
    }
}
