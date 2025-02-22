import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String name = "";
		String lastName = "";
		String email = "";
		String password = "";
		
		
		Scanner scanner = new Scanner(System.in);
		UserManagement userManagement = new UserManagement();
		
	
		System.out.println("***************************");
		System.out.println("Welcome to our user management system!");
		System.out.println("***************************");
		System.out.println("What would you like to do today? ");
		System.out.println("1- Sign in");
		System.out.println("2- Log in");
		int option = scanner.nextInt();
		
		switch(option) {
		
		case 1: userManagement.register();
		case 2: userManagement.LogIn();
		}
		
		
		
	}

}
