package br.com.bmo.taskmanagerlight.api.manufacturer;

public class ManufacturerViewFactory {

	ManufacturerView factory(Manufacturer manufacturer) {
		return new ManufacturerView(manufacturer.getId().toString(), manufacturer.getFormalName(),
				manufacturer.getDisplayName(), manufacturer.getAddress(), manufacturer.getStatus().toString());
	}
}
