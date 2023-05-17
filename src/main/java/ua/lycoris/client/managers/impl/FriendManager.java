package ua.lycoris.client.managers.impl;

import ua.lycoris.client.managers.Manager;

import java.util.ArrayList;
import java.util.List;

public class FriendManager extends Manager {

    private List<Friend> friends = new ArrayList<Friend>();

    public void addFriend(String friendName) {
        Friend friend = new Friend(friendName);
        friends.add(friend);
    }

    public void dellFriend(String friendName) {
        Friend friend = new Friend(friendName);
        friends.remove(friend);
    }

    boolean isFriend(String friendName) {
        return this.friends.stream().anyMatch(friend -> friend.username.equalsIgnoreCase(friendName));
    }



    public static class Friend {
        private final String username;
        public Friend(String username) {
            this.username = username;
        }
        public String getUsername() {
            return this.username;
        }
    }

}
