import java.util.Scanner;

public class Models {

    static String[][] handBags = {{"classic handBag", "large handBag", "casual handBag"}, {"30", "50", "40"}};
    static String[][] backPacks = {{"classic backpack", "large backpack", "leather backpack"}, {"32", "50", "38"}};
    static String[][] sportBoots = {{"black boot with thread", "adidas boot", "normal sport black boot with thread"}, {"37", "70", "50"}};
    static String[][] schoolShoes = {{"fabric boot", "black leather school boot", "white leather school boot"}, {"40", "50", "50"}};
    static String[][] beautyTools = {{"Lipstick", "false eyelashes", "nail polish", "Make-up remover", "mascara", "eyeLiner", "basis", "concealer", "powder", "eyeshadow", "cosmetic eye lenses"},
                                     {"2", "2", "3", "5", "7", "4", "6", "6", "6", "10", "11"}};

    static Scanner scanner = new Scanner(System.in);
    static int totalPrice = 0;
    static String bill = "";

    public static void main(String[] args) {
        System.out.println("Welcome\n");
        while (true) {
            int categoryChoice = getCategoryChoice();
            switch (categoryChoice) {
                case 1:
                    handleBags();
                    break;
                case 2:
                    handleShoes();
                    break;
                case 3:
                    addItemToBill(beautyTools);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            handlePostPurchaseOptions();
        }
    }

    static int getCategoryChoice() {
        System.out.println("1 - Bags");
        System.out.println("2 - Shoes");
        System.out.println("3 - Beauty Tools");
        System.out.print("Enter number: ");
        return scanner.nextInt();
    }

    static void handleBags() {
        System.out.println("1 - Hand Bags");
        System.out.println("2 - BackPacks");
        System.out.print("Enter number: ");
        int bagChoice = scanner.nextInt();
        switch (bagChoice) {
            case 1:
                addItemToBill(handBags);
                break;
            case 2:
                addItemToBill(backPacks);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    static void handleShoes() {
        System.out.println("1 - Sports Shoes");
        System.out.println("2 - School Shoes");
        System.out.print("Enter number: ");
        int shoeChoice = scanner.nextInt();
        switch (shoeChoice) {
            case 1:
                addItemToBill(sportBoots);
                break;
            case 2:
                addItemToBill(schoolShoes);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    static void addItemToBill(String[][] items) {
        for (int i = 0; i < items[0].length; i++) {
            System.out.println((i + 1) + ". " + items[0][i] + " ... " + items[1][i]);
        }
        System.out.print("Enter index: ");
        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= items[0].length) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
        bill += "- " + items[0][index] + " ... " + items[1][index] + "\n";
        totalPrice += Integer.parseInt(items[1][index]);
        System.out.println("The item " + items[0][index] + " has been added to the bill.");
    }

    static void handlePostPurchaseOptions() {
        while (true) {
            System.out.println("\n1 - Remove an item from the bill");
            System.out.println("2 - Buy another item");
            System.out.println("3 - Finish the transaction");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    removeItem();
                    break;
                case 2:
                    return;
                case 3:
                    finishTransaction();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void removeItem() {
        if (bill.isEmpty()) {
            System.out.println("Your bill is empty.");
            return;
        }
        System.out.println("Your List: \n" + bill);
        System.out.print("Enter the index of the item to remove: ");
        int index = scanner.nextInt() - 1;

        String[] items = bill.split("\n");
        if (index < 0 || index >= items.length) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        String removedItem = items[index];
        int itemPrice = Integer.parseInt(removedItem.split(" ... ")[1]);
        totalPrice -= itemPrice;
        bill = bill.replace(removedItem + "\n", "");
        System.out.println("The item " + removedItem + " has been removed from the bill.");
    }

    static void finishTransaction() {
        System.out.println("\nTOTAL PRICE: " + totalPrice);
        System.out.println("Your List: \n" + bill);
        System.out.println("Thank you for shopping with us!");
    }
}
