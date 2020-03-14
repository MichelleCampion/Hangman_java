import java.util.*;
import java.io.*;

public class Hangman
{
	public static void main(String [] args) throws Exception
	{

		String [][] illustration0 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|__"},
			{ "|          |  "},
			{ "|        _/ \\_"},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration1 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|__"},
			{ "|          |  "},
			{ "|        _/ \\"},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration2 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|__"},
			{ "|          |  "},
			{ "|         / \\"},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration3 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|__"},
			{ "|          |  "},
			{ "|         /   "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration4 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|__"},
			{ "|          |  "},
			{ "|            "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration5 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|        __|  "},
			{ "|          |  "},
			{ "|            "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration6 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|          |  "},
			{ "|          |  "},
			{ "|            "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration7 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|         / \\"},
			{ "|         \\_/ "},
			{ "|             "},
			{ "|             "},
			{ "|            "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		String [][] illustration8 = new String[][] {
			{ " __________"},
			{ "|          |"},
			{ "|             "},
			{ "|             "},
			{ "|             "},
			{ "|             "},
			{ "|            "},
			{ "|            "},
			{ "|            "},
			{ "|___________"}
		};

		FileIO reader = new FileIO();
        Scanner scan = new Scanner(System.in);

		//array of strings from dictionary file
        String [] dictionary = reader.load("C://Users//miche//OneDrive//Desktop//CS211//Lab5//dictionary.txt");    //Reading the File as a String array

		int n = dictionary.length;

		int lives = 9;


		int randomIndex = (int)Math.floor(Math.random()*n);
		//System.out.println(randomIndex);

		String gameWord = dictionary[randomIndex];
		gameWord = gameWord.substring(0, gameWord.length()-1);


		char [] gameLetters = new char [gameWord.length()];
		char [] playerLetters = new char[gameLetters.length];

		//keep track of new wrong guesses
		char [] wrongGuesses = new char[26];
		int wrongGuessIndex = 0;

		// starting display for player: letters represented as underscore chars
		for(int i=0; i<gameLetters.length; i++)
		{
			gameLetters[i] = gameWord.charAt(i);
			playerLetters[i] = '_';
		}


		boolean win = false;
		boolean startOfGame = true;
		char letterGuessed;


		System.out.println("*****WELCOME TO HANGMAN******");
		System.out.println();

		// game continues while not won and lives remaining...
		while(lives > 0 && win==false)
		{
			boolean letterFound = false;
			boolean letterRepeated = false;	// checks if player is repeating a guess


			printArray(playerLetters);
			System.out.println();

			// different output depending on whether game is just starting of mid-play
			if(startOfGame)
			{
				System.out.println("You have " + lives + " lives to guess the word!");
				System.out.print("Try guess a letter: ");
				letterGuessed = scan.next().charAt(0);
				System.out.println();
				startOfGame = false;
			}
			else
			{
				System.out.print("Enter your next guess: ");
				letterGuessed = scan.next().charAt(0);
				System.out.println();
			}

			for(int i=0; i<gameLetters.length; i++)
			{

				// if trying to guess a letter already guessed correctly
				if(playerLetters[i]==letterGuessed)
				{
					System.out.println("Hey! You've guessed that letter already! Try another...");
					System.out.println();
					letterFound = true;
					letterRepeated = true;
					break;
				}
				else if(gameLetters[i]==letterGuessed)	// if correct and not already guessed
				{
					playerLetters[i] = letterGuessed;
					letterFound=true;
				}
			}

			if(letterFound==true && letterRepeated == false)
			{
				System.out.println("Woohoo! Good guess!");
				System.out.println();
			}

			if(winner(playerLetters))
			{
				System.out.println("Congratulations! You have guessed the word!");
				System.out.println("*****************WINNER*******************");
				System.out.println();
				win = true;
				break;
			}

			if(letterFound==false)
			{
				if(checkForRepeatedGuess(letterGuessed, wrongGuesses)==false)
				{
					wrongGuesses[wrongGuessIndex] = letterGuessed;
					wrongGuessIndex++;
					lives--;

					if(lives==8) print2DArray(illustration8);
					else if(lives==7) print2DArray(illustration7);
					else if(lives==6) print2DArray(illustration6);
					else if(lives==5) print2DArray(illustration5);
					else if(lives==4) print2DArray(illustration4);
					else if(lives==3) print2DArray(illustration3);
					else if(lives==2) print2DArray(illustration2);
					else if(lives==1) print2DArray(illustration1);
					else if(lives==0) print2DArray(illustration0);

					System.out.println("Whoops! That's too bad!");
					System.out.println("You have " + lives + " lives remaining...");
					System.out.println();
				}
				else
				{
					System.out.println("Sorry, but you've tried that one already! Please try another letter...");
					System.out.println();
				}
			}

			if(lives==0)
			{
				System.out.println("The word was " + gameWord);
				System.out.println("*********GAME OVER***********");
				System.out.println();
				break;
			}

		}




	}

	public static void printArray(char [] array)
	{
		for(int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void print2DArray(String [][] array)
	{
		for(int row=0; row<array.length; row++)
		{
			for(int col=0; col<array[row].length; col++)
			{
				System.out.print(array[row][col]);
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	public static boolean winner(char [] array)
	{
		boolean win = true;

		for(int i=0; i<array.length; i++)
		{
			if(array[i]=='_')
			{
				win = false;
				break;
			}
		}

		return win;
	}

	public static boolean checkForRepeatedGuess(char c, char [] array)
	{
		boolean letterRepeated = false;
		for(int i=0; i<array.length; i++)
		{
			if(array[i]==c)
			{
				letterRepeated = true;
			}
		}
		return letterRepeated;
	}

}

class FileIO{

  public String[] load(String file) {
    File aFile = new File(file);
    StringBuffer contents = new StringBuffer();
    BufferedReader input = null;
    try {
      input = new BufferedReader( new FileReader(aFile) );
      String line = null;
      int i = 0;
      while (( line = input.readLine()) != null){
        contents.append(line);
        i++;
        contents.append(System.getProperty("line.separator"));
      }
    }
    catch (FileNotFoundException ex) {
      System.out.println("Can't find the file - are you sure the file is in this location: "+file);
      ex.printStackTrace();
    }
    catch (IOException ex){
      System.out.println("Input output exception while processing file");
      ex.printStackTrace();
    }
    finally {
      try {
        if (input!= null) {
          input.close();
        }
      }
      catch (IOException ex) {
        System.out.println("Input output exception while processing file");
        ex.printStackTrace();
      }
    }
    String[] array = contents.toString().split("\n");
    for(String s: array){
        s.trim();
    }
    return array;
  }


  public void save(String file, String[] array) throws FileNotFoundException, IOException {

    File aFile = new File(file);
    Writer output = null;
    try {
      output = new BufferedWriter( new FileWriter(aFile) );
      for(int i=0;i<array.length;i++){
        output.write( array[i] );
        output.write(System.getProperty("line.separator"));
      }
    }
    finally {
      if (output != null) output.close();
    }
  }
}
