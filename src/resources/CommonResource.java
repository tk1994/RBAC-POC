package resources;

import java.util.ArrayList;
import java.util.HashMap;

import actions.ResourceActions;
import actions.UserRoles;

public class CommonResource extends Resource {
	public CommonResource(int id, int data) {
		super(id, data);
		this.accessMap = new HashMap<actions.UserRoles, ArrayList<ResourceActions>>();
		ArrayList<ResourceActions> adminActions = new ArrayList<ResourceActions>(4);
		adminActions.add(0, ResourceActions.Create);
		adminActions.add(1, ResourceActions.Read);
		adminActions.add(2, ResourceActions.Update);
		adminActions.add(3, ResourceActions.Delete);
		this.accessMap.put(UserRoles.Admin, adminActions);

		ArrayList<ResourceActions> level1Actions = new ArrayList<ResourceActions>(4);
		level1Actions.add(0, ResourceActions.Create);
		level1Actions.add(1, ResourceActions.Read);
		level1Actions.add(2, ResourceActions.Update);
		level1Actions.add(3, ResourceActions.Delete);
		this.accessMap.put(UserRoles.Level1, level1Actions);
		
		ArrayList<ResourceActions> defaultActions = new ArrayList<ResourceActions>(4);
		defaultActions.add(0, ResourceActions.Create);
		defaultActions.add(1, ResourceActions.Read);
		defaultActions.add(2, ResourceActions.Update);
		defaultActions.add(3, ResourceActions.Delete);
		this.accessMap.put(UserRoles.Default, defaultActions);
	}
}
