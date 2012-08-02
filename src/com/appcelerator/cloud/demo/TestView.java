
// added by Edward Sun - 2012-07-22

package com.appcelerator.cloud.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appcelerator.cloud.sdk.CCConstants;
import com.appcelerator.cloud.sdk.CCMeta;
import com.appcelerator.cloud.sdk.CCRequestMethod;
import com.appcelerator.cloud.sdk.CCResponse;
import com.appcelerator.cloud.sdk.CCUser;
import com.appcelerator.cloud.sdk.Cocoafish;
import com.appcelerator.cloud.sdk.CocoafishError;
import com.appcelerator.cloud.sdk.oauth2.DialogError;
import com.appcelerator.cloud.sdk.oauth2.DialogListener;

public class TestView extends Activity {

    static final int LAUNCH_SIGNUP = 0;
    static final String appKey = "UQV0xzXC7weM4qJVz37Ja8rT2m1bu8tw";
    static final String appConsumerKey = "PwlZOBLGdZ0SxTohTf51mGwrrLivN5CI";
    static final String appConsumerSecret = "cqSreePIH9pMW37MAFO5epKIqz46oWWD";
    static final String appApiHost = "api-staging.cloud.appcelerator.com/v1/";
    static final String appAuthHost = "security-staging.cloud.appcelerator.com";
//    boolean bStop = false;
    int indexCreateCocoafishSdk_1 = 0;
    int indexCreateCocoafishSdk_2_context = 0;
	int indexCreateCocoafishSdk_3_api_forKey = 0;
	int indexCreateCocoafishSdk_3_api_forApi = 0;
	int indexCreateCocoafishSdk_2_secret_forKey = 0;
	int indexCreateCocoafishSdk_2_secret_forSecret = 0;
	int indexCreateCocoafishSdk_3_secret_forKey = 0;
	int indexCreateCocoafishSdk_3_secret_forSecret = 0;
	int indexCreateCocoafishSdk_4_forKey = 0;
	int indexCreateCocoafishSdk_4_forSecret = 0;
	int indexCreateCocoafishSdk_4_forApi = 0;
	
	int indexID = 0;
	int indexKey = 0;
	int indexApi = 0;
	int indexAuth = 0;
	int indexSecret = 0;
	int indexSecure = 0;
	int indexAction = 0;
	int indexSecureArg = 0;
//    List<CCResponse> listOfCheckin;
    private Cocoafish sdk;
    private Cocoafish sdkGloble;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.testview);
        
        View acsAuthButton = findViewById(R.id.acsAuth); 
        acsAuthButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				performAuthorization();
		}});

        View acsSignUpButton = findViewById(R.id.acsSignUp); 
        acsSignUpButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				performACSSignUp();
		}});
        
        View testResetButton = findViewById(R.id.testReset); 
        testResetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testReset();
		}});
        
        View testCreateCocoafish_1_Button = findViewById(R.id.testCreateCocoafishSdk_1); 
        testCreateCocoafish_1_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_1();		
		}});
        
        View testCreateCocoafish_2_context_Button = findViewById(R.id.testCreateCocoafishSdk_2_context); 
        testCreateCocoafish_2_context_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_2_context();		
		}});
        
        View testCreateCocoafish_3_api_Button = findViewById(R.id.testCreateCocoafishSdk_3_api); 
        testCreateCocoafish_3_api_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_3_api();		
		}});
        
        View testCreateCocoafish_2_secret_Button = findViewById(R.id.testCreateCocoafishSdk_2_secret); 
        testCreateCocoafish_2_secret_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_2_secret();		
		}});
        
        View testCreateCocoafish_3_secret_Button = findViewById(R.id.testCreateCocoafishSdk_3_secret); 
        testCreateCocoafish_3_secret_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_3_secret();		
		}});
        
        View testCreateCocoafish_4_Button = findViewById(R.id.testCreateCocoafishSdk_4); 
        testCreateCocoafish_4_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testCreateCocoafishSdk_4();		
		}});
        
        View testSignin_arguments_Button = findViewById(R.id.testSignin_arguments); 
        testSignin_arguments_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignin_arguments();		
		}});
        
        View testSignin_withoutSecure_properties_Button = findViewById(R.id.testSignin_withoutSecure_properties); 
        testSignin_withoutSecure_properties_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignin_withoutSecure_properties();		
		}});
        
        View testSignin_withSecure_properties_Button = findViewById(R.id.testSignin_withSecure_properties); 
        testSignin_withSecure_properties_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignin_withSecure_properties();		
		}});
        
        View testSignup_arguments_Button = findViewById(R.id.testSignup_arguments); 
        testSignup_arguments_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignup_arguments();		
		}});
        
        View testSignup_withoutSecure_properties_Button = findViewById(R.id.testSignup_withoutSecure_properties); 
        testSignup_withoutSecure_properties_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignup_withoutSecure_properties();		
		}});
        
        View testSignup_withSecure_properties_Button = findViewById(R.id.testSignup_withSecure_properties); 
        testSignup_withSecure_properties_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignup_withSecure_properties();		
		}});
        
        View testSignout_Button = findViewById(R.id.testSignout); 
        testSignout_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignout();		
		}});
        
        View signin_Button = findViewById(R.id.signin); 
        signin_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				signin();		
		}});
        
        View testSignoutGloble_Button = findViewById(R.id.testSignoutGloble); 
        testSignoutGloble_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testSignoutGloble();		
		}});
        
        View signupGloble_Button = findViewById(R.id.signupGloble); 
        signupGloble_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				signupGloble();		
		}});
        
        View signinGloble_Button = findViewById(R.id.signinGloble); 
        signinGloble_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				signinGloble();		
		}});
        
        View testGetAndSet_Button = findViewById(R.id.testGetAndSet); 
        testGetAndSet_Button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				testGetAndSet();		
		}});
        
        sdkGloble = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
        sdkGloble.setAuthHost(appAuthHost);
        sdkGloble.useThreeLegged(true);
        
