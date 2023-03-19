public class rbTree<T extends Comparable<T>> {
    rbNode<T> root;
    private int size;
    public <T> void insert(T newValue){
        insertNode(newValue);
        rbNode node = getNodeOfValue((Comparable) newValue);
        if(node != root && node.parent != root){
            if(!node.parent.isBlack()){
                if(node.parent.parent.lChild == node.parent){
                    //Case 1: Uncle exists and is red
                    if( node.parent.parent.rChild != null && !node.parent.parent.rChild.isBlack()){
                        node.parent.parent.setAsRed();;
                        node.parent.parent.rChild.setAsBlack();
                        node.parent.setAsBlack();
                        //Case 2: Uncle is Null Or Black
                    }else{
                        //Case Of Left Right
                        if(node.parent.rChild == node){
                            leftRotate(node.value);
                            rightRotate(node.value);
                            node.setAsBlack();
                            node.rChild.setAsRed();
                            //Case Of Left Left
                        }else{
                            rightRotate(node.parent.value);
                            node.parent.setAsRed();
                            node.parent.rChild.setAsRed();
                        }
                    }
                }else {
                    //Case 1: Uncle exists and is red
                    if( node.parent.parent.lChild != null && !node.parent.parent.lChild.isBlack()){
                        node.parent.parent.setAsRed();;
                        node.parent.parent.lChild.setAsBlack();
                        node.parent.setAsBlack();
                        //Case 2: Uncle is Null Or Black
                    }else{
                        //Case Of Right Left
                        if(node.parent.rChild == node){
                            rightRotate(node.value);
                            leftRotate(node.value);
                            node.setAsBlack();
                            node.lChild.setAsRed();
                            //Case Of Right Right
                        }else{
                            leftRotate(node.parent.value);
                            node.parent.setAsRed();
                            node.parent.lChild.setAsRed();
                        }
                    }
                }
            }
        }
        root.setAsBlack();
    }
    private <T> void insertNode(T newValue){
        rbNode newNode = new rbNode();
        newNode.value = (Comparable) newValue;
        if(root == null){
            root = newNode;
            size++;
            return;
        }
        rbNode compare= root;
        while(compare != null){
            if(compare.value.compareTo(newNode.value) < 0){
                if(compare.rChild != null){
                    compare = compare.rChild;
                }else{
                    compare.rChild = newNode;
                    newNode.parent = compare;
                    size++;
                    return;
                }
            }else if(compare.value.compareTo(newNode.value) > 0) {
                if(compare.lChild != null){
                    compare = compare.lChild;
                }else {
                    compare.lChild = newNode;
                    newNode.parent = compare;
                    size++;
                    return;
                }
            }else {
                System.out.println("Value " + newValue + " Already Exists!!");
            }
        }
    }

    private void handleDoubleBlack(rbNode node, rbNode parent){
        if(node == root)
            return;
        rbNode sibling = node == parent.lChild? parent.rChild: parent.lChild;
        //Case 1: Sibling and Children are Black
        if(sibling.isBlack() &&
                (sibling.lChild == null || sibling.lChild.isBlack()) &&
                (sibling.rChild == null || sibling.rChild.isBlack())){
            sibling.setAsRed();
            if(!parent.isBlack()){
                parent.setAsBlack();
            }else
                handleDoubleBlack(parent, parent.parent);
        }
        //Case 2" Sibling is Black and a Child is Red
        else if(sibling.isBlack()){
            if(sibling == parent.rChild){
                //Case: RL
                if((sibling.rChild == null || sibling.rChild.isBlack()) &&
                        (sibling.lChild != null && !sibling.lChild.isBlack())){
                    rbNode child = sibling.lChild;
                    rightRotate(child.value);
                    child.setAsBlack();
                    sibling.setAsRed();
                    sibling = child;
                }
                //Case: RR
                if(sibling.rChild != null && !sibling.rChild.isBlack()){
                    leftRotate(sibling.value);
                    if(parent.isBlack())
                        sibling.rChild.setAsBlack();
                    else{
                        if(!(parent.rChild == null || parent.rChild.isBlack())){
                            parent.setAsBlack();
                            sibling.setAsRed();
                            sibling.rChild.setAsBlack();
                        }
                    }
                }
            }else {
                //Case: LR
                if((sibling.lChild == null || sibling.lChild.isBlack()) &&
                        (sibling.rChild != null && !sibling.rChild.isBlack())){
                    rbNode child = sibling.rChild;
                    leftRotate(child.value);
                    child.setAsBlack();
                    sibling.setAsRed();
                    sibling = child;
                }
                //Case: LL
                if(sibling.lChild != null && !sibling.lChild.isBlack()){
                    rightRotate(sibling.value);
                    if(parent.isBlack())
                        sibling.lChild.setAsBlack();
                    else{
                        if(!(parent.lChild == null || parent.lChild.isBlack())){
                            parent.setAsBlack();
                            sibling.setAsRed();
                            sibling.lChild.setAsBlack();
                        }
                    }
                }
            }
        }
        //Case 3: Sibling is Red
        else {
            if(sibling == parent.rChild){
                leftRotate(sibling.value);
                sibling.setAsBlack();
                parent.setAsRed();
                handleDoubleBlack(node, parent);
            }else {
                rightRotate(sibling.value);
                sibling.setAsBlack();
                parent.setAsRed();
                handleDoubleBlack(node, parent);
            }
        }
    }
    private void deleteRB(rbNode node){
        //Case 1: Node is Red With No Children
        if(!node.isBlack() && node.lChild == null && node.rChild == null){
            if(node.parent.lChild == node)
                node.parent.lChild = null;
            else
                node.parent.rChild = null;
            node.parent = null;
        }
        //Case 2: Node Is Red With A Child
        else if(!node.isBlack()){
            rbNode child;
            if(node.lChild != null){
                child = node.lChild;
            }else {
                child = node.rChild;
            }
            child.parent = node.parent;
            if(node.parent.lChild == node)
                node.parent.lChild = child;
            else
                node.parent.rChild = child;
            node.parent = null;
            node.lChild = null;
            node.rChild = null;
            child.setAsBlack();
        }
        //Case 3: Node and Children are Black
        else {
            rbNode child;
            rbNode parent = node.parent;
            if(node.lChild != null)
                child = node.lChild;
            else
                child = node.rChild;
            if(node.parent.lChild == node)
                node.parent.lChild = null;
            else
                node.parent.rChild = null;
            node.parent = null;
            handleDoubleBlack(child, parent);
        }
    }

    public <T> void deleteNode(T value){
        rbNode deletedNode;
        if (size == 0) {
            System.out.println("Value Doesn't Exist!!");
            return;
        }
        rbNode node = getNodeOfValue((Comparable)value);
        if(node == null)
            return;
        //Case 1: No Children
        if(node.lChild == null && node.rChild == null){
            if(node == root){
                root = null;
                size = 0;
                return;
            }

            deletedNode = node;

            /*if(node != root && node == node.parent.rChild){
                node.parent.rChild = null;
            }else if(node != root){
                node.parent.lChild = null;
            }
            node.parent = null;*/
        }
        //Case 2: Has Only Left Child
        else if(node.rChild == null){
            rbNode swapNode = node.lChild;
            while(swapNode.rChild != null)
                swapNode = swapNode.rChild;
            Comparable<T> temp = swapNode.value;
            swapNode.value = node.value;
            node.value = temp;
            deletedNode = swapNode;
        }
        //Case 3: Has A Right Child
        else{
            rbNode swapNode = node.rChild;
            while(swapNode.lChild != null)
                swapNode = swapNode.lChild;
            Comparable<T> temp = swapNode.value;
            swapNode.value = node.value;
            node.value = temp;
            deletedNode = swapNode;
        }
        deleteRB(deletedNode);
        size--;
    }
    public void rightRotate(Comparable<T> value){
        rbNode node = getNodeOfValue(value);
        if(node == root){
            System.out.println("Can't Rotate The Root!!");
            return;
        }
        if(node == null){
            System.out.println("Value Doesn't Exist!!");
            return;
        }
        if(node.parent.lChild != node){
            System.out.println("Can't Rotate This Node!!");
            return;
        }
        rbNode parent = node.parent;
        parent.lChild = node.rChild;
        if(node.rChild != null)
            parent.lChild.parent = parent;
        node.parent = parent.parent;
        if(parent == root){
            root = node;
        }else {
            if(parent == parent.parent.lChild)
                parent.parent.lChild = node;
            else
                parent.parent.rChild = node;
        }
        node.rChild = parent;
        parent.parent = node;
    }
    public void leftRotate(Comparable<T> value){
        rbNode node = getNodeOfValue(value);
        if(node == root){
            System.out.println("Can't Rotate The Root!!");
            return;
        }
        if(node == null){
            System.out.println("Value Doesn't Exist!!");
            return;
        }
        if(node.parent.rChild != node){
            System.out.println("Can't Rotate This Node!!");
            return;
        }
        rbNode parent = node.parent;
        parent.rChild = node.lChild;
        if(node.lChild != null)
            parent.rChild.parent = parent;
        node.parent = parent.parent;
        if(parent == root){
            root = node;
        }else {
            if(parent == parent.parent.lChild)
                parent.parent.lChild = node;
            else
                parent.parent.rChild = node;
        }
        node.lChild = parent;
        parent.parent = node;
    }
    public int getHeight(){
        rbNode node = root;
        return 1 + Math.max(getHeight(node.lChild), getHeight(node.rChild));
    }
    public int getHeight(rbNode node){
        if(node == null)
            return 0;
        return 1 + Math.max(getHeight(node.lChild), getHeight(node.rChild));
    }
    public int getHeight(Comparable<T> value){
        return getHeight(getNodeOfValue(value));
    }
    private rbNode getNodeOfValue(Comparable<T> value){
        if(root == null)
            return null;
        rbNode node = root;
        while(node.value.compareTo(value) != 0){
            if(node.value.compareTo(value) < 0){
                node = node.rChild;
            } else if (node.value.compareTo(value) > 0) {
                node = node.lChild;
            }
            if(node == null){
                System.out.println("Value Doesn't Exist!!");
                break;
            }
        }

        return node;
    }
    public void printTree(){
        if(root != null){
            printTree(root);
            return;
        }
        System.out.println("Empty Tree Can't Be Printed");
    }
    public void printTree(rbNode node){
        if(node == null)
            return;
        if(node != root){
            System.out.print("Parent: " + node.parent.value + ", ");
        }
        String color = node.isBlack()?"B":"R";
        System.out.print("Node: " + node.value + " C: " + color + " Children: ");
        if(node.lChild == null){
            System.out.print("No Left, ");
        }else {
            System.out.print("L: " + node.lChild.value + ", ");
        }
        if(node.rChild == null){
            System.out.print("No Right\n");
        }else {
            System.out.print("R: " + node.rChild.value + "\n");
        }
        if(node.lChild != null)
            printTree(node.lChild);
        if(node.rChild != null)
            printTree(node.rChild);
    }

    public int getSize() {
        return size;
    }
}