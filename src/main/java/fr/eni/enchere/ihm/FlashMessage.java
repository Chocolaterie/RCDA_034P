package fr.eni.enchere.ihm;

public class FlashMessage {
    public static final int FLASH_ERROR = 0;
    public static final int FLASH_SUCCESS = 1;

    String message;
    int type; // 0 = error, 1 = success

    public FlashMessage(int type, String message) {
        this.message = message;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
