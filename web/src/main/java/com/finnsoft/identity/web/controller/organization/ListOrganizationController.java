/**
 * 
 */
package com.finnsoft.identity.web.controller.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.finnsoft.identity.core.model.Organization;
import com.finnsoft.identity.core.service.Organizations;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class ListOrganizationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organizations;

	private TreeNode root;

	private TreeNode selected;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("root", null);
		buildTree(root, organizations.findByParent(null));
	}

	private void buildTree(TreeNode parentNode, List<Organization> children) {
		for (Organization o : children) {
			TreeNode tempNode = new DefaultTreeNode(o, parentNode);
			buildTree(tempNode, organizations.findByParent(o.getId()));
		}
	}

	public String newOrganization() {
		if (selected == null) {
			return "/pages/organization/new?faces-redirect=true";
		}
		return String.format(
				"/pages/organization/new?parentId=%sfaces-redirect=true",
				((Organization) selected.getData()).getId());
	}

	public String viewUsers() {
		return String.format(
				"/pages/organization/user/list?id=%sfaces-redirect=true",
				((Organization) selected.getData()).getId());
	}

	public String updateOrganization() {
		return String.format(
				"/pages/organization/update?id=%sfaces-redirect=true",
				((Organization) selected.getData()).getId());
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelected() {
		return selected;
	}

	public void setSelected(TreeNode selected) {
		this.selected = selected;
	}
}
