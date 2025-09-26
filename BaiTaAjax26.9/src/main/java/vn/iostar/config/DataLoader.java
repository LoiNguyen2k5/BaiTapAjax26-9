package vn.iostar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.iostar.model.Category;
import vn.iostar.model.Product;
import vn.iostar.model.User;
import vn.iostar.repository.CategoryRepository;
import vn.iostar.repository.ProductRepository;
import vn.iostar.repository.UserRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Nếu không có user nào, mới tạo dữ liệu mẫu
        if (userRepository.count() == 0) {
            System.out.println("Generating sample data...");

            // 1. Tạo Users
            User user1 = new User("Nguyen Van A", "a@example.com", "123", "0901");
            User user2 = new User("Tran Thi B", "b@example.com", "123", "0902");
            userRepository.saveAll(Arrays.asList(user1, user2));

            // 2. Tạo Categories
            Category cat1 = new Category("Electronics", "electronics.jpg"); // Sẽ có ID = 1
            Category cat2 = new Category("Books", "books.jpg");             // Sẽ có ID = 2
            categoryRepository.saveAll(Arrays.asList(cat1, cat2));

            // 3. Tạo Products và GÁN chúng vào các category đã tạo
            Product p1 = new Product("Laptop Dell", 10, "Laptop core i5", 1500.0, user1, cat1); // Gán vào cat1 (ID=1)
            Product p2 = new Product("Smart Watch", 30, "Đồng hồ thông minh", 200.0, user2, cat1); // Gán vào cat1 (ID=1)
            Product p3 = new Product("Sach Spring Boot", 100, "Sach lap trinh", 50.0, user1, cat2); // Gán vào cat2 (ID=2)
            Product p4 = new Product("iPhone 15", 20, "Apple iPhone", 2500.0, user2, cat1); // Gán vào cat1 (ID=1)
            productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
            
            System.out.println("Sample data generated!");
        }
    }
}