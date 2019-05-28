package users;

import java.util.ArrayList;
import java.util.Scanner;

import actions.*;

public class User {
	public int id;
	public ArrayList<UserRoles> roles;
	
	public User(int id, ArrayList<UserRoles> roles) {
		this.id = id;
		if(roles.isEmpty()) {
			ArrayList<UserRoles> newRoles = new ArrayList<UserRoles>();
			newRoles.add(UserRoles.Default);
			this.roles = newRoles;
		} else {
			this.roles = roles;
		}
	}
	
	public int showPrompt() {
		System.out.println("Choose operation to perform:");
		System.out.println("1. Create Resource 2. Read Resource 3. Update Resource 4. Delete Resource");
		System.out.println("5. Create User 6. Update User Role 7. Delete User");
		System.out.println("8. List all users 9. List all resources 10. Logout");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		return choice;
		
	}
}
