package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class avlTreeTest {
    @Test
    void testSize(){
        var tree = new avlTree<>();
        for (int i = 1;i<=100000;i++){
            tree.insert(i);
            assertEquals(i, tree.size());
        }
    }
    @Test
    void testInsertion(){
        var tree = new avlTree<>();
        for(int i = 1;i <= 100000;i++){
            assertTrue(tree.insert(i));
        }
        for(int i = 1;i <= 20000;i++){
            assertFalse(tree.insert(i));
        }
    }
    @Test
    void testDeletion(){
        var tree = new avlTree<>();
        for (int i = 1;i<=100000;i++){
            tree.insert(i);
        }
        for (int i = -10000;i<=0;i++){
            assertFalse(tree.delete(i));
        }
        for (int i = 1;i<=100000;i++){
            assertTrue(tree.delete(i));
        }
    }
    @Test
    void testSearch(){
        var tree = new avlTree<>();
        for (int i = 1;i<=100000;i++){
            tree.insert(i);
        }
        for (int i = -10000;i<=0;i++){
            assertFalse(tree.search(i));
        }
        for (int i = 1;i<=100000;i++){
            assertTrue(tree.search(i));
        }
    }
    @Test
    void testHeight(){
        var tree = new avlTree<>();
        for (int i = 1;i <= 100000;i++){
            tree.insert(i);
            double logSize = Math.log(i+1) / Math.log(2);
            assertEquals(Math.ceil(logSize), tree.height());
        }
    }
}