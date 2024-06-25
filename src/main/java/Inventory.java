import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Inventory{

    Map<Integer,Product> inventory = new LinkedHashMap<>();


    void addProduct(Product product){
        inventory.put(product.productId, product);
    }

    boolean removeProduct(int productId){
        inventory.remove(productId);
        return inventory.remove(productId) != null;
    }

    Product findProductById(int productId){
        return inventory.get(productId);
    }

    List<Product> findProductbyCategory(String category){
        // inventory.values : returns a Collection view of the values contained in this map
        // notwendig, weil hier Collections als Argument ist

        return inventory.values().stream().filter(x ->x.category.equals(category)).toList();
    }

    List<Product> getAllProducts(){
        return inventory.values().stream().toList();
    }

    void sortProductsByName(){

        Map<String,Product> sortedMap = new TreeMap<>();
        for (Product product : this.inventory.values()){
            sortedMap.put(product.getName(),product);
        }
        inventory.clear();
        for (Map.Entry<String, Product> x : sortedMap.entrySet()){
            inventory.put(x.getValue().getProductId(), x.getValue());
        }
    }

    void sortProductsByPrice(){

        Map<Double,Product> sortedMap = new TreeMap<>();
        for (Product product : this.inventory.values()){
            sortedMap.put(product.getPrice(),product);
        }
        inventory.clear();
        for (Map.Entry<Double, Product> x : sortedMap.entrySet()){
            inventory.put(x.getValue().getProductId(), x.getValue());
        }

    }

    List<Product> getLowStockProducts(int threshold){
        return inventory.values().stream().filter(x -> x.getQuantity() < threshold).toList();
    }

    List<Product> filterProducts(Predicate<Product> predicate){
        return inventory.values().stream().filter(predicate).toList();
    }

    void applyToProducts(Consumer<Product> consumer){
        for(Product product : inventory.values()){
            consumer.accept(product);
        }

    }





}
