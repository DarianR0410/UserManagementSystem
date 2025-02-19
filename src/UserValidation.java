
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidation {

	int idUser;
	String name;
	String lastName;
	String password;
	String email = "ram.darian@gmail.com";
	int role;
	
	User user = new User(name, password, email, lastName);
	
	
	public void emailValidation(String email) {
		
		String emailDomain = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		Pattern pattern = Pattern.compile(emailDomain, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		boolean isValid = matcher.matches();
		
		if(isValid) {
			System.out.println("Valid email format");
		} else {
			throw new IllegalArgumentException("Invalid email format.");
			
		}
		

		
	};
	
	public void passwordValidation(String password) {
		
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+]).{8,}$";

		Pattern patternPassword = Pattern.compile(regex);
		Matcher passwordMatches = patternPassword.matcher(password);
		
		boolean isValid = passwordMatches.matches();
		
		if(isValid) {
			System.out.println("Valid password");
		} else if (!isValid) {
			throw new IllegalArgumentException(
	        "This isn't a valid password format." +
	        "Password must contain:" +
	         "- At least one uppercase letter" +
	        "- At least one lowercase letter" +
	        "- At least one number" +
	       "- At least one special character (!@#$%^&*()_+)" +
	        "- At least 8 characters in length"
	       );
		}
				
	};
	
	
}
