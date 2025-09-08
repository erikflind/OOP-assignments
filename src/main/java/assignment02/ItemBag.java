package assignment02;

import java.util.ArrayList;

public class ItemBag {
    private final ArrayList<Item> collectionOfItems;
    private final double maximumWeight;
    private double currentWeight;

    public ItemBag(double weight) {
        this.collectionOfItems = new ArrayList<>();
        this.maximumWeight = weight;
        this.currentWeight = 0;
    }

    // Adds item to ItemBag - automatically sorts by weight high > low
    public int addItem(Item item){
        if (item.getItemWeight() + this.currentWeight > this.maximumWeight) {
            return -1;
        } else if (this.collectionOfItems.isEmpty()) {
            this.collectionOfItems.add(item);
            this.currentWeight = item.getItemWeight();
            return 0;
        } else {
            for (int i = 0; i < this.collectionOfItems.size(); i++){
                double itemAtIndex = this.collectionOfItems.get(i).getItemWeight();
                if (item.getItemWeight() >= itemAtIndex) {
                    this.collectionOfItems.add(i,item);
                    this.currentWeight += item.getItemWeight();
                    return i;
                }
            }
            this.collectionOfItems.add(item);
            this.currentWeight += item.getItemWeight();
            return this.collectionOfItems.size() - 1;
        }
    }

    // Removes item at specific index and returns item reference
    public Item removeItemAt(int index){
        if (index < 0 || index > this.collectionOfItems.size() - 1 ) {
            return null;
        } else {
            Item itemReference = this.collectionOfItems.get(index);
            this.collectionOfItems.remove(index);
            this.currentWeight -= itemReference.getItemWeight();
            return itemReference;
        }
    }

    // Removes the heaviest item from the bag and returns item reference
    public Item popItem(){ return removeItemAt(0); }

    // Returns the toString of item at specific index
    public String peekItemAt(int index){
        if (this.collectionOfItems.isEmpty() || index < 0 || index > this.collectionOfItems.size() - 1) {
            return "";
        } else
            return this.collectionOfItems.get(index).toString();
    }


    public double getMaxWeight() { return maximumWeight; }
    public int getNumOfItems() { return this.collectionOfItems.size(); }
    public double getCurrentWeight() { return this.currentWeight; }

}
