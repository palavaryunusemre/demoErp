package kodlamaio.nortwind.dataAccess.abstracts;

import kodlamaio.nortwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    Product getByProductName(String productName);
    Product getByProductNameAndCategory(String productName, int categoryId);
    List<Product> getByProductNameOrCategory(String productName, int categoryId);
    List<Product> getByCategoryIn(List<Integer> categories);
    List<Product> getByProductNameContains(String productName);
    List<Product> getByProductNameStartsWith(String productName);
    @Query("From Product where productName=:productName and category.category_id=:categoryId")
    List<Product> getByNameAndCategory(String productName,int categoryId);

}
