package com.dongholab.programmers.bad_user;

import java.util.*;

class Solution {
    static Set<Set<String>> saveCounter;
    static boolean[] checked;

    public int solution(String[] user_id, String[] banned_id) {
        saveCounter = new HashSet<>();
        checked = new boolean[user_id.length];

        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace('*', '.');
        }

        dfs(0, user_id, banned_id, new HashSet<>());
        return saveCounter.size();
    }

    private static void dfs(int index, String[] user_id, String[] banned_id, Set<String> removeSet) {
        if (removeSet.size() == banned_id.length) {
            saveCounter.add(new HashSet<>(removeSet));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (checked[i]) continue;
            if (user_id[i].matches(banned_id[index])) {
                checked[i] = true;
                removeSet.add(user_id[i]);
                dfs(index + 1, user_id, banned_id, removeSet);
                removeSet.remove(user_id[i]);
                checked[i] = false;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}) == 2);
    }
}
