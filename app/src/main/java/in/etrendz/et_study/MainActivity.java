package in.etrendz.et_study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvCards = (ListView) findViewById(R.id.list_cards);
        CardsAdapter adapter = new CardsAdapter(this);

        lvCards.setAdapter(adapter);
        adapter.addAll(new CardModel(R.drawable.student, R.string.student, R.string.student_sub),
                new CardModel(R.drawable.fees, R.string.fees, R.string.fees_sub),
                new CardModel(R.drawable.attendance, R.string.attendance, R.string.attendance_sub),
                new CardModel(R.drawable.exam, R.string.exam, R.string.exam_sub));

        lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                //If you wanna send any data to nextActicity.class you can use
//                i.putExtra(String key, value.get(position));

                startActivity(i);
            }
        });
    }
}
