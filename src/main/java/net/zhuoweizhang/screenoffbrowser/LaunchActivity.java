package net.zhuoweizhang.screenoffbrowser;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;

public class LaunchActivity extends Activity implements TextView.OnEditorActionListener {
	private TextView urlText;
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.launch);
		urlText = (TextView) findViewById(R.id.url);
		urlText.setOnEditorActionListener(this);
	}
	public void pastePressed(View v) {
		// get the text from the clipboard
		android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		if (!clipboardManager.hasText()) {
			return;
		}
		launchBrowser(clipboardManager.getText().toString());
	}
	private void launchBrowser(String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url), this, BrowserActivity.class);
		startActivity(intent);
	}

	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		launchBrowser(urlText.getText().toString());
		return true;
	}
}