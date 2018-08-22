package test.android.ui.propertylist;

import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import test.android.BaseActivity;
import test.android.FragmentListener;
import test.android.R;
import test.android.ui.propertylist.detail.DetailActivity;
import test.android.ui.propertylist.detail.PropertyDetailFragment;
import test.android.ui.propertylist.master.PropertyListFragment;
import test.android.utils.Config;

/**
 * Main Activity which has the master-detail flow.
 *
 * created by fahad on 21/08/2018
 */
public class MainActivity extends BaseActivity implements PropertyListFragment.OnItemClickListener, FragmentListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If the screen width accomodates the master detail flow, add the detail fragment
        if (findViewById(R.id.detail_container) != null){
            mTwoPane = true;

            //Add the fragment on first launch.
            if (savedInstanceState == null){
                PropertyDetailFragment detailFragment = PropertyDetailFragment.Companion.getInstance("");
                getSupportFragmentManager().beginTransaction().add(R.id.detail_container, detailFragment).commit();
            }

        }else{
            mTwoPane = false;
        }
    }

    //Callback function for list item selected
    @Override
    public void onItemSelected(String itemId) {
        //If its a master-detail layout, then replace the detail fragment, else start the detail activity
        if (mTwoPane){
            PropertyDetailFragment detailFragment = PropertyDetailFragment.Companion.getInstance(itemId);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }else{
            final Intent intent = new Intent(this, DetailActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString(Config.SELECTED_ITEM_ID, String.valueOf(itemId)); // pass the selected item id to be displayed
            intent.putExtras(bundle);

            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public void showToastInActivity(@NotNull String message) {
        showToast(message);
    }
}
