package kodlamaio.nortwind.dataAccess.abstracts;

import kodlamaio.nortwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    @Query("From Product where productName=:productName")
    Product getByProductName(String productName);
    @Query("From Product where productName=:productName and category.categoryId=:categoryId")
    Product getByProductNameAndCategory(String productName, int categoryId);
    @Query("From Product where productName=:productName or category.categoryId=:categoryId")
    List<Product> getByProductNameOrCategory(String productName, int categoryId);
    @Query("From Product where category.categoryId IN:categories")
    List<Product> getByCategoryIn(List<Integer> categories);
    @Query("From Product where productName LIKE %:productName%")
    List<Product> getByProductNameContains(String productName);
    @Query("FROM Product WHERE productName LIKE CONCAT(:productName, '%')")
    List<Product> getByProductNameStartsWith(String productName);

}
