/**
 * A class that describes the operations of a shopping cart.
 *
 * @author Elias Magdaleno
 * Date: September 19, 2022
 */

public class ShoppingCart {

    BagInterface<Item> shoppingCart;

    /**
     * Creates an empty shopping cart.
     */
    public ShoppingCart() {
        shoppingCart = new ArrayBag<>();
    }

    /**
     * Creates a new shopping cart and adds all the items
     * in the input array to the cart.
     *
     * @param items the Items to be added to the cart
     */
    public ShoppingCart(Item[] items) {
        shoppingCart = new ArrayBag<Item>();
        for (Item item : items) {
            shoppingCart.add(item);
        }
    }

    /**
     * Adds the requested quantity of the specified item to the cart.
     *
     * @param item     the Item to be added to the cart
     * @param quantity how many of the specified item we want to add
     * @return true if the addition is successful, or false if not
     */
    public boolean addToCart(Item item, int quantity) {

        if ((shoppingCart.getCurrentSize() + quantity) < shoppingCart.getArraySize()) {
            for (int i = 0; i < quantity; i++) {
                shoppingCart.add(item);
            }
            return true;

        }
        return false;
    }

    /**
     * Removes the requested quantity of the specified item to the cart.
     *
     * @param item     the Item to be removed from the cart
     * @param quantity how many of the specified item we want to remove
     * @return true if the removal is successful, or false if not
     */
    public boolean removeFromCart(Item item, int quantity) {
        int itemCounter = 0;


        if (shoppingCart.contains(item)) {
            while (itemCounter < quantity) {
                shoppingCart.remove(item);
                itemCounter++;
            }
            return true;
        }

        return false;
    }

    /**
     * Prints out the items currently in the cart in an organized manner.
     * Sample output:
     * 3x Bird feeder @ $20.50
     * 1x Bird bath @ $44.99
     * 1x Sunflower seeds @ $12.95
     * Total cost: $119.44
     * <p>
     * Items may be listed in any order.
     * Duplicate items are indicated with the quantity (eg. "3x Bird feeder")
     * rather than listing that item multiple times.
     */
    public void viewCart() {
        //System.out.println(shoppingCart.getCurrentSize());
        Object[] cartItem = shoppingCart.toArray();
        Item[] printItems = new Item[shoppingCart.getCurrentSize()]; 
        for (int i = 0; i < shoppingCart.getCurrentSize(); i++) {
            Item currentItem = (Item) cartItem[i];
            boolean contains = false;
            for (int j = 0; j < shoppingCart.getCurrentSize(); j++) {
                if (currentItem.equals(printItems[j])) { //if the item is in already printItem then it will not print anything to avoid duplicates
                    contains = true;
                }
            }
            if (!contains) { //if the item is not already in printItem then it will print the quantity, name, and price
                System.out.println(shoppingCart.getFrequencyOf(currentItem) + "x " + currentItem.getName() + " @ $" + currentItem.getPrice());
                printItems[i] = currentItem;
            }


        }
        System.out.println();

    }

    /**
     * Simulates the checkout process by removing items from the cart, one at a time.
     * We print out the name of each item as it is removed
     * and also calculate and print out the total cost of all the items.
     * <p>
     * Note: This is the same code we wrote in class, reproduced here for your reference.
     * No need to modify this in your Assignment 2 submission.
     */
    public void checkout() {
        double totalCost = 0;
        while (!shoppingCart.isEmpty()) {
            Item removedItem = shoppingCart.remove();
            System.out.println("Purchasing " + removedItem.getName());
            totalCost = totalCost + removedItem.getPrice();
        }
        System.out.println("Total cost: " + "\t$" + totalCost);
    }
}
