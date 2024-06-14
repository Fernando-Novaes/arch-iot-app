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
@Setter
@Getter
public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    public void removeChild(TreeNode<T> child) {
        this.children.remove(child);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", children=" + children +
                '}';
    }
}
