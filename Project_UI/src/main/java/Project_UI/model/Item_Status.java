package Project_UI.model;

public enum Item_Status {
	sealed("미개봉"),
	unsealed("개봉");
	
	private String description;

    Item_Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
