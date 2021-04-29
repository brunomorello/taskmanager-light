package br.com.bmo.taskmanagerlight.api.goods;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface GoodsController {

	public ResponseEntity<GoodsView> findById(@PathVariable Long id);
	
	public ResponseEntity<GoodsListView> findByNameLike(@PathVariable String name);
	
	public ResponseEntity<GoodsListView> findByQueryParams();

}
