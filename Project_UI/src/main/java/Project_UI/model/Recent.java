package Project_UI.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Recent {
	long recent_id;
	String buyer_id;
	long item_id;
	LocalDateTime recent_inputdate;
}
