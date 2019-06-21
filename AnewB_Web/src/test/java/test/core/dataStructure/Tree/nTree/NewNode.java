package test.core.dataStructure.Tree.nTree;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewNode {

    public Integer data;

    public NewNode leftNode;

    public NewNode rightNode;

    public NewNode(Integer data) {
        this.data = data;
    }
}
