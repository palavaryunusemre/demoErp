package kodlamaio.nortwind.api.controller;

import kodlamaio.nortwind.business.abstracts.ProductService;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductService productService;
    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/getAll")
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }
    @GetMapping("/getAllSort")
    public DataResult<List<Product>> getAllSorted(){
        return this.productService.getAllSorted();
    }
    @GetMapping("/getAllByPage")
    public DataResult<List<Product>> getAll(int pageNo,int pageSize){
        return this.productService.getAll(pageNo,pageSize);
    }
    @PostMapping("/add")
    public Result add(@RequestBody Product product) {
        return this.productService.add(product);
    }
    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName){
        return this.productService.getByProductName(productName);
    }
    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam String productName,@RequestParam int categoryId){
        return this.productService.getByProductNameAndCategoryId(productName,categoryId);
    }
    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
        return this.productService.getByProductNameContains(productName);
    }
    @GetMapping("/getByProductNameOrCategoryId")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByProductNameOrCategoryId(productName,categoryId);
    }
    @GetMapping("/getByCategoryIn")
    public DataResult<List<Product>> getByCategoryIn(@RequestParam List<Integer> categories){
        return this.productService.getByCategoryIdIn(categories);
    }
    @GetMapping("/getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
        return this.productService.getByProductNameStartsWith(productName);
    }

}