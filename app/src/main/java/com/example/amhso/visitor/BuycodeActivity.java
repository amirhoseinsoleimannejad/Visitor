package com.example.amhso.visitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class BuycodeActivity extends AppCompatActivity {

    public String id_visitor;
    public String date;
    public String description;
    public String qty;
    public String how_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycode);

        G.activity=this;



        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        id_visitor=sharedpreferences.getString(G.id_user ,"0");



        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText qtye=(EditText)findViewById(R.id.qty);
                qty=qtye.getText().toString();


                EditText datee=(EditText)findViewById(R.id.date_pay);
                date=datee.getText().toString();


                EditText descriptione=(EditText)findViewById(R.id.description_pay);
                description=descriptione.getText().toString();


                EditText how_paye=(EditText)findViewById(R.id.how_pay);
                how_pay=how_paye.getText().toString();





                HttpPostAsyncTask task = new HttpPostAsyncTask();
                task.execute(G.urlserver + "request_code");


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


            if (result.equals("1")){


                Toast.makeText(G.activity, "ستاسو غوښتنه سمه ده. / درخواست شما به درستی ارسال شد.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(G.activity, "ستاسو غوښتنه د تېروتنې سره مخ شوه / درخواست شما  با خطا مواجه شد", Toast.LENGTH_LONG).show();
            }


        }






        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(G.activity,
                    "په مهربانۍ سره لږ ځنډ وکړئ/چند لحظه منتظر بمانید",
                    "په درناوي/با تشکر");
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
                nameValuePairs = new ArrayList<>(5);
                nameValuePairs.add(new BasicNameValuePair("id_visitor",id_visitor.trim()));
                nameValuePairs.add(new BasicNameValuePair("date",date.trim()));
                nameValuePairs.add(new BasicNameValuePair("description",description.trim()));
                nameValuePairs.add(new BasicNameValuePair("qty",qty.trim()));
                nameValuePairs.add(new BasicNameValuePair("how_pay",how_pay.trim()));



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
