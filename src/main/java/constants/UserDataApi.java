package constants;

import org.apache.commons.lang3.RandomStringUtils;
import pojos.UserRequest;

public class UserDataApi {
    public static UserRequest getUniqueUser() {
        return new UserRequest(
                RandomStringUtils.randomAlphanumeric(10) + "@test.com",
                RandomStringUtils.randomAlphanumeric(9),
                RandomStringUtils.randomAlphanumeric(8)
        );
    }
}
