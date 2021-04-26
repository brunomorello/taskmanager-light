package br.com.bmo.taskmanagerlight.api.shared.domain;

import java.util.List;

import javax.persistence.Entity;

import br.com.bmo.taskmanagerlight.api.product.Product;

@Entity
public class Shopping extends Task {
	
	private List<Product> products;
	
	public Shopping(String title, String details) {
		super(title, details);
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}

}
