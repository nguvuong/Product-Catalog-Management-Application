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
	public String indexPage() {
	return "/index";
	}
	

	// login security
	@GetMapping("/login")
	public String securityLogin() {
	return "/login";
	}
	
	// add function
	@GetMapping("/admin/productDataInput")
	public String productDataInput(Model model) {
	model.addAttribute("product1", new Product());
	return "/admin/productDataInput";
	}
	
	@PostMapping("/admin/productDataInput")
	public String addProduct(Model model, @ModelAttribute Product product) {
		
	String message;
	long numberOfRows = cda.addProduct(product);
	
	if (numberOfRows > 0)
	{message = "Success! The product object successfully added to the database.";}
	else
	{message = "Failure --- The product object is not added to the database.";}
	
	model.addAttribute("message", message);
	
	return "/admin/productAddOutcome";
	}
	
	
	@GetMapping("/admin/productAddOutcome")
	public String productAddOutcome() {
	return "/admin/productAddOutcome";
	}
	
	
	// list function
//	@GetMapping ("/listOfProducts")
//	public String viewListOfProducts(Model model) {
//	List<Product> products = cda.selectProducts();
//	model.addAttribute("products", products);
//	return "listOfProducts";
//	}
	
	
	
	// edit function
	@GetMapping ("/admin/editableListOfProducts")
	public String vieweditableListOfProducts(Model model) {
	List<Product> products = cda.selectProducts();
	model.addAttribute("products", products);
	return "/admin/editableListOfProducts"; }
	
	@GetMapping("/admin/updateProductData/{id}")
	public String editProductData(Model model, @PathVariable("id") int id) {
	Product productById = cda.selectProductById(id);
	model.addAttribute("product", productById);
	return "/admin/updateProductData";
	}
	

	

	@PostMapping("/admin/updateProductData")
	public String updateProduct(Model model, @ModelAttribute Product product) {
	String message;
	int productId = product.getId();
	long rowsUpdated = cda.updateProductById(productId, product);
	if (rowsUpdated > 0)
	{message = "Success! The course object successfully updated in the database.";}
	else
	{message = "Failure --- The course object is not updated in the database.";}
	model.addAttribute("message", message);
	return "/admin/productUpdateOutcome";
	}
	
	
	// delete function
//	@GetMapping ("/deleteableListOfProducts")
//	public String deleteableListOfProducts(Model model) {
//	List<Product> products = cda.selectProducts();
//	model.addAttribute("products", products);
//	return "deleteableListOfProducts"; }
//	
	@GetMapping("/admin/deleteProductData/{id}")
	public String deleteProductData(Model model, @PathVariable("id") int id) {
		Product productById = cda.selectProductById(id);
	model.addAttribute("product", productById);
	return "/admin/deleteProductData";
	}
	
	@PostMapping("/admin/deleteProductData")
	public String deleteProduct(Model model, @ModelAttribute Product product) {
	String message;
	int productId = product.getId();
	long rowsUpdated = cda.deleteProductById(productId, product);
	if (rowsUpdated > 0)
	{message = "Success! The course object successfully updated in the database.";}
	else
	{message = "Failure --- The course object is not updated in the database.";}
	model.addAttribute("message", message);
	return "/admin/productDeleteOutcome";
	}
	
	
	
	// search by product brand function
	@GetMapping ("/sales/SearchProductBrand")
	public String viewSearchProductBrand(Model model) {
		model.addAttribute("product1", new Product());
		return "/sales/SearchProductBrand"; 
	}
	
	@PostMapping ("/sales/SearchProductBrand")
	public String listProductBrand(Model model, @ModelAttribute Product product) {
		List<Product> productByProductBrand = cda.selectProductByProductBrand(product.getProductBrand());
		model.addAttribute("products", productByProductBrand);
		return "/sales/SearchProductBrandResult"; 
	}
	
	
	
	
	// search by thresshold
	@GetMapping ("/sales/SearchByQuantityThresHold")
	public String SearchByQuantityThresHold(Model model) {
		model.addAttribute("product2", new Product());
		return "/sales/SearchByQuantityThresHold"; 
	}
	
	@PostMapping ("/sales/SearchByQuantityThresHold")
	public String listByQuantityThreshold(Model model, @ModelAttribute Product product) {
		List<Product> productByQuantityThresHold = cda.selectProductByQuantityThreshold(product.getQuantity());
		model.addAttribute("products", productByQuantityThresHold);
		return "/sales/SearchByQuantityThresHoldResult"; 
	}
	
	
	

		
	
	
	



}
