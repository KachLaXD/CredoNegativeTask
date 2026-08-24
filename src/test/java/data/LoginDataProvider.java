package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidCredentials")
    public static Object[][] invalidCredentials() {
        return new Object[][]{
                {
                        "",
                        "",
                        "Both fields empty"
                },
                {
                        RandomStringUtils.randomAlphabetic(10),
                        "",
                        "Password empty"
                },
                {
                        "",
                        RandomStringUtils.randomAlphanumeric(10),
                        "Username empty"
                },
                {
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        "Incorrect credentials"
                }
        };
    }
}
