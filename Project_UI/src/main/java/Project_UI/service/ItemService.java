package Project_UI.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Project_UI.model.Item;
import Project_UI.model.Wish;
import Project_UI.repository.ItemMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {
	private final ItemMapper itemMapper;
	
	public void insertItem(Item item) {
		itemMapper.insertItem(item);
	}
	
	public List<Item> findAllItem(String item_category) {
		return itemMapper.findAllItem(item_category);
	}
	
	public List<Item> findSearchItem(String item_category, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("item_category", item_category);
		map.put("searchWord", searchWord);
		
		return itemMapper.findSearchItem(map);
	}
	
	public Item findItem(long item_id) {
		return itemMapper.findItem(item_id);
	}
	
	@Transactional
	public void updateItem(Item item) {
		itemMapper.updateItem(item);
	}
	
	public void deleteItem(long item_id) {
		itemMapper.deleteItem(item_id);
	}
	
	public void purchase(Item item) {
		itemMapper.purchase(item);
	}
	
	public void purchase_cancel(Item item) {
		itemMapper.purchase_cancel(item);
	}
	
	public List<Item> sellList(String seller_id) {
		return itemMapper.sellList(seller_id);
	}
	
	public List<Item> buyList(String buyer_id) {
		return itemMapper.buyList(buyer_id);
	}
	
	@Transactional
	public void review(Item item) {
		itemMapper.review(item);
	}
	
	public List<Item> rating(String seller_id) {
		return itemMapper.rating(seller_id);
	}
}
