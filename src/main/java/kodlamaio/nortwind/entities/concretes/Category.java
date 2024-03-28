package kodlamaio.nortwind.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","products"})
public class Category {
    @Id
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")  // categories -> products
    private List<Product> products;

}
