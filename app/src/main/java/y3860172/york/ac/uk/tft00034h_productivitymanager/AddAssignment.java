package y3860172.york.ac.uk.tft00034h_productivitymanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.imageAdaptor;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Media;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.add_picture;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.picture;
import y3860172.york.ac.uk.tft00034h_productivitymanager.types.Assignment;

public class AddAssignment extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    final public int ASSIGNMENT_MODE_NEW = 1;
    final public int ASSIGNMENT_MODE_EDIT = 0;
    public Date dueDate = new Date();
    public String title;
    public String notes;
    public RecyclerView mRecycleView;
    EditText title_input;
    EditText notes_input;
    private List<Media> mPhotoList;
    //todo add voice recording (maybe in the future)
    private imageAdaptor mAdaptor;
    private int index;
    private File photoFile = null;
    private int assignmentMode;
    private ImageView imageView;
    private String currentPhotoPath;
    //todo intent setTheme()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        //taken from android studio documentation
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                back();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);


        //get toolbar

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);


        mPhotoList = new ArrayList<>();


        if (getIntent().hasExtra("assignment")) {
            //set parcel items to everything
            Bundle data = getIntent().getExtras();
            Assignment assignment = data.getParcelable("assignment");
            title = assignment.getTitle();
            notes = assignment.getNotes();
            dueDate = assignment.getDueDate();
            List<String> bitmapList = assignment.getPhotos();
            for (String photo : bitmapList) {
                mPhotoList.add(new picture(photo));
            }
            //set index get index
            index = data.getInt("index");
            Log.d("adaptor",String.valueOf(index));
            assignmentMode = ASSIGNMENT_MODE_EDIT;

        } else {
            assignmentMode = ASSIGNMENT_MODE_NEW;
        }

        invalidateOptionsMenu();




        //change to calender is duedate
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dueDate);


        DatePicker datePicker = findViewById(R.id.datePicker);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("time was set", "something happened");
                        dueDate.setYear(year - 1900);
                        dueDate.setMonth(monthOfYear);
                        dueDate.setDate(dayOfMonth);
                        updateDate();
                    }
                }
        );

        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        updateDate();
        TimePicker timePicker = findViewById(R.id.TimePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                dueDate.setHours(hourOfDay);
                dueDate.setMinutes(minute);
                updateDate();
            }
        });


        bind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                save();
                break;
            case R.id.home:
                back();
                break;
            case R.id.action_delete:
                delete();
                break;
            default:
                back();
                break;
        }
//        back2();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch (assignmentMode) {
            case ASSIGNMENT_MODE_EDIT:
                menu.findItem(R.id.action_add).setVisible(false);
                menu.findItem(R.id.action_delete).setVisible(true);
                break;
            case ASSIGNMENT_MODE_NEW:
                menu.findItem(R.id.action_add).setVisible(true);
                menu.findItem(R.id.action_delete).setVisible(false);
                break;
            default:
                menu.findItem(R.id.action_add).setVisible(false);
                menu.findItem(R.id.action_delete).setVisible(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void updateDate() {
        SimpleDateFormat dd_mmm = new SimpleDateFormat("dd MMM");
        TextView dateText = findViewById(R.id.date_display);
        dateText.setText(dd_mmm.format(dueDate));
        SimpleDateFormat hhmm = new SimpleDateFormat("hh:mm aa");
        TextView timeText = findViewById(R.id.time_display);
        timeText.setText(hhmm.format(dueDate));
    }

    private void bind() {
        bindNotes();
        bindPictures();
        bindTitle();
    }

    private void bindNotes() {
        notes_input = findViewById(R.id.notes_input);
        notes_input.setText(notes);
    }

    private void bindTitle() {
        title_input = findViewById(R.id.title_input_editText);
        if (title_input.getText().toString().matches(getResources().getString(R.string.untitled))) {
            title_input.setText("");
        } else {
            title_input.setText(title);
        }
    }

    private void bindPictures() {
        mRecycleView = findViewById(R.id.photo_recycleView);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        //done check if null
        mPhotoList.add(new add_picture());

        //done add the add new button
        mAdaptor = new imageAdaptor(mPhotoList, this);
        mRecycleView.setAdapter(mAdaptor);
    }

    public void camera(View view) {

        Log.d("camera", "activated");
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            photoFile = null;
            try {
                photoFile = imageFileCreator();
            } catch (IOException ex) {
                Log.d("camera", "photofile failed");
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "example.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
//            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }
        private File imageFileCreator () throws IOException {
            //taken from https://developer.android.com/training/camera/photobasics
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = image.getAbsolutePath();
            return image;
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                super.onActivityResult(requestCode, resultCode, data);
                Bundle extras = data.getExtras();
//                Bitmap photo = (Bitmap) extras.get("data");
                Bitmap photo = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                mPhotoList.add(0, new picture(photoFile.getAbsolutePath()));
                mAdaptor.notifyItemInserted(0);

                Log.d("camera", photoFile.getAbsolutePath());
            }
        }

        public void onClickToggleTime (View v){
            VisExpander(findViewById(R.id.TimePicker), (TextView) findViewById(R.id.time_display));
            VisDisable(findViewById(R.id.datePicker), (TextView) findViewById(R.id.date_display));
        }

    public void onClickToggleDate(View v) {
//        TransitionManager.beginDelayedTransition((ViewGroup) v.getParent(), new AutoTransition());
        VisExpander(findViewById(R.id.datePicker), (TextView) findViewById(R.id.date_display));
        VisDisable(findViewById(R.id.TimePicker), (TextView) findViewById(R.id.time_display));
    }

    public void VisExpander(View picker, TextView buttonText) {
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent(), new AutoTransition());
            if (picker.getVisibility() == View.GONE) {
                picker.setVisibility(View.VISIBLE);
                toggleBold(buttonText, true);
            } else {
                picker.setVisibility(View.GONE);
                toggleBold(buttonText, false);
            }
        }

    public void VisDisable(View picker, TextView otherText) {
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent().getParent(), new AutoTransition());
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent(), new AutoTransition());
        toggleBold(otherText, false);
            picker.setVisibility(View.GONE);
        }

    public void toggleBold(TextView view, boolean YN) {
        if (YN) {
            view.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            view.setTypeface(Typeface.DEFAULT);
        }
    }


    //todo add delete function for picture recycleview
        //todo add voice memo recycleview


    public Bitmap BitmapConverter ( int image){
            Bitmap icon = BitmapFactory.decodeResource(getResources(),
                    image);
            return icon;
        }
        public void save (View view){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("assignment", payload());
            i.putExtra("index", index);
            setResult(Activity.RESULT_OK, i);
            finish();
        }

    public void save() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("assignment", payload());
        i.putExtra("index", index);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
        //Assignment button
        public void back (View view){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("assignment", payload());
            i.putExtra("index", index);
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        }

    public void back() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("assignment", payload());
        i.putExtra("index", index);
        setResult(Activity.RESULT_CANCELED, i);
        finish();
    }

    public void delete() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("assignment", payload());
        i.putExtra("index", index);
        setResult(2, i);
        finish();
    }
        public Assignment payload () {
            //saving photolist
            List<String> photolist;
            photolist = new ArrayList<>();
            for (Media photo : mPhotoList) {
                if (photo.getType() == Media.MEDIA_PICTURE) {
                    picture temp = (picture) photo;
                    if (temp.getImage_file_path() == null) {
                        Log.d("storage","error");
                        photolist.add(temp.getImage_file_path());
                    } else {

                        photolist.add(temp.getImage_file_path());
                    }
                }
            }

            //get title
            if (title_input.getText().toString().matches("")) {
                title = getResources().getString(R.string.untitled);
            } else {
                title = title_input.getText().toString();
            }
            //get notes
            notes = notes_input.getText().toString();

            return new Assignment(photolist, title, dueDate, notes);
        }
    }
