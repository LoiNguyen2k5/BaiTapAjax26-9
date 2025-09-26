package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import vn.iostar.model.Category;
import vn.iostar.model.Product;
import vn.iostar.model.User;
import vn.iostar.repository.CategoryRepository;
import vn.iostar.repository.ProductRepository;
import vn.iostar.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphqlApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // =========== QUERIES ===========

    // Yêu cầu 2.1: Hiển thị tất cả product có price từ thấp đến cao
    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // Yêu cầu 2.2: Lấy tất cả product của 01 category
    @QueryMapping
    public List<Product> productsByCategoryId(@Argument Integer categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    // Queries CRUD cơ bản
    @QueryMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<User> getUserById(@Argument Integer id) {
        return userRepository.findById(id);
    }

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    @QueryMapping
    public Optional<Category> getCategoryById(@Argument Integer id) {
        return categoryRepository.findById(id);
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Optional<Product> getProductById(@Argument Integer id) {
        return productRepository.findById(id);
    }
    
    // =========== MUTATIONS ===========

    // --- User CRUD ---
    @MutationMapping
    public User createUser(@Argument UserInput user) {
        User newUser = new User(user.fullname(), user.email(), user.password(), user.phone());
        return userRepository.save(newUser);
    }

    @MutationMapping
    public User updateUser(@Argument Integer id, @Argument UserInput user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setFullname(user.fullname());
        existingUser.setEmail(user.email());
        existingUser.setPassword(user.password());
        existingUser.setPhone(user.phone());
        return userRepository.save(existingUser);
    }

    @MutationMapping
    public boolean deleteUser(@Argument Integer id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    // --- Category CRUD ---
    @MutationMapping
    public Category createCategory(@Argument CategoryInput category) {
        Category newCategory = new Category(category.name(), category.images());
        return categoryRepository.save(newCategory);
    }

    @MutationMapping
    public Category updateCategory(@Argument Integer id, @Argument CategoryInput category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        existingCategory.setName(category.name());
        existingCategory.setImages(category.images());
        return categoryRepository.save(existingCategory);
    }

    @MutationMapping
    public boolean deleteCategory(@Argument Integer id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }

    // --- Product CRUD ---
    @MutationMapping
    public Product createProduct(@Argument ProductInput product) {
        User user = userRepository.findById(product.userId())
                .orElseThrow(() -> new RuntimeException("User not found for product creation"));
        Product newProduct = new Product(product.title(), product.quantity(), product.desc(), product.price(), user, null);
        return productRepository.save(newProduct);
    }
    
    @MutationMapping
    public Product updateProduct(@Argument Integer id, @Argument ProductInput product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        User user = userRepository.findById(product.userId())
                .orElseThrow(() -> new RuntimeException("User not found for product update"));

        existingProduct.setTitle(product.title());
        existingProduct.setQuantity(product.quantity());
        existingProduct.setDesc(product.desc());
        existingProduct.setPrice(product.price());
        existingProduct.setUser(user);
        return productRepository.save(existingProduct);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Integer id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    // Lớp Input lồng trong Controller để tiện quản lý
    public record UserInput(String fullname, String email, String password, String phone) {}
    public record CategoryInput(String name, String images) {}
    public record ProductInput(String title, Integer quantity, String desc, Double price, Integer userId) {}
}