package shared.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUser {
    private String username;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
