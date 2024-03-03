package Enums;

public enum AlertsButtons {

    ALERT("JS Alert", "jsAlert()"),
    CONFIRM("JS Confirm", "jsConfirm()"),
    PROMPT("JS Prompt", "jsPrompt()");

    private final String TEXTONBUTTON;
    private final String ON_CLICK_JS_FUCTION;

    AlertsButtons(String textOnButton, String onClickJSFunction) {
        this.TEXTONBUTTON = textOnButton;
        this.ON_CLICK_JS_FUCTION= onClickJSFunction;
    }




    public String getTEXT_ON_BUTTON() {
        return TEXTONBUTTON;
    }

    public String getON_CLICK_JS_FUCTION() {
        return ON_CLICK_JS_FUCTION;
    }
}