//        this.setView();
        
        
        //Honeycomb and above don't allow networking on main thread, otherwise NetworkOnMainThreadException
        //will be thrown. Applications targeting earlier SDK versions are allowed to do networking on their 
        //main event loop threads, but it's heavily discouraged. Networking should be done in AsyncTasks.
        //This is a temporary fix to make login/logout/signup work. 
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        
    }
  
    @Override
    public void onPause()
    {
    	super.onPause();
    	// Ugly solution to hide the soft keyboard by forcing it to show first, then toggle it
    	InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    	inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 
    			InputMethodManager.HIDE_IMPLICIT_ONLY); 
    	inputManager.toggleSoftInput(0, 0);
    }
    
    protected void performAuthorization() {

    	if( null!=sdk.getAccessToken() ) {
			Toast.makeText(TestView.this, "Authorizing", Toast.LENGTH_SHORT).show();
			try {
//				sdk.authorize(TestView.this, Cocoafish.ACTION_LOGIN, new LoginDialogListener(), false);
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new LoginDialogListener());
			} catch (CocoafishError e) {
				Toast.makeText( TestView.this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText( TestView.this, "Has valid session", Toast.LENGTH_SHORT).show();
			
			CCResponse result = null;
			String errorMsg = null;
			
			try {
				result = sdk.sendRequest("users/show/me.json", CCRequestMethod.GET, null, false);
				JSONObject resJson = result.getResponseData();
				if (resJson != null)
					Toast.makeText(TestView.this, resJson.toString(), Toast.LENGTH_SHORT).show();
				else 
					Toast.makeText(TestView.this, "null response data", Toast.LENGTH_SHORT).show();
				
//				showTestView();
				
			} catch (CocoafishError e) {
				errorMsg = e.getLocalizedMessage();
    			Toast.makeText( TestView.this, errorMsg, Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				errorMsg = e.getLocalizedMessage();
    			Toast.makeText( TestView.this, errorMsg, Toast.LENGTH_SHORT).show();
			}
			
		}
    }
    
    
    protected void performACSSignUp() {
		Toast.makeText(TestView.this, "Signing Up", Toast.LENGTH_SHORT).show();
		try {
			sdk.authorize(TestView.this, Cocoafish.ACTION_SIGNUP, new LoginDialogListener());
		} catch (CocoafishError e) {
			Toast.makeText( TestView.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
    }
    
	
	//***********************************************************************
	//***********************************************************************
	// LoginDialogListener - ypjin
	//***********************************************************************
	//***********************************************************************
	public final class LoginDialogListener implements DialogListener {
		public void onComplete(Bundle values) {
			CCResponse result = null;
			String errorMsg = null;
			try {
				//The user has logged in, so now you can query and use their cocoafish info
				result = sdk.sendRequest("users/show/me.json", CCRequestMethod.GET, null, false);
				JSONObject resJson = result.getResponseData();
    			Toast.makeText( TestView.this, resJson.toString(), Toast.LENGTH_SHORT).show();
//				Toast.makeText( Connect.this, "Thank you for Logging In, " + firstName + " " + lastName + "!", Toast.LENGTH_SHORT).show();

				//save your access token here
				//sdk.getAccessToken();
				//sdk.getAccessExpiresIn();
				
//				showTestView();
				
			} catch( CocoafishError error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			} catch( Exception error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		
		public void onCocoafishError(CocoafishError error) {
			Toast.makeText( TestView.this, "CocoafishError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		
		public void onError(DialogError error) {
			Toast.makeText( TestView.this, "DialogError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		 
        public void onCancel() {
			Toast.makeText( TestView.this, "Cancelled!", Toast.LENGTH_LONG).show();
        	//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
	}
	//-----------------------------------------------------
	// Edward Sun - 2012-07-22
	//-----------------------------------------------------
	public final class ListenerForVerify implements DialogListener {
		public void onComplete(Bundle values) {
			CCResponse result = null;
			String errorMsg = null;
			try {
				result = sdk.sendRequest("users/show/me.json", CCRequestMethod.GET, null, false);
				String strName = null!=((Object)sdk.getCurrentUser()) ? sdk.getCurrentUser().getUserName().toString() : "null";
				Date d = new Date(sdk.getAccessExpires());
				Toast.makeText(TestView.this, 
    							"Token:\t" + sdk.getAccessToken() + "\n" + 
    							"ExIn:\t" + String.valueOf(sdk.getAccessExpiresIn()) + "\n" + 
    							"Ex:\t" + String.valueOf(d) + "\n" + 
    							"Code:\t" + result.getMeta().getCode() + "\n" + 
    							"Method:\t" + result.getMeta().getMethod() + "\n" + 
    							"Status:\t" + result.getMeta().getStatus() + "\n" + 
    							"Message:\t" + result.getMeta().getMessage() + "\n" +
    							"Name:\t" + strName,
    							Toast.LENGTH_LONG).show();
				
			} catch( CocoafishError error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			} catch( Exception error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		
		public void onCocoafishError(CocoafishError error) {
			Toast.makeText( TestView.this, "CocoafishError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		
		public void onError(DialogError error) {
			Toast.makeText( TestView.this, "DialogError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		 
        public void onCancel() {
			Toast.makeText( TestView.this, "Cancelled!", Toast.LENGTH_LONG).show();
        	//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
	}
	
	public final class ListenerForVerifyGloble implements DialogListener {
		public void onComplete(Bundle values) {
			CCResponse result = null;
			String errorMsg = null;
			try {
				result = sdkGloble.sendRequest("users/show/me.json", CCRequestMethod.GET, null, false);
				String strName = null!=((Object)sdkGloble.getCurrentUser()) ? sdkGloble.getCurrentUser().getUserName().toString() : "null";
				Date d = new Date(sdkGloble.getAccessExpires());
				Toast.makeText(TestView.this, 
    							"Token:\t" + sdkGloble.getAccessToken() + "\n" + 
    							"ExIn:\t" + String.valueOf(sdkGloble.getAccessExpiresIn()) + "\n" + 
    							"Ex:\t" + String.valueOf(d) + "\n" + 
    							"Code:\t" + result.getMeta().getCode() + "\n" + 
    							"Method:\t" + result.getMeta().getMethod() + "\n" + 
    							"Status:\t" + result.getMeta().getStatus() + "\n" + 
    							"Message:\t" + result.getMeta().getMessage() + "\n" +
    							"Name:\t" + strName,
    							Toast.LENGTH_LONG).show();
				
			} catch( CocoafishError error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			} catch( Exception error ) {
				Toast.makeText( TestView.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		
		public void onCocoafishError(CocoafishError error) {
			Toast.makeText( TestView.this, "CocoafishError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		
		public void onError(DialogError error) {
			Toast.makeText( TestView.this, "DialogError: " + error.toString(), Toast.LENGTH_LONG).show();
			//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
		 
        public void onCancel() {
			Toast.makeText( TestView.this, "Cancelled!", Toast.LENGTH_LONG).show();
        	//Toast.makeText( Connect.this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
		}
	}
	
	protected void callSignin(Cocoafish mySdk)
	{
//		mySdk.setAppKey(appKey);
		mySdk.setAuthHost(appAuthHost);
//		mySdk.setHostname(appApiHost);
		mySdk.useThreeLegged(true);
		
		try {
			mySdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void printPro(String k, String v)
	{
		try
		{
			Toast.makeText(this, 
					"Setting Properties..." + "\n" +
					k + ":\t" + v, 
					Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void printOut(String key)
	{
		String secret = "";
		this.printOut(key, secret);
	}
	protected void printOut(String key, String secret)
	{
		String api = "";
		this.printOut(key, secret, api);
	}
	protected void printOut(String key, String secret, String api)
	{
		try
		{
			Toast.makeText(this, 
					"Create Cocoafish..." + "\n" +
					"Key:\t" + key + "\n" +
					"Secret:\t" + secret + "\n" +
					"ApiHost:\t" + api + "\n", 
					Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void testReset()
	{
		this.indexCreateCocoafishSdk_1 = 0;
		this.indexCreateCocoafishSdk_2_context = 0;
		this.indexCreateCocoafishSdk_3_api_forKey = 0;
		this.indexCreateCocoafishSdk_3_api_forApi = 0;
		this.indexCreateCocoafishSdk_2_secret_forKey = 0;
		this.indexCreateCocoafishSdk_2_secret_forSecret = 0;
		this.indexCreateCocoafishSdk_3_secret_forKey = 0;
		this.indexCreateCocoafishSdk_3_secret_forSecret = 0;
		this.indexCreateCocoafishSdk_4_forKey = 0;
		this.indexCreateCocoafishSdk_4_forSecret = 0;
		this.indexCreateCocoafishSdk_4_forApi = 0;
		
		this.indexID = 0;
		this.indexKey = 0;
		this.indexApi = 0;
		this.indexAuth = 0;
		this.indexSecret = 0;
		this.indexSecure = 0;
		this.indexAction = 0;
		this.indexSecureArg = 0;
	}
	
	protected void testCreateCocoafishSdk_1()
	{
		String arrSdk_1[] = {//appConsumerKey, 
									//"this is wrong key",
									//"",
									null};
		
		int index = this.indexCreateCocoafishSdk_1;
		if(arrSdk_1.length==index)
		{
			Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
			return;
		}
		this.printOut(arrSdk_1[index]);
		sdk = new Cocoafish(arrSdk_1[index]);
		callSignin(sdk);
		this.indexCreateCocoafishSdk_1++;		
	}
	
	protected void testCreateCocoafishSdk_2_context()
	{
		String arrSdk_2_context[] = {appConsumerKey, 
									"this is wrong key",
									"",
									null};
		
		int index = this.indexCreateCocoafishSdk_2_context;
		if(arrSdk_2_context.length==index)
		{
			Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
			return;
		}
		this.printOut(arrSdk_2_context[index]);
		sdk = new Cocoafish(arrSdk_2_context[index], getApplicationContext());
		callSignin(sdk);
		this.indexCreateCocoafishSdk_2_context++;		
	}
	
	protected void testCreateCocoafishSdk_3_api()
	{
		String arrSdk_3_apiForKey[] = {//appConsumerKey, 
										"this is wrong key",
										//"",
										null};
		String arrSdk_3_apiForAPI[] = {//appApiHost,
										"www.baidu.com",
										"this is wrong api host",
										"",
										null};
		
		int indexForKey = this.indexCreateCocoafishSdk_3_api_forKey;
		int indexForApi = this.indexCreateCocoafishSdk_3_api_forApi;
		if(arrSdk_3_apiForKey.length > indexForKey)
		{
			this.printOut(arrSdk_3_apiForKey[indexForKey], "", this.appApiHost);
			sdk = new Cocoafish(arrSdk_3_apiForKey[indexForKey], getApplicationContext(), this.appApiHost);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_3_api_forKey++;
			return;			
		}	
		else if(arrSdk_3_apiForAPI.length > indexForApi)
		{
			this.printOut(this.appConsumerKey, "", arrSdk_3_apiForAPI[indexForApi]);
			sdk = new Cocoafish(this.appConsumerKey, getApplicationContext(), arrSdk_3_apiForAPI[indexForApi]);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_3_api_forApi++;
			return;
		}
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;
	}
	
	protected void testCreateCocoafishSdk_2_secret()
	{
		String arrSdk_2_secretForKey[] = {appConsumerKey, 
											"this is wrong key",
											"",
											null};
		String arrSdk_2_secretForSecret[] = {appConsumerSecret,
												"this is wrong secret",
												"",
												null};
		
		int indexForKey = this.indexCreateCocoafishSdk_2_secret_forKey;
		int indexForSecret = this.indexCreateCocoafishSdk_2_secret_forSecret;
		if(arrSdk_2_secretForKey.length > indexForKey)
		{
			this.printOut(arrSdk_2_secretForKey[indexForKey], this.appConsumerSecret);
			sdk = new Cocoafish(arrSdk_2_secretForKey[indexForKey], this.appConsumerSecret);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_2_secret_forKey++;
			return;			
		}	
		else if(arrSdk_2_secretForSecret.length > indexForSecret)
		{
			this.printOut(this.appConsumerKey, arrSdk_2_secretForSecret[indexForSecret]);
			sdk = new Cocoafish(this.appConsumerKey, arrSdk_2_secretForSecret[indexForSecret]);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_2_secret_forSecret++;
			return;
		}
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testCreateCocoafishSdk_3_secret()
	{
		String arrSdk_3_secretForKey[] = {appConsumerKey, 
											"this is wrong key",
											"",
											null};
		String arrSdk_3_secretForSecret[] = {appConsumerSecret,
												"this is wrong secret",
												"",
												null};
		
		int indexForKey = this.indexCreateCocoafishSdk_3_secret_forKey;
		int indexForSecret = this.indexCreateCocoafishSdk_3_secret_forSecret;
		if(arrSdk_3_secretForKey.length > indexForKey)
		{
			this.printOut(arrSdk_3_secretForKey[indexForKey], this.appConsumerSecret);
			sdk = new Cocoafish(arrSdk_3_secretForKey[indexForKey], this.appConsumerSecret, getApplicationContext());
			callSignin(sdk);
			this.indexCreateCocoafishSdk_3_secret_forKey++;
			return;			
		}	
		else if(arrSdk_3_secretForSecret.length > indexForSecret)
		{
			this.printOut(this.appConsumerKey, arrSdk_3_secretForSecret[indexForSecret]);
			sdk = new Cocoafish(this.appConsumerKey, arrSdk_3_secretForSecret[indexForSecret], getApplicationContext());
			callSignin(sdk);
			this.indexCreateCocoafishSdk_3_secret_forSecret++;
			return;
		}
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testCreateCocoafishSdk_4()
	{
		String arrSdk_4ForKey[] = {//appConsumerKey, 
											//"this is wrong key",
											//"",
											null};
		String arrSdk_4ForSecret[] = {appConsumerSecret,
												"this is wrong secret",
												"",
												null};
		String arrSdk_4ForAPI[] = {appApiHost,
									"www.baidu.com",
									"this is wrong api host",
									"",
									null};
		
		int indexForKey = this.indexCreateCocoafishSdk_4_forKey;
		int indexForSecret = this.indexCreateCocoafishSdk_4_forSecret;
		int indexForApi = this.indexCreateCocoafishSdk_4_forApi;
		if(arrSdk_4ForKey.length > indexForKey)
		{
			this.printOut(arrSdk_4ForKey[indexForKey], this.appConsumerSecret, this.appApiHost);
			sdk = new Cocoafish(arrSdk_4ForKey[indexForKey], this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_4_forKey++;
			return;			
		}	
		else if(arrSdk_4ForSecret.length > indexForSecret)
		{
			this.printOut(this.appConsumerKey, arrSdk_4ForSecret[indexForSecret], this.appApiHost);
			sdk = new Cocoafish(this.appConsumerKey, arrSdk_4ForSecret[indexForSecret], getApplicationContext(), this.appApiHost);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_4_forSecret++;
			return;
		}
		else if(arrSdk_4ForAPI.length > indexForApi)
		{
			this.printOut(this.appConsumerKey, this.appConsumerSecret, arrSdk_4ForAPI[indexForApi]);
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), arrSdk_4ForAPI[indexForApi]);
			callSignin(sdk);
			this.indexCreateCocoafishSdk_4_forApi++;
			return;
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testSignin_arguments()
	{
		sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
		sdk.setAuthHost(this.appAuthHost);
		sdk.useThreeLegged(true);
		//to pass in arguments
		String arrAction[] = {Cocoafish.ACTION_LOGIN,
								"invalid string",
								"",
								null};
		boolean arrSecureArg[] = {true, 
								false};
		
		int indexForAction = this.indexAction;
		int indexForSecureArg = this.indexSecureArg;
		
		if(arrAction.length > indexForAction)
		{
			this.printPro("Action", arrAction[indexForAction]);
			try {
				sdk.authorize(this, arrAction[indexForAction], new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAction++;
			return;			
		}	
		if(arrSecureArg.length > indexForSecureArg)
		{
			this.printPro("SecureArg", String.valueOf(arrSecureArg[indexForSecureArg]));
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), arrSecureArg[indexForSecureArg]);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecureArg++;
			return;			
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
	}
	
	protected void testSignin_withoutSecure_properties()
	{
		//to set properties
		String arrID[] = {//this.appKey,
							//"wrong ID",
							//"",
							//null
							};
		String arrKey[] = {//this.appConsumerKey,
							//"wrong key",
							//"",
							//null
							};
		String arrSecret[] = {//this.appConsumerSecret,
								//"wrong secret",
								//"",
								//null
								};
		String arrApi[] = {//this.appApiHost,
							//"www.baidu.com",
							//"wrong url",
							//"",
							//null
								};
		String arrAuth[] = {this.appAuthHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		
		int indexForID = this.indexID;
		int indexForKey = this.indexKey;
		int indexForApi = this.indexApi;
		int indexForAuth = this.indexAuth;
		int indexForSecret = this.indexSecret;
		
		if(arrID.length > indexForID)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAppKey(arrID[indexForID]);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("ID", arrID[indexForID]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexID++;
			return;			
		}	
		else if(arrKey.length > indexForKey)
		{
			sdk = new Cocoafish(arrKey[indexForKey], this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Key", arrKey[indexForKey]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexKey++;
			return;
		}
		else if(arrSecret.length > indexForSecret)
		{
			sdk = new Cocoafish(this.appConsumerKey, arrSecret[indexForSecret], getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secret", arrSecret[indexForSecret]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecret++;
			return;
		}
		else if(arrApi.length > indexForApi)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), arrApi[indexForApi]);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Api Host", arrApi[indexForApi]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexApi++;
			return;
		}
		else if(arrAuth.length > indexForAuth)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			this.printPro("Auth Host", arrAuth[indexForAuth]);
			sdk.setAuthHost(arrAuth[indexForAuth]);
			sdk.useThreeLegged(true);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAuth++;
			return;
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testSignin_withSecure_properties()
	{
		//to set properties
		String arrID[] = {//this.appKey,
							//"wrong ID",
							//"",
							//null
							};
		String arrKey[] = {//this.appConsumerKey,
							//"wrong key",
							//"",
							//null
							};
		String arrSecret[] = {//this.appConsumerSecret,
								//"wrong secret",
								//"",
								//null
								};
		String arrApi[] = {//this.appApiHost,
							//"www.baidu.com",
							//"wrong url",
							//"",
							//null
							};
		String arrAuth[] = {this.appAuthHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		boolean arrSecure[] = {true,
								false};
		
		int indexForID = this.indexID;
		int indexForKey = this.indexKey;
		int indexForApi = this.indexApi;
		int indexForAuth = this.indexAuth;
		int indexForSecret = this.indexSecret;
		int indexForSecure = this.indexSecure;
		
		if(arrID.length > indexForID)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			sdk.setAppKey(arrID[indexForID]);
			this.printPro("ID", arrID[indexForID]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexID++;
			return;			
		}	
		else if(arrKey.length > indexForKey)
		{
			sdk = new Cocoafish(arrKey[indexForKey], this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Key", arrKey[indexForKey]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexKey++;
			return;
		}
		else if(arrSecret.length > indexForSecret)
		{
			sdk = new Cocoafish(this.appConsumerKey, arrSecret[indexForSecret], getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secret", arrSecret[indexForSecret]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecret++;
			return;
		}
		else if(arrApi.length > indexForApi)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), arrApi[indexForApi]);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Api Host", arrApi[indexForApi]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexApi++;
			return;
		}
		else if(arrAuth.length > indexForAuth)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			this.printPro("Auth Host", arrAuth[indexForAuth]);
			sdk.setAuthHost(arrAuth[indexForAuth]);
			sdk.useThreeLegged(true);
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAuth++;
			return;
		}
		else if(arrSecure.length > indexForSecure)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secure", String.valueOf(arrSecure[indexForSecure]));
			try {
				sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify(), arrSecure[indexForSecure]);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecure++;
			return;
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testSignup_arguments()
	{
		sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
		sdk.setAuthHost(this.appAuthHost);
		sdk.useThreeLegged(true);
		//to pass in arguments
		String arrAction[] = {Cocoafish.ACTION_SIGNUP,
								"invalid string",
								"",
								null};
		boolean arrSecureArg[] = {true, 
								false};
		
		int indexForAction = this.indexAction;
		int indexForSecureArg = this.indexSecureArg;
		
		if(arrAction.length > indexForAction)
		{
			this.printPro("Action", arrAction[indexForAction]);
			try {
				sdk.authorize(this, arrAction[indexForAction], new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAction++;
			return;			
		}	
		if(arrSecureArg.length > indexForSecureArg)
		{
			this.printPro("SecureArg", String.valueOf(arrSecureArg[indexForSecureArg]));
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), arrSecureArg[indexForSecureArg]);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecureArg++;
			return;			
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
	}
	
	protected void testSignup_withoutSecure_properties()
	{
		//to set properties
		String arrID[] = {this.appKey,
							"wrong ID",
							"",
							null
							};
		String arrKey[] = {this.appConsumerKey,
							"wrong key",
							"",
							null
							};
		String arrSecret[] = {this.appConsumerSecret,
								"wrong secret",
								"",
								null
								};
		String arrApi[] = {this.appApiHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		String arrAuth[] = {this.appAuthHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		
		int indexForID = this.indexID;
		int indexForKey = this.indexKey;
		int indexForApi = this.indexApi;
		int indexForAuth = this.indexAuth;
		int indexForSecret = this.indexSecret;
		
		if(arrID.length > indexForID)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			sdk.setAppKey(arrID[indexForID]);
			this.printPro("ID", arrID[indexForID]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexID++;
			return;			
		}	
		else if(arrKey.length > indexForKey)
		{
			sdk = new Cocoafish(arrKey[indexForKey], this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Key", arrKey[indexForKey]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexKey++;
			return;
		}
		else if(arrSecret.length > indexForSecret)
		{
			sdk = new Cocoafish(this.appConsumerKey, arrSecret[indexForSecret], getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secret", arrSecret[indexForSecret]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecret++;
			return;
		}
		else if(arrApi.length > indexForApi)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), arrApi[indexForApi]);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Api Host", arrApi[indexForApi]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexApi++;
			return;
		}
		else if(arrAuth.length > indexForAuth)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			this.printPro("Auth Host", arrAuth[indexForAuth]);
			sdk.setAuthHost(arrAuth[indexForAuth]);
			sdk.useThreeLegged(true);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify());
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAuth++;
			return;
		}
		
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void testSignup_withSecure_properties()
	{
		//to set properties
		String arrID[] = {this.appKey,
							"wrong ID",
							"",
							null};
		String arrKey[] = {this.appConsumerKey,
							"wrong key",
							"",
							null};
		String arrSecret[] = {this.appConsumerSecret,
								"wrong secret",
								"",
								null};
		String arrApi[] = {this.appApiHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		String arrAuth[] = {this.appAuthHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		boolean arrSecure[] = {true,
								false};
		
		int indexForID = this.indexID;
		int indexForKey = this.indexKey;
		int indexForApi = this.indexApi;
		int indexForAuth = this.indexAuth;
		int indexForSecret = this.indexSecret;
		int indexForSecure = this.indexSecure;
		
		if(arrID.length > indexForID)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			sdk.setAppKey(arrID[indexForID]);
			this.printPro("ID", arrID[indexForID]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexID++;
			return;			
		}	
		else if(arrKey.length > indexForKey)
		{
			sdk = new Cocoafish(arrKey[indexForKey], this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Key", arrKey[indexForKey]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexKey++;
			return;
		}
		else if(arrSecret.length > indexForSecret)
		{
			sdk = new Cocoafish(this.appConsumerKey, arrSecret[indexForSecret], getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secret", arrSecret[indexForSecret]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecret++;
			return;
		}
		else if(arrApi.length > indexForApi)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), arrApi[indexForApi]);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Api Host", arrApi[indexForApi]);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexApi++;
			return;
		}
		else if(arrAuth.length > indexForAuth)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			this.printPro("Auth Host", arrAuth[indexForAuth]);
			sdk.setAuthHost(arrAuth[indexForAuth]);
			sdk.useThreeLegged(true);
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), true);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAuth++;
			return;
		}
		else if(arrSecure.length > indexForSecure)
		{
			sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
			sdk.setAuthHost(this.appAuthHost);
			sdk.useThreeLegged(true);
			this.printPro("Secure", String.valueOf(arrSecure[indexForSecure]));
			try {
				sdk.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerify(), arrSecure[indexForSecure]);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecure++;
			return;
		}

		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;	
	}
	
	protected void verifySignout(Cocoafish mySdk)
	{
		try {
			CCResponse result = null;
			result = mySdk.sendRequest("users/show/me.json", CCRequestMethod.GET, null, false);
			String strName = null!=((Object)mySdk.getCurrentUser()) ? mySdk.getCurrentUser().getUserName().toString() : "null";
			Date d = new Date(mySdk.getAccessExpires());
			Toast.makeText(TestView.this, 
							"Token:\t" + mySdk.getAccessToken() + "\n" + 
							"ExIn:\t" + String.valueOf(mySdk.getAccessExpiresIn()) + "\n" + 
							"Ex:\t" + String.valueOf(d) + "\n" + 
							"Code:\t" + result.getMeta().getCode() + "\n" + 
							"Method:\t" + result.getMeta().getMethod() + "\n" + 
							"Status:\t" + result.getMeta().getStatus() + "\n" + 
							"Message:\t" + result.getMeta().getMessage() + "\n" +
							"Name:\t" + strName,
							Toast.LENGTH_LONG).show();
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void signin()
	{
		sdk = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
		sdk.setAuthHost(this.appAuthHost);
		sdk.useThreeLegged(true);
		try {
			sdk.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerify());
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void signinGloble()
	{
		sdkGloble.setAuthHost(this.appAuthHost);
		sdkGloble.useThreeLegged(true);
		try {
			sdkGloble.authorize(this, Cocoafish.ACTION_LOGIN, new ListenerForVerifyGloble());
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void signupGloble()
	{
		sdkGloble.setAuthHost(this.appAuthHost);
		sdkGloble.useThreeLegged(true);
		try {
			sdkGloble.authorize(this, Cocoafish.ACTION_SIGNUP, new ListenerForVerifyGloble());
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void testSignoutGloble()
	{
		try {
//			String str = sdkGloble.logout(this, true);
			sdkGloble.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);			
			this.verifySignout(this.sdkGloble);
		} catch (CocoafishError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void testSignout()
	{
		//to set properties
		String arrID[] = {this.appKey,
							"wrong ID",
							"",
							null
							};
		String arrKey[] = {this.appConsumerKey,
							"wrong key",
							"",
							null
							};
		String arrSecret[] = {this.appConsumerSecret,
								"wrong secret",
								"",
								null
								};
		String arrApi[] = {this.appApiHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		String arrAuth[] = {this.appAuthHost,
							"www.baidu.com",
							"wrong url",
							"",
							null};
		boolean arrSecure[] = {true,
								false};
		
		int indexForID = this.indexID;
		int indexForKey = this.indexKey;
		int indexForApi = this.indexApi;
		int indexForAuth = this.indexAuth;
		int indexForSecret = this.indexSecret;
		int indexForSecure = this.indexSecure;
		
		if(arrID.length > indexForID)
		{
			sdk.setAppKey(arrID[indexForID]);
			this.printPro("ID", arrID[indexForID]);
			try {
//				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexID++;
			this.verifySignout(this.sdk);
			return;			
		}	
		else if(arrKey.length > indexForKey)
		{
			this.printPro("Key", arrKey[indexForKey]);
			try {
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexKey++;
			this.verifySignout(this.sdk);
			return;
		}
		else if(arrSecret.length > indexForSecret)
		{
			this.printPro("Secret", arrSecret[indexForSecret]);
			try {
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecret++;
			this.verifySignout(this.sdk);
			return;
		}
		else if(arrApi.length > indexForApi)
		{
			this.printPro("Api Host", arrApi[indexForApi]);
			try {
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexApi++;
			this.verifySignout(this.sdk);
			return;
		}
		else if(arrAuth.length > indexForAuth)
		{
			sdk.useThreeLegged(true);
			try {
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, false);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexAuth++;
			this.verifySignout(this.sdk);
			return;
		}
		else if(arrSecure.length > indexForSecure)
		{
			this.printPro("Secure", String.valueOf(arrSecure[indexForSecure]));
			try {
				sdk.sendRequest("users/logout.json", CCRequestMethod.GET, null, arrSecure[indexForSecure]);
			} catch (CocoafishError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			this.indexSecure++;
			this.verifySignout(this.sdk);
			return;
		}
		Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
		return;		
	}
	
	protected void testGetAndSet()
	{
		Cocoafish sdkT = new Cocoafish(this.appConsumerKey, this.appConsumerSecret, getApplicationContext(), this.appApiHost);
        sdkT.setAuthHost(appAuthHost);
        sdkT.useThreeLegged(true);
        
        String arrString[] = {"aString", 
        						"",
        						null};
//        long arrL[] = {0,
//        			-9223372036854775808L,
//        			9223372036854775807L,
//        			-9223372036854775808L - 1L,
//        			9223372036854775807L + 1L};
        long arrL[] = {0,
        		134,
        		1343,
    			3,
    			4};
        int arrI[] = {0,
        				2147483647,
        				-2147483648,
        				2147483647 + 1,
        				-2147483648-1
        				};
        		
        for(int i=0;i<3;i++)
        {
        	try
        	{
        		String str = arrString[i];
//	        	sdkT.setAccessExpiresIn(str);
	        	sdkT.setAccessToken(str);
	        	sdkT.setAppKey(str);
	        	sdkT.setAuthHost(str);
	        	sdkT.setHostname(str);
	        	
//	        	sdkT.getAccessExpiresIn();
	        	boolean b1 = str == sdkT.getAccessToken();
	        	boolean b2 = str == sdkT.getAppKey();
	        	boolean b3 = str == sdkT.getAuthHost();
	        	boolean b4 = str == sdkT.getHostname();
	        	if(b1 & b2 & b3 & b4){}
	        	else Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show();
        	}
        	catch(Exception e)
        	{
        		Toast.makeText(this, "Exception", Toast.LENGTH_LONG).show();
        	}       	
        }
//        for(int i=0;i<5;i++)
//        {
//        	try
//        	{
//        		sdkT.setAccessExpires(arrL[i]);
//        		sdkT.setAccessExpiresIn(arrI[i]);
////        		long l = sdkT.getAccessExpires();
//        		boolean b1 = arrL[i] == sdkT.getAccessExpires();
//        		boolean b2 = arrI[i] == sdkT.getAccessExpiresIn();
//        		if(b1 & b2) {}
//        		else Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show();
//        	}
//        	catch(Exception e)
//        	{
//        		Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show();
//        	}       	
//        }
        
        Toast.makeText(this, "Test Done", Toast.LENGTH_LONG).show();
        
	}
	protected void setView()
	{
		MyDlgCustomizer dlg = new MyDlgCustomizer();
		try
		{
			dlg.setUpTitle(null);
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();	
		}
		
		
		sdkGloble.setDlgCustomizer(dlg);
	}
	
}