package io.caster.example.butterknife;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.caster.example.butterknife.dummy.DummyContent;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<DummyContent.User> users;
    private final int layoutId;

    public UserAdapter(List<DummyContent.User> users, int layoutId) {
        this.users = users;
        this.layoutId = layoutId;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        DummyContent.User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
