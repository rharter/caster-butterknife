package io.caster.example.butterknife.dummy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.caster.example.butterknife.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<User> ITEMS = Arrays.asList(
          new User(1, "frakkar", "Fred Kar", R.drawable.avatar_1),
          new User(2, "ladylexy", "Lex Xu", R.drawable.avatar_2),
          new User(3, "erondu", "Blake Smith", R.drawable.avatar_3),
          new User(4, "adellecharles", "Adelle Charles", R.drawable.avatar_4),
          new User(5, "jadlimcaco", "Jason Adlimcaco", R.drawable.avatar_5),
          new User(6, "sauro", "Sam Scheen", R.drawable.avatar_6),
          new User(7, "jsa", "John Andrews", R.drawable.avatar_7),
          new User(8, "brynn", "Brynn Flats", R.drawable.avatar_8),
          new User(9, "itsjonq", "Jon Qu", R.drawable.avatar_9)
  );

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, User> ITEM_MAP = new HashMap<String, User>();

  static {
    // Add some sample items.
    for (User user : ITEMS) {
      ITEM_MAP.put(user.username, user);
    }
  }

  /**
   * A dummy item representing a piece of content.
   */
  public static class User {
    public final int id;
    public final String username;
    public final String name;
    public final int avatarResId;

    public User(int id, String username, String name, int avatarResId) {
      this.id = id;
      this.username = username;
      this.name = name;
      this.avatarResId = avatarResId;
    }
  }
}
