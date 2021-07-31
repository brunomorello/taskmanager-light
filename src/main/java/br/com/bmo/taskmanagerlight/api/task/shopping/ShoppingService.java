package br.com.bmo.taskmanagerlight.api.task.shopping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.task.TaskService;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.AccuweatherService;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.City;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.Forecast;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ShoppingService implements TaskService<ShoppingDTOInput, ShoppingDTOView> {
	
	@Autowired
	private ShoppingRepository repository;
	
	@Autowired
	private AccuweatherService accuweatherService;
	
	@Override
	public Task create(@Valid ShoppingDTOInput form) {
		Shopping shoppingTask = (Shopping) form.parse();
		City cityByIp;
		cityByIp = getLocationByIp(form.getIp());
		
		shoppingTask.setDetails(shoppingTask.getDetails() + "\n" + parseForecast(accuweatherService.get5dayForecastByLocationKey(cityByIp.getKey())));
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return repository.save(shoppingTask);
	}

	private String parseForecast(Forecast forecast) {
		return forecast.getHeadline().getText();
	}

	private City getLocationByIp(IpifyIP myIp) {
		try {
			return accuweatherService.getCityByIp(myIp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShoppingDTOView update(@Valid ShoppingDTOInput form, Long id) {
		Shopping shoppingTask = (Shopping) form.parse();
		shoppingTask.setId(id);
		return new ShoppingDTOView(repository.save(shoppingTask));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ShoppingDTOView getTaskById(Long id) {
		return repository.findById(id).map(task -> new ShoppingDTOView(task)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Shopping Task by " + id));
	}

	@Override
	public Page<ShoppingDTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Shopping> pagedResult = repository.findAll(paging);
		
		return pagedResult.map(task -> new ShoppingDTOView(task));
	}

}
