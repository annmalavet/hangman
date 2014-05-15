package com.malavet.hangman;
import java.util.ArrayList;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;


//cmd + Shift + F for formatting

public class Game {
	protected Main context;

	public Game(Context context) {
		this.context = (Main) context;
	}

	public void newGame() {
		
		context.randomWord = context.gW.getWord(context.words).toUpperCase();
		context.guessedLetters = new ArrayList<String>();
		showLetters();
	}

	public void showLetters() {
		String displayWord = context.randomWord;
		String noDuplicates = removeDups(context.randomWord);
		for (int i = 0; i < noDuplicates.length(); i++) {
			// this changes the letters to dashes
			if (!context.guessedLetters.contains("" + noDuplicates.charAt(i))) {
				displayWord = displayWord.replace("" + noDuplicates.charAt(i),
						"_ ");

			} else {
				// got it right and replacing
				displayWord = displayWord.replace("" + noDuplicates.charAt(i),
						"" + noDuplicates.charAt(i) + " ");
			}

		}
		TextView t = (TextView) context.findViewById(R.id.this_text);
		t.setText(displayWord);
	}

	
	public static String removeDups(final String input) {
		final StringBuilder resultString = new StringBuilder();
		// input length is randomWord.length
		for (int i = 0; i < input.length(); i++) {
			// substring is creating a string from a string it is
			// theWord.substring(int start, int end);
			String currentChar = input.substring(i, i + 1);
			// String.indexOf() returns the index within a string of the first
			// occurrence of the specified character or substring 
			if (resultString.indexOf(currentChar) < 0) 
				resultString.append(currentChar);
		}
		return "" + resultString;
	}

	// ///
	// ///This is called when a letter button is pressed
	// //
	public void newGuess(String letter) {
		//The letter is added on to guessedLetters - in case you want to do something with that
		context.guessedLetters.add(letter);
		if (!context.randomWord.contains(letter)) {
			hangEm();
		}
		showLetters();

	}

	public int mistakeCt() {
		int mistakes = 0;
		//This iterates through guessed letters and if the randomWord the game 
		//is using does NOT contain the guessedLetter then add +1 mistakes
		for (int i = 0; i < context.guessedLetters.size(); i++) {
			if (!context.randomWord.contains(context.guessedLetters.get(i)))
				mistakes++;
		}
		return mistakes;
	}

	// //
	// // This is the animation that changes based on how many mistakes are made
	
	public void hangEm() {

		context.img = (ImageView) context.findViewById(R.id.imageView1);

		int mistakeN = mistakeCt();
		if (mistakeN < 6) {
			//if mistakes are less than 6 - see how many there are and get the hangman animation that 
			//is first/next
			context.img.setBackgroundResource(context.getResources()
					.getIdentifier("hanging" + mistakeN, "anim",
							context.getPackageName()));
			// Get the background, which has been compiled to an
			// AnimationDrawable object.
			context.frameAnimation = (AnimationDrawable) context.img
					.getBackground();
			// Start the animation (looped playback by default).
			context.frameAnimation.stop();
			context.frameAnimation.start();

		} else {
			//if there are more than 6 mistakes he is dead
			//you could just change the background to an image - or you can add images to this file
			context.img.setBackgroundResource(R.anim.dead);
			// Get the background, which has been compiled to an
			// AnimationDrawable object.
			context.frameAnimation = (AnimationDrawable) context.img
					.getBackground();
			// Start the animation (looped playback by default).
			//This is stopped then started so it can start a new animation otherwise it is considered to still be running
			context.frameAnimation.stop();
			context.frameAnimation.start();
		}
	}
}
