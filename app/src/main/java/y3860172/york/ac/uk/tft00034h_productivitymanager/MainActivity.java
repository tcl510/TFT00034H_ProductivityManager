package y3860172.york.ac.uk.tft00034h_productivitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.cardview.widget;

import android.os.Bundle;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card();
        weather();

//        mRecycleViewWeather = (RecyclerView) findViewById(R.id.RecycleView2);
//
//
//        mRecycleViewWeather.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//        mWeatherCardList = new ArrayList<>();
//        mWeatherCardList.add(new Card2("sunny", "Hong Kong", 25, R.drawable.weather_sunny));
//
//
//
//
//        mAdapter2 = new cardAdapter2(mWeatherCardList, this);
//        mRecycleViewWeather.setAdapter(mAdapter2);

        //get weather data based on gps
        //get calander data
        initialize();
    }

    private void initialize(){


    }
    public void card(){
        //binding recycleview
        mRecycleView = (RecyclerView) findViewById(R.id.idRecycleView);
        //fix size
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //populate
        mCardList = new ArrayList<>();

        mCardList.add(new Card ("weather", "Hong Kong", "Cloudy", "25", R.drawable.weather_alert, R.drawable.weather_cloudy));
        mCardList.add(new Card ("card","Ted Ted", "Default Subtitle goes here", "A great get together with my many brothers! waaaaa", R.drawable.tedted, R.drawable.tedtedparty));
        mCardList.add(new Card ("card","Ted Ted", "Default Subtitle goes here, more words, more words", "Wheeeeeeee", R.drawable.tedted, R.drawable.sunset));

        //set adapter to recycleview
        mAdapter = new cardAdapter(mCardList,this);
        mRecycleView.setAdapter(mAdapter);
    }
    public void weather(){


    }

//    Card tester = new Card ("Default Title", "Default Subtitle goes here, more words, words and more words", "a subtitle to behold!", "tedted.jpg", "tedted.jpg");

}
