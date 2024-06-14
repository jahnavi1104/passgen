import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+<>?";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 1) throw new IllegalArgumentException("Length must be greater than 0");
        
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        String password = generatePassword(12);
        System.out.println("Generated Password: " + password);
    }
}
