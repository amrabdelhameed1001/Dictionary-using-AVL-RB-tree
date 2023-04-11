package tree;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String insertedFilePath = "D:\\output_file.txt";
    String deletedFilePath = "D:\\delete.txt";
    String beforeDeleteFilePath = "D:\\beforeDelete.txt";
    @Test
    void testBatchInsertInAVL(){
        var tree = new avlTree<>();
        var main = new Main();
        String result = main.batchInsert(insertedFilePath, tree);
        assertTrue("success = 999254 ,fail = 746".equals(result));
    }
    @Test
    void testBatchInsertInRB(){
        var tree = new rbTree<>();
        var main = new Main();
        String result = main.batchInsert(insertedFilePath, tree);
        assertTrue("success = 999254 ,fail = 746".equals(result));
    }
    @Test
    void testBatchDeleteInAVL(){
        var tree = new avlTree<>();
        var main = new Main();
        String insertResult = main.batchInsert(beforeDeleteFilePath,tree);
        String result = main.batchDelete(deletedFilePath, tree);
        assertTrue("success = 5000 ,fail = 5000".equals(result));    }
    @Test
    void testBatchDeleteInRB(){
        var tree = new rbTree<>();
        var main = new Main();
        String insertResult = main.batchInsert(beforeDeleteFilePath,tree);
        String result = main.batchDelete(deletedFilePath, tree);
        assertTrue("success = 5000 ,fail = 5000".equals(result));
    }
    @Test
    void testInsertionRunTime(){
        var rb_tree = new rbTree<>();
        var avl_tree = new avlTree<>();
        int input = 2;
        long startTime,endTime;
        while (input <= Math.pow(2,22)){
            rb_tree = new rbTree<>();
            avl_tree = new avlTree<>();
            for(int i = 1;i<=input;i++){
                rb_tree.insert(i);
            }
            startTime = System.nanoTime();
            rb_tree.insert(input+1);
            endTime = System.nanoTime();
            System.out.println("RB-Tree  insertion time = " + ((endTime-startTime)) + " nanosecond, at n = " + input);
            for(int i = 1;i<=input;i++){
                avl_tree.insert(i);
            }
            startTime = System.nanoTime();
            avl_tree.insert(input+1);
            endTime = System.nanoTime();
            System.out.println("AVL-Tree insertion time = " + ((endTime-startTime)) + " nanosecond, at n = " + input);
            input*=8;
        }
    }
    @Test
    void testDeletionRunTime(){
        var rb_tree = new rbTree<>();
        var avl_tree = new avlTree<>();
        int input = 2;
        long startTime,endTime;
        while (input <= Math.pow(2,22)){
            rb_tree = new rbTree<>();
            avl_tree = new avlTree<>();
            for(int i = 1;i<=input;i++){
                rb_tree.insert(i);
            }
            startTime = System.nanoTime();
            rb_tree.delete(input);
            endTime = System.nanoTime();
            System.out.println("RB-Tree  Deletion time = " + ((endTime-startTime)) + " nanosecond, at n = " + input);
            for(int i = 1;i<=input;i++){
                avl_tree.insert(i);
            }
            startTime = System.nanoTime();
            avl_tree.delete(input);
            endTime = System.nanoTime();
            System.out.println("AVL-Tree Deletion time = " + ((endTime-startTime)) + " nanosecond, at n = " + input);
            input *=8;
        }
    }
    @Test
    void testSearchTime(){
        var rb_tree = new rbTree<>();
        var avl_tree = new avlTree<>();
        for(int i=1;i<=1000000;i++){
            rb_tree.insert(i);
            avl_tree.insert(i);
        }
        Random rand = new Random();
        long startTime,endTime;
        for(int i=0;i<10;i++){
            int random = rand.nextInt();
            startTime = System.nanoTime();
            boolean isFound = rb_tree.search(random);
            endTime = System.nanoTime();
            System.out.println("RB-Tree  Search time = " + ((endTime-startTime)) + " nanosecond, at n = 1000000");
            startTime = System.nanoTime();
            isFound = avl_tree.search(random);
            endTime = System.nanoTime();
            System.out.println("AVL-Tree  Search time = " + ((endTime-startTime)) + " nanosecond, at n = 1000000");
        }
    }
}