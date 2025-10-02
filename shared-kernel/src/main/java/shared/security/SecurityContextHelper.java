package shared.security;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {
    
//    public static String getCurrentUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            throw new SecurityException("No authentication present in SecurityContext");
//        }
//        return authentication.getName();
//    }
    
    public static AuthenticatedUser getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            throw new SecurityException("No authentication present in SecurityContext");
//        }
        
        // Assuming authentication principal contains necessary user details.
        // Implementation details would depend on how user details are stored in Authentication
        AuthenticatedUser user = new AuthenticatedUser();
        //user.setUsername(authentication.getName());
        // Set other user properties as needed
        return user;
    }
}