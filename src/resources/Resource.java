package resources;

import java.util.HashMap;
import java.util.ArrayList;
import actions.*;

public class Resource {
	public int id;
	public int data;
	public HashMap<UserRoles, ArrayList<ResourceActions> > accessMap;
	
	Resource(int id, int data) {
		this.id = id;
		this.data = data;
	}
}
