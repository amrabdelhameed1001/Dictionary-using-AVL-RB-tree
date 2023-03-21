public class AVLTree {
    Node root;
    int height(Node N){
        if(N== null){
            return 0;
        }
        return N.height;
    }



    int size;
    public AVLTree(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
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
            size++;
            //System.out.println("The word has been inserted");
            return (new Node(key));
        }


        if(key < node.key){
            node.left = insert(node.left , key);
        }
        else if (key > node.key){
            node.right = insert(node.right, key);
        }
        else{
            //System.out.println("The word is already exist");
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


    public void insert(String key){
        int intkey = key.hashCode();
        root = insert(root,intkey);
    }

    Node delete(Node root , int key){
        if(root == null) {
            size--;

            return root;
        }
        if(key < root.key){
            root.left = delete(root.left,key);
        }
        else if(key > root.key){
            root.right=delete(root.right,key);
        }

        else{

            if((root.left == null) || (root.right == null)){
                Node temp = null;
                if(temp == root.left){
                    temp = root.right;
                }
                else{
                    temp = root.left;
                }

                if(temp == null){
                    temp = root;
                    root=null;
                }
                else{
                    root = temp;
                }
            }

            else{
                Node temp = min_node(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        if(root == null){

            return root;
        }

        root.height=max(height(root.left), height(root.right))+1;

        if(balance(root) > 1 && balance(root.left) >=0){
            return rightRotate(root);
        }
        if(balance(root) > 1 && balance(root.left) < 0){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if(balance(root) < -1 && balance(root.right) <= 0){
            return leftRotate(root);
        }

        if(balance(root) < -1 && balance(root.right)>0){
            root.right = rightRotate(root.right);
            return  leftRotate(root);
        }
        return root;
    }

    public void delete(String key){
        int intkey = key.hashCode();
        root = delete(root,intkey);
    }

    public boolean search(Node node , int key){
        if(node == null){
            return false;
        }
        if(key == node.key){
            return true;
        }
        if(key < node.key){
            return search(node.left, key);
        }
        else{
            return search(node.right, key);
        }
    }
}
