public class Trio<T> {
    private T item1, item2, item3;

    public Trio(T item1, T item2, T item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public Trio(T item) {
        this(item,item,item);
    }

    public void setItem1(T item1){
        this.item1 = item1;
    }
    public void setItem2(T item2){
        this.item2 = item2;
    }
    public void setItem3(T item3){
        this.item3 = item3;
    }

    public T getItem1(){
        return item1;
    }
    public T getItem2(){
        return item2;
    }
    public T getItem3(){
        return item3;
    }

    @Override
    public String toString(){
        return item1.toString() + "\t" + item2.toString() + "\t" + item3.toString();
    }

    public void replaceAll(T newItem) {
        this.item1 = newItem;
        this.item2 = newItem;
        this.item3 = newItem;
    }

    public boolean hasDuplicates() {
        return item1.equals(item2) || item1.equals(item3) || item2.equals(item3);
    }

    public int count(T item){
        int itemCount = 0;
        if (item1.equals(item)){
            itemCount++;
        }
        if (item2.equals(item)){
            itemCount++;
        }
        if (item3.equals(item)){
            itemCount++;
        }
        return itemCount;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Trio<?>) {
            Trio<T> otherTrio = (Trio<T>) other;
            return (this.count(otherTrio.item1) == otherTrio.count(otherTrio.item1)) && (this.count(otherTrio.item2) == otherTrio.count(otherTrio.item2)) && (this.count(otherTrio.item3) == otherTrio.count(otherTrio.item3));

        }
        return false;

    }

}
