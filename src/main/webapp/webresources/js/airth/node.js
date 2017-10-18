function Node(data,left,right){
    this.data = data;
    this.left = left;
    this.right = right;
}
function BST(){
    this.root = null;
    this.insert = insert;
    this.find = find;
    this.remove = remove;
}
/*
* 二叉树增加节点
*/
function insert(data){
    var node = new Node(data,null,null);
    if(this.root == null){
        this.root = node;
    }else{
        var current = this.root;
        while(true){
            if(current.data > data){
                if(current.left === null){
                    current.left = node;
                    break;
                }
                current = current.left;
            }else{
                if(current.right === null){
                    current.right = node;
                    break;
                }
                current = current.right;
            }
        }
    }
}
/*
* 二叉树查节点
*/
function find(data){
    var current = this.root;
    while(true){
        if(data === current.data){
            return current;
        }
        current = data < current.data ? current.left : current.right;
        if(current === null){
            return null;
        }
    }
}
/*
* 二叉树删除节点 (只删除叶子节点、只有一个子节点的Node
*/
function remove(data){
    this.root = removeNode(this.root,data);
}
function removeNode(node,data){
    if(node === null){
        return null;
    }
    if(data === node.data){
        if(node.left === null && node.right === null){
            return null;
        }
        if(node.left === null){
            return node.right;
        }
        if(node.right === null){
            return node.left;
        }
    }else if(data < node.data){
        node.left = removeNode(node.left,data);
        return node;
    }else{
        node.right = removeNode(node.right,data);
        return node;
    }
}
var bst = new BST();
bst.insert(5);
bst.insert(3);
bst.insert(7);
bst.insert(2);
bst.remove(2);