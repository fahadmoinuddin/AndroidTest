package test.android.ui.propertylist.detail;

import android.os.Bundle;
import android.view.MenuItem;

import org.jetbrains.annotations.Nullable;

import test.android.BaseActivity;
import test.android.R;
import test.android.utils.Config;

/**
 * Detail activity which holds the detail fragment.
 *
 * created by fahad on 21/08/2018
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);

            try {
                getSupportActionBar().setTitle(getString(R.string.detail));
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }catch (NullPointerException exception){

            }

            String selectedItemId = getIntent().getStringExtra(Config.SELECTED_ITEM_ID);

            if (savedInstanceState == null) {
                PropertyDetailFragment detailFragment = PropertyDetailFragment.Companion.getInstance(selectedItemId);
                getSupportFragmentManager().beginTransaction().add(R.id.detail_container, detailFragment).commit();
            }
        }catch (Exception exception){
            showToast("Something went wrong. Please try again");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
