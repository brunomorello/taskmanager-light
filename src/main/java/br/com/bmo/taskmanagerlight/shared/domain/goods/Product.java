package br.com.bmo.taskmanagerlight.shared.domain.goods;

import java.math.BigDecimal;

import javax.persistence.Entity;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

@Entity
public class Product extends Goods {

	private Manufacturer manufacturer;
	
	public Product(String name, BigDecimal price) {
		super(name);
		this.setPrice(price);
	}
	
	public Product(String name, Manufacturer manufacturer) {
		super(name);
		this.manufacturer =  manufacturer;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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
