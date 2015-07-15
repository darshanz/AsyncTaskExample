package me.games.asynctaskexample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    private static final String DOWNLOAD_IMAGE_URL = "http://1.bp.blogspot.com/-Hh-jLfDXFIc/VaSZggZrThI/AAAAAAAAA8g/oKxBD1bGM68/s1600/overall-graphic-070915.jpg";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.imageView);

       new DownloadImageAsyncTask().execute(DOWNLOAD_IMAGE_URL);


    }


    //Download image
    private Bitmap getImage(String urlString){
       Bitmap bitmap =null;

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream
             is = connection.getInputStream();

             bitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }


    class  DownloadImageAsyncTask extends AsyncTask<String, Integer, Bitmap>{
         ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download Image");
            progressDialog.setMessage("Please wait. Download in progress..");
            progressDialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            // Calcualate percent of the progress and set progress
           //  int progress = 0;
           //  setProgress(progress);

            return getImage(urls[0]);

        }


        @Override
        protected void onPostExecute(Bitmap bm) {
            super.onPostExecute(bm);
            progressDialog.dismiss();
            mImageView.setImageBitmap(bm);;

        }
    }


}
