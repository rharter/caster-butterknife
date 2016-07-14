package io.caster.example.butterknife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.caster.example.butterknife.dummy.DummyContent;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

  // Views
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.fab) FloatingActionButton fab;
  @BindView(R.id.item_list) RecyclerView recyclerView;

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

    // setup the recyclerView
    recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));

    // check for dual pane view
    if (detailView != null) {
      mTwoPane = true;
    }
  }

  class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;

    SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
      mValues = items;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_list_content, parent, false);
      return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.mItem = mValues.get(position);
      holder.mIdView.setText(mValues.get(position).id);
      holder.mContentView.setText(mValues.get(position).content);

      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit();
          } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

            context.startActivity(intent);
          }
        }
      });
    }

    @Override public int getItemCount() {
      return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
      final View mView;
      @BindView(R.id.id) TextView mIdView;
      @BindView(R.id.content) TextView mContentView;
      DummyContent.DummyItem mItem;

      ViewHolder(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
      }

      @Override public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
      }
    }
  }
}
