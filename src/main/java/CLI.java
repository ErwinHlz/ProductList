import java.util.Scanner;

public class CLI {
    Inventory inventory = new Inventory();
    Scanner scanner = new Scanner(System.in);

    void showMenu(){


        System.out.println("Welcome to your ProductListManager");
        System.out.println("1. Add product");
        System.out.println("2. Remove product");
        System.out.println("3. Search product by ID");
        System.out.println("4. Print product by Category");
        System.out.println("5. Print all products");
        System.out.println("6. Sort products by Name");
        System.out.println("7. Sort products by Price");
        System.out.println("8. Print products with low quantity");
        System.out.println("9. Print products by filter");
        System.out.println("10. Increase prices by percentage");
        System.out.println("11. End program");
        System.out.println("Please select an option:");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch (input){
            case 1:
                productAdd();
                showMenu();
                break;
            case 2:
                productRemove();
                showMenu();
                break;
            case 3:
                searchProductById();
                showMenu();
                break;
            case 4:
                findProductbyCategory();
                showMenu();
                break;
            case 5:
                printAll();
                showMenu();
                break;
            case 6:
                sortProductByName();
                showMenu();
                break;
            case 7:
                sortProductByPrice();
                showMenu();
                break;
            case 8:
                printLowQuantities();
                showMenu();
                break;
            case 9:
                printProductsByPredicate();
                showMenu();
                break;
            case 10:
                increasePrice();
                showMenu();
                break;
            case 11:
                System.exit(0);
                break;
            default:
                System.out.println("not a valid option, stinker");
                showMenu();
        }
    }

    void productAdd(){

        System.out.println("name?");
        String name = scanner.next();
        System.out.println("ID?");
        int id = scanner.nextInt();
        System.out.println("category?");
        String category = scanner.next();
        System.out.println("Price?");
        double price = scanner.nextDouble();
        System.out.println("Quantity?");
        int quantity = scanner.nextInt();

        Product addProduct = new Product(id,name,category,price,quantity);
        inventory.addProduct(addProduct);
    }

    void productRemove(){
        System.out.println("Which Product you want to remove? Enter ID:");
        int id = scanner.nextInt();
        inventory.removeProduct(id);

    }

    void searchProductById(){
        System.out.println("Which Product you want to search? Enter ID:");
        int id = scanner.nextInt();
        System.out.println(inventory.findProductById(id));
    }

    void findProductbyCategory(){
        System.out.println("Which category you want to find?");
        String category = scanner.next();
        inventory.findProductbyCategory(category).forEach(System.out::println);
    }

    void printAll(){
        inventory.getAllProducts().forEach(System.out::println);
    }

    void sortProductByName(){
        inventory.sortProductsByName();
        System.out.println("Sorted products by name:");
        printAll();
    }

    void sortProductByPrice(){
        inventory.sortProductsByPrice();
        System.out.println("Sorted products by price:");
        printAll();

    }

    void printLowQuantities(){
        System.out.println("Until which quantity level?");
        int quantityLevel = scanner.nextInt();
        inventory.getLowStockProducts(quantityLevel).forEach(System.out::println);
    }

    void printProductsByPredicate(){
        System.out.println("Enter filter type (name, category, price, quantity):");
        String filter = scanner.next();

        switch (filter.toLowerCase()) {
            case "name":
                System.out.println("Enter product name:");
                String name = scanner.next();
                inventory.filterProducts(product -> product.getName().equals(name)).forEach(System.out::println);
                break;
            case "category":
                System.out.println("Enter product category:");
                String category = scanner.next();
                inventory.filterProducts(product -> product.getCategory().equals(category)).forEach(System.out::println);
                break;
            case "price":
                System.out.println("Enter product price:");
                double price = scanner.nextDouble();
                inventory.filterProducts(product -> product.getPrice() == price).forEach(System.out::println);
                break;
            case "quantity":
                System.out.println("Enter product quantity:");
                int quantity = scanner.nextInt();
                inventory.filterProducts(product -> product.getQuantity() == quantity).forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid filter type.");
                printProductsByPredicate();
        }

    }

    void increasePrice(){
        System.out.println("Percentage?");
        double percentage = scanner.nextDouble();
        inventory.applyToProducts(x ->x.setPrice(x.getPrice() + percentage/100 * x.getPrice()));
    }



}
