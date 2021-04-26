package br.com.bmo.taskmanagerlight.api.shared.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Entity;

@Entity
public class Shopping extends Task {

	private List<Goods> products = new ArrayList<>();

	public Shopping(String title, String details) {
		super(title, details);
	}

	public void addProduct(Goods p) {
		products.add(p);
	}

	public List<Goods> getProducts() {
		return new ArrayList<>(products);
	}

	public int getListSize() {
		return this.products.size();
	}

	public void clearList() {
		this.products.clear();
	}

	public Long getQuantityOf(Goods goods) {
		Stream<Goods> filter = products.stream().filter(g -> g.equals(goods));
		return Long.valueOf(filter.count());
	}

}
