package y3860172.york.ac.uk.tft00034h_productivitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.cardview.widget;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.adapter.cardAdapter;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private cardAdapter mAdapter;
    private List<Card> mCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding recycleview
        mRecycleView = (RecyclerView) findViewById(R.id.idRecycleView);
        //fix size
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        //populate
        mCardList = new ArrayList<>();
        mCardList.add(new Card ("Ted Ted", "Default Subtitle goes here", "A great get together with my many brothers! waaaaa", R.drawable.tedted, R.drawable.tedtedparty));
        mCardList.add(new Card ("Ted Ted", "Default Subtitle goes here, more words, more words", "Wheeeeeeee", R.drawable.tedted, R.drawable.sunset));

        //set adapter to recycleview
        mAdapter = new cardAdapter(mCardList,this);
        mRecycleView.setAdapter(mAdapter);


        //get weather data based on gps
        //get calander data
        initialize();
    }

    private void initialize(){


    }

//    Card tester = new Card ("Default Title", "Default Subtitle goes here, more words, words and more words", "a subtitle to behold!", "tedted.jpg", "tedted.jpg");

}
