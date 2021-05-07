package br.com.bmo.taskmanagerlight.api.task.shopping;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.bmo.taskmanagerlight.api.goods.product.ProductView;
import br.com.bmo.taskmanagerlight.api.task.TaskDTOView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;

public class ShoppingDTOView extends TaskDTOView {
	
	private List<ProductView> products = new ArrayList<>();

	public ShoppingDTOView(String id, String title, String details, String status, String createdOn, String updatedOn,
			String dueDate, List<ProductView> products) {
		super(id, title, details, status, createdOn, updatedOn, dueDate);
		this.products = products;
	}
	
	public ShoppingDTOView(String title, String details, String status, String createdOn, String updatedOn,
			String dueDate, List<ProductView> products) {
		super(title, details, status, createdOn, updatedOn, dueDate);
		this.products = products;
	}
	
	public ShoppingDTOView(Shopping shoppingTask) {
		super(shoppingTask.getId().toString(),
				shoppingTask.getTitle(), 
				shoppingTask.getDetails(), 
				shoppingTask.getStatus().toString(),
				shoppingTask.getCreatedOn().format(DateTimeFormatter.ISO_DATE_TIME),
				shoppingTask.getUpdatedOn().format(DateTimeFormatter.ISO_DATE_TIME),
				shoppingTask.getDueDate() != null ? shoppingTask.getDueDate().format(DateTimeFormatter.ISO_DATE_TIME) : "");
		shoppingTask.getProducts().forEach(goods -> {
			Product product = (Product) goods;
			products.add(new ProductView(product));
		});
	}

	public List<ProductView> getProducts() {
		return products;
	}

}
