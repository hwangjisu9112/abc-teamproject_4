package Project_UI.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	long member_idn;
	String member_id;
	String member_nick;
	String member_pw;
	String member_name;
	String email;
	LocalDateTime joindate;
	Role role;
}
