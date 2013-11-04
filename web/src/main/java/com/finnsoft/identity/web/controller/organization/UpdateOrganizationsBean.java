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
public class UpdateOrganizationsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organizations;

	private TreeNode root;

	private Organization organization;

	private TreeNode selected;

	@PostConstruct
	public void init() {
		System.out.println("aaaaaaaaaa");
		root = new DefaultTreeNode("root", null);
		Long parentId = null; // TODO falta obtener mediante la sesion
		buildTree(root, organizations.findByParent(parentId));
	}

	private void buildTree(TreeNode parentNode, List<Organization> children) {
		for (Organization o : children) {
			TreeNode tempNode = new DefaultTreeNode(o, parentNode);
			buildTree(tempNode, organizations.findByParent(o.getId()));
		}
	}

	public void addOrganization() {
		organization = new Organization((Organization) selected.getData());
	}

	public void assignToModify() {
		organization = (Organization) selected.getData();
	}

	public String saveOrganization() {
		organizations.save(organization);
		return "/pages/organization/list?faces-redirect=true";
	}

	public TreeNode getRoot() {
		return root;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public TreeNode getSelected() {
		return selected;
	}

	public void setSelected(TreeNode selected) {
		this.selected = selected;
	}
}
