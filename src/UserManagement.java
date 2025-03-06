import java.util.Scanner;

public class UserManagement {
    
    int idUser;
    String name;
    String lastName;
    String password;
    String email;
    String role;
    String hashedPassword;
    boolean isValid = false;
    boolean isPasswordValid = false;

    Scanner scanner = new Scanner(System.in);
    DataBase db = new DataBase();

    public void register() {
        UserValidation userValidation = new UserValidation();

        System.out.println("To register, please provide the following information: ");
 
        while (!isValid) {
            System.out.print("Name: ");
            name = scanner.nextLine();
            System.out.print("Lastname: ");
            lastName = scanner.nextLine();
           
            System.out.print("Enter your id (must begin with the current year): ");
            idUser = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.print("Email: ");
            email = scanner.nextLine();
            try {
                userValidation.emailValidation(email);   
                System.out.println("Email successfully validated and stored.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email address. Please enter a valid email domain.");
                continue; 
            }

            System.out.println("Please select your role: ");
            System.out.println("1- User");
            System.out.println("2- Moderator");
            System.out.println("3- Administrator");

            int roleOption = scanner.nextInt();
            scanner.nextLine(); 

            switch (roleOption) {
                case 1:role = "User";
                break;
                case 2: role = "Moderator";
                break;
                case 3: role = "Administrator";
                break;
                default:
                    System.out.println("Invalid role selection. Please select 1, 2, or 3.");
                    continue; 
            }

            isValid = true; 
        }
        
        

        while (!isPasswordValid) {
            System.out.print("Password: ");
            password = scanner.nextLine();
            try {
                userValidation.passwordValidation(password);
               hashedPassword = userValidation.hashpassword(password);
                isPasswordValid = true;  
                System.out.println("Password successfully validated.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid password. Please ensure it meets all the requirements.");
            }
        }

        
        db.insertUser(name, lastName, email, hashedPassword, idUser);
        db.insertRole(role);

        System.out.println("You have successfully registered.");
    }
    
    public void LogIn() {
    	
    	UserValidation userValidation = new UserValidation();
    	
        System.out.println("To Log in, please provide the following information: ");
    	
    	System.out.print("Email: ");
    	String email = scanner.nextLine();
    	
    	System.out.print("Password: ");
    	String password = scanner.nextLine();
    	
    	db.LogIn(email, password);
    	hashedPassword = userValidation.hashpassword(password);
    	userValidation.viewPassword(password, hashedPassword);
    	db.LogInHistory();
    	
    	System.out.println("Successful Log in.");

    	
    }
    
    
    public void LogOut() {
}
}

    


	
