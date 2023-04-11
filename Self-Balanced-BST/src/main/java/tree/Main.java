package tree;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        new Main().ui();

        /*avlTree <String> tree = new avlTree<>();
        tree.insert(41);
        tree.printTree();
        System.out.println();
        tree.insert(38);
        tree.printTree();
        System.out.println();
        tree.insert(31);
        tree.printTree();
        System.out.println();
        tree.insert(12);
        tree.printTree();
        System.out.println();
        tree.insert(19);
        tree.printTree();
        System.out.println();
        tree.insert(8);
        tree.printTree();
        System.out.println();
        System.out.println();
        System.out.println();
        tree.delete(8);
        tree.printTree();
        System.out.println();
        tree.delete(12);
        tree.printTree();
        System.out.println();
        tree.delete(19);
        tree.printTree();
        System.out.println();
        tree.delete(31);
        tree.printTree();
        System.out.println();
        tree.delete(38);
        tree.printTree();
        System.out.println();
        tree.delete(41);
        tree.printTree();
        System.out.println();*/

        /*rbTree<Integer> tree = new rbTree();
        System.out.println("BH: " + tree.getBlackHeight());
        System.out.println("H: " + tree.height());
        tree.insert(41);
        tree.printTree();
        System.out.println("\n");
        tree.insert(38);
        tree.printTree();
        System.out.println("\n");
        tree.insert(31);
        tree.printTree();
        System.out.println("\n");
        tree.insert(12);
        tree.printTree();
        System.out.println("\n");
        tree.insert(19);
        tree.printTree();
        System.out.println("\n");
        tree.insert(8);
        tree.printTree();
        System.out.println("\n");
        System.out.println("Size: " + tree.size() + "\n");
        System.out.println("BH: " + tree.getBlackHeight());
        System.out.println("H: " + tree.height());
        System.out.println("\n");
        tree.delete(19);
        tree.printTree();
        System.out.println("BH: " + tree.getBlackHeight());
        System.out.println("H: " + tree.height());
        System.out.println("Size: " + tree.size());*/
    }
    public String batchDelete(String deleteFilePath, bstTree tree){
        String output = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(deleteFilePath))) {
            String word;
            int successCount = 0, failCount = 0;
            while ((word = reader.readLine()) != null) {
                if (tree.delete(word)) {
                    successCount++;
                } else {
                    failCount++;
                }
            }
            output = "success = " + Integer.toString(successCount) + " ,fail = " + Integer.toString(failCount);
            System.out.println("Batch Delete Complete:");
            System.out.println("Success: " + successCount + " Failure: " + failCount + "\n");
        } catch (IOException e) {
            output = "error";
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        return output;
    }
    public String batchInsert(String filePath, bstTree tree){
        String output = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            int successCount = 0, failCount = 0;
            while ((word = reader.readLine()) != null) {
                if (tree.insert(word)) {
                    successCount++;
                } else {
                    failCount++;
                }
            }
            output = "success = " + Integer.toString(successCount) + " ,fail = " + Integer.toString(failCount);
            System.out.println("Batch Insert Complete:");
            System.out.println("Success: " + successCount + " Failure: " + failCount + "\n");
        } catch (IOException e) {
            output = "error";
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        return output;
    }
    public void ui() {
        String flag = new String();
        String clear = new String(new char[50]).replace("\0", "\r\n");
        Scanner input = new Scanner(System.in);
        bstTree tree;
        tree = buildTree();
        if(tree instanceof avlTree<?>)
            System.out.println("Created AVL Tree!!");
        else
            System.out.println("Created Red-Black Tree!!");
        System.out.println(clear);
        while (true) {
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Batch Insert");
            System.out.println("5. Batch Delete");
            System.out.println("6. Size");
            System.out.println("7. Height");
            System.out.println("8. Change Tree Type");
            int option = input.nextInt();
            input.nextLine();
            System.out.println(clear);
            switch (option) {
                case 1:
                    System.out.println("Word: ");
                    Object insertWord = input.nextLine();
                    String insertStatus = tree.insert(insertWord)?"Successful": "Failed";
                    System.out.println("Insert Status: " + insertStatus + "\n");
                    break;
                case 2:
                    System.out.println("Word: ");
                    Object deleteWord = input.nextLine();
                    String deleteStatus = tree.delete(deleteWord)?"Successful": "Failed";
                    System.out.println("Delete Status: " + deleteStatus + "\n");
                    break;
                case 3:
                    System.out.println("Word: ");
                    Object searchWord = input.nextLine();
                    String searchStatus = tree.search(searchWord)?"Successful": "Failed";
                    System.out.println("Search Status: " + searchStatus + "\n");
                    break;
                case 4://Batch Insert: todo
                    System.out.println("Enter the file path: ");
                    String filePath = input.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String word;
                        int successCount = 0, failCount = 0;
                        while ((word = reader.readLine()) != null) {
                            if (tree.insert(word)) {
                                successCount++;
                            } else {
                                failCount++;
                            }
                        }
                        flag = "success = " + Integer.toString(successCount) + " ,fail = " + Integer.toString(failCount);
                        System.out.println("Batch Insert Complete:");
                        System.out.println("Success: " + successCount + " Failure: " + failCount + "\n");
                    } catch (IOException e) {
                        flag = "error";
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    System.out.println(flag);
                    break;
                case 5://Batch Delete: todo

                    System.out.println("Enter the file path: ");
                    String deleteFilePath = input.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(deleteFilePath))) {
                        String word;
                        int successCount = 0, failCount = 0;
                        while ((word = reader.readLine()) != null) {
                            if (tree.delete(word)) {
                                successCount++;
                            } else {
                                failCount++;
                            }
                        }
                        System.out.println("Batch Delete Complete:");
                        System.out.println("Success: " + successCount + " Failure: " + failCount + "\n");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;
                case 6:
                    System.out.println("Size: " + tree.size() + "\n");
                    break;
                case 7:
                    System.out.println("Height: " + tree.height() + "\n");
                    break;
                case 8:
                    System.out.println("Are You Sure This Will Delete Your Current Tree!!\n1. Yes\t\t2. No");
                    int choice = input.nextInt();
                    if(choice == 1){
                        tree = buildTree();
                        if(tree instanceof avlTree<?>)
                            System.out.println("Created AVL Tree!!");
                        else
                            System.out.println("Created Red-Black Tree!!");
                    }
                    System.out.println(clear);
                    break;
            }
        }
    }
    bstTree buildTree(){
        System.out.println("1. AVL Tree\n2. Red-Black Tree");
        int type = new Scanner(System.in).nextInt();
        System.out.println("\nSelect Tree Type:\n1. String\t\t2. Integer\t\t3. Float");
        int keyType = new Scanner(System.in).nextInt();
        if(type == 1){
            switch (keyType){
                case 1:
                    return new avlTree<String>();
                case 2:
                    return new avlTree<Integer>();
                case 3:
                    return new avlTree<Double>();
                default:
                    return new avlTree<String>();
            }
        }
        switch (keyType){
            case 1:
                return new rbTree<String>();
            case 2:
                return new rbTree<Integer>();
            case 3:
                return new rbTree<Double>();
            default:
                return new rbTree<String>();
        }
    }
}
