package Sequential;

public class Queue {
    int front_index = 0;
    int back_index = -1;
    int[] queue = new int[2000000];

    void push(int n){
        queue[++back_index] = n;
    }

    int pop(){
        if (this.size() == 0){
            return -1;
        } else {
            return queue[front_index++];
        }
    }

    int size(){
        return back_index - front_index + 1;
    }

    int empty(){
        if (this.size() == 0){
            return 1;
        } else {
            return 0;
        }
    }

    int front(){
        if (this.size() == 0){
            return -1;
        } else {
            return queue[front_index];
        }
    }

    int back(){
        if (this.size() == 0){
            return -1;
        } else {
            return queue[back_index];
        }
    }
}

