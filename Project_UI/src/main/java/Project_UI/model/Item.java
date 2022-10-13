package Project_UI.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	long item_id;
	String seller_id;
	String buyer_id;
	String item_category;
	String item_name;
	Item_Status item_status;
	long item_price;
	long item_hit;
	String item_des;
	LocalDateTime item_inputdate;
	LocalDateTime item_updatedate;
	Deal_Status deal_status;
	long rating;
	String review;
	LocalDateTime review_inputdate;
	
	public void addHit() {
        item_hit += 1;
    }
}
