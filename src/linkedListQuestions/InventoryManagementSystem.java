package linkedListQuestions;

import java.util.Comparator;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Name: " + itemName +
                ", Quantity: " + quantity + ", Price: " + price;
    }
}

class ItemNode {
    Item data;
    ItemNode next;

    public ItemNode(Item data) {
        this.data = data;
        this.next = null;
    }
}

class InventoryLinkedList {
    ItemNode head;

    public InventoryLinkedList() {
        head = null;
    }

    // 1. Add Operations

    public void addFirst(Item item) {
        ItemNode newNode = new ItemNode(item);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(Item item) {
        ItemNode newNode = new ItemNode(item);
        if (head == null) {
            head = newNode;
            return;
        }
        ItemNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void addAtPosition(Item item, int position) {
        if (position < 0) {
            System.out.println("Invalid position. Position must be non-negative.");
            return;
        }
        if (position == 0) {
            addFirst(item);
            return;
        }
        ItemNode newNode = new ItemNode(item);
        ItemNode current = head;
        int count = 0;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Invalid position. Position exceeds list length.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    // 2. Remove Operation

    public void removeById(int itemId) {
        if (head == null) {
            System.out.println("List is empty. Cannot remove.");
            return;
        }
        if (head.data.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " removed.");
            return;
        }
        ItemNode current = head;
        ItemNode prev = null;
        while (current != null && current.data.itemId != itemId) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println("Item with ID " + itemId + " not found.");
            return;
        }
        prev.next = current.next;
        System.out.println("Item with ID " + itemId + " removed.");
    }

    // 3. Update Operation

    public void updateQuantity(int itemId, int newQuantity) {
        ItemNode current = head;
        while (current != null) {
            if (current.data.itemId == itemId) {
                current.data.quantity = newQuantity;
                System.out.println("Quantity updated for item with ID " + itemId);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    // 4. Search Operations

    public Item searchById(int itemId) {
        ItemNode current = head;
        while (current != null) {
            if (current.data.itemId == itemId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Item searchByName(String itemName) {
        ItemNode current = head;
        while (current != null) {
            if (current.data.itemName.equals(itemName)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // 5. Calculate Total Value

    public double calculateTotalValue() {
        double totalValue = 0;
        ItemNode current = head;
        while (current != null) {
            totalValue += current.data.quantity * current.data.price;
            current = current.next;
        }
        return totalValue;
    }

    // 6. Sort Operations
    // Helper function for merging two sorted sublists (used in merge sort)
    private ItemNode merge(ItemNode a, ItemNode b, Comparator<Item> comparator) {
        ItemNode result = null;

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (comparator.compare(a.data, b.data) <= 0) {
            result = a;
            result.next = merge(a.next, b, comparator);
        } else {
            result = b;
            result.next = merge(a, b.next, comparator);
        }
        return result;
    }

    // Recursive merge sort function
    private ItemNode mergeSort(ItemNode h, Comparator<Item> comparator) {
        if (h == null || h.next == null) {
            return h;
        }

        // Get the middle of the list
        ItemNode middle = getMiddle(h);
        ItemNode nextOfMiddle = middle.next;
        middle.next = null; // Split the list

        // Recursively sort the two halves
        ItemNode left = mergeSort(h, comparator);
        ItemNode right = mergeSort(nextOfMiddle, comparator);

        // Merge the sorted halves
        return merge(left, right, comparator);
    }


    // Utility function to get the middle of the linked list
    private ItemNode getMiddle(ItemNode h) {
        if (h == null) {
            return h;
        }
        ItemNode slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public void sortByName(boolean ascending) {
        Comparator<Item> nameComparator = Comparator.comparing(item -> item.itemName);
        if (!ascending) {
            nameComparator = nameComparator.reversed();
        }
        head = mergeSort(head, nameComparator);
    }

    public void sortByPrice(boolean ascending) {
        Comparator<Item> priceComparator = Comparator.comparingDouble(item -> item.price);
        if (!ascending) {
            priceComparator = priceComparator.reversed();
        }
        head = mergeSort(head, priceComparator);
    }

    // Display all items
    public void displayAllItems() {
        ItemNode current = head;
        if (current == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory Items:");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryLinkedList inventory = new InventoryLinkedList();

        // Adding items
        inventory.addLast(new Item("Laptop", 101, 5, 1200.00));
        inventory.addLast(new Item("Mouse", 102, 20, 25.00));
        inventory.addFirst(new Item("Keyboard", 103, 10, 75.00));
        inventory.addAtPosition(new Item("Monitor", 104, 8, 300.00), 2);

        // Displaying items
        inventory.displayAllItems();

        // Calculating total value
        System.out.println("\nTotal Inventory Value: $" + inventory.calculateTotalValue());

        // Updating quantity
        inventory.updateQuantity(102, 25);
        System.out.println("\nAfter updating quantity of item 102:");
        inventory.displayAllItems();

        // Searching for items
        Item foundItem = inventory.searchById(103);
        System.out.println("\nSearching for item with ID 103: " + (foundItem != null ? foundItem : "Not Found"));


    }
}
