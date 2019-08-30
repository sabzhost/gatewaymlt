package miladesign.cordova.gateway;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class GatewayPlugin extends CordovaPlugin {
	private static final String LOG_TAG = "Gateway";
	private static Activity mActivity = null;
	public CordovaInterface cordova = null;
	private String schema;

	@Override
	public void initialize(CordovaInterface initCordova, CordovaWebView WebView) {
		Log.e (LOG_TAG, "initialize");
		cordova = initCordova;
		super.initialize (cordova, webView);
	}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if ("initialize".equals(action)) {
			initialize(args, callbackContext);
			return true;
	    }
		if ("openUrl".equals(action)) {
			openUrl(args, callbackContext);
			return true;
	    }
		if ("getResult".equals(action)) {
			getResult(callbackContext);
			return true;
	    }
	    return false;
	}
	
	@Override
	public void onNewIntent(Intent intent) {
		cordova.getActivity().setIntent(intent);
		super.onNewIntent(intent);
    }
	
	private void initialize(JSONArray args, CallbackContext callbackContext) throws JSONException {
		schema = "malaaghe://app";
		mActivity = cordova.getActivity();
		callbackContext.success();
	}
	
	private void openUrl(JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String url = args.getString(0);
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				mActivity.startActivity(i);
			}
		});
	}
	
	private void getResult(final CallbackContext callbackContext) {
		final Intent intent = cordova.getActivity().getIntent();
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (intent.getData() != null) {
					PluginResult pr = new PluginResult(PluginResult.Status.OK, intent.getData().toString());
	    			pr.setKeepCallback(true);
	    			callbackContext.sendPluginResult(pr);
				} else {
					callbackContext.error("Intent is null!");
				}
			}
		});
	}
}
