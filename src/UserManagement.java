import java.util.Scanner;

public class UserManagement {
    
    int idUser;
    String name;
    String lastName;
    String password = "Darian0110";
    String email = "ram.darian@gmail.com";
    int role;
    boolean isRoleValid = false;
    boolean isEmailValid = false;
    boolean isPasswordValid = false;

    User user = new User(name, password, email, lastName);
    Scanner scanner = new Scanner(System.in);

    public void register(String email, String name, String lastName, String password) {

        UserValidation userValidation = new UserValidation();

        System.out.println("**********************************");
        System.out.println("Welcome to our user management system");
        System.out.println("**********************************");
        System.out.println("To register, please provide the following information: ");
 
        System.out.print("Name: ");
        name = scanner.nextLine();
        
        System.out.print("Lastname: ");
        lastName = scanner.nextLine();
       
        System.out.print("Enter your id (must begin with the current year): ");
        idUser = scanner.nextInt();
        scanner.nextLine(); 

        while (!isEmailValid) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            try {
                userValidation.emailValidation(email);  
                isEmailValid = true;  
                System.out.println("Email successfully validated and stored.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email address. Please enter a valid email domain.");
            }
        }
     
        while (!isRoleValid) {
            System.out.println("Select your role: ");
            System.out.println("1- User");
            System.out.println("2- Moderator");
            System.out.println("3- Administrator");
            role = scanner.nextInt();
            scanner.nextLine();

            switch (role) {
                case 1:
                    System.out.println("You selected the user role.");
                    isRoleValid = true;
                    break;
                case 2:
                    System.out.println("You selected the moderator role.");
                    isRoleValid = true;
                    break;
                case 3:
                    System.out.println("You selected the administrator role.");
                    isRoleValid = true;
                    break;
                default:
                    System.out.println("ERROR, please select an available role.");
            }
        }


        while (!isPasswordValid) {
            System.out.print("Password: ");
            password = scanner.nextLine();
            try {
                userValidation.passwordValidation(password);  
                isPasswordValid = true;  
                System.out.println("Password successfully validated.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid password. Please ensure it meets all the requirements.");
            }
        }
        
        
        System.out.println("You have successfully registered.");
    }
}

	
