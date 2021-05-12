package br.com.bmo.taskmanagerlight.api.task.shopping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.bmo.taskmanagerlight.api.goods.product.ProductView;
import br.com.bmo.taskmanagerlight.api.task.TaskDTOInput;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;
import br.com.bmo.taskmanagerlight.shared.domain.task.Status;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.InvalidStatusTransition;

public class ShoppingDTOInput extends TaskDTOInput {
	private List<ProductView> products = new ArrayList<>();
	
	private IpifyIP ip;

	public ShoppingDTOInput(String id, String title, String details, String status, String dueDate, List<ProductView> products, String ip) {
		super(id, title, details, status, dueDate);
		this.products = products;
		if (ip != null && !ip.isEmpty())
			this.ip = new IpifyIP(ip);
	}
	
	public ShoppingDTOInput(String title, String details, String dueDate, List<ProductView> products, String ip) {
		super(title, details, dueDate);
		this.products = products;
		if (ip != null && !ip.isEmpty())
			this.ip = new IpifyIP(ip);
	}
	
	public ShoppingDTOInput() {
	}

	public List<ProductView> getProducts() {
		return products;
	}
	
	public IpifyIP getIp() {
		return ip;
	}

	@Override
	public Task parse() {
		Shopping shopping = new Shopping(getTitle(), getDetails());
		if (getStatus() != null) {
			try {
				shopping.setStatus(Status.valueOf(getStatus()));
			} catch (InvalidStatusTransition e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		shopping.setDueDate(LocalDateTime.parse(getDueDate(), DateTimeFormatter.ISO_DATE_TIME));
		return shopping;
	}

}
