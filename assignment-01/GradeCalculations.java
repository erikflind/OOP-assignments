public class GradeCalculations {

    //TASK #2 - Calculates the mean of the students' scores
    public static void calculateMean() {
        int totalScore = 0;
        for (int i = 0; i< Menu.studentScores.length; i++)      //sums students' scores
            totalScore += Menu.studentScores[i];

        float average = (totalScore) / 7f;      //calculates average and casts to float variable with 2 decimals
        System.out.println();
        System.out.printf("The mean of the numbers is %.2f%n", average);
    }


    //TASK #3 - Compares the elements of the array and finds the highest and lowest scores
    public static void findHighLowScores(){
        int highestScore = - 1;              //variables used to store/compare student scores,--
        int secondHighest = - 1;             //--initialized with values that will always be replaced by conditional statements below
        int lowestScore = 101;               //
        int secondLowest = 101;              //

        for (int i = 0; i < Menu.studentScores.length; i++) {   //compares scores in array with current highest score for every loop
            if (Menu.studentScores[i] >= highestScore) {
                secondHighest = highestScore;                   //replaces secondHighest with old highestScore
                highestScore = Menu.studentScores[i];           //assigns new highestScore value
            }

            if (Menu.studentScores[i] != highestScore && Menu.studentScores[i] > secondHighest) {   //replaces secondHighest if current score is higher
                secondHighest = Menu.studentScores[i];
            }

            if (Menu.studentScores[i] <= lowestScore ) {        //similar code as above however this time searching for lowest score (lines 32-38)
                secondLowest = lowestScore;
                lowestScore = Menu.studentScores[i];
            }

            if (Menu.studentScores[i] != lowestScore && Menu.studentScores[i] < secondLowest){
                secondLowest = Menu.studentScores[i];
            }
        }

        System.out.println("The two lowest scores provided are " + lowestScore + ", and " + secondLowest);
        System.out.println("The two highest scores provided are " + highestScore + ", and " + secondHighest);
    }


    //TASK #4 - Compares the array elements and finds the highest score and its position
    public static void highestScoreBelongsTo(){
        int highestScore = - 1;         //used to find & store the highest student score
        int position = 0;       //used to store the position of the highest score in the array

        for (int i = 0; i < Menu.studentScores.length; i++) {
            if (Menu.studentScores[i] > highestScore) {
                highestScore = Menu.studentScores[i];
                position = i;
            }
        }

        switch (position + 1) {         //expanding program to include more than 20 students would require adding code for exceptions
            case 1:
                System.out.println("The highest score is " + highestScore + " and belongs to the " + (position + 1) + "st student.");
                break;
            case 2:
                System.out.println("The highest score is " + highestScore + " and belongs to the " + (position + 1) + "nd student.");
                break;
            case 3:
                System.out.println("The highest score is " + highestScore + " and belongs to the " + (position + 1) + "rd student.");
                break;
            default:
                System.out.println("The highest score is " + highestScore + " and belongs to the " + (position + 1) + "th student.");
                break;
        }
    }

}
