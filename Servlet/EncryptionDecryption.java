import java.util.Base64;

public class EncryptionDecryption {

    // ===== Caesar Cipher =====
    public static String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                result.append((char) ((character - base + shift) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, 26 - shift); // Reverse shift
    }

    // ===== Vigenère Cipher =====
    public static String vigenereEncrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase(); // Convert key to lowercase
        int keyIndex = 0; // Key index for repeating key

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int shift = key.charAt(keyIndex % key.length()) - 'a'; // Determine the shift
                result.append((char) ((character - base + shift) % 26 + base)); // Apply the shift
                keyIndex++; // Move to the next character in the key
            } else {
                result.append(character); // Non-letter characters are added without change
            }
        }
        return result.toString();
    }

    public static String vigenereDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase(); // Convert key to lowercase
        int keyIndex = 0; // Key index for repeating key

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int shift = key.charAt(keyIndex % key.length()) - 'a'; // Determine the shift
                result.append((char) ((character - base - shift + 26) % 26 + base)); // Apply the shift
                keyIndex++; // Move to the next character in the key
            } else {
                result.append(character); // Non-letter characters are added without change
            }
        }
        return result.toString();
    }

    // ===== Base64 Encoding =====
    public static String base64Encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String base64Decode(String encodedText) {
        return new String(Base64.getDecoder().decode(encodedText));
    }
}