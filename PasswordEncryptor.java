import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

public class PasswordEncryptor {
    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int AES_KEY_SIZE = 256;
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(AES_KEY_SIZE);
        return keyGen.generateKey();
    }

    public static String encrypt(String password, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encryptedPassword = cipher.doFinal(password.getBytes());
        byte[] encryptedPasswordWithIv = new byte[GCM_IV_LENGTH + encryptedPassword.length];
        System.arraycopy(iv, 0, encryptedPasswordWithIv, 0, GCM_IV_LENGTH);
        System.arraycopy(encryptedPassword, 0, encryptedPasswordWithIv, GCM_IV_LENGTH, encryptedPassword.length);
        return Base64.getEncoder().encodeToString(encryptedPasswordWithIv);
    }

    public static void main(String[] args) {
        try {
            PasswordGenerator generator = new PasswordGenerator();
            String password = generator.generatePassword(12);
            System.out.println("Generated Password: " + password);

            SecretKey key = generateAESKey();
            String encryptedPassword = encrypt(password, key);
            System.out.println("Encrypted Password: " + encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
