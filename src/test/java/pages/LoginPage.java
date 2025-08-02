package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By accountButton = By.id("navbarAccount");
    private By loginNavButton = By.id("navbarLoginButton");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");
    private By logoutButton = By.id("navbarLogoutButton");
    private By forgotPasswordLink = By.linkText("Forgot your password?");
    private By resetEmailField = By.id("email");
    private By forgotPasswordHeading = By.xpath("//h1[text()='Forgot Password']");
    private By resetButton = By.id("resetButton");
    private By snackBar = By.xpath("//div[contains(@class, 'error') and text()='Invalid email or password.']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginForm() {
        driver.findElement(accountButton).click();
        driver.findElement(loginNavButton).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLogoutDisplayed() {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(accountButton).click();
        return driver.findElement(logoutButton).isDisplayed();
    }

    public String getSnackBarText() {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return driver.findElement(snackBar).getText();
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }

    public void resetPassword(String email) {
        driver.findElement(resetEmailField).clear();
        driver.findElement(resetEmailField).sendKeys(email);
        // driver.findElement(resetButton).click();
    }
    
    public void verifyForgotPasswordPage() {
        try {
            if (driver.findElement(forgotPasswordHeading).isDisplayed()) {
                System.out.println("User is on the Forgot Password page.");
            }
        } catch (Exception e) {
            System.out.println("Forgot Password heading is not displayed. User may not be on the correct page.");
        }
    }
}
