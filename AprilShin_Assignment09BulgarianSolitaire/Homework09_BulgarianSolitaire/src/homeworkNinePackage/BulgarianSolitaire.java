// April Shin
// March 31, 2018
// Assignment 09 - Bulgarian Solitaire
// CSIS 139 Advanced Java

package homeworkNinePackage;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class BulgarianSolitaire 
{
	public static int numRounds = 1;

	public static void main(String[] args)
	{
		generatePiles();
	}
	
	
	public static void generatePiles()
	{
		ArrayList<Integer>insideArray = new ArrayList<Integer>();
		ArrayList <Integer> cardArrangement = new ArrayList <Integer>();
		Random randomNums = new Random();
		int begNumPiles;
		int sum = 0;		
		
		
		// Generating random number of piles
		begNumPiles = randomNums.nextInt(45)+1;
		
		
		// To distribute random number of cards in each pile
		cardArrangement.add(0, randomNums.nextInt(45/begNumPiles)+1);
		int remaining = 45 - cardArrangement.get(0);

		for (int i = 1; i < begNumPiles-1; ++i)
		{
			int inside;
			inside = (remaining/(begNumPiles-i))+1;
			insideArray.add(inside);
			cardArrangement.add(i, randomNums.nextInt(inside)+1);
			remaining = remaining - cardArrangement.get(i);
		}
		for (int i = 0; i < begNumPiles-1; ++i)
		{
			sum = sum + cardArrangement.get(i);
		}
		cardArrangement.add(45-sum);
		
		// Sorting piles of cards in ascending order
		Collections.sort(cardArrangement);
		
		
		// Just in case one pile ends up with zero cards, add one card to the empty pile and subtract one card from a guaranteed full pile(the last pile after sorting)
		if (cardArrangement.get(0)==0)
		{
			cardArrangement.set(0, 1);
			cardArrangement.set(cardArrangement.size()-1, (cardArrangement.get(cardArrangement.size()-1))-1);
		}
		
		// Sorting again just in case any changes were made from the lines of code above
		Collections.sort(cardArrangement);
		
		display(cardArrangement);
		playGame(cardArrangement);
	}
	
	public static void playGame(ArrayList<Integer> modifiedArray)
	{
		int zeros = 0; // initializing number of zeros in array


		// Subtracting one card from each pile
		for(int i = 0; i < modifiedArray.size();++i)
		{
			modifiedArray.set(i, modifiedArray.get(i)-1);
		}

		// Adding all of the subtracted cards and creating a new pile with those cards
		modifiedArray.add(modifiedArray.size());
		
		// Sorting piles in ascending order
		Collections.sort(modifiedArray);

		// Counting number of piles with a number of zero or less
		for(int i = 0; i < modifiedArray.size(); ++i)
		{
			if(modifiedArray.get(i) <= 0)
			{
				++zeros;
			}
		}
		
		// Eliminating all piles with a number of zero or less
		for(int i = 0; i < zeros; ++i)
		{
			modifiedArray.remove(0);
		}
		
		display(modifiedArray);
		gameOver(modifiedArray);
	}
	
	public static void gameOver(ArrayList<Integer> input)
	{
		int equalCounter = 0;
		
		if ( input.size() == 9)
		{
			for (int i = 0; i < 9; ++i)
			{
				if(input.get(i) == i+1)
				{
					++equalCounter;
				}
			}
			
			if(equalCounter == 9)
				System.out.println("\n\nGAME OVER");
			else if (equalCounter != 9)
				playGame(input);
		}
		else
		{
			playGame(input);
		}
	}	
	
	public static void display(ArrayList<Integer>input)
	{
		System.out.print("\n\nRound " + numRounds + ":     ");
		for(int i = 0; i < input.size();++i)
		{
			System.out.print(input.get(i) + " ");
		}
		++numRounds;
	}
}
