package io.caster.example.butterknife;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListActivity extends AppCompatActivity {

  // Views
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.fab) FloatingActionButton fab;

  // Fragments
  UserListFragment userListFragment;

  // Options Views
  private View detailView;

  private boolean mTwoPane;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_list);

    // get the views
    ButterKnife.bind(this);

    // get the optional view
    detailView = findViewById(R.id.item_detail_container);

    // get the user list fragment
    userListFragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);

    // setup the toolbar
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

    // setup the fab on click listener
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    // check for dual pane view
    if (detailView != null) {
      mTwoPane = true;
    }
  }
}
