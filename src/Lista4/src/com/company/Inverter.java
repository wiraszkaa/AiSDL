package Lista4.src.com.company;

import Lista4.src.com.company.exceptions.EmptyQueueException;
import Lista4.src.com.company.exceptions.FullQueueException;
import Lista4.src.com.company.exceptions.FullStackException;


public class Inverter {
    public static <T> IQueue<T> invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException, FullStackException {
        IStack<T> stack = new ArrayStack<>(queue.size());
        while(!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }
        IQueue<T> result = new TwoWayLinkedListQueue<>(queue.size());
        while(!stack.isEmpty()) {
            result.enqueue(stack.pop());
        }
        return result;
    }
}
