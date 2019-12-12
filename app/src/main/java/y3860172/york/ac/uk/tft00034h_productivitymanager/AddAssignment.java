package y3860172.york.ac.uk.tft00034h_productivitymanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.imageAdaptor;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Media;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.add_picture;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.picture;

public class AddAssignment extends AppCompatActivity {

    Date dueDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final Calendar calendar = Calendar.getInstance();
        datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("time was set", "something happened");
                        dueDate.setYear(year);
                        dueDate.setMonth(monthOfYear);
                        dueDate.setDate(dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");
                        TextView dateText = findViewById(R.id.date_display);
                        dateText.setText(simpleDateFormat.format(dueDate));

                    }
                }
        );
        datePicker.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");
        TextView dateText = findViewById(R.id.date_display);
        dateText.setText(simpleDateFormat.format(dueDate));
        //todo clean this shit up please
        //todo bind timer as well


        bindPictures();
    }

    public RecyclerView mRecycleView;

    private List<Media> mPhotoList;
    //todo add voice recording
    private imageAdaptor mAdaptor;
    public void bindPictures(){

        mRecycleView = findViewById(R.id.photo_recycleView);
        mRecycleView.setLayoutManager(new GridLayoutManager(this,4));
//        mRecycleView.setNestedScrollingEnabled(false);
        mPhotoList = new ArrayList<>();
//        mPhotoList.add(new picture(BitmapConverter(R.drawable.tedtedparty)));
//        mPhotoList.add(new picture(BitmapConverter(R.drawable.sunset)));
        mPhotoList.add(new add_picture());
        //todo add the add new button
        mAdaptor = new imageAdaptor(mPhotoList, this);
        mRecycleView.setAdapter(mAdaptor);

    }

    public void onClickToggleDate(View v){
//        TransitionManager.beginDelayedTransition((ViewGroup) v.getParent(), new AutoTransition());
        VisExpander(findViewById(R.id.datePicker));
        VisDisable(findViewById(R.id.TimePicker));
        //todo make selected text darker
    }


    //assignment button
    public void back(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    //todo make this shit look presentable


    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    public void camera (View view){

            Log.d("camera", "activated");
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mPhotoList.add(0, new picture(photo));
            mAdaptor.notifyItemInserted(0);

            Log.d("camera", "picture binded");
        }


    }




    public void onClickToggleTime(View v){
        VisExpander(findViewById(R.id.TimePicker));
        VisDisable(findViewById(R.id.datePicker));
    }
    public void VisExpander(View picker){
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent(), new AutoTransition());
        if (picker.getVisibility() == View.GONE){
            picker.setVisibility(View.VISIBLE);
        } else {
            picker.setVisibility(View.GONE);
        }
    }
    public void VisDisable(View picker){
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent(), new AutoTransition());

        picker.setVisibility(View.GONE);
    }
    //todo add time and date updates to tags
    //todo make the pickers start at current time
    //todo make transitions, its kinda fixed but needs work
    //todo make focus
    //todo finish the picture recycleview
    //todo picture recycleview add camera functionality
    //todo add picture view when clicked
    //todo add delete function for picture recycleview
    //todo add voice memo recycleview
    //todo make data saving in each assignment

    public Bitmap BitmapConverter (int image){
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                image);
        return icon;
    }
}
