/**
 * Fred Besteman
 * CIS 2818
 * Jackson
 * Application 2: QuizMeYourName
 * 
 *Main method and event handlers.
 */



package com.quizMe.app;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizMeFredBesteman extends Activity {

	int number;

	TextView question;
	RadioButton yesButton;
	RadioButton noButton;
	Button submit;
	Button next;

	String[] questions = {
			"Is this a valid Java identifer?\n@TheStart\n\n\n",
			"Is this a valid Java identifer?\nTax4State\n\n\n\n",
			"Will this code compile?\ninterface A { void run(); }\nclass B implements A { }\n\n",
			"Will this code compile?\ninterface A { void run(); }\nabstract class B implements A { }\n\n",
			"Will this code compile?\ninterface A { void run(); }\nclass B implements A {\n   public void run() {} \n}",
			"Will this code compile?\nclass X { private int x; }\nclass Y extends X { }\n\n",
			"Will this code compile?\nclass X { private int x; }\nclass Y extends X {\n   public Y() { x = 10; }\n",
			"Will this code compile?\nclass X { int x; }\nclass Y extends X {\n   public Y() { x = 10; }\n",
			"Will this code compile?\ninterface A { void run(); }\nclass B implements A {\n   void run() {} \n}", };

	boolean[] key = { false, true, false, true, true,

	true, false, true, false };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_me_fred_besteman);

		question = (TextView) findViewById(R.id.qstText);
		yesButton = (RadioButton) findViewById(R.id.radioYes);
		noButton = (RadioButton) findViewById(R.id.radioNo);
		submit = (Button) findViewById(R.id.submit);
		next = (Button) findViewById(R.id.next);

		NextButtonListener nextListener = new NextButtonListener();	
		next.setOnClickListener(nextListener);
		next.performClick();
	}

	class NextButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			
			//the following code generates a random index and sets a random question to be displayed
			Random random = new Random();
			number = random.nextInt(questions.length);
			question.setText(questions[number]);
			
			question.setBackgroundColor(Color.WHITE);
			
			next.setEnabled(false); //greys out the next button
			submit.setEnabled(true); //greys out the submit button
			
			//creates and sets a submit button listener
			SubmitButtonListener submitListener = new SubmitButtonListener();
			submit.setOnClickListener(submitListener);
			
			question.setBackgroundColor(Color.WHITE);
		}
	}
	
	class SubmitButtonListener implements OnClickListener{
		
		@Override
		public void onClick(View v){
			
				//The following lines of code determine whether an answer is correct.
				boolean guess;
				if (yesButton.isChecked())
					guess = true;
				else
					guess = false;

				boolean answer = key[number];
				if (answer == guess)
				{
					Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
					question.setBackgroundColor(Color.GREEN);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Wrong answer...", Toast.LENGTH_SHORT).show();
					question.setBackgroundColor(Color.RED);					
				}
				next.setEnabled(true);
				submit.setEnabled(false);
		}
	}
				
}
