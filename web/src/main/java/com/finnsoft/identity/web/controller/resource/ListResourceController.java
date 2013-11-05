/**
 * 
 */
package com.finnsoft.identity.web.controller.resource;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.finnsoft.identity.core.model.Resource;
import com.finnsoft.identity.core.service.Resources;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class ListResourceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Resources resources;

	private TreeNode root;

	private TreeNode selected;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("root", null);
		buildTree(root, resources.findByParent(null));
	}

	private void buildTree(TreeNode parentNode, List<Resource> children) {
		for (Resource r : children) {
			TreeNode tempNode = new DefaultTreeNode(r, parentNode);
			buildTree(tempNode, resources.findByParent(r.getId()));
		}
	}

	public String newResource() {
		if (selected == null) {
			return "/pages/resource/new?faces-redirect=true";
		}
		return String.format(
				"/pages/resource/new?parentId=%sfaces-redirect=true",
				((Resource) selected.getData()).getId());
	}

	public String updateResource() {
		return String.format("/pages/resource/update?id=%sfaces-redirect=true",
				((Resource) selected.getData()).getId());
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
