public class rbNode<T extends Comparable<T>> {
    T value;
    rbNode<T> parent;
    rbNode<T> lChild;
    rbNode<T> rChild;
    private boolean isBlack = false;

    public void setAsBlack(){
        isBlack = true;
    }
    public void setAsRed(){
        isBlack = false;
    }
    public boolean isBlack(){
        return isBlack;
    }
}
