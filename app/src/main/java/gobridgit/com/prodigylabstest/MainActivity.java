package gobridgit.com.prodigylabstest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentGridList fragmentGridList = new FragmentGridList();
        replaceFragment(fragmentGridList,false);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void replaceFragment(Fragment mFragment, boolean addBackToStack){

        if(mFragment != null){

            getSupportFragmentManager();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main_layout_fragment, mFragment);
            if(addBackToStack)
                transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
