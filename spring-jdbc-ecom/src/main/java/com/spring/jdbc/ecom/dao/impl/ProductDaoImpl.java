package com.spring.jdbc.ecom.dao.impl;

import com.spring.jdbc.ecom.dao.ProductDao;
import com.spring.jdbc.ecom.model.Product;
import com.spring.jdbc.ecom.model.ProductWithCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;


// Same as @Component but special for Dao layer
@Repository
public class ProductDaoImpl implements ProductDao {

//    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Constructor injection - by default
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String createQuery = "CREATE TABLE IF NOT EXISTS Products(id int primary key, title varchar(200) not null, description varchar(500) not null, price int not null,catId int,FOREIGN KEY (catId) REFERENCES categories(id))";
        jdbcTemplate.update(createQuery);
        System.out.println("Products table created ");

    }

    @Override
    public Product create(Product product) {
        String query = "insert into Products(id,title,description,price,catId) values(?,?,?,?,?)";
        int updatedRow = jdbcTemplate.update(query,
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCatId());
        System.out.println(updatedRow + " updated row ");
        return product;
    }

    @Override
    public Product update(Product product, int productId) {
        String query = "update Products set title=?,description =?,price =? where id =?";
        int updatedRow = jdbcTemplate.update(
                query, product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                productId);
        product.setId(productId);
        return product;
    }

    @Override
    public void delete(int productId) {
        String query = "delete from Products where id = ?";
        int updatedRow = jdbcTemplate.update(query,productId);
        System.out.println("Row affected : "+updatedRow);

    }

    @Override
    public List<Product> getAll() {
        String query = "select * from Products";
//        List<Product> products1 = jdbcTemplate.queryForList(query, Product.class);
        // we can remove this below written anonymous class

        List<Product> products = jdbcTemplate.query(query, new RowMapperImpl());

//        List<Product> products = jdbcTemplate.query(query, new RowMapper<Product>() {
//            @Override // this is called Anonymous class
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setPrice(rs.getInt("price"));
//                product.setDescription(rs.getString("description"));
//                product.setTitle(rs.getString("title"));
//                return product;
//            }
//        });
//        ------------------------------------------------------------------------------------------
        // lambda expression
//        List<Product> products = jdbcTemplate.query(query,(rs, rowNum)->{
//            Product product = new Product();
//            product.setId(rs.getInt("id"));
//            product.setPrice(rs.getInt("price"));
//            product.setDescription(rs.getString("description"));
//            product.setTitle(rs.getString("title"));
//            return product;
//        });
        return products;
    }

    @Override
    public Product getSingleProduct(int productId) {
        String query = "select * from Products where id =?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query,productId);
//        Product product1 = jdbcTemplate.queryForObject(query, Product.class);
//        while(sqlRowSet.next()){
//            int id = sqlRowSet.getInt("id");
//            int price = sqlRowSet.getInt("price");
//            String desc = sqlRowSet.getString("description");
//            String title = sqlRowSet.getString("title");
//            System.out.println("{ productId : " + id +", title : " + title + ", description : "+ desc+", price : "+price);
//        }
        Product product = jdbcTemplate.queryForObject(query, new RowMapperImpl(), productId);
        return product;
    }

    @Override
    public List<Product> search(String keyword) {
        return List.of();
    }

    // get all product of categor

    //    get all products with category
    @Override
    public List<Product> getAllCategory() {
        return List.of();
    }

    @Override
    public List<ProductWithCategory> getAllWithCategory() {
        String query = "Select p.id  as id , p.title as title , p.description as description,  p.price as price , c.title as catTitle FROM products p INNER JOIN categories c ON p.catId=c.id";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            ProductWithCategory productWithCategory = new ProductWithCategory();
            productWithCategory.setId(rs.getInt("id"));
            productWithCategory.setTitle(rs.getString("title"));
            productWithCategory.setDescription(rs.getString("description"));
            productWithCategory.setCatTitle(rs.getString("catTitle"));
            return productWithCategory;

        });
//        return jdbcTemplate.queryForList(query);
    }
}
