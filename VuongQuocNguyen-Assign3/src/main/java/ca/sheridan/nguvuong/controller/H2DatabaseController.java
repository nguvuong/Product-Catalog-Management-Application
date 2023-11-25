package ca.sheridan.nguvuong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import ca.sheridan.nguvuong.dao.ProductsDatabaseAccess;

import ca.sheridan.nguvuong.model.Product;

@Controller
public class H2DatabaseController {
	@Autowired
	private ProductsDatabaseAccess cda;
	
	@GetMapping("/index")
	public String index() {
	return "index";
	}
	
	// add function
	@GetMapping("/productDataInput")
	public String productDataInput(Model model) {
	model.addAttribute("product1", new Product());
	return "productDataInput";
	}
	
	@PostMapping("/productDataInput")
	public String addProduct(Model model, @ModelAttribute Product product) {
		
	String message;
	long numberOfRows = cda.addProduct(product);
	
	if (numberOfRows > 0)
	{message = "Success! The product object successfully added to the database.";}
	else
	{message = "Failure --- The product object is not added to the database.";}
	
	model.addAttribute("message", message);
	
	return "productAddOutcome";
	}
	
	
	@GetMapping("/productAddOutcome")
	public String productAddOutcome() {
	return "productAddOutcome";
	}
	
	
	// list function
//	@GetMapping ("/listOfProducts")
//	public String viewListOfProducts(Model model) {
//	List<Product> products = cda.selectProducts();
//	model.addAttribute("products", products);
//	return "listOfProducts";
//	}
	
	
	
	// edit function
	@GetMapping ("/editableListOfProducts")
	public String vieweditableListOfProducts(Model model) {
	List<Product> products = cda.selectProducts();
	model.addAttribute("products", products);
	return "editableListOfProducts"; }
	
	@GetMapping("/updateProductData/{id}")
	public String editProductData(Model model, @PathVariable("id") int id) {
	Product productById = cda.selectProductById(id);
	model.addAttribute("product", productById);
	return "updateProductData";
	}
	

	

	@PostMapping("/updateProductData")
	public String updateProduct(Model model, @ModelAttribute Product product) {
	String message;
	int productId = product.getId();
	long rowsUpdated = cda.updateProductById(productId, product);
	if (rowsUpdated > 0)
	{message = "Success! The course object successfully updated in the database.";}
	else
	{message = "Failure --- The course object is not updated in the database.";}
	model.addAttribute("message", message);
	return "productUpdateOutcome";
	}
	
	
	// delete function
//	@GetMapping ("/deleteableListOfProducts")
//	public String deleteableListOfProducts(Model model) {
//	List<Product> products = cda.selectProducts();
//	model.addAttribute("products", products);
//	return "deleteableListOfProducts"; }
//	
	@GetMapping("/deleteProductData/{id}")
	public String deleteProductData(Model model, @PathVariable("id") int id) {
		Product productById = cda.selectProductById(id);
	model.addAttribute("product", productById);
	return "deleteProductData";
	}
	
	@PostMapping("/deleteProductData")
	public String deleteProduct(Model model, @ModelAttribute Product product) {
	String message;
	int productId = product.getId();
	long rowsUpdated = cda.deleteProductById(productId, product);
	if (rowsUpdated > 0)
	{message = "Success! The course object successfully updated in the database.";}
	else
	{message = "Failure --- The course object is not updated in the database.";}
	model.addAttribute("message", message);
	return "productDeleteOutcome";
	}
	
	
	
	// search by product brand function
	@GetMapping ("/SearchProductBrand")
	public String viewSearchProductBrand(Model model) {
		model.addAttribute("product1", new Product());
		return "SearchProductBrand"; 
	}
	
	@PostMapping ("/SearchProductBrand")
	public String listProductBrand(Model model, @ModelAttribute Product product) {
		List<Product> productByProductBrand = cda.selectProductByProductBrand(product.getProductBrand());
		model.addAttribute("products", productByProductBrand);
		return "SearchProductBrandResult"; 
	}
	
	
	
	
	// search by thresshold
	@GetMapping ("/SearchByQuantityThresHold")
	public String SearchByQuantityThresHold(Model model) {
		model.addAttribute("product2", new Product());
		return "SearchByQuantityThresHold"; 
	}
	
	@PostMapping ("/SearchByQuantityThresHold")
	public String listByQuantityThreshold(Model model, @ModelAttribute Product product) {
		List<Product> productByQuantityThresHold = cda.selectProductByQuantityThreshold(product.getQuantity());
		model.addAttribute("products", productByQuantityThresHold);
		return "SearchByQuantityThresHoldResult"; 
	}
	
	

		
	
	
	



}
