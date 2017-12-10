package gobridgit.com.prodigylabstest;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.List;

/**
 * Created by janki on 2017-12-08.
 */

public class FragmentGridList extends Fragment {

    private MainActivity mainActivity;
    private String URL = "https://s3.amazonaws.com/sapi.aminheidari.com/tmp/list.json";
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private GridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;

    private LinearLayoutManager recylerViewLayoutManager;
    private LinearLayout mLinearLayout;
    ArrayList<List> mArrayLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.content_main,container,false);
        mRecyclerView = (RecyclerView)mView.findViewById(R.id.container_layout_recyclerview);
        mLinearLayout = (LinearLayout)mView.findViewById(R.id.content_main_linear);
        mainActivity = (MainActivity) getActivity();
        mRecyclerView.setLayoutManager(new GridLayoutManager(mainActivity,2));

        new FetchData().execute(URL);
        return mView;
    }

    private class FetchData extends AsyncTask<String,Void,Void>{

        ProgressDialog mProgressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(mainActivity,"Wait.","Fetching data....");
        }

        @Override
        protected Void doInBackground(String... params) {
            String data = getDataFromUrl(params[0]);
            System.out.println("Data From  the network is : -"+data);

            mArrayLists = new ArrayList<>();

            try {
                JSONObject mJsonObject = new JSONObject(data);
                JSONArray mJsonArray = mJsonObject.getJSONArray("List");

                if(mJsonArray != null){
                    Gson  mGson = new Gson();

                    mArrayLists = mGson.fromJson(mJsonArray.toString(),new TypeToken<ArrayList<List>>() { }.getType());
                    System.out.println("Size of ArralyList is : - "+mArrayLists.size());

                   /* for(int i = 0;i<mArrayLists.size();i++){
                        List mList = mArrayLists.get(i);
                        System.out.println("Id :- "+mList.getId());
                        System.out.println("First Name :- "+mList.getFirstName());
                        System.out.println("Last Name :- "+mList.getLastName());
                        System.out.println("Description :- "+mList.getDescription());
                        System.out.println("Portrait :- "+mList.getPortrait());
                        System.out.println("BadgeColor :- "+mList.getBadgeColor());
                    }*/
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateAdapter();
            mProgressDialog.dismiss();

        }
    }

    private void updateAdapter() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(mainActivity,mArrayLists);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private String getDataFromUrl(String url) {

        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


}
