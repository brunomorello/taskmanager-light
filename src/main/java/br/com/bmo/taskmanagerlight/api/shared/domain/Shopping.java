package br.com.bmo.taskmanagerlight.api.shared.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Shopping extends Task {
	
	private Set<Goods> products = new HashSet<>();
	
	public Shopping(String title, String details) {
		super(title, details);
	}
	
	public void addProduct(Goods p) {
		products.add(p);
	}

	public Set<Goods> getProducts() {
		return new HashSet<>(products);
	}
	
	public void clearList() {
		this.products.clear();
	}
	
}
