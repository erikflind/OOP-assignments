package assignment02;

import java.util.Locale;

public class Item {
    private final String itemName;
    private final int healingPower;
    private final double itemWeight;

    public Item(String itemName, int healingPower, double itemWeight){
        this.itemName = itemName;
        this.healingPower = healingPower;
        this.itemWeight = itemWeight;
    }

    // Returns true if two items are equal
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        } else if (anotherObject == null) {
            return false;
        } else if (anotherObject instanceof Item) {
            Item otherObject = (Item) anotherObject;
            boolean sameItemName = this.itemName.equals(otherObject.itemName);
            boolean sameHealingPower = this.healingPower == otherObject.healingPower;
            boolean sameItemWeight = this.itemWeight == otherObject.itemWeight;

            return sameItemName && sameHealingPower && sameItemWeight;
        } else {
            return false;
        }
    }


    public String toString(){
        double truncatedValue = Math.floor(this.itemWeight * 100) / 100;
        return String.format(Locale.ENGLISH , this.itemName + " heals " + this.healingPower + " HP. (%.2f)" , truncatedValue);
    }


    public String getItemName() { return this.itemName; }
    public int getPowerValue() { return this.healingPower; }
    public double getItemWeight() { return this.itemWeight; }

}
