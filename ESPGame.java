/*
 * Class: CMSC203 
 * Instructor: Professor Grinberg
 * Description: ESPGame allows the user to play a guessing game where the user inputs a file with select colors for them to guess in various
 * difficulties.
 * Due: 02/10/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Jonathan Chang
*/

import java.util.*;
import java.io.*;
public class ESPGame {

	public static void main(String[] args) throws FileNotFoundException{
		//C:\\Users\\jkjk8\\Downloads\\colors.txt
		
		
		//Declares variables that need to be used throughout the whole program.
		Scanner input = new Scanner(System.in);
		int option = 0;
		String fileName = "";
		int numColors;
		final int OPTION_1 = 16;
		final int OPTION_2 = 10;
		final int OPTION_3 = 5;
		
		
		System.out.println("CMSC203 Assignment1: Test your ESP skills!");
		System.out.println("Welcome to ESP - extrasensory perception!");

		//Uses a do-while loop because it wants to prompt the user to play at least once.
		do
		{		
			//Gives the user all the options and then allows them to play the game if they wish.
			System.out.println("Would you please choose one of the 4 options from the menu: ");
			System.out.println("     1-	read and display on the screen first 16 names of colors from a file colors.txt, so the player can select one of them names of colors.");
			System.out.println("     2-	read and display on the screen first 10 names of colors from a file colors.txt, so the player can select one of them names of colors.");
			System.out.println("     3-	read and display on the screen first 5 names of colors from a file colors.txt, so the player can select one of them names of colors.");
			System.out.println("     4- Exit from a program");
			System.out.print("Enter the option: ");
			option = input.nextInt();
			
			//If the user wants to play, then it requests the file name for the colors to be read from.
			if(option != 4)
			{
				input.nextLine();
				System.out.print("Enter the filename: ");
				fileName = input.nextLine();
			}
			
			//Since options 1, 2, and 3 require a different amount of color choices to be printed, the amount of colors to be printed this game is stored.
			if(option == 1 || option == 2 || option == 3)
			{
				if(option == 1)
					numColors = OPTION_1;
				else if(option == 2)
					numColors = OPTION_2;
				else
					numColors = OPTION_3;
				
				
				//Opening the file given by the user, it prints out the colors from the file. According to the option the user chose, that's how many colors are printed.
				try(Scanner file = new Scanner(new File(fileName)))
				{
					System.out.println("There are " + numColors + " colors from a file:");
					for(int i = 1; i <= numColors; i++)
					{
						System.out.println(i + " " + file.nextLine());
					}
					file.close();
				}
				
				
				System.out.println();
				
				
				/*
				 * While keeping track of the points the user has, using a for loop, the program chooses a color by random using random.nextInt().
				 * The number from the random class corresponds to each line of the file. For example, if the random class returns 1, then the color 
				 * corresponds to the color on the first line. By doing so, it ensures that a random color is chosen every time.
				 */
				int points = 0;
				for(int j = 1; j <= 3; j++)
				{
					Random random = new Random();
			        int randomNumber = random.nextInt(16) + 1;
			        String randomColor = "";
			        try(Scanner resetFile = new Scanner(new File(fileName)))
			        {
			        	String color = "";
			        	for(int i = 0; i < randomNumber; i++)
				        {
				        	color = resetFile.nextLine();
				        	
				        }
			        	randomColor = color;
			        	resetFile.close();
			        }
			        
			        //With the random color found, the user tries to guess that color. If they get it correct, then they get a point.
					System.out.println("Round " + j);
					System.out.println();
					System.out.println("I am thinking of a color.");
					System.out.println("Is it one of list of colors above?");
					System.out.println("Enter your guess: ");
					String guess = input.nextLine();
					if(guess.equalsIgnoreCase(randomColor))
						points++;
					System.out.println();
					System.out.println("I was thinking of " + randomColor);
				}
				
				//Lets the user know how many of the 3 questions they got correct.
				System.out.println("Game Over");
				System.out.println();
				System.out.println("You guessed " + points + " out of 3 colors correctly");
				
				//Prompts the user if they want to play again.
				System.out.println("Would you like to continue a Game? Type Yes/No");
				String again = input.nextLine();
				
				/*
				 * If they don't want to play again, then they will be prompted for their name, a description, and a due date. It is then printed out.
				 * Finally, a new .txt file is opened up and all information collected like score, name, description, date, are printed to the file.
				 * Sets option to 4 so they don't play again.
				 */
				if(again.equalsIgnoreCase("No"))
				{
					option = 4;
					System.out.print("Enter your name: ");
					String name = input.nextLine();
					System.out.print("Describe yourself: ");
					String describe = input.nextLine();
					System.out.print("Due Date: ");
					String dueDate = input.nextLine();
					System.out.println("Username: " + name);
					System.out.println("User Description: " + describe);
					System.out.println("Date: " + dueDate);
					
					
					PrintWriter returnFile = new PrintWriter(".\\EspGameResults.txt");
					returnFile.println("Game Over");
					returnFile.println("You guessed " + points + " out of 3 colors correctly.");
					returnFile.println("Due Date: 2/10");
					returnFile.println("Username: " + name);
					returnFile.println("User Description: " + describe);
					returnFile.println("Date: " + dueDate);
					returnFile.close();
				}
					
				
			}
		//Uses a loop so the user can continue to play until they choose not to
		}while(option != 4);
		
		
		
	}

}
