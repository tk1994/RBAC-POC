import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

import resources.*;
import users.*;
import actions.*;

public class SystemMemoryClass {
	static HashMap<Integer, Resource> resources = new HashMap<Integer, Resource>();
	static HashMap<Integer, User> users = new HashMap<Integer, User>();
	static int loggedInUserId = -1;
	static int userIdCount = 0;
	static int resourceIdCount = 0;
	
	public static int addUser(ArrayList<UserRoles> roles) {
		if(loggedInUserId == -1 || users.get(loggedInUserId).roles.contains(UserRoles.Admin)) {
			User newUser = new User(userIdCount, roles);
			userIdCount++;
			users.put(newUser.id, newUser);
			return newUser.id;
		} else {
			return -1;
		}
	}
	
//	public static ArrayList<UserRoles> readUser(int id) {
//		if(users.containsKey(id)) {
//			return users.get(id).roles;
//		} else {
//			return (new ArrayList<UserRoles>());
//		}
//	}
	
	public static void grantRole(int id, UserRoles role) {
		if(users.get(loggedInUserId).roles.contains(UserRoles.Admin)) {
			if(users.containsKey(id)) {
				if(!users.get(id).roles.contains(role)) {
					users.get(id).roles.add(role);
				}
			}
		} else {
			System.out.println("permission denied");
		}
	}
	
	public static void revokeRole(int id, UserRoles role) {
		if(users.get(loggedInUserId).roles.contains(UserRoles.Admin)) {
			if(users.containsKey(id)) {
				if(users.get(id).roles.contains(role)) {
					users.get(id).roles.remove(role);
					if(users.get(id).roles.isEmpty()) {
						users.get(id).roles.add(UserRoles.Default);
					}
				}
			}
		} else {
			System.out.println("permission denied");
		}
	}
	
	public static void deleteUser(int id) {
		if(users.get(loggedInUserId).roles.contains(UserRoles.Admin)) {
			if(users.containsKey(id)) {
				users.remove(id);
			}
			if(id == loggedInUserId) {
				loggedInUserId = -1;
				System.out.println("deleted self, logeed out");
			}
		} else {
			System.out.println("permission denied");
		}
	}
	
	public static int addResource(int data, int type) {
		Resource rs;
		switch(type) {
		case 1:
			rs = new SystemResource(resourceIdCount, data);
			break;
		case 2:
			rs = new Level1Resource(resourceIdCount, data);
			break;
		case 3:
			rs = new CommonResource(resourceIdCount, data);
			break;
		default:
			return -1;
		}
		ArrayList<UserRoles> currentRoles = users.get(loggedInUserId).roles;
		for(int i = 0; i < currentRoles.size(); i++) {
			if(rs.accessMap.containsKey(currentRoles.get(i)) && rs.accessMap.get(currentRoles.get(i)).contains(ResourceActions.Create)) {
				resources.put(rs.id, rs);
				resourceIdCount++;
				return rs.id;
			}
		}
		return -1;
	}
	
	public static void updateResource(int id, int data) {
		if(resources.containsKey(id)) {
			Resource rs = resources.get(id);
			ArrayList<UserRoles> currentRoles = users.get(loggedInUserId).roles;
			for(int i = 0; i < currentRoles.size(); i++) {
				if(rs.accessMap.containsKey(currentRoles.get(i)) && rs.accessMap.get(currentRoles.get(i)).contains(ResourceActions.Create)) {
					rs.data = data;
					return;
				}
			}
			System.out.println("permission denied");
		} else {
			System.out.println("resource doesnt exist");
		}
	}
	
	public static int readResource(int id) {
		if(resources.containsKey(id)) {
			Resource rs = resources.get(id);
			ArrayList<UserRoles> currentRoles = users.get(loggedInUserId).roles;
			for(int i = 0; i < currentRoles.size(); i++) {
				if(rs.accessMap.containsKey(currentRoles.get(i)) && rs.accessMap.get(currentRoles.get(i)).contains(ResourceActions.Create)) {
					return rs.data;
				}
			}
			System.out.println("permission denied");
			return -1;
		} else {
			System.out.println("resource doesnt exist");
			return -1;
		}
	}
	
	public static void deleteResource(int id) {
		if(resources.containsKey(id)) {
			Resource rs = resources.get(id);
			ArrayList<UserRoles> currentRoles = users.get(loggedInUserId).roles;
			for(int i = 0; i < currentRoles.size(); i++) {
				if(rs.accessMap.containsKey(currentRoles.get(i)) && rs.accessMap.get(currentRoles.get(i)).contains(ResourceActions.Create)) {
					resources.remove(id);
					return;
				}
			}
			System.out.println("permission denied");
			return;
		} else {
			System.out.println("resource doesnt exist");
			return;
		}
	}
	
	public static void listAllUsers() {
		for (HashMap.Entry<Integer,User> entry : users.entrySet())  
            System.out.println("id : " + entry.getKey() + ", Roles : " + entry.getValue().roles); 
	}
	
	public static void listAllResources() {
		for (HashMap.Entry<Integer,Resource> entry : resources.entrySet())  
            System.out.println("id : " + entry.getKey()); 
	}
	
	public static void logoutUser() {
		loggedInUserId = -1;
		return;
	}
	
//	SystemMemoryClass() {
//		this.users.put(0, )
//		
//	}
}
