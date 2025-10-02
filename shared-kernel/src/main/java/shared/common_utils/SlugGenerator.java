package shared.common_utils;

public class SlugGenerator {
    public static String generateSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        // Convert to lowercase
        String slug = input.toLowerCase();
        // Replace spaces with hyphens
        slug = slug.replaceAll("\\s+", "-");
        // Remove all non-alphanumeric characters except hyphens
        slug = slug.replaceAll("[^a-z0-9-]", "");
        // Remove leading and trailing hyphens
        slug = slug.replaceAll("^-|-$", "");
        return slug;
    }
}
