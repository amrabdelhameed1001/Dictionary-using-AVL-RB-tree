public class AVLTree {
    Node root;
    int height(Node N){
        if(N== null){
            return 0;
        }
        return N.height;
    }

    int max(int a , int b){
        return (a>b) ? a : b;
    }

    int balance(Node N){
        if(N==null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node min_node(Node node){
        Node current = node;

        while(current.left != null)
            current = current.left;
        return current;
    }

    Node rightRotate(Node y){
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;
        y.height = max(height(y.left),height(y.right))+1;

        return x;
    }
    
    Node leftRotate(Node x){
        Node y = x.right;
        Node t2 = y.left;
        
        y.left =x;
        x.right = t2;

        x.height = max(height(x.left),height(x.right))+1;
        y.height = max(height(y.left),height(y.right))+1;

        return y;
    }

    Node insert(Node node, int key){
        if(node == null){
            return (new Node(key));
        }


        if(key < node.key){
            node.left = insert(node.left , key);
        }
        else if (key > node.key){
            node.right = insert(node.right, key);
        }
        else{
            return node;
        }

        node.height = 1+ max(height(node.left),height(node.right));

        if(balance(node) > 1 && key < node.left.key){
            return rightRotate(node);
        }
        if(balance(node) < -1 && key > node.right.key){
            return leftRotate(node);
        }
        if(balance(node) > 1 && key > node.left.key){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if(balance(node) < -1 && key < node.right.key){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


}
