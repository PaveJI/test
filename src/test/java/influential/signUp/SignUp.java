package influential.signUp;

import influential.WebDriverSettings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class SignUp extends WebDriverSettings {

    @Test
    public void signUp(){

        driver.manage().window().maximize();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open();
        homePage.getStarted();

        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.openSignUpForm();

        driver.findElement(By.id("name-first")).sendKeys("Pavel");
        driver.findElement(By.id("name-last")).sendKeys("Kipriyanov");

        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        String myemail = "kipriyanov_pavel" + n + "@gmail.com";
        String username = "KipriyanovPavel" + n;

        driver.findElement(By.id("email")).sendKeys(myemail);

        driver.findElement(By.id("company-name")).sendKeys("FullHDTV");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("parol123");
        driver.findElement(By.id("password-confirm")).sendKeys("parol123");
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-complete")));

        System.out.println("Тест на регистрацию выполнен, браузер закрыт");
    }

    @Test
    public void signUpFailure(){

//        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver245");
//        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://portal.dev.influential.co");
        WebElement header = driver.findElement(By.id("header"));
        header.findElement(By.cssSelector("[href=\"/signup\"]")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main-signup")));
        driver.findElement(By.cssSelector("[href=\"/signup/talent#form-anchor\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));

        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"error error-msg\"]")));

        WebElement nameFirst = driver.findElement(By.id("name-first"));
        WebElement nameFirstParent = nameFirst.findElement(By.xpath(".."));
        String nameFirstError = nameFirstParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(nameFirstError, "PLEASE ADD A FIRST NAME");

        WebElement nameLast = driver.findElement(By.id("name-last"));
        WebElement nameLastParent =  nameLast.findElement(By.xpath(".."));
        String nameLastError = nameLastParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(nameLastError, "PLEASE ADD A LAST NAME");

        WebElement email = driver.findElement(By.id("email"));
        WebElement emailParent = email.findElement(By.xpath(".."));
        String emailError = emailParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(emailError, "PLEASE ADD AN EMAIL ADDRESS");

        WebElement company = driver.findElement(By.id("company-name"));
        WebElement companyParent = company.findElement(By.xpath(".."));
        String errorCompany = companyParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(errorCompany, "PLEASE ADD A COMPANY NAME");

        WebElement userName = driver.findElement(By.id("username"));
        WebElement userNameParent = userName.findElement(By.xpath(".."));
        String userNameError = userNameParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(userNameError, "PLEASE ADD A USERNAME");

        WebElement password = driver.findElement(By.id("password"));
        WebElement passwordParent = password.findElement(By.xpath(".."));
        String passwordError = passwordParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(passwordError, "PLEASE ADD A PASSWORD");

        WebElement confirmPassword = driver.findElement(By.id("password-confirm"));
        WebElement confirmPasswordParent = confirmPassword.findElement(By.xpath(".."));
        String confirmPasswordError = confirmPasswordParent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(confirmPasswordError, "PLEASE CONFIRM YOUR PASSWORD");

        System.out.println("Тест завершился успешно и браузер закрыт");

    }


}
