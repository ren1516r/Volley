package com.example.user.volley;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
Button get_string,get_jsonobjt,get_image;
    AlertDialog progressDialog;
    String url = "https://androidtutorialpoint.com/api/volleyString";
    String url2="https://androidtutorialpoint.com/api/volleyJsonObject";
    String url3="https://androidtutorialpoint.com/api/lg_nexus_5x";

    public static final String TAG ="errrrrr" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_string=(Button)findViewById(R.id.get_string);

        get_string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyStringRequst(url);
            }
        });

        get_jsonobjt=(Button)findViewById(R.id.json_obj);
        get_jsonobjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonObjectRequest(url2);
            }
        });


        get_image=(Button)findViewById(R.id.jsn_image);
        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyimageRequest(url3);
            }


        });
    }

    private void volleyimageRequest(String url3) {
        ImageLoader imageLoader = AppSingleton.getInstance(getApplicationContext()).getImageLoader();
        
        

        imageLoader.get(url3, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {

                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                    View showDialogView = li.inflate(R.layout.json_image, null);
                    ImageView outputImageView = (ImageView) showDialogView.findViewById(R.id.id_image);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setView(showDialogView);
                    alertDialogBuilder
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .setCancelable(false)
                            .create();
                    outputImageView.setImageBitmap(response.getBitmap());
                    alertDialogBuilder.show();
                }
            }
        });
    }

    public void  volleyStringRequst(String url){

    String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

   /* progressDialog.setMessage("Loading...");
    progressDialog.show();*/

    StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {


        @Override
        public void onResponse(String response) {
            Log.d(TAG, response.toString());

            LayoutInflater li = LayoutInflater.from(MainActivity.this);
            View showDialogView = li.inflate(R.layout.strng, null);
            TextView outputTextView = (TextView) showDialogView.findViewById(R.id.textView);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setView(showDialogView);
            alertDialogBuilder
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setCancelable(false)
                    .create();
            outputTextView.setText(response.toString());
            alertDialogBuilder.show();
//            progressDialog.hide();
        }
    }, new Response.ErrorListener() {

        public static final String TAG = "Error";

        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
        //    progressDialog.hide();
        }
    });


    // Adding String request to request queue
    AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
}

    public void volleyJsonObjectRequest(String url){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";


        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        View showDialogView = li.inflate(R.layout.jsonobjct, null);
                        TextView outputTextView = (TextView) showDialogView.findViewById(R.id.txt_json);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq,REQUEST_TAG);
    }


}
