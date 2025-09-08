import java.util.Scanner;

public class IOScanner {

    public static Scanner input = new Scanner(System.in);

    // reads String; returns value
    public static String readString(String promptMessage){
        System.out.println(promptMessage);
        return input.nextLine();
    }

    // reads integer; returns value
    public static int readInt(){
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    // closes Scanner function
    public static void closeScanner() {input.close();}

}
