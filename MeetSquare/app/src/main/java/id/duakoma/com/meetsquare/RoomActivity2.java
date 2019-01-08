package id.duakoma.com.meetsquare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 9/9/2018.
 */

public class RoomActivity2 extends AppCompatActivity implements CustomAdapter.OnItemClickListener{
    public static final String EXTRA_IMG = "image";
    public static final String EXTRA_DESC = "description";

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> data_list;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, data_list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getId());
                }
            }
        });
    }

    private void load_data_from_server(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/room/script.php?id="+id)
                       // .url("http://rizkyernanda-septianid.000webhostapp.com/server.php?operasi=ruangan")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        MyData data = new MyData(jsonObject.getInt("id"),
                                jsonObject.getString("description"),
                                jsonObject.getString("image"));
                        data_list.add(data);
                        adapter.setOnItemClickListener(RoomActivity2.this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };
        task.execute(id);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(RoomActivity2.this, DetailActivity.class);
        MyData clickedItem = data_list.get(position);

        detailIntent.putExtra(EXTRA_IMG, clickedItem.getImage_link());
        detailIntent.putExtra(EXTRA_DESC, clickedItem.getDescription());

        startActivity(detailIntent);
    }
}
