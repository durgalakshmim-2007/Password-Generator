
import java.security.SecureRandom;
import java.util.*;

public class PasswordGenerator {

    static Scanner sc = new Scanner(System.in);
    static SecureRandom random = new SecureRandom();

    static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    static final String NUMBERS = "0123456789";
    static final String SPECIAL = "!@#$%^&*";

    public static void main(String[] args) {

        System.out.println("===== SECURE PASSWORD GENERATOR =====");

        boolean again = true;

        while (again) {
            generatePassword();

            System.out.print("\nGenerate another password? (yes/no): ");
            again = sc.next().equalsIgnoreCase("yes");
        }

        System.out.println("\nThank you for using Password Generator!");
        sc.close();
    }

    static void generatePassword() {

        System.out.print("\nEnter password length: ");
        int length = sc.nextInt();

        String characters = "";
        ArrayList<Character> password = new ArrayList<>();

        System.out.print("Include uppercase? (yes/no): ");
        if (sc.next().equalsIgnoreCase("yes")) {
            characters += UPPER;
            password.add(randomChar(UPPER));
        }

        System.out.print("Include lowercase? (yes/no): ");
        if (sc.next().equalsIgnoreCase("yes")) {
            characters += LOWER;
            password.add(randomChar(LOWER));
        }

        System.out.print("Include numbers? (yes/no): ");
        if (sc.next().equalsIgnoreCase("yes")) {
            characters += NUMBERS;
            password.add(randomChar(NUMBERS));
        }

        System.out.print("Include special characters? (yes/no): ");
        if (sc.next().equalsIgnoreCase("yes")) {
            characters += SPECIAL;
            password.add(randomChar(SPECIAL));
        }

        if (characters.isEmpty()) {
            System.out.println("Please select at least one character type!");
            return;
        }

        if (length < password.size() || length < 1) {
            System.out.println("Invalid password length!");
            return;
        }

        while (password.size() < length) {
            password.add(randomChar(characters));
        }

        Collections.shuffle(password, random);

        StringBuilder result = new StringBuilder();

        for (char ch : password) {
            result.append(ch);
        }

        System.out.println("\nGenerated Password: " + result);
        System.out.println("Password Length: " + result.length());
        System.out.println("Password Strength: " + checkStrength(result.toString()));
    }

    static char randomChar(String characters) {
        return characters.charAt(random.nextInt(characters.length()));
    }

    static String checkStrength(String password) {

        int score = 0;

        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[^a-zA-Z0-9].*")) score++;

        if (score >= 5) return "Strong";
        if (score >= 3) return "Medium";

        return "Weak";
    }
}
