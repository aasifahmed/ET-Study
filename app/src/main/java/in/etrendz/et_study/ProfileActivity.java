package in.etrendz.et_study;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aasif on 1/6/2018.
 */

public class ProfileActivity extends AppCompatActivity {
//    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> contactList;
    String url = "http://demo.etrendz.in/sample.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

//        new GetContacts().execute();
        getData();
    }



    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                pDialog = new ProgressDialog(ProfileActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
//                String uri = params[0];
                String uri = url;

                BufferedReader bufferedReader = null;
                try {
                    URL urls = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) urls.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                url=result;
                showList();
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(url);

            JSONObject student = jsonObj.getJSONObject("student");
            String ids = student.getString("id");
            String fname = student.getString("first_name");
            String lname = student.getString("last_name");
            String gendr = student.getString("gender");
            String name = fname + " " + lname;
            Log.d(name, "+++++++++++++++");

            HashMap<String, String> students =new HashMap<>();
            students.put("id", ids);
            students.put("first_name", fname);
            students.put("last_name", lname);
            students.put("name", name);
            students.put("gender", gendr);
            contactList.add(students);

            ListAdapter adapter = new SimpleAdapter(
                    ProfileActivity.this, contactList, R.layout.student_profile,
                    new String[]{"name"},
                    new int[]{R.id.student_name}
            );

            lv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
