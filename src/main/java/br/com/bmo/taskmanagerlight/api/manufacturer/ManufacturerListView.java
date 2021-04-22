package br.com.bmo.taskmanagerlight.api.manufacturer;

import java.util.List;

public class ManufacturerListView {

	private List<ManufacturerView> list;
	
	private ManufacturerViewMapper mapper = new ManufacturerViewMapper();

	public ManufacturerListView(List<Manufacturer> manufacturerList) {
		manufacturerList.forEach(m -> list.add(mapper.map(m)));
	}
	
}
