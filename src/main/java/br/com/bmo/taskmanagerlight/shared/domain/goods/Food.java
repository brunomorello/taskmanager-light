package br.com.bmo.taskmanagerlight.shared.domain.goods;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.bmo.taskmanagerlight.shared.exceptions.DateCannotBeInThePast;

public class Food extends Goods {

	private LocalDate expirationDate;
	
	public Food(String name, BigDecimal price) {
		super(name);
		this.setPrice(price);
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		if (expirationDate.isBefore(LocalDate.now())) {
			throw new DateCannotBeInThePast("Expiration Date cannot be in the past");
		}
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
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
		Food other = (Food) obj;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		return true;
	}
	
}
