package com.malavet.hangman;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity implements OnClickListener{
	
	//array of words that are in the game
	public ArrayList<String> words = new ArrayList<String>();
	//word for the game - chosen randomly
	String randomWord;
	///to add to the list of guessed letters
	List<String> guessedLetters;
	Context context;
	//the view of the word to guess
	TextView text;
	GetWords gW;
	Game g;
	//for animation
	AnimationDrawable frameAnimation;
	ImageView img;
	/////
	////
	int mistakes = 0;
	//
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gW = new GetWords();
		g = new Game(this);
	
		///
		mistakes=0;

		try {
			words = gW.populateWords(getAssets().open("words.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		////To toast the words.txt file located in assets folder
		/*for (int i=0;i<words.size(); i++){
		 Toast toast = Toast.makeText(this, words.get(i),
					Toast.LENGTH_SHORT);
					 toast.show();
		}*/
		// FOR A NEW GAMEnewGame() has to be below populating words
		//////
		////
		g.newGame();
		b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
		    public void onClick(View v){
		        //do something
		    	g.newGame();
		    	populateKeyboard();
		    	mistakes=0;
		    	img.setBackgroundResource(R.drawable.gallows);
		    }
		});
		////
		//////
		populateKeyboard();
		////
		///
	}

	/////////
	////////
	////////
	////////
/////////
////////
/////////
////////
	public void populateKeyboard() {

		GridView keyboard = (GridView) findViewById(R.id.grid);
		/* disable scrolling */
		keyboard.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		return true;
		}
		return false;
		}
		});

		//this is putting every letter on the keyboard
		Button alpha = null;
		ArrayList<Button> mButtons = new ArrayList<Button>();
		for (char buttonChar = 'A'; buttonChar <= 'Z'; buttonChar++) {
			alpha = new Button(this);
			alpha.setText("" + buttonChar);
			alpha.setPadding(0, 0, 0, 0);
			alpha.setId(buttonChar);
			alpha.setTextColor(Color.parseColor("white"));
			alpha.setTextSize(25);
			alpha.setOnClickListener(this);
			alpha.setBackgroundColor(Color.parseColor("#000000"));
			
			alpha.setOnClickListener(this);
		//}
		mButtons.add(alpha);
		}

		keyboard.setAdapter(new CustomAdapter(mButtons));
		}
////////
////////
////////
////////
/////////
////////
////////
////////
/////////
////////
////////
////////
	@Override
	protected void onResume() {
		super.onResume();
	//
	}
	
	
	
	
	/////we arent using the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}


	@Override
	public void onClick(View v) {
		///
		////
		Button selection = (Button) v;
		//changing background color of the letter when pressed
		selection.setBackgroundColor(Color.parseColor("#3399FF"));
		selection.setOnClickListener(null);
		// to process the new guess
		g.newGuess((String) selection.getText());
		///
	}



}
