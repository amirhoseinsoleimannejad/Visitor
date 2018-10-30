package com.example.amhso.visitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ExtendActivity extends AppCompatActivity {

    public String id_sick;
    public String code;
    public String id_visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);


        G.activity=this;




        Bundle bundle = getIntent().getExtras();
        id_sick=bundle.getString("id_sick");



        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        id_visitor=sharedpreferences.getString(G.id_user ,"0");

    }




    public void extended(View view) {



        EditText codee=(EditText) G.activity.findViewById(R.id.codeextended);


        code=codee.getText().toString();


        HttpPostAsyncTask task = new HttpPostAsyncTask();
        task.execute(G.urlserver + "extended_sick");




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



            if(result.equals("1")){
                TextView textView=(TextView) G.activity.findViewById(R.id.condition);
                textView.setText("ستاسو کړنه سمه پایته ورسېدله/عملیات به درستی انجام شد.");

            }

            else {
                EditText codee=(EditText) G.activity.findViewById(R.id.codeextended);
                codee.setError("ستاسو کوډ د اعتبار وړ نه دی/کد شما نامعتبر می باشد");
            }


        }






        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(G.activity,
                    "په مهربانۍ سره لږ ځنډ وکړئ/لطفا صبر کنید",
                    "با تشکر");
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
                nameValuePairs = new ArrayList<>(3);
                nameValuePairs.add(new BasicNameValuePair("id_sick",id_sick.trim()));
                nameValuePairs.add(new BasicNameValuePair("code",code.trim()));
                nameValuePairs.add(new BasicNameValuePair("id_visitor",id_visitor.trim()));


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
