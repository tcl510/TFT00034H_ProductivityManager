package y3860172.york.ac.uk.tft00034h_productivitymanager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class AddAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
    }

    public void onClickToggleDate(View v){
        TransitionManager.beginDelayedTransition((ViewGroup) v.getParent(), new AutoTransition());

        VisExpander(findViewById(R.id.datePicker));
        VisDisable(findViewById(R.id.TimePicker));
    }
    public void onClickToggleTime(View v){
        VisExpander(findViewById(R.id.TimePicker));
        VisDisable(findViewById(R.id.datePicker));
    }
    public void VisExpander(View picker){
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent(), new AutoTransition());

        if (picker.getVisibility() == View.GONE){
            picker.setVisibility(View.VISIBLE);
        } else {

            picker.setVisibility(View.GONE);
        }
    }
    public void VisDisable(View picker){
//        TransitionManager.beginDelayedTransition((ViewGroup) picker.getParent().getParent(), new AutoTransition());

        picker.setVisibility(View.GONE);
    }
    //todo add time and date updates to tags
    //todo make the pickers start at current time
    //todo make transitions
}
