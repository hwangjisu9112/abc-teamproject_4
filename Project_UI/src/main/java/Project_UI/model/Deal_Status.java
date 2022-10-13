package Project_UI.model;

public enum Deal_Status {
	newitem("신상"),
	reserved("거래중"),
	soldout("거래완료");
	
	private String description;

    Deal_Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
