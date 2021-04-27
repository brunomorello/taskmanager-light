package br.com.bmo.taskmanagerlight.api.manufacturer;

import org.springframework.stereotype.Component;

import br.com.bmo.taskmanagerlight.api.shared.domain.manufacturer.Manufacturer;
import br.com.bmo.taskmanagerlight.api.shared.utils.Mapper;

@Component
public class ManufacturerViewMapper implements Mapper<Manufacturer, ManufacturerView> {

	@Override
	public ManufacturerView map(Manufacturer source) {
		ManufacturerView view = new ManufacturerView();
		
		if (source.getId() != null)
			view.setId(source.getId().toString());
		
		if (source.getFormalName() != null)
			view.setFormalName(source.getFormalName());
		
		if (source.getDisplayName() != null)
			view.setDisplayName(source.getDisplayName());
		
		if (source.getAddress() != null)
			view.setAddress(source.getAddress());
		
		view.setStatus(source.getStatus().toString());
		
		return view;
	}

}
