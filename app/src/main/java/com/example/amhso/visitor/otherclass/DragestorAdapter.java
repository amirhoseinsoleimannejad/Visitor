package com.example.amhso.visitor.otherclass;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.amhso.visitor.G;
import com.example.amhso.visitor.ListsickActivity;
import com.example.amhso.visitor.ExtendActivity;
import com.example.amhso.visitor.R;

import java.util.List;


public class DragestorAdapter extends ArrayAdapter<dragestor> {

    private final Activity context;
    private final List<dragestor> itemname;



    public DragestorAdapter(Activity context, List<dragestor> itemname) {
        super(context, R.layout.listdragestor, itemname);
        this.context=context;
        this.itemname=itemname;
    }


    public View getView(final int position, View view, ViewGroup parent) {




        View listItem = view;
        MyWrapper wrapper = null;


        try {

            if (listItem == null) {

                listItem = LayoutInflater.from(context).inflate(R.layout.listdragestor, parent, false);
                wrapper = new MyWrapper(listItem);
                listItem.setTag(wrapper);

            } else {
                wrapper = (MyWrapper) listItem.getTag();
            }


            wrapper.getText().setText(itemname.get(position).getText());
            wrapper.getAddress().setText(itemname.get(position).getAddress());
            wrapper.getMobile().setText(itemname.get(position).getMobile());





            wrapper.getMobile().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(itemname.get(position).getMobile())));
                    G.activity.startActivity(intent);

                }
            });




            wrapper.getAddress().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent jalil=new Intent(G.activity,ExtendActivity.class);

                    jalil.putExtra("id_sick",itemname.get(position).getId());
                    G.activity.startActivity(jalil);
                }
            });


        }
        catch (Exception e){
            Log.i("eeeeee", "eeeeeeeeeeeeeeee"+e.toString());
        }



        return listItem;


    };







    class MyWrapper
    {
        private View base;
        private TextView text;
        private TextView mobile;
        private TextView address;

        public MyWrapper(View base)
        {
            this.base = base;
        }



        public TextView getText(){
            if(text == null){
                text = (TextView) base.findViewById(R.id.text);

            }
            return text;
        }






        public TextView getMobile(){
            if(mobile == null){
                mobile = (TextView) base.findViewById(R.id.mobile);

            }
            return mobile;
        }





        public TextView getAddress(){
            if(address == null){
                address = (TextView) base.findViewById(R.id.address);

            }
            return address;
        }




    }
}
