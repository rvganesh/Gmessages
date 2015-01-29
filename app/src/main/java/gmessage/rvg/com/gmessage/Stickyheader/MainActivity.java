package gmessage.rvg.com.gmessage.Stickyheader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import gmessage.rvg.com.gmessage.R;
import gmessage.rvg.com.gmessage.Stickyheader.fragment.MainFragment;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new MainFragment());
        }

    }


    public void loadFragment(final Fragment fragment) {

        getFragmentManager().beginTransaction().replace(R.id.layout_container, fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).commit();

    }

}
