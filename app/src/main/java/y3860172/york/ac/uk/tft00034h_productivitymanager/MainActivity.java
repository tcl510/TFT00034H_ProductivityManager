package y3860172.york.ac.uk.tft00034h_productivitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.cardview.widget;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.cardAdapter;
import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.cardAdapter2;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card2;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private cardAdapter mAdapter;
    private List<Card> mCardList, mWeatherList;


    public String weather_current;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        card();
        weather();
//        getCat();


    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume(){
        mAdapter.notifyDataSetChanged();
        super.onResume();

    }


    public void card(){
        //binding recycleview
        mRecycleView = (RecyclerView) findViewById(R.id.idRecycleView);
        //fix size
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //populate
        mCardList = new ArrayList<>();

        Log.d("pass", "card");
        mCardList.add(new Card ("weather", "York", weather_current, "25", R.drawable.weather_alert, R.drawable.weather_cloudy));
        mCardList.add(new Card ("card","Ted Ted", "Default Subtitle goes here", "A great get together with my many brothers! waaaaa", R.drawable.tedted, R.drawable.tedtedparty));
        mCardList.add(new Card ("card","Ted Ted", "Default Subtitle goes here, more words, more words", "Wheeeeeeee", R.drawable.tedted, R.drawable.sunset));

        //set adapter to recycleview
        mAdapter = new cardAdapter(mCardList,this);
        mRecycleView.setAdapter(mAdapter);
    }
    public void weather(){

    }

    public void initialize(){
        String key = "7676be54a54f4b58b79d8d3a5cf16936";
        new GetWeather().execute("http://api.openweathermap.org/data/2.5/weather?q=York,uk&APPID=7676be54a54f4b58b79d8d3a5cf16936");

    }
    public void tester(View view){
        new GetWeather().execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=7676be54a54f4b58b79d8d3a5cf16936");

//        new GetWeather().execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
    }

    public void setFact(String weather_state, String tempature) {
        Card temp = mCardList.get(0);
        temp.subtitle = weather_state;
        temp.supporting = tempature;
        mCardList.set(0, temp);
        weather_current = weather_state;
        mAdapter.notifyDataSetChanged();
    }

    private class GetWeather extends AsyncTask<String, Void, String> {
        //based after https://github.com/UoY-TFTV-InteractiveMedia/MobileInteraction/blob/master/Practical4/app/src/main/java/uk/ac/york/tftv/im/mi/practical4/CatShow.java#L58

        /**
         * When we call "execute" in getCat(), this is the method that is called. Note that "String..." means it can accept a list of strings.
         * We need to do it this way, because this is how AsyncTask is wired.
         */
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
//                setFact("Error!");
                return;
            }

            //If there hasn't been an error, "result" now contains a string in JSON format. We only want the fact out of it.
            //Load the link in a web browser to see the kind of data we get: https://catfact.ninja/fact

            //We have to grab the fact out of the JSON.
            String weather_state;
            String weatherMain = "";
            String tempature = "";
            try {
                JSONObject json = new JSONObject(result);
                JSONArray weather_states = json.getJSONArray("weather");
                JSONObject first_obj = weather_states.getJSONObject(0);

                JSONObject main = json.getJSONObject("main");

                weatherMain = first_obj.getString("description");
                tempature = main.getString("temp");
                float temp_num = Float.parseFloat(tempature);
                tempature = makeWeatherString(convert(temp_num));

            }
            catch(JSONException e) {
                weather_state = e.getLocalizedMessage(); //if there is an error in the JSON.
                Log.d("something", e.toString());
            }
            setFact(weatherMain, tempature); //once the data has been collected, set the cat fact on the screen
        }
    }


    public String makeWeatherString(float tempature_value){
        return Math.round(tempature_value) + "°c";
    }
    public float convert(float temp){
        return (float) (temp - 273);
    }
}



//    Card tester = new Card ("Default Title", "Default Subtitle goes here, more words, words and more words", "a subtitle to behold!", "tedted.jpg", "tedted.jpg");


