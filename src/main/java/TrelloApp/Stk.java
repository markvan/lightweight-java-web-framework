package TrelloApp;

public class Stk {
    private int[] s = new int[10];
    private int ptr = -1;

    public void push(int val){
        ptr+=1;
        s[ptr]=val;
    }

    public int pop(){
        int retVal = s[ptr];
        ptr -= 1;
        return retVal;
    }

    public int count() {
        return ptr+1;
    }
}


