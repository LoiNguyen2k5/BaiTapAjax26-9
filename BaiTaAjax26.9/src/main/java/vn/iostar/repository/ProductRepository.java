package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Tìm tất cả sản phẩm và sắp xếp theo giá tăng dần
    List<Product> findAllByOrderByPriceAsc();

    // SỬA DÒNG NÀY
    // Tên phương thức phải khớp với thuộc tính "category" trong lớp Product
    List<Product> findByCategory_Id(Integer categoryId);

}