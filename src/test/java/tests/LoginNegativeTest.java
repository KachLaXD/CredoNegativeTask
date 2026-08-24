package tests;

import base.BaseTest;
import data.LoginDataProvider;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

@Feature("MyCredo Authentication")
public class LoginNegativeTest extends BaseTest {

    @Test(
            dataProvider = "invalidCredentials",
            dataProviderClass = LoginDataProvider.class
    )
    public void invalidCredentialsTest(
            String username,
            String password,
            String scenario
    ) {
        LoginPage page = new LoginPage(driver);

        page.login(username, password);

        SoftAssert softAssert = new SoftAssert();

        if (username.isEmpty() && password.isEmpty()) {

            softAssert.assertTrue(
                    page.isFirstErrorDisplayed(),
                    "First validation error should be displayed"
            );

            softAssert.assertTrue(
                    page.isSecondErrorDisplayed(),
                    "Second validation error should be displayed"
            );

        } else if (username.isEmpty() || password.isEmpty()) {

            softAssert.assertTrue(
                    page.isFirstErrorDisplayed(),
                    "Validation error should be displayed"
            );

        } else {

            softAssert.assertTrue(
                    page.isAlertDisplayed(),
                    "Incorrect credentials alert should be displayed"
            );

            String alertText = page.getAlertText();

            softAssert.assertTrue(
                    alertText.contains("მონაცემები არასწორია"),
                    "Expected 'მონაცემები არასწორია', but got: " + alertText
            );
        }

        softAssert.assertAll();
    }
}
