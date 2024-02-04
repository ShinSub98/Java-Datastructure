package Sequential;

public class Stack{
    int[] stack = new int[10000];
    int front = -1;

    void push(int n){
        stack[++front] = n;
    }

    int pop(){
        if (front == -1){
            return -1;
        } else {
            return stack[front--];
        }
    }

    int size(){
        return front+1;
    }

    int empty(){
        if (front == -1){
            return 1;
        } else {
            return 0;
        }
    }

    int top(){
        if (front == -1){
            return -1;
        } else {
            return stack[front];
        }
    }
}

