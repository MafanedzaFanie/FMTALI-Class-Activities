import java.util.Scanner;

public class SecretCodeNameGenerator{
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ask the user to enter their full name
            System.out.print("Enter your full name (or type 'exit' to quit): ");
            String fullName = scanner.nextLine();

            // Exit loop if user types "exit"
            if (fullName.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye agent.");
                break;
            }

            // Step 1: Trim leading/trailing spaces and convert to lowercase
            fullName = fullName.trim().toLowerCase();

            // Step 2: Split full name into first and last names
            String[] parts = fullName.split(" ");
            if (parts.length < 2) {
                System.out.println("Please enter both a first and last name.");
                continue;
            }
            String firstName = parts[0];
            String lastName = parts[1];

            // Step 3: Capitalize first letter of each name
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

            // Step 4: Reverse the last name using StringBuilder
            StringBuilder reversedLastName = new StringBuilder(lastName).reverse();

            // Step 5: Combine first 2 letters of first name with reversed last name
            String codeNameBase = firstName.substring(0, Math.min(2, firstName.length())) + reversedLastName.toString();

            // Step 6: Add special ending
            StringBuilder finalCodeName = new StringBuilder(codeNameBase);
            char lastChar = codeNameBase.charAt(codeNameBase.length() - 1);
            if (codeNameBase.contains("x") || "aeiouAEIOU".indexOf(lastChar) >= 0) {
                finalCodeName.append("X-Agent");
            } else {
                finalCodeName.insert(codeNameBase.length() / 2, "-007");
            }

            // Step 9: Compare first and last names alphabetically
            int comparison = firstName.compareTo(lastName);
            String comparisonResult;
            if (comparison < 0) {
                comparisonResult = "First name comes before last name alphabetically.";
            } else if (comparison > 0) {
                comparisonResult = "Last name comes before first name alphabetically.";
            } else {
                comparisonResult = "First name and last name are alphabetically equal.";
            }

            // Step 10: Ask for mode and stylize name accordingly
            System.out.print("Choose your mode [Hero/Villain]: ");
            String mode = scanner.nextLine().trim().toLowerCase();

            if (mode.equals("villain")) {
                finalCodeName = new StringBuilder(finalCodeName.toString().toUpperCase().replace('A', '@').replace('E', '3'));
            } else {
                finalCodeName = new StringBuilder("Captain " + finalCodeName);
            }

            // Step 7: Display final result
            System.out.println(String.format("\nYour secret code name is: %s", finalCodeName));
            System.out.println(comparisonResult);
            System.out.println("––––––––––––––––––––––––––––––––––––––\n");
        }

        scanner.close();
    }

}