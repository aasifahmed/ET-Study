package in.etrendz.et_study;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static boolean CheckInternet(Context context)
    {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return wifi.isConnected() || mobile.isConnected();
    }

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

                Log.i("Item clicked is at :", "qqqqqqqqqqqq " + position);
                if(position == 0) {
                    Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                    //If you wanna send any data to nextActicity.class you can use
//                i.putExtra(String key, value.get(position));
                    if (CheckInternet(getApplicationContext())) {
                        startActivity(i);
                    } else
                        Toast.makeText(getApplicationContext(), "Unable to load content. Check your Internet connection.", Toast.LENGTH_SHORT).show();
//                startActivity(i);
                } else {

                }
            }
        });
    }
}
