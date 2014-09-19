package org.soft.rul.cuentas.ui.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.webkit.WebView;

import org.soft.rul.cuentas.R;

public class WebViewFragment extends Activity {

	public final static String TAG = WebViewFragment.class.getSimpleName();
//    ActionBar actionBar;

//	public WebViewFragment() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static WebViewFragment newInstance() {
//		return new WebViewFragment();
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);
        // Specify that tabs should be displayed in the action bar.
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActionBar().setDisplayHomeAsUpEnabled(true);
//		setRetainInstance(true);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://www.paulusworld.com");
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.fragment_webview, container, false);
//	}
//
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		WebView webView = (WebView) view.findViewById(R.id.webView);
//		webView.loadUrl("http://www.paulusworld.com");
//	}
}
