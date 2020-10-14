import java.util.Arrays;

public class ArrayFrontBackLimitedSizeList<T> implements FrontBackLimitedSizeListInterface<T> {
    private T[] listArray;
    private int numberOfElements;

    public ArrayFrontBackLimitedSizeList(int maxSize){
        T[] tempArray = (T[]) new Object[maxSize];
        listArray = tempArray;
        numberOfElements = 0;
    }

    public boolean addFront(T newEntry){
        if (isFull()) {
            return false;
        }
        // for non-empty list, make room for the first element
        for (int index = numberOfElements-1; index >= 0; index--){
            listArray[index + 1] = listArray[index];
        }
        // add the new entry at position 0
        listArray[0] = newEntry;
        numberOfElements++;
        return true;
    }

    public boolean addBack(T newEntry){
        if (isFull()) {
            return false;
        } else{
            listArray[numberOfElements] = newEntry;
            numberOfElements++;
            return true;
        }
    }

    public T removeFront(){
        if (isEmpty()){
            return null;
        }
        T result = listArray[0];
        // remove gap at position 0
        for (int index = 0; index < numberOfElements -1; index++){
            listArray[index] = listArray[index+1];
        }
        numberOfElements--;
        return result;
    }

    public T removeBack() {
        if (isEmpty()) {
            return null;
        } else {
            numberOfElements--;
            T result = listArray[numberOfElements];
            listArray[numberOfElements] = null;
            return result;
        }
    }

    public boolean contains (T anEntry){
        boolean found = false;
        int index = 0;
        while (!found && (index < numberOfElements)){
            if (anEntry.equals(listArray[index])){
                found = true;
            }
            index++;
        }
        return found;
    }

    public int indexOf(T anEntry){
        int where = -1;
        boolean found = false;
        int index = 0;
        while (!found && (index< numberOfElements)){
            if (anEntry.equals(listArray[index])){
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }

    public int lastIndexOf(T anEntry){
        int where = -1;
        boolean found = false;
        int index = numberOfElements-1;
        while (!found && (index >= 0)){
            if (anEntry.equals(listArray[index])){
                found = true;
                where = index;
            }
            index--;
        }
        return where;
    }

    public T getEntry(int givenPosition){
        if (isEmpty() || givenPosition >= numberOfElements || givenPosition < 0){
            return null;
        } else {
            return listArray[givenPosition];
        }
    }

    @Override
    public String toString(){
        T[] result = (T[]) new Object[numberOfElements];
        for (int index = 0; index < numberOfElements; index++) {
            result[index] = listArray[index];
        }
        return "size=" + numberOfElements + "; capacity=" + listArray.length +";  " + Arrays.toString(result);
    }

    public void clear(){
        for (int index = 0; index < numberOfElements; index++){
            listArray[index] = null;
        }
        numberOfElements = 0;
    }

    public boolean isEmpty(){
        return numberOfElements == 0;
    }

    public boolean isFull(){
        return numberOfElements >= listArray.length;
    }

    public int size(){
        return numberOfElements;
    }
}
