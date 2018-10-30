package com.example.amhso.visitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {


    String id_visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        G.activity=this;

        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        id_visitor=sharedpreferences.getString(G.id_user ,"0");



    }



    public void signdoctor(View view) {
        Intent jalil=new Intent(G.activity,SignupdoctorActivity.class);
        startActivity(jalil);
    }



    public void signsick(View view) {
        Intent jalil=new Intent(G.activity,SignupsickActivity.class);
        startActivity(jalil);
    }



    public void extended(View view) {
        Intent jalil=new Intent(G.activity,ListsickActivity.class);
        startActivity(jalil);
    }



    public void getcode(View view) {

        HttpPostAsyncTask task = new HttpPostAsyncTask();
        task.execute(G.urlserver + "getcodevisitor?id_visitor="+id_visitor);
    }










    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }













    public class HttpPostAsyncTask extends AsyncTask<String, String, String> {


        HttpPost httppost;
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        public ProgressDialog progressDialog;



        @Override
        protected void onPostExecute(String result) {

            Log.i("22222222222222222", "22222222222222222222222222" + result);



            if(!result.equals("0")){


                setClipboard(G.activity,result);
                Toast.makeText(G.activity,"ستاسو کوډ په کلیب برډ کې زخیره شو/کد شما در کلیب برد ذخیره شد",
                        Toast.LENGTH_SHORT).show();


            }
            else{
                Toast.makeText(G.activity,"تاسو د فعال کولو کوډ نلري/شما کدی برای فعال سازی ندارید",
                        Toast.LENGTH_SHORT).show();

                Intent jalil=new Intent(G.activity,BuycodeActivity.class);
                jalil.putExtra("id_visitor",id_visitor);
                startActivity(jalil);
            }

        }






        @Override
        protected void onPreExecute() {


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
