package resources;

import java.util.*;

import actions.*;

public class SystemResource extends Resource {
	public SystemResource(int id, int data) {
		super(id, data);
		this.accessMap = new HashMap<actions.UserRoles, ArrayList<ResourceActions>>();
		ArrayList<ResourceActions> adminActions = new ArrayList<ResourceActions>(4);
		adminActions.add(0, ResourceActions.Create);
		adminActions.add(1, ResourceActions.Read);
		adminActions.add(2, ResourceActions.Update);
		adminActions.add(3, ResourceActions.Delete);
		this.accessMap.put(UserRoles.Admin, adminActions); 
	}
}
