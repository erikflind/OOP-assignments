import java.util.Arrays;
import java.util.Locale;

public class Menu {

    public static int[] studentScores = new int[7];     //array for storing students' scores
    public static int menuOption = 0;       //variable used for main menu - temporarily stores input user option


    // TASK #1 - prompts the user to enter the students scores - stores & prints the values | used for calculations in task #2, #3, & #4
    public static void readStudentScores() {
        for (int studentNumber = 0; studentNumber < studentScores.length; studentNumber++) {
            switch (studentNumber + 1) {
                case 1:
                    System.out.print("Enter the score for the " + (studentNumber + 1) + "st student ");
                    break;
                case 2:
                    System.out.print("Enter the score for the " + (studentNumber + 1) + "nd student ");
                    break;
                case 3:
                    System.out.print("Enter the score for the " + (studentNumber + 1) + "rd student ");
                    break;
                default:
                    System.out.print("Enter the score for the " + (studentNumber + 1) + "th student ");
                    break;
            }
            int scoreInput = IOScanner.readInt();
            if (scoreInput < 0 || scoreInput > 100) {       //determines the acceptable input boundaries - checks, then prompts user to reenter score if value is out of bounds
                System.out.println("Error - Input out of bound. Score can only be between 0 and 100.");
                studentNumber = studentNumber - 1;          //if input was out of bounds: subtracts 1 from the variable 'studentNumber' - effectively "repeating" the current iteration--
            } else {                                        //--we then end up reentering loop with the same studentNumber value - lets user enter the same student's score again
                studentScores[studentNumber] = scoreInput;
            }
        }
        System.out.println("Thank you for your input. Your entered scores are: ");
        System.out.println(Arrays.toString(studentScores).replace("[", "").replace("]", " "));
    }


    // TASK #5 - reads user input & extracts hashtag words from input String
    public static void collectHashtags(){
        boolean hashtagPresence = false;        //boolean control variable for hashtag detection
        String hashtagsFound = "Hashtags found:";       //String variable for printing found hashtags

        String post = IOScanner.readString("Type your post:");
        String[] splitPostArray = post.split(" ");              // splits input String to array using empty space as delimiter

        for (int i = 0; i < splitPostArray.length; i++)                 //iterates through array and--
            if (splitPostArray[i].startsWith("#")) hashtagPresence = true;      //--updates boolean to == true if any hashtag is detected

        if (hashtagPresence) {
            for (int i = 0; i < splitPostArray.length; i++)
                if (splitPostArray[i].startsWith("#")) hashtagsFound = hashtagsFound + " " + splitPostArray[i];     //concatenates hashtags to output String

            System.out.println(hashtagsFound);

        } else System.out.println("No hashtags were typed.");
    }


    public static void main(String[] args){

        Locale.setDefault(Locale.ENGLISH);

        readStudentScores();    //calls task #1 before presenting user with menu

        // TASK #6 | MENU SYSTEM - READS USER INPUT OPTION 1-6 THEN CALLS RELEVANT METHOD USING SWITCH STATEMENT
        do {
            System.out.println("Welcome to the menu. Choose one of the options below:");
            System.out.println("1. Register new scores for students.");
            System.out.println("2. Calculate the mean of the entered scores.");
            System.out.println("3. Find the two highest and two lowest scores.");
            System.out.println("4. Find the highest score and its position.");
            System.out.println("5. Collect hashtags from a post.");
            System.out.println("6. To exit.");
            System.out.println();
            System.out.print("Type your option: ");

            menuOption = IOScanner.readInt();

            switch (menuOption) {
                case 1: {
                    readStudentScores();
                    break;
                }
                case 2: {
                    GradeCalculations.calculateMean();
                    break;
                }
                case 3: {
                    GradeCalculations.findHighLowScores();
                    break;
                }
                case 4: {
                    GradeCalculations.highestScoreBelongsTo();
                    break;
                }
                case 5: {
                    collectHashtags();
                    break;
                }
                case 6:
                    System.out.println("Thank you for using our grading system. Have a nice day!");
                    IOScanner.closeScanner();
                    System.exit(0);
                default:
                    System.out.println("Error - Invalid value. Please type between 1 and 6.");
                    break;
            }
        } while (menuOption != 6);

    }
}
