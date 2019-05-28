import java.util.ArrayList;
import java.util.Scanner;

import actions.*;
import resources.*;
import users.*;

public class CoreSystemClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Creating Data Structures and Memory facilities");
		System.out.println("System Started: creating admin user with id 0");
		ArrayList<UserRoles> newRoles = new ArrayList<UserRoles>();
		newRoles.add(UserRoles.Admin);
		int adminId = SystemMemoryClass.addUser(newRoles);
		System.out.println("Loggin you in as user 0 : admin");
		SystemMemoryClass.loggedInUserId = adminId;
		while(true) {
			if(SystemMemoryClass.loggedInUserId == -1) {
				int id = showLoginPrompt();
				if(id != -1) {
					SystemMemoryClass.loggedInUserId = id;
				}
			} else {
				int choice = SystemMemoryClass.users.get(SystemMemoryClass.loggedInUserId).showPrompt();
				switch(choice) {
				case 1:
					System.out.println("enter type of resource");
					System.out.println("1. System Resource 2. Level 1 Resource 3. Common Resource");
					int type = sc.nextInt();
					System.out.println("enter data for Resource:");
					int data = sc.nextInt();
					int id = SystemMemoryClass.addResource(data, type);
					if(id == -1) {
						System.out.println("permission denied");
					} else {
						System.out.println("Resource created with id: " + id);
					}
					break;
				case 2:
					System.out.println("enter resource id:");
					int readId = sc.nextInt();
					int readData = SystemMemoryClass.readResource(readId);
					if(readData != -1) {
						System.out.println(readData);
					}
					break;
				case 3:
					System.out.println("enter resource id:");
					int updateId = sc.nextInt();
					System.out.println("enter new data:");
					int newData = sc.nextInt();
					SystemMemoryClass.updateResource(updateId, newData);
					break;
				case 4:
					System.out.println("enter resource id:");
					int deleteId = sc.nextInt();
					SystemMemoryClass.deleteResource(deleteId);
					break;
				case 5:
					ArrayList<UserRoles> newUserRoles = new ArrayList<UserRoles>();
					newRoles.add(UserRoles.Default);
					int newUserId = SystemMemoryClass.addUser(newUserRoles);
					if(newUserId == -1) {
						System.out.println("permission denied");
					} else {
						System.out.println("new user created with default role and id: " + newUserId);
					}
					break;
				case 6:
					System.out.println("enter user id:");
					int updateUserId = sc.nextInt();
					System.out.println("1. grant role 2. revoke role");
					int userUpdateChoice = sc.nextInt();
					System.out.println("1. admin 2. level 1 user 3. default");
					int userRoleUpdateChoice = sc.nextInt();
					UserRoles userUpdateRole;
					switch(userRoleUpdateChoice) {
					case 1:
						userUpdateRole = UserRoles.Admin;
						break;
					case 2:
						userUpdateRole = UserRoles.Level1;
						break;
					case 3:
						userUpdateRole = UserRoles.Default;
						break;
					default:
						userUpdateRole = UserRoles.Default;
						break;
					}
					if(userUpdateChoice == 1) {
						SystemMemoryClass.grantRole(updateUserId, userUpdateRole);
					} else {
						SystemMemoryClass.revokeRole(updateUserId, userUpdateRole);
					}
					break;
				case 7:
					System.out.println("enter user id:");
					int deleteUserId = sc.nextInt();
					SystemMemoryClass.deleteResource(deleteUserId);
					break;
				case 8:
					SystemMemoryClass.listAllUsers();
					break;
				case 9:
					SystemMemoryClass.listAllResources();
					break;
				case 10:
					SystemMemoryClass.logoutUser();
					break;
				default:
					break;
				}
			}
		}
	}
	
	static int showLoginPrompt() {
		int inp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter User id  to login, enter -1 to print all users, -2 for all resources");
		inp = sc.nextInt();
		if(inp == -1) {
			SystemMemoryClass.listAllUsers();
			return -1;
		} else if(inp == -2) {
			SystemMemoryClass.listAllResources();
			return -1;
		}
		else if(SystemMemoryClass.users.containsKey(inp)) {
			return inp;
		} else {
			return -1;
		}
	}
}
