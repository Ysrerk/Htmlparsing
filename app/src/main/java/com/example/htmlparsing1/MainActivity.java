package com.example.htmlparsing1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String url="https://alexkanada.000webhostapp.com/htmlpars.php";
    Document document;
    TextView textView,h1textview,ptextview,litextview;
    Elements h1element,pelement,imgelement,ullielement;
    String imgurl;
    ImageView imageView;
    /* modue gradle icine  asagidaki kodun eklenmesi gerekiyor
    implementation "org.jsoup:jsoup:1.7.3"
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Htmlparsing().execute();
        /*Htmlparsing htmlparsing=new Htmlparsing();
        htmlparsing.execute();*/
    }
    public class Htmlparsing extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Burada tanimmlamalari yapiyorum
            textView=findViewById(R.id.title);
            h1textview=findViewById(R.id.h1);
            ptextview=findViewById(R.id.p);
            imageView=findViewById(R.id.img);
            litextview=findViewById(R.id.lil);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                //burada document icinden elementlei cekip atamalari yapiyorum
                document=Jsoup.connect(url).get();
                h1element=document.select("h1");
               pelement=document.select("p");
               imgelement=document.select("img");
               //element icindeki attribute erismek icin bu sekilde aliyoruz.
               imgurl=imgelement.attr("src");

               ullielement=document.select("ul>li");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i("sonuc",""+imgelement);
            //Burada degerleri textviewlara atamasini yapiyorum.
            textView.setText(document.title());
            h1textview.setText(h1element.text());
            ptextview.setText(pelement.text());
            //resimi gosteriyoruz
            Picasso.with(getApplicationContext()).load(imgurl).into(imageView);
            //li elementi degerlerini textview e atamasini yaptim
            litextview.setText(ullielement.text());
            ///li elementini logda gosterme
            for(Element i:ullielement){
                Log.i("eger",""+i.text());
            }
        }
    }
}