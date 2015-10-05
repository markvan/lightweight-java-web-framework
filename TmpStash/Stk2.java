package SpecialStack;

import java.util.ArrayList;
import java.util.List;

public class Stk2 {
    List<Integer> s = new ArrayList<Integer>();


    public void push(int val) {
        s.add(val);
    }

    public int pop() {
        int retVal = s.remove(s.size()-1);
        return retVal;
    }

    public int count() {
        return s.size();
    }
}


