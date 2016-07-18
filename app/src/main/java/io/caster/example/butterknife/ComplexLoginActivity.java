package io.caster.example.butterknife;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("WeakerAccess")
public class ComplexLoginActivity extends AppCompatActivity {

  @BindView(R.id.start_container) View startContainer;
  @BindView(R.id.signup_container) View signupContainer;
  @BindView(R.id.login_container) View loginContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_complex_login);
    ButterKnife.bind(this);

    showStartContainer(false);
  }

  @OnClick({R.id.show_login1, R.id.show_login2})
  public void showLogin() {
    showLoginContainer(true);
  }

  @OnClick({R.id.show_signup1, R.id.show_signup2})
  public void showSignup() {
    showSignupContainer(true);
  }

  // - Internal methods

  @Override public void onBackPressed() {
    if (startContainer.getVisibility() != View.VISIBLE) {
      showStartContainer(true);
    } else {
      super.onBackPressed();
    }
  }

  private void showStartContainer(boolean animated) {
    if (animated) {
      AnimatorSet s = new AnimatorSet();
      s.playTogether(
          ObjectAnimator.ofFloat(startContainer, View.ALPHA, 1),
          ObjectAnimator.ofFloat(signupContainer, View.ALPHA, 0),
          ObjectAnimator.ofFloat(loginContainer, View.ALPHA, 0)
      );
      s.addListener(new AnimatorListenerAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
          startContainer.setVisibility(View.VISIBLE);
          signupContainer.setVisibility(View.GONE);
          loginContainer.setVisibility(View.GONE);
        }
      });
      s.start();
    } else {
      startContainer.setVisibility(View.VISIBLE);
      signupContainer.setVisibility(View.GONE);
      loginContainer.setVisibility(View.GONE);
    }
  }

  private void showSignupContainer(boolean animated) {
    if (animated) {
      AnimatorSet s = new AnimatorSet();
      s.playTogether(
          ObjectAnimator.ofFloat(signupContainer, View.ALPHA, 1),
          ObjectAnimator.ofFloat(startContainer, View.ALPHA, 0),
          ObjectAnimator.ofFloat(loginContainer, View.ALPHA, 0)
      );
      s.addListener(new AnimatorListenerAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
          signupContainer.setVisibility(View.VISIBLE);
          startContainer.setVisibility(View.GONE);
          loginContainer.setVisibility(View.GONE);
        }
      });
      s.start();
    } else {
      signupContainer.setVisibility(View.VISIBLE);
      startContainer.setVisibility(View.GONE);
      loginContainer.setVisibility(View.GONE);
    }
  }

  private void showLoginContainer(boolean animated) {
    if (animated) {
      AnimatorSet s = new AnimatorSet();
      s.playTogether(
          ObjectAnimator.ofFloat(loginContainer, View.ALPHA, 1),
          ObjectAnimator.ofFloat(signupContainer, View.ALPHA, 0),
          ObjectAnimator.ofFloat(startContainer, View.ALPHA, 0)
      );
      s.addListener(new AnimatorListenerAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
          loginContainer.setVisibility(View.VISIBLE);
          signupContainer.setVisibility(View.GONE);
          startContainer.setVisibility(View.GONE);
        }
      });
      s.start();
    } else {
      loginContainer.setVisibility(View.VISIBLE);
      signupContainer.setVisibility(View.GONE);
      startContainer.setVisibility(View.GONE);
    }
  }
}
