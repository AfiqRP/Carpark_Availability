package sg.edu.rp.c346.id21019423.carparkavailability;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCar;
    AsyncHttpClient client;
    ArrayAdapter<Car> aaCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCar = findViewById(R.id.listViewID);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Car> alCar = new ArrayList<Car>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String carpark_number;
            String update_datetime;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrData = firstObj.getJSONArray("carpark_data");
                    for(int i = 0; i < jsonArrData.length(); i++) {
                        JSONObject jsonObjData = jsonArrData.getJSONObject(i);
                        carpark_number = jsonObjData.getString("carpark_number");
                        update_datetime = jsonObjData.getString("update_datetime");
                        Car car = new Car(carpark_number, update_datetime);
                        alCar.add(car);
                    }
                }
                catch(JSONException e){

                }

                ArrayAdapter<Car> aaCar = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alCar);
                lvCar.setAdapter(aaCar);
            }
        });
    }

}