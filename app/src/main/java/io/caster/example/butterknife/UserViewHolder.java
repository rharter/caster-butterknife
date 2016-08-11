package io.caster.example.butterknife;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.caster.example.butterknife.dummy.DummyContent;

class UserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.username) @Nullable TextView username;
    @BindView(R.id.name) TextView name;

    UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.item) public void onItemClick() {
        Toast.makeText(itemView.getContext(), "Item " + getAdapterPosition() + " clicked!", Toast.LENGTH_SHORT).show();
    }

    public void bind(DummyContent.User user) {
        image.setImageResource(user.avatarResId);
        name.setText(user.name);
        if (username != null) {
            username.setText(user.username);
        }
    }
}
