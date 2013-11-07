/**
 * 
 */
package com.finnsoft.identity.web.controller.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Permission;
import com.finnsoft.identity.core.model.Resource;
import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.service.Resources;
import com.finnsoft.identity.core.service.Roles;
import com.finnsoft.identity.web.controller.exception.WrongParameterTypeException;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class ListPermissionController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Resources resources;

	@Inject
	private Roles roles;

	private TreeNode root;

	private TreeNode[] selectedResources;

	private Role role;

	private String roleParameterName = "id";

	@PostConstruct
	public void init() {
		setRole();
		if ((role != null)) {
			root = new DefaultTreeNode("root", null);
			buildTree(root, resources.findByParent(null));
		}
	}

	private void setRole() {
		try {
			Long roleId = getRequestParameterAsLong(roleParameterName);
			if (roleId != null) {
				role = roles.findById(roleId);
			}
		} catch (WrongParameterTypeException | EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	private void buildTree(TreeNode parentNode, List<Resource> children) {
		for (Resource r : children) {
			TreeNode tempNode = new DefaultTreeNode(r, parentNode);
			tempNode.setSelected(isResourceSelected(r));
			buildTree(tempNode, resources.findByParent(r.getId()));
		}
	}

	private boolean isResourceSelected(Resource r) {
		for (Permission p : role.getPermissionList()) {
			if (p.getResourceId().equals(r.getId())) {
				return true;
			}
		}
		return false;
	}

	public String save() {
		System.out.println("seleccionados");
		for (TreeNode n : selectedResources) {
			System.out.println(((Resource) n.getData()).getName());
		}

		try {
			roles.updatePermissions(role.getId(), getSelectedResourceList());
			FacesMessageHelper.addInfo(String.format(
					"Permisos actualizados para el rol: %s", role.getName()));
			return "/pages/role/list?faces-redirect=true";
		} catch (EntityNotFoundException e) {
			FacesMessageHelper.addError("Error al actualizar los permisos",
					e.getMessage());
		}
		return null;
	}

	private List<Long> getSelectedResourceList() {
		List<Long> resources = new ArrayList<>();
		if (selectedResources != null) {
			for (TreeNode node : selectedResources) {
				resources.add(((Resource) node.getData()).getId());
			}
		}
		return resources;
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode[] getSelectedResources() {
		return selectedResources;
	}

	public void setSelectedResources(TreeNode[] selectedResources) {
		this.selectedResources = selectedResources;
	}

	public Role getRole() {
		return role;
	}

}
