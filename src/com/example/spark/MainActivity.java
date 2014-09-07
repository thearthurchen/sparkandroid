package com.example.spark;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.spark.httpGeoProx.httpAsyncGet;
import com.example.spark.httpGeoProx.httpAsyncPost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
//		ImageButton addSpark = (ImageButton) findViewById(R.id.addSpark);
		ImageButton getSpark = (ImageButton) findViewById(R.id.getSpark);
		ImageButton cookSpark = (ImageButton) findViewById(R.id.cookSpark);
		final ImageView   status	= (ImageView) findViewById(R.id.status);
//		ImageButton stopSpark = (ImageButton) findViewById(R.id.stopSpark);

		handler = new Handler();
		
		httpAsyncGet getStatus = new httpAsyncGet();
		getStatus.addData("spark", "test");
		getStatus.execute("status");
		String response;
		
		try {
			response = getStatus.get();
			JSONObject jsonResp = new JSONObject(response.toString());
			Log.v("posted", jsonResp.getString("status"));
			if (jsonResp.getString("status").equals("0")) {
				status.setImageResource(R.drawable.statusidle);
			} else if (jsonResp.getString("status").equals("1")) {
				status.setImageResource(R.drawable.statuscooking);
			} else if (jsonResp.getString("status").equals("2")) {
				status.setImageResource(R.drawable.statusdone);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		httpAsyncGet getID = new httpAsyncGet();
//		getID.addData("grant_type", "test");
//		getID.addData("username", "thejasonliu@gmail.com");
//		getID.addData("password", "sparkcore");
//		getID.execute("oauth/token");
//		String getidresp;
//		
//		try {
//			getidresp = getStatus.get();
//			JSONObject jsonResp = new JSONObject(getidresp.toString());
//			Log.v("posted", jsonResp.getString("status"));
//			if (jsonResp.getString("status").equals("0")) {
//				status.setImageResource(R.drawable.statusidle);
//			} else if (jsonResp.getString("status").equals("1")) {
//				status.setImageResource(R.drawable.statuscooking);
//			} else if (jsonResp.getString("status").equals("2")) {
//				status.setImageResource(R.drawable.statusdone);
//			}
//
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		addSpark.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View myview) {
//				
//  				final View ref = myview;
//				httpAsyncPost post = new httpAsyncPost();
//				post.addData("spark",  "test");
//				post.execute("spark");
//				String response;
//				try {
//					response = post.get();
//					JSONObject jsonResp = new JSONObject(response.toString());
//					Log.v("posted", jsonResp.getString("status"));
//					if (jsonResp.getString("status").equals("0") || jsonResp.getString("status").equals("1") ) {
//						handler.post(new Runnable() {
//							@Override
//							public void run() {
//								Toast.makeText(ref.getContext(), "Added Spark!", Toast.LENGTH_LONG).show();
//							}
//				        }); 
//					} else if (jsonResp.getString("status").equals("2")) {
//						handler.post(new Runnable() {
//							@Override
//							public void run() {
//								Toast.makeText(ref.getContext(), "Bad Spark", Toast.LENGTH_LONG).show();
//							}
//				        }); 
//					}
//					Log.v("joinroom", response);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		});
		
		getSpark.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View myview) {
				
  				final View ref = myview;
  				httpAsyncGet get = new httpAsyncGet();
  				get.addData("spark",  "test");
  				get.execute("status");
				String response;
				try {
					response = get.get();
					JSONObject jsonResp = new JSONObject(response.toString());
					Log.v("posted", jsonResp.getString("status"));
					if (jsonResp.getString("status").equals("0")) {
						status.setImageResource(R.drawable.statusidle);
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Ready to Cook!", Toast.LENGTH_LONG).show();
							}
				        }); 
						
					} else if (jsonResp.getString("status").equals("1")) {
						status.setImageResource(R.drawable.statuscooking);
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Cooking!", Toast.LENGTH_LONG).show();
							}
				        }); 
					} else if (jsonResp.getString("status").equals("2")) {
						status.setImageResource(R.drawable.statusdone);
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Done!", Toast.LENGTH_LONG).show();
							}
				        }); 
					}
					Log.v("joinroom", response);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		//TODO: server should automatically set status back to zero
		cookSpark.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View myview) {
				
  				final View ref = myview;
				httpAsyncPost post = new httpAsyncPost();
				post.addData("spark",  "test");
				post.addData("cook",  "1");
				post.execute("spark");
				String response;
				try {
					response = post.get();
					JSONObject jsonResp = new JSONObject(response.toString());
					Log.v("posted", jsonResp.getString("status"));
					if (jsonResp.getString("status").equals("0")) {
						status.setImageResource(R.drawable.statuscooking);
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Cooking!", Toast.LENGTH_LONG).show();
							}
				        }); 
					} else if (jsonResp.getString("status").equals("1")) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Already Cooking!", Toast.LENGTH_LONG).show();
							}
				        }); 
					} else if (jsonResp.getString("status").equals("2")) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ref.getContext(), "Stopping!", Toast.LENGTH_LONG).show();
							}
				        }); 
					}
					Log.v("joinroom", response);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
//		stopSpark.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View myview) {
//				
//  				final View ref = myview;
//				httpAsyncPost post = new httpAsyncPost();
//				post.addData("spark",  "test");
//				post.addData("cook",  "0");
//				post.execute("spark");
//				String response;
//				try {
//					response = post.get();
//					JSONObject jsonResp = new JSONObject(response.toString());
//					Log.v("posted", jsonResp.getString("status"));
//					if (jsonResp.getString("status").equals("0")) {
//						handler.post(new Runnable() {
//							@Override
//							public void run() {
//								Toast.makeText(ref.getContext(), "Cooking!", Toast.LENGTH_LONG).show();
//							}
//				        }); 
//					} else if (jsonResp.getString("status").equals("1")) {
//						handler.post(new Runnable() {
//							@Override
//							public void run() {
//								Toast.makeText(ref.getContext(), "Already Cooking!", Toast.LENGTH_LONG).show();
//							}
//				        }); 
//					} else if (jsonResp.getString("status").equals("2")) {
//						handler.post(new Runnable() {
//							@Override
//							public void run() {
//								Toast.makeText(ref.getContext(), "Stopping!", Toast.LENGTH_LONG).show();
//							}
//				        }); 
//					}
//					Log.v("joinroom", response);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		});
		
	}
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main_menu, menu);
//		return true;
//	}
//	


}
