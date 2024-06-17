package br.ufrj.cos.components.treeview;

import br.ufrj.cos.domain.IoTDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/***
 *     "iot_domain": "Healthcare",
 *     "id": "ARC25 - SS25",
 *     "architecture_solution": "Distributed Edge-Cloud ",
 *     "quality_requirement": "Security",
 *     "technologies": ""
 */
public class TreeNode<T> {
    private final T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        child.setParent(this);
        children.add(child);
    }

    private void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }
}
