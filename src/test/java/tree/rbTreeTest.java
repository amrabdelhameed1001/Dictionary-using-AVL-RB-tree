package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class rbTreeTest {
    @Test
    void testSize(){
        var tree = new rbTree<>();
        for (int i = 1;i<=100000;i++){
            tree.insert(i);
            assertEquals(i, tree.size());
        }
    }
    @Test
    void testInsertion(){
        var tree = new rbTree<>();
        for(int i = 1;i <= 100000;i++){
            assertTrue(tree.insert(i));
        }
        for(int i = 1;i <= 20000;i++){
            assertFalse(tree.insert(i));
        }
    }
    @Test
    void testDeletion(){
        var tree = new rbTree<>();
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
        var tree = new rbTree<>();
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
        var tree = new rbTree<>();
        assertEquals(0,tree.height());
        tree.insert(15);
        assertEquals(1,tree.height());
        tree.insert(20);
        assertEquals(2,tree.height());
        tree.insert(25);
        assertEquals(2,tree.height());
        tree.insert(30);
        assertEquals(3,tree.height());
    }
    @Test
    void testPrinting(){
        var tree = new rbTree<>();
        tree.insert(15);
        tree.insert(20);
        tree.insert(25);
        tree.insert(30);
        tree.printTree();
    }
}