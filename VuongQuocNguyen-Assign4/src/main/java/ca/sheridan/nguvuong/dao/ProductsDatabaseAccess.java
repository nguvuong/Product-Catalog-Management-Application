package ca.sheridan.nguvuong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import ca.sheridan.nguvuong.model.Product;

@Repository
public class ProductsDatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	// Method to adda Product object to the database

	public long addProduct(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String insert= "INSERT INTO product(productCode, productBrand, quantity, productPrice) VALUES(:productCode, :productBrand, :quantity, :productPrice);";
		namedParameters.addValue("productCode", product.getProductCode());
		namedParameters.addValue("productBrand", product.getProductBrand());
		namedParameters.addValue("quantity", product.getQuantity());
		namedParameters.addValue("productPrice", product.getProductPrice());

		int rowsAffected = jdbc.update(insert, namedParameters);
		return rowsAffected;
	}

	public List<Product> selectProducts() {
		MapSqlParameterSource namedParameters =
		new MapSqlParameterSource();
		String query = "SELECT * FROM product";
		List<Product> products = jdbc.query(query,
		namedParameters, new
		BeanPropertyRowMapper<Product>(Product.class));
		return products;
	}


	public Product selectProductById(int id) {
		MapSqlParameterSource namedParameters = new
		MapSqlParameterSource();
		Product product;
		String productById = "SELECT * FROM product where id = :id";
		namedParameters.addValue("id", id);
		List<Product> products =jdbc.query(productById, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		product = products.get(0);
		return product;
	}
	
	public List<Product> selectProductByProductBrand(String productBrand) {
		MapSqlParameterSource namedParameters = new
		MapSqlParameterSource();
	
		String productByProductBrand = "SELECT * FROM product where productBrand = :productBrand";
		namedParameters.addValue("productBrand", productBrand);
		List<Product> products =jdbc.query(productByProductBrand, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		
		if (products.size() > 0 ) {
			return products;
		} else
			products.add( new Product());
		
		return products;
	}
	
	public List<Product> selectProductByQuantityThreshold(int quantity) {
		MapSqlParameterSource namedParameters = new
		MapSqlParameterSource();
	
		String productByProductBrand = "SELECT * FROM product where quantity < :quantity";
		namedParameters.addValue("quantity", quantity);
		List<Product> products =jdbc.query(productByProductBrand, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		
		if (products.size() > 0 ) {
			return products;
		} else
			products.add( new Product());
		
		return products;
	}
	
		
	
		
	
	
	

	public long updateProductById(int productId, Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String updateProduct =
		"update product Set productCode = :productCode, "
		+ "productBrand = :productBrand, quantity = :quantity,  "
		+ "productPrice = :productPrice "
		+ "where id = :productId;";
		namedParameters.addValue("productId", productId);
		namedParameters.addValue("productCode", product.getProductCode());
		namedParameters.addValue("productBrand", product.getProductBrand());
		namedParameters.addValue("quantity", product.getQuantity());
		namedParameters.addValue("productPrice", product.getProductPrice());

		long rowsUpdated = jdbc.update(updateProduct, namedParameters);
		return rowsUpdated;
	}
	public long deleteProductById(int productId, Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String deleteProduct =  "delete from product where id = :productId;";
		namedParameters.addValue("productId", productId);
		long rowsUpdated = jdbc.update(deleteProduct, namedParameters);
		return rowsUpdated;
	}
}