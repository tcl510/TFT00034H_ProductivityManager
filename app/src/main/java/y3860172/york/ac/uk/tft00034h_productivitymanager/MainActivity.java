package y3860172.york.ac.uk.tft00034h_productivitymanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.cardAdapter;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.assignment_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.assignments_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.tester_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.time_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.weather_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.types.Assignment;

//import androidx.cardview.widget;

//todo (might scrap) setup different pages and link with tabs https://developer.android.com/reference/com/google/android/material/tabs/TabLayout#setupWithViewPager\(ViewPager\) https://material.io/develop/android/components/tab-layout/
//todo make since u went thru the effort of making it all recycleview, make a setting page where u can modify the homepage! https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.u7416aupw
//todo make the settings page, where u can modify the layout
//todo add the calender functionality (in the future)
//todo 5 days a week in a future()
//todo grey out 5 days
public class MainActivity extends AppCompatActivity {

    private cardAdapter mAdapter;
    private List<Card> mCardList;
    public List<Card> assignments;
    public String weather_current;


    public Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        fullscreen();
        setContentView(R.layout.activity_main);
        //start handler
        handler = new Handler();
        initialize();
        card();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
//    @Override
//    protected void onFocus(){
//
//    }
    public void fullscreen(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public Runnable runnable = new Runnable() {

        public void run() {
//            boolean update = false;
            for (Card card : mCardList) {
                if (card.getType() == Card.CARD_TIME) {
                    time_card temp = (time_card) card;
                    temp.timeString = temp.getTimeString();
                    temp.fullLowerString = temp.getFullLowerString();
                    mAdapter.notifyItemChanged(mCardList.indexOf(card), ((time_card) card).timeString);
                    mAdapter.notifyItemChanged(mCardList.indexOf(card), ((time_card) card).fullLowerString);
                }
            }
//            if (update) {
//
//            }
            handler.postDelayed(this, 500);
        }
    };


    public void card(){
        //binding recycleview
        RecyclerView mRecycleView = findViewById(R.id.idRecycleView);
        //fix size
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //populate
        mCardList = new ArrayList<>();

        mCardList.add(new time_card());
        mCardList.add(new assignments_card(assignments));
//        Log.d("json", String.valueOf(assignments.get(0).getType()));
        Log.d("json", "asdfasdf");
        mCardList.add(new tester_card("Ted Ted", "Default Subtitle goes here", "A great get together with my many brothers! waaaaa", R.drawable.tedted, R.drawable.tedtedparty));
//        mCardList.add(new tester_card ("Ted Ted", "Default Subtitle goes here, more words, more words", "Wheeeeeeee", R.drawable.tedted, R.drawable.sunset));
//        //set adapter to recycleview
        mCardList.add(new assignments_card(assignments));
        mAdapter = new cardAdapter(mCardList, this);
        mRecycleView.setAdapter(mAdapter);

    }
    public void initialize(){
        /*todo add gps https://github.com/rohitsthaa/retrofit-openweather   https://stackoverflow.com/questions/2227292/how-to-get-latitude-and-longitude-of-the-mobile-device-in-android https://github.com/UoY-TFTV-InteractiveMedia/MobileInteraction/blob/master/SensorExamples/Position/app/src/main/java/uk/ac/york/tftv/im/mi/position/MainActivity.java*/
        //call weather api
        weather();
        //start timer
        handler.postDelayed(runnable, 500);
        //make a new list of assignments
        assignments = new ArrayList<>();
        loadData();
    }

    //add testing functions here
    public void tester(View view){
//        assignments.add(new assignment_card("Mobile interaction", new Time(15884848)));
//        new GetWeather().execute("http://api.openweathermap.org/data/2.5/weather?q=hong+kong,cn&APPID=" + weatherKey);
//        mAdapter.notifyDataSetChanged();
//        testSave();
//        testLoad();
//        saveData();
//        openImage();
    }

    //test code
//    void openImage() {
////        Intent intent = new Intent();
////        Uri uri = Uri.parse("android.resource://y3860172.york.ac.uk.tft00034h_productivitymanager/drawable/tedted.jpg");
////        intent.setAction(Intent.ACTION_VIEW);
////        intent.setDataAndType(uri, "image/*");
//////        startActivity(intent);
////        startActivity(new Intent(Intent.ACTION_VIEW, uri));
////        Uri uri = Uri.parse("android.resource://y3860172.york.ac.uk.tft00034h_productivitymanager/drawable/tedted.jpg");
//        Resources resources = this.getResources();
//        int resId = R.drawable.tedted;
//        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resId) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(resId));
//        showPhoto(uri);
//    }

    //test code
//    private void showPhoto(Uri photoUri) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(photoUri, "image/*");
//        startActivity(intent);
//    }


    //Assignment button
    public int ADD_ASSIGNMENT = 69;
    public void addAssignment(View view){
        Intent i = new Intent(this,AddAssignment.class);
        startActivityForResult(i, ADD_ASSIGNMENT);
    }
    public int SEE_ASSIGNMENT = 420;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if add assignment
        if (requestCode == ADD_ASSIGNMENT && resultCode == Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Assignment assignment = data.getParcelableExtra("assignment");
            if (assignment == null) {
                Toast toast = Toast.makeText(this, "Assignment failed to add", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                assignments.add(0, new assignment_card(assignment));
            }
            for (Card card : mCardList) {
                if (card.getType() == Card.CARD_ASSIGNMENTS) {
                    mAdapter.notifyItemChanged(mCardList.indexOf(card));
                }
            }

        }
        if (requestCode == SEE_ASSIGNMENT){
            super.onActivityResult(requestCode, resultCode, data);
            Assignment assignment = data.getParcelableExtra("assignment");
            int index = data.getIntExtra("index",0);
            if (assignment == null) {
                Toast toast = Toast.makeText(this, "Assignment failed to update", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                assignments.set(index, new assignment_card(assignment));
            }
            for (Card card : mCardList) {
                if (card.getType() == Card.CARD_ASSIGNMENTS) {
                    mAdapter.notifyItemChanged(mCardList.indexOf(card));
                }
            }

        }
        //if result code is delete
        if (resultCode == 2) {
            super.onActivityResult(requestCode, resultCode, data);
            int index = data.getIntExtra("index", 0);
            assignments.remove(index);
            for (Card card : mCardList) {
                if (card.getType() == Card.CARD_ASSIGNMENTS) {
                    mAdapter.notifyItemChanged(mCardList.indexOf(card));
                }
            }
            Log.d("assignment_list", "updated");
        }
        saveData();
        //todo sort list
    }



    //getting weather thru api
    String weatherKey = "7676be54a54f4b58b79d8d3a5cf16936";
    public void weather(){
        new GetWeather().execute("http://api.openweathermap.org/data/2.5/weather?q=York,uk&APPID=" + weatherKey);
    }

    private class GetWeather extends AsyncTask<String, Void, String> {
        //based after https://github.com/UoY-TFTV-InteractiveMedia/MobileInteraction/blob/master/Practical4/app/src/main/java/uk/ac/york/tftv/im/mi/practical4/CatShow.java#L58

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]); //set the url to be the first parameter sent in the list of urls. See note above.


            } catch (MalformedURLException e) {
                //Java requires us to handle the error "MalformedURLException", in case we pass an invalid URL.
                //here we would show some kind of error, but let's just exit and return a blank string:
                Log.d("something", e.toString());
                return "something";
            }

            StringBuilder sb = new StringBuilder();  //Stringbuilder is a helper class to build strings from remote sources (remember, we don't get all the data at once).

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //attempt to connect to the server
                connection.connect();
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream())); //a bufferedreader is used to read from the data stream. this line hooks it to the connection
                String line;//this will hold each line of data from the server.
                while ((line = bf.readLine()) != null)//while the connection still has data arriving
                {
                    sb.append(line + "\n"); //add the data to our growing stringbuilder
                }
                bf.close();//it has finished, so close the buffer...
                connection.getInputStream().close(); //and close the connection

                return(sb.toString());//return the entire contents of the stringbuilder as a string. (this will be a JSON formatted string containing the cat fact)

            } catch (IOException e) {
                //the above functions might raise an exception if there is an error communicating with the remote server (or there is a network issue)
                //as before, let's just exit and return a blank string again:
                Log.d("something", e.toString());
                return "";
            }
        }

        /**
         * onPostExecute displays the results of the AsyncTask. It is here we pass back the data to the main thread of the application.
         * the String "result" contains the cat fact we retrieved from the server in some JSON
         */
        @Override
        protected void onPostExecute(String result) {
            if(result.length()==0) {//remember we returned an empty string if there was an error. In that case lets return an error message.
//                setWeather("Error!");
                return;
            }

            //If there hasn't been an error, "result" now contains a string in JSON format. We only want the fact out of it.
            //Load the link in a web browser to see the kind of data we get: https://catfact.ninja/fact

            //We have to grab the fact out of the JSON.

            String weatherMain;
            String tempature;
            String icon;
            String location;
            try {
                JSONObject json = new JSONObject(result);
                JSONArray weather_states = json.getJSONArray("weather");
                JSONObject first_obj = weather_states.getJSONObject(0);

                JSONObject main = json.getJSONObject("main");

                weatherMain = first_obj.getString("description");
                tempature = main.getString("temp");
                float temp_num = Float.parseFloat(tempature);
                tempature = makeWeatherString(convert(temp_num));


                String icon_temp = first_obj.getString("icon");
                char night_day_char = 'd';
                for (char c: icon_temp.toCharArray()){
                    if (Character.isLetter(c)){
                        night_day_char = c;
                    }
                }
                String night_day = String.valueOf(night_day_char);
                if (icon_temp.contains("d")) {
                    int resID = getResources().getIdentifier("wi_owm_day_" + first_obj.getString("id"), "string", getPackageName());
                    icon = getString(resID);
                } else {
                    int resID = getResources().getIdentifier("wi_owm_night_" + first_obj.getString("id"), "string", getPackageName());
                    icon = getString(resID);
                }
                location = json.getString("name");


                mCardList.add(1,new weather_card());
                setWeather(weatherMain, tempature, icon, location, night_day);
            }
            catch(JSONException e) {
                Log.d("error", e.toString());
            }
             //once the data has been collected, set the cat fact on the screen
        }

        String makeWeatherString(float tempature_value) {
            return Math.round(tempature_value) + "°c";
        }

        float convert(float temp) {
            return temp - 273;
        }

        void setWeather(String weather_state, String tempature, String icon, String location, String night_day) {
            int index = 1;
            weather_card temp = (weather_card)mCardList.get(index);
            //set strings

            temp.condition = weather_state.substring(0,1).toUpperCase() + weather_state.substring(1); //done capitalize first word
            temp.temperature_string = tempature;

            //set picture
            temp.weather_image = icon;
            temp.night_day = night_day;

            //set location
            temp.location = location;

            final Card set = mCardList.set(index, temp);
            weather_current = weather_state;
            mAdapter.notifyItemChanged(index);
        }
    }


    public void saveData(){
//        MyObject myObject = new MyObject;
//set variables of 'myObject', etc.
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(assignments);
        prefsEditor.putString("assignments", json);
        prefsEditor.apply();
    }
    public void loadData(){
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("assignments", "");
        Log.d("json", json);
        Type type = new TypeToken<List<assignment_card>>(){}.getType();
        //        assignments = (List<Card>) assignments_load;
        assignments = gson.fromJson(json, type);
//        mAdapter.notifyDataSetChanged();
    }
}

