import java.util.ArrayList;
import java.util.List;

public class ListFrontBackLimitedSizeList<T> implements FrontBackLimitedSizeListInterface<T> {
    private List<T> list; // initialize to type ArrayList<T> in the ListFrontBackLimitedSizeList constructor
    private int maxSize;

    public ListFrontBackLimitedSizeList(int size) {
        List<T> tempList = new ArrayList<T>(size);
        list = tempList;
        maxSize = size;
    }

    public boolean addFront(T newEntry) {
        if (isFull()){
            return false;
        } else {
            list.add(0,newEntry);
            return true;
        }
    }

    public boolean addBack(T newEntry) {
        if (isFull()){
            return false;
        } else {
            list.add(newEntry);
            return true;
        }
    }

    public T removeFront(){
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public T removeBack(){
        // add type check
        if (isEmpty()) {
            return null;
        }
        return list.remove(list.size()-1);
    }

    public boolean contains (T anEntry){
        if (isEmpty()) {
            return false;
        }
        return list.contains(anEntry);
    }

    public int indexOf(T anEntry){
        if (isEmpty()){
            return -1;
        }
        return list.indexOf(anEntry);
    }

    public int lastIndexOf(T anEntry){
        if (isEmpty()){
            return -1;
        }
        return list.lastIndexOf(anEntry);
    }

    public T getEntry(int givenPosition){
        if (givenPosition >= list.size() || givenPosition < 0 ){
            return null;
        }
        return list.get(givenPosition);
    }

    @Override
    public String toString(){
        return "size=" + list.size() + "; capacity=" + maxSize +";  " + list ;
    }

    public void clear(){
        list.clear();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public boolean isFull(){
        return list.size() >= maxSize;
    }

    public int size(){
        return list.size();
    }
}
