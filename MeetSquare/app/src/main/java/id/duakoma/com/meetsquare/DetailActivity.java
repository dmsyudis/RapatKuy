package id.duakoma.com.meetsquare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.microedition.khronos.opengles.GL;

import static id.duakoma.com.meetsquare.RoomActivity.EXTRA_TITLE;
import static id.duakoma.com.meetsquare.RoomActivity.EXTRA_DESC;
import static id.duakoma.com.meetsquare.RoomActivity.EXTRA_IMG;

/**
 * Created by lenovo on 9/9/2018.
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE);
        String imageURL = intent.getStringExtra(EXTRA_IMG);

        String description = intent.getStringExtra(EXTRA_DESC);
        TextView textTitle = findViewById(R.id.text_view_creator);
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewDesc = findViewById(R.id.text_view_creator_detail);

        textTitle.setText(title);

        try {
            URL url = new URL(imageURL);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Drawable drawable = new BitmapDrawable(this.getResources(), bmp);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(drawable);
            }
            //getWindow().setBackgroundDrawable(drawable);
            //imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Glide.with(this).load(imageURL).fitCenter().into(imageView);
        //imageView.setBackgroundResource(R.drawable.ruang_a);
//        Glide.with(this).load(imageURL).asBitmap().into(imageView);
        //Picasso.get().load(imageURL).into(imageView);
        textViewDesc.setText(description);
    }
}
