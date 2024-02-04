package Sequential;

class Deque{
    int front_index = 0;
    int back_index = 0;
    int[] deque = new int[10000];

    int size(){
        if (back_index > front_index){
            return back_index - front_index;
        } else if (back_index == front_index){
            return 0;
        } else {
            return 10000 - (front_index - (back_index));
        }
    }

    void push_front(int n) {
        if (front_index == 0){
            front_index = 9999;
        } else {
            front_index -= 1;
        }
        deque[front_index] = n;
    }

    void push_back(int n) {
        if (back_index == 9999){
            deque[back_index] = n;
            back_index = 0;
        } else {
            deque[back_index++] = n;
        }
    }

    int pop_front() {
        if (this.size() == 0){
            return -1;
        } else {
            if (front_index == 9999){
                front_index = 0;
                return deque[9999];
            } else {
                return deque[front_index++];
            }
        }
    }

    int pop_back() {
        if (this.size() == 0){
            return -1;
        } else {
            if (back_index == 0){
                back_index = 9999;
                return deque[back_index];
            } else {
                return deque[--back_index];
            }
        }
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
            return deque[front_index];
        }
    }

    int back() {
        if (this.size() == 0) {
            return -1;
        } else {
            if (back_index == 0) {
                return deque[9999];
            } else {
                return deque[back_index - 1];
            }
        }
    }
}
