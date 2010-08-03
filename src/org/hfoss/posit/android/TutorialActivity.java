package org.hfoss.posit.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Tutorial activity is a class designed to make the user more comfortable with
 * posit the first time posit is opened. contains 4 pages of information, with
 * previous, next, skip, and finish buttons.
 * 
 */

public class TutorialActivity extends Activity implements OnClickListener {

	private int pageNumber;
	private WebView mWebView;
	private Button next;
	private Button previous;
	private Button finish;
	private Button skip;

	/**
	 * Method that is executed the first time the user opens the program.
	 * Creates the activity space and displays the first page with the
	 * accompanying buttons.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebView = new WebView(this);
		setContentView(R.layout.tutorial_view);
		pageNumber = 1;
		skip = (Button) findViewById(R.id.skipButton);
		next = (Button) findViewById(R.id.nextButton);
		previous = (Button) findViewById(R.id.previousButton);
		finish = (Button) findViewById(R.id.finishButton);

		skip.setOnClickListener(this);
		next.setOnClickListener(this);
		previous.setOnClickListener(this);
		finish.setOnClickListener(this);

		updateView();

	}

	/**
	 * Method that handles what happens when user changes the page. Receives the
	 * requested page number and then displays that page with appropriate
	 * buttons.
	 */
	private void updateView() {
		switch (pageNumber) {
		case 1:
			findViewById(R.id.previousButton).setVisibility(EditText.GONE);
			mWebView = (WebView) (findViewById(R.id.tutorialView));
			mWebView.loadUrl("file:///android_asset/tutorialpage1.html");
			break;
		case 2:
			findViewById(R.id.finishButton).setVisibility(EditText.GONE);
			findViewById(R.id.skipButton).setVisibility(EditText.VISIBLE);
			findViewById(R.id.previousButton).setVisibility(EditText.VISIBLE);
			mWebView.loadUrl("file:///android_asset/tutorialpage2.html");
			break;
		case 3:
			findViewById(R.id.finishButton).setVisibility(EditText.GONE);
			findViewById(R.id.skipButton).setVisibility(EditText.VISIBLE);
			findViewById(R.id.previousButton).setVisibility(EditText.VISIBLE);
			mWebView.loadUrl("file:///android_asset/tutorialpage3.html");
			break;
		case 4:
			findViewById(R.id.finishButton).setVisibility(EditText.VISIBLE);
			findViewById(R.id.skipButton).setVisibility(EditText.GONE);
			mWebView.loadUrl("file:///android_asset/tutorialpage4.html");
			break;
		}

	}

	/**
	 * Method that handles when a user clicks on Buttons: skip, finish,
	 * previous, or next.
	 */
	@Override
	public void onClick(View v) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor mEdit = sp.edit();
		switch (v.getId()) {
		case (R.id.skipButton):
			mEdit.putBoolean("tutorialComplete", true);
			mEdit.commit();
			finish();
			break;
		case (R.id.finishButton):
			mEdit.putBoolean("tutorialComplete", true);
			mEdit.commit();
			finish();
			break;
		case R.id.previousButton:
			if (pageNumber > 0)
				pageNumber--;
			updateView();
			break;
		case R.id.nextButton:
			if (pageNumber < 4)
				pageNumber++;
			updateView();
			break;
		}
	}
}