package eu.gordaniil.rikexercisebe.helper;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class UserHelper {

    public static ApiUser getUserFromContext() {
        return new ApiUser("3f7c41d0-7a69-4f5f-8107-cc9d0183198c", "Daniil"); // THIS INFO SHOULD BE SET AT SPRING SECURITY AFTER VALIDATING JWT'S PAYLOAD OR SOME OTHER AUTHENTICATION METHOD.
    }

}
