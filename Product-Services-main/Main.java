import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface ProductService {
    void addProduct(Product product);
    void updateProduct(int productId, Product updatedProduct);
    void deleteProduct(int productId);
    Product getProduct(int productId);
    Map<Integer, Product> getAllProducts();
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ProductServiceImpl implements ProductService {
    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        this.products = new HashMap<>();
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void updateProduct(int productId, Product updatedProduct) {
        if (products.containsKey(productId)) {
            products.put(productId, updatedProduct);
        } else {
            System.out.println("Product not found!");
        }
    }

    @Override
    public void deleteProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
        } else {
            System.out.println("Product not found!");
        }
    }

    @Override
    public Product getProduct(int productId) {
        return products.get(productId);
    }

    @Override
    public Map<Integer, Product> getAllProducts() {
        return products;
    }
}

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Get Product");
            System.out.println("5. Get All Products");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    Product product = new Product(id, name, price);
                    productService.addProduct(product);
                    System.out.println("Product added successfully!");
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int updateId = scanner.nextInt();
                    Product updatedProduct = productService.getProduct(updateId);
                    if (updatedProduct != null) {
                        System.out.print("Enter new product name: ");
                        String updatedName = scanner.next();
                        System.out.print("Enter new product price: ");
                        double updatedPrice = scanner.nextDouble();
                        updatedProduct.setName(updatedName);
                        updatedProduct.setPrice(updatedPrice);
                        productService.updateProduct(updateId, updatedProduct);
                        System.out.println("Product updated successfully!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID: ");
                    int deleteId = scanner.nextInt();
                    productService.deleteProduct(deleteId);
                    System.out.println("Product deleted successfully!");
                    break;
                case 4:
                    System.out.print("Enter product ID: ");
                    int getId = scanner.nextInt();
                    Product getProduct = productService.getProduct(getId);
                    if (getProduct != null) {
                        System.out.println("Product ID: " + getProduct.getId());
                        System.out.println("Product Name: " + getProduct.getName());
                        System.out.println("Product Price: " + getProduct.getPrice());
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 5:
                    Map<Integer, Product> allProducts = productService.getAllProducts();
                    if (!allProducts.isEmpty()) {
                        System.out.println("All Products:");
                        for (Product p : allProducts.values()) {
                            System.out.println("Product ID: " + p.getId());
                            System.out.println("Product Name: " + p.getName());
                            System.out.println("Product Price: " + p.getPrice());
                            System.out.println("------------------------");
                        }
                    } else {
                        System.out.println("No products found!");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
