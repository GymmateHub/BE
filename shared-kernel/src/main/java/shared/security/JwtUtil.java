package shared.security;

public class JwtUtil {
    public static String generateToken(AuthenticatedUser authenticatedUser) {
        return null;
    }

    public static AuthenticatedUser getAuthenticatedUser(String token) {
        return null;
    }
     public static boolean validateToken(String token) {
        return false;
     }

     public static String getUsernameFromToken(String token) {
        return null;
     }

     public static String getRoleFromToken(String token) {
        return null;
     }

    public static String parse(String token) {
        return token.replace("Bearer ", "");
    }
}
