package io.caster.example.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caster.example.butterknife.dummy.DummyContent;

public class UserListFragment extends Fragment {

    @BindView(R.id.recycler) RecyclerView list;

    private UserAdapter adapter;
    private GridLayoutManager layoutManager;
    private List<DummyContent.User> users;
    private boolean listView = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        layoutManager = new GridLayoutManager(getActivity(), 1);
        list.setLayoutManager(layoutManager);

        users = DummyContent.ITEMS;
        setupRecyclerView(listView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.user_list, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.list_type);
        item.setIcon(listView ? R.drawable.ic_view_grid : R.drawable.ic_view_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_type:
                listView = !listView;
                setupRecyclerView(listView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecyclerView(boolean asList) {
        if (asList) {
            adapter = new UserAdapter(users, R.layout.item_user_list);
            layoutManager.setSpanCount(1);
        } else {
            adapter = new UserAdapter(users, R.layout.item_user_grid);
            layoutManager.setSpanCount(2);
        }
        list.setAdapter(adapter);
    }
}
