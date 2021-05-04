package br.com.bmo.taskmanagerlight.api.goods;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public interface GoodsService {

	public Goods create(GoodsForm form);
	
	public GoodsView update(@Valid GoodsForm form, Long id);
	
	public void delete(Long id);
	
	public GoodsView getGoodsById(Long id);
		
	public Page<?> getAllGoods(Integer pageNum, Integer pageSize, String sortBy);
	
}
