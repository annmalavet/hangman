package com.malavet.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.widget.Toast;

public class GetWords {
Main main; 

//this class is for getting the array from the Textfile/InputStream and also choosing a random word
	
public ArrayList<String> populateWords(InputStream stream){
	ArrayList<String> words;
	words = new ArrayList<String>();
	try {
		InputStreamReader inputStreamReader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line;
		while ((line = br.readLine()) != null) {
			words.add(line);
		}
		br.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block

	}
		return words;
	}
	
	public String getWord(ArrayList<String> words) {
		String randomWord = "";
			//this chooses a word from the array list
		    //based on a random number chosen from array size
		//what would be better would be making a random array and storing it in preferences
		//so that the random words arent repeated. one would have to update preferences
		//to switch up the order as well
			randomWord = words.get(new Random().nextInt(words.size()-1));

		return randomWord;

	}
}
