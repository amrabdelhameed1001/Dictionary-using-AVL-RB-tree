package tree;

public interface bstTree {
    <T> boolean insert(T newValue);
    <T> boolean delete(T newValue);
    <T> boolean search(T newValue);
    int size();
    int height();
}
