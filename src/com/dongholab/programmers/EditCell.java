package com.dongholab.programmers;

import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        LinkedList<Integer> removeList = new LinkedList<>();
        for (String command: cmd) {
            StringTokenizer st = new StringTokenizer(command);
            char type = st.nextToken().charAt(0);
            int value;
            switch (type) {
                case 'U':
                    value = Integer.parseInt(st.nextToken());
                    k -= value;
                    break;
                case 'D':
                    value = Integer.parseInt(st.nextToken());
                    k += value;
                    break;
                case 'C':
                    removeList.push(k);
                    n--;
                    if (k == n) {
                        k--;
                    }
                    break;
                case 'Z':
                    value = removeList.pop();
                    if (k >= value) {
                        k++;
                    }
                    n++;
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append('O');
        }
        while(!removeList.isEmpty()) {
            sb.insert(removeList.pop().intValue(), 'X');
        }
        return sb.toString();
    }
}

public class EditCell {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
        System.out.println(s.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
