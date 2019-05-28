package resources;

import java.util.ArrayList;
import java.util.HashMap;

import actions.ResourceActions;
import actions.UserRoles;

public class Level1Resource extends Resource {
	public Level1Resource(int id, int data) {
		super(id, data);
		this.accessMap = new HashMap<actions.UserRoles, ArrayList<ResourceActions>>();
		ArrayList<ResourceActions> adminActions = new ArrayList<ResourceActions>(4);
		adminActions.add(0, ResourceActions.Create);
		adminActions.add(1, ResourceActions.Read);
		adminActions.add(2, ResourceActions.Update);
		adminActions.add(3, ResourceActions.Delete);
		this.accessMap.put(UserRoles.Admin, adminActions);

		ArrayList<ResourceActions> level1Actions = new ArrayList<ResourceActions>(2);
		level1Actions.add(0, ResourceActions.Read);
		level1Actions.add(1, ResourceActions.Update);
		this.accessMap.put(UserRoles.Level1, level1Actions);
	}
}
