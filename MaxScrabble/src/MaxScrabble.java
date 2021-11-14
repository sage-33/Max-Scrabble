import java.io.IOException;

/**
 * Represents a file that is scanned and is then read where the sum of scores of
 * characters in a line is saved until the highest scoring line is found by
 * comparing each line to the line saved and saving the highest scoring line
 * until a new highest scoring line is found. This results with the final
 * winning string and its score is printed out.
 * 
 * @author sagesilberman
 *
 */
public class MaxScrabble extends TextFileAccessor {
	private final int[] SCRABBLE_SCORES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
			10 }; // array of respective Scrabble scores to each letter
	private final int ASCII_CODE_a = 97;// a number that represents the letter "a"
	private double highestScore; // highest scoring line
	private String winningString;// winning string of characters that form a line

	/**
	 * Initializes the code by setting the starting value for numOnes to 0 and
	 * frequency as an array with 26 values. Opens the file but throws IOException
	 * if it can't open the file.
	 * 
	 * @param filename the name of file
	 * @throws IOException if can't open file
	 */
	public MaxScrabble(String filename) throws IOException {
		openFile(filename);
		highestScore = 0;

	}

	@Override
	protected void processLine(String curLine) {
		curLine = curLine.toLowerCase();
		double curLineScore = 0;
		for (int i = 0; i < curLine.length(); i++) {
			char ch = curLine.charAt(i);
			if (Character.isLetter(ch) && getLetterPos(ch) < 26) {
				int letterScore = (SCRABBLE_SCORES[getLetterPos(ch)]);

				if (i % 4 == 0) {
					letterScore = letterScore * 2;

				} else if (i % 9 == 0) {

					letterScore = letterScore * 3;

				}

				curLineScore = curLineScore + letterScore;
			}

		}
		if (highestScore < curLineScore) {
			highestScore = curLineScore;
			winningString = curLine;
		}
	}

	/**
	 * Returns the position of a character by assigning each letter of the alphabet
	 * to a number
	 * 
	 * @param ch the character from file
	 * @return the position of a character by assigning each letter of the alphabet
	 *         to a number
	 */
	private int getLetterPos(char ch) {
		return ch - ASCII_CODE_a;
	}

	@Override
	public void printReport() {
		System.out.println("winner: \"" + winningString + "\" score: " + highestScore);

	}

}
