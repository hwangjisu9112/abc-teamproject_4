package Project_UI.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Project_UI.model.Image;
import Project_UI.model.Item;
import Project_UI.model.Wish;

@Mapper
public interface ItemMapper {
	public void insertItem(Item item);
	public List<Item> findAllItem(String item_category);
	public List<Item> findSearchItem(HashMap<String, String> map);
	public Item findItem(long item_id);
	
	public void insertImage(Image image);
	public void cancelImage(Image image);
	public Image findOneImage(long item_id);
	public List<Image> findImage(long item_id);
	
	public void updateItem(Item item);
	public void deleteItem(long item_id);
	public void purchase(Item item);
	public void purchase_cancel(Item item);
	public void wish(Wish wish);
	
	public List<Item> sellList(String seller_id);
	public List<Item> buyList(String buyer_id);
	public void review(Item item);
	public List<Item> rating(String seller_id);
}
