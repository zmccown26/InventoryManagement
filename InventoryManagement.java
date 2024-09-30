// Inventory Management System with Enhanced Features
//
// Key Features:
// - Add, remove, and view items in the inventory
// - Create and manage a restocking list for low inventory items
// - Track items that are not selling well and take action
// - Adjust item prices or mark items as no-restock
// - View updated price lists and items marked for no-restock
//
// Ideal for businesses such as restaurants, grocery stores, or retail shops that need efficient inventory management.






import java.util.Scanner;

public class InventoryManagement {

    // Arrays to store inventory data
    private static String[] inventoryItems = new String[100];
    private static int[] inventoryQuantities = new int[100];
    private static String[] lowInventoryItems = new String[100];
    private static int[] lowInventoryQuantities = new int[100];
    private static String[] notSellingItems = new String[100];
    private static String[] newPriceItems = new String[100];
    private static double[] newPrices = new double[100];
    private static String[] dontRestockItems = new String[100];

    private static int inventoryCount = 0;
    private static int lowInventoryCount = 0;
    private static int notSellingCount = 0;
    private static int newPriceCount = 0;
    private static int dontRestockCount = 0;

    private static Scanner scanner = new Scanner(System.in);
    private static String company;

    public static void main(String[] args) {
        // Get the name of the company
        System.out.print("What company will you be doing inventory for? ");
        company = scanner.nextLine();

        boolean continueProgram = true;

        while (continueProgram) {
            // Show menu options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add item to inventory");
            System.out.println("2. Remove item from inventory");
            System.out.println("3. View current inventory");
            System.out.println("4. Make a list of items low in inventory");
            System.out.println("5. View low inventory list");
            System.out.println("6. Make a list of items that are not selling");
            System.out.println("7. View list of items that are not selling");
            System.out.println("8. View list of items with a new price");
            System.out.println("9. View list of items that should not be restocked again");
            System.out.println("10. Exit\n");

            // Get user input
            System.out.print("Enter your choice (1-10): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    lowInventory();
                    break;
                case 5:
                    viewLowInventory();
                    break;
                case 6:
                    notSelling();
                    break;
                case 7:
                    viewNotSellingList();
                    break;
                case 8:
                    viewNewPriceList();
                    break;
                case 9:
                    viewDontRestockList();
                    break;
                case 10:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Ask the user if they want to continue
            if (continueProgram) {
                System.out.print("\nWould you like to continue? (Y/N): ");
                String response = scanner.nextLine().trim().toUpperCase();
                if (response.equals("N")) {
                    continueProgram = false;
                    System.out.println("Exiting program. Goodbye!");
                }
            }
        }
    }

    // Add item to inventory
    public static void addItem() {
        System.out.print("Enter name of item: ");
        String item = scanner.nextLine();
        System.out.print("Enter quantity of item: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 

        // Add item to inventory arrays
        inventoryItems[inventoryCount] = item;
        inventoryQuantities[inventoryCount] = quantity;
        inventoryCount++;

        System.out.println(quantity + " " + item + "(s) added to inventory.");
    }

    // Remove item from inventory
    public static void removeItem() {
        System.out.print("Enter name of item: ");
        String item = scanner.nextLine();

        // Find item in inventory
        for (int i = 0; i < inventoryCount; i++) {
            if (inventoryItems[i].equals(item)) {
                System.out.print("Enter quantity to be removed: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); 

                if (quantity > inventoryQuantities[i]) {
                    System.out.println("Error: Only " + inventoryQuantities[i] + " " + item + "(s) in inventory.");
                } else {
                    inventoryQuantities[i] -= quantity;
                    System.out.println(quantity + " " + item + "(s) removed from inventory.");
                }
                return;
            }
        }
        System.out.println("Item not found in inventory.");
    }

    // View current inventory
    public static void viewInventory() {
        if (inventoryCount == 0) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current inventory for " + company + ":");
            for (int i = 0; i < inventoryCount; i++) {
                System.out.println(inventoryItems[i] + ": " + inventoryQuantities[i]);
            }
        }
    }

    // Add item to low inventory list
    public static void lowInventory() {
        System.out.print("Enter name of item: ");
        String item = scanner.nextLine();
        System.out.print("Enter quantity to be ordered: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 

        // Add item to low inventory arrays
        lowInventoryItems[lowInventoryCount] = item;
        lowInventoryQuantities[lowInventoryCount] = quantity;
        lowInventoryCount++;

        System.out.println(quantity + " " + item + "(s) should be ordered soon.");
    }

    // View low inventory list
    public static void viewLowInventory() {
        if (lowInventoryCount == 0) {
            System.out.println("Low inventory list is empty.");
        } else {
            System.out.println("Low inventory for " + company + ":");
            for (int i = 0; i < lowInventoryCount; i++) {
                System.out.println(lowInventoryItems[i] + ": Order " + lowInventoryQuantities[i]);
            }
        }
    }

    // Add item to not selling list
    public static void notSelling() {
        System.out.print("Enter name of item that isn't selling: ");
        String item = scanner.nextLine();
        System.out.print("Enter price of item: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 

        // Add to not selling list
        System.out.println("What would you like to do with this item?");
        System.out.println("1. Drop the price");
        System.out.println("2. Don't restock");
        System.out.println("3. Keep as is");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            System.out.print("Enter amount to drop the price: ");
            double priceDrop = scanner.nextDouble();
            scanner.nextLine(); 
            double newPrice = price - priceDrop;
            newPriceItems[newPriceCount] = item;
            newPrices[newPriceCount] = newPrice;
            newPriceCount++;
            System.out.println("The new price of " + item + " is $" + newPrice);
        } else if (choice == 2) {
            dontRestockItems[dontRestockCount] = item;
            dontRestockCount++;
            System.out.println("The item " + item + " has been added to the no-restock list.");
        } else {
            notSellingItems[notSellingCount] = item;
            notSellingCount++;
            System.out.println("Keep the item the same, but consider more advertising.");
        }
    }

    // View not selling list
    public static void viewNotSellingList() {
        if (notSellingCount == 0) {
            System.out.println("No items in the not selling list.");
        } else {
            System.out.println("Items that aren't selling:");
            for (int i = 0; i < notSellingCount; i++) {
                System.out.println(notSellingItems[i]);
            }
        }
    }

    // View new price list
    public static void viewNewPriceList() {
        if (newPriceCount == 0) {
            System.out.println("No items with a new price.");
        } else {
            System.out.println("Items with new prices:");
            for (int i = 0; i < newPriceCount; i++) {
                System.out.println(newPriceItems[i] + ": $" + newPrices[i]);
            }
        }
    }

    // View no-restock list
    public static void viewDontRestockList() {
        if (dontRestockCount == 0) {
            System.out.println("No items in the no-restock list.");
        } else {
            System.out.println("Items that should not be restocked:");
            for (int i = 0; i < dontRestockCount; i++) {
                System.out.println(dontRestockItems[i]);
            }
        }
    }
}
