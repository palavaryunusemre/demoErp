package kodlamaio.nortwind.business.abstracts;

import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.entities.concretes.Product;
import kodlamaio.nortwind.entities.dtos.ProductWithCategoryDto;

import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAllSorted();
    DataResult<List<Product>> getAll(int pageNo, int pageSize);
    Result add(Product product);
    DataResult <Product> getByProductName(String productName);
    DataResult <Product> getByProductNameAndCategoryId(String productName, int categoryId);
    DataResult <List<Product>> getByProductNameOrCategoryId(String productName, int categoryId);
    DataResult <List<Product>> getByCategoryIdIn(List<Integer> categories);
    DataResult <List<Product>> getByProductNameContains(String productName);
    DataResult <List<Product>> getByProductNameStartsWith(String productName);
    DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();

}
