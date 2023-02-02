package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessSignInSignUpResponse {
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private NestedUserInfo user;
}
