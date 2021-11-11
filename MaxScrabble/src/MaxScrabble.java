import java.io.IOException;

public class MaxScrabble extends TextFileAccessor {
	private final int[] SCRABBLE_SCORES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
			10 };
	private final int ASCII_CODE_a = 97;
	private double highestScore;
	private String winningString;

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
//					letterScore *= 2; Same thing as below but different format
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

	private int getLetterPos(char ch) {
		return ch - ASCII_CODE_a;
	}

	@Override
	public void printReport() {
		System.out.println("winner: \"" + winningString + "\" score: " + highestScore);

	}

}
