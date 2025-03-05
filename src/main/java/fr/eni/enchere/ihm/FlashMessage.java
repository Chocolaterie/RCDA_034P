package fr.eni.enchere.ihm;

import java.io.Serializable;

public class FlashMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int FLASH_ERROR = 0;
    public static final int FLASH_SUCCESS = 1;
    public static final int FLASH_WARNING = 2;

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

    public String getStyleClassName(){
        if (type == FLASH_ERROR) {
            return "uk-alert-danger";
        }
        if (type == FLASH_SUCCESS) {
            return "uk-alert-success";
        }
        return "uk-alert-warning";
    }
}
