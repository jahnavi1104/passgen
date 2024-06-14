public class Main {
    public static void main(String[] args) {
        try {
            // Generate a random password
            String password = PasswordGenerator.generatePassword(12);
            System.out.println("Generated Password: " + password);

            // Encrypt the generated password
            SecretKey key = PasswordEncryptor.generateAESKey();
            String encryptedPassword = PasswordEncryptor.encrypt(password, key);
            System.out.println("Encrypted Password: " + encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
