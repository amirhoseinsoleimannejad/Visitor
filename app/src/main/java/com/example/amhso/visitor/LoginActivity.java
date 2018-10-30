package com.example.amhso.visitor;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;


import android.os.AsyncTask;


import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {




    private EditText password;
    private EditText Username;

    private String pass="";
    private String user="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        G.activity=this;

        password = (EditText) findViewById(R.id.password);
        Username = (EditText) findViewById(R.id.username);







        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);


        if(!sharedpreferences.getString(G.id_user ,"0").equals("0")) {
            Intent myIntent = new Intent(G.activity, MainActivity.class);
            startActivity(myIntent);
            finish();
        }




        Button mEmailSignInButton = (Button) findViewById(R.id.login);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                pass=password.getText().toString();
                user=Username.getText().toString();


                HttpPostAsyncTask task = new HttpPostAsyncTask();
                task.execute(G.urlserver + "authvisitor");


            }
        });






        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(G.activity,SignupActivity.class);
                startActivity(myIntent);

            }
        });


    }









    public class HttpPostAsyncTask extends AsyncTask<String, String, String> {


        HttpPost httppost;
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        public ProgressDialog progressDialog;



        @Override
        protected void onPostExecute(String result) {

            Log.i("22222222222222222", "22222222222222222222222222" + result);


            try {
                progressDialog.dismiss();

            }
            catch (Exception e){

            }

            String responce[]=result.split(":");

            if (responce[0].equals("1")){

                SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(G.id_user,responce[1]);
                editor.commit();


                Intent myIntent = new Intent(G.activity,MainActivity.class);
                startActivity(myIntent);
                G.activity.finish();
            }
            else{
                Toast.makeText(G.activity, "وارد شوي معلومات سم نه دي.", Toast.LENGTH_LONG).show();
            }


        }






        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(G.activity,
                    "په مهربانۍ سره لږ ځنډ وکړئ",
                    "په درناوي");
        }



        // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
        @Override
        protected String doInBackground(String... params) {

            try {


                Log.i("urluuuuuuuuuuuuuuu", "doInBackground: "+params[0]);

                httpclient=new DefaultHttpClient();
                httppost= new HttpPost(params[0]); // make sure the url is correct.
                //add your data

                Log.i("uuuuuu", "urluuuuuuuuuuuu "+params[0]);
                nameValuePairs = new ArrayList<>(2);
                nameValuePairs.add(new BasicNameValuePair("username",user.trim()));
                nameValuePairs.add(new BasicNameValuePair("password",pass.trim()));


//                Log.i("dddddddddd", "doInBackground: "+shpref.getString("id_user","-1").trim());
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Response : " + response);
                return response;



            } catch (Exception e) {
                Log.i("error rrrrrrr", e.toString());
            }

            return "0";
        }
    }
}

