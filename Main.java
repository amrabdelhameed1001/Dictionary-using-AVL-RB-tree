import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Enter type of tree
        System.out.print("Enter type of tree (1 for AVL, 2 for Red-Black) ");
        int choice = input.nextInt();

        if(choice == 1){
            AVLTree dic1 = new AVLTree();

            while(true){


                System.out.println("if need insert press 1");
                System.out.println("if need delete press 2");
                System.out.println("if need search press 3");
                System.out.println("if need batch insert press 4");
                System.out.println("if need batch delete press 5");
                System.out.println("if need size press 6");
                System.out.println("if need height press 7");

                int option= input.nextInt();
                input.nextLine();


                switch(option){
                    case 1:
                        System.out.println("input word which you need to insert");
                        String word = input.nextLine();
                        dic1.insert(word);
                        break;

                    case 2:



                }

            }
        }

        if(choice == 2){
            rbTree dic2 = new rbTree();

            while(true){


                System.out.println("if need insert press 1");
                System.out.println("if need delete press 2");
                System.out.println("if need search press 3");
                System.out.println("if need batch insert press 4");
                System.out.println("if need batch delete press 5");
                System.out.println("if need size press 6");
                System.out.println("if need height press 7");

                int option= input.nextInt();
                input.nextLine();


                switch(option){
                    case 1:
                        System.out.println("input word which you need to insert");
                        String word = input.nextLine();
                        dic2.insert(word);
                        break;

                    case 2:



                }

            }
        }
    }
}
