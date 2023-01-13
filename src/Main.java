import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double weight = 0;
        try {
            File readFile = new File("dragonTrackProgress.txt");
            if (readFile.createNewFile()) {
                System.out.println("File created: " + readFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File readWeight = new File("dragonTrackProgress.txt");
            Scanner readFile = new Scanner(readWeight);
            while (readFile.hasNextLine()) {
                String data = readFile.nextLine();
                weight = Double.parseDouble(data);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        short daysPassed = 0;
        boolean terminate = false;

        System.out.println("Type '1' for weight calculator.\nType '2' to view weight.\nType '3' to reset weight to 500.\n");
        Scanner listChoice = new Scanner(System.in); //create scanner object
        int confirmOption = listChoice.nextInt();


        switch (confirmOption) {
            case 1: {
                do {
                    double calorieBodyWeightSustain = weight * 0.2;
                    double calorieLimit = weight * 0.5;
                    System.out.println("Your current weight is: " + Math.floor(weight));
                    System.out.println("Your calorie intake needs to be more than: " + Math.ceil(calorieBodyWeightSustain));
                    System.out.println("But you need to eat " + Math.ceil(calorieLimit));
                    System.out.println("You can only absorb a max of " + Math.ceil((calorieLimit - calorieBodyWeightSustain)) + " calories. \n");
                    System.out.println("Have you consumed more than " + Math.ceil(calorieBodyWeightSustain) +
                            " of your body weight? 'yes'/'no'");
                    System.out.println("Type 'exit' to leave the application.");
                    Scanner input = new Scanner(System.in); //create scanner object
                    String confirmGoal = input.nextLine(); //scans input

                    switch (confirmGoal) {
                        case "yes": {
                            System.out.println("How many pounds did you eat?");

                            double calorieIntake = input.nextInt();

                            if (calorieIntake >= calorieLimit)
                                weight = weight + (calorieLimit - calorieBodyWeightSustain);

                            if ((calorieIntake < calorieLimit) && (calorieIntake > calorieBodyWeightSustain))
                                weight = weight + (calorieIntake - calorieBodyWeightSustain);

                            if (calorieIntake <= calorieBodyWeightSustain)
                                System.out.println("If you are done type 'exit' \n");

                            System.out.println("You now weigh: " + Math.floor(weight));
                            System.out.println("Calories lost: " + (calorieIntake - (calorieLimit + calorieBodyWeightSustain)) + "\n");
                            daysPassed++;
                            System.out.println("Days in a row: " + daysPassed + "\n");
                            System.out.println("If you are done type 'exit' \n");
                            try {
                                String saveWeight = String.valueOf(weight);
                                FileWriter writeWeight = new FileWriter("dragonTrackProgress.txt");
                                writeWeight.write(saveWeight);
                                writeWeight.close();
                                System.out.println("Successfully wrote to the file.");
                            } catch (IOException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                            break;
                        }

                        case "no": {
                            System.out.println("Shame on you! \nHow many pounds did you eat?");
                            double calorieIntake = input.nextInt();
                            weight = weight - (calorieBodyWeightSustain - calorieIntake);
                            System.out.println("You now weigh: " + Math.floor(weight));
                            System.out.println("Calories needed: " + Math.ceil(((calorieBodyWeightSustain - calorieIntake))));
                            daysPassed = 0;
                            System.out.println("If you are done type 'exit' \n");
                            break;
                        }

                        case "exit": {
                            terminate = true;
                            break;
                        }
                    }

                } while (!terminate);
                break;
            }
            case 2: {
                try {
                    File readWeight = new File("dragonTrackProgress.txt");
                    Scanner readFile = new Scanner(readWeight);
                    while (readFile.hasNextLine()) {
                        String data = readFile.nextLine();
                        weight = Double.parseDouble(data);
                    }
                    readFile.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println(Math.floor(weight));
                break;
            }
            case 3: {
                try {
                    weight = 500;
                    String saveWeight = String.valueOf(weight);
                    FileWriter writeWeight = new FileWriter("dragonTrackProgress.txt");
                    writeWeight.write(saveWeight);
                    writeWeight.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                try {
                    int setWeight = listChoice.nextInt();
                    weight = setWeight;
                    String saveWeight = String.valueOf(weight);
                    FileWriter writeWeight = new FileWriter("dragonTrackProgress.txt");
                    writeWeight.write(saveWeight);
                    writeWeight.close();
                    System.out.println("Successfully set weight.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
