import java.util.LinkedList;
import java.util.List;

public class ListDeque<T> implements DequeInterface<T> {
    private List<T> list;
//    public List<T> list;

    public ListDeque() {
        list = new LinkedList<T>();
    }

    public void addToFront(T newEntry){
        list.add(0,newEntry);
    }

    public void addToBack(T newEntry) {
        list.add(newEntry);
    }

    public T removeFront(){
        if (list.isEmpty()){
            throw new EmptyQueueException();
        } else {
            return list.remove(0);
        }
    }

    public T removeBack(){
        if (list.isEmpty()){
            throw new EmptyQueueException();
        } else {
            return list.remove(list.size()-1);
        }
    }

    public T getFront(){
        return list.get(0);
    }

    public T getBack(){
        return list.get(list.size()-1);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }
}
