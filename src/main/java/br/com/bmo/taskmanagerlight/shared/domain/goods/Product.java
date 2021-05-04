package br.com.bmo.taskmanagerlight.shared.domain.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;

@Entity
public class Product extends Goods {

	@ManyToOne
	@JoinColumn(name = "manufacturer_id", nullable = true)
	private Manufacturer manufacturer;
	
	@ManyToMany
	@JsonIgnore
	private List<Shopping> shoppingTask = new ArrayList<>();
	
	public Product(String name, BigDecimal price) {
		super(name);
		this.setPrice(price);
	}
	
	public Product(String name, BigDecimal price, Manufacturer manufacturer) {
		super(name);
		this.setPrice(price);
		this.manufacturer =  manufacturer;
	}
	
	public Product() {
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<Shopping> getShoppingTask() {
		return shoppingTask;
	}

	public void setShoppingTask(List<Shopping> shoppingTask) {
		this.shoppingTask = shoppingTask;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		return true;
	}
	
}
