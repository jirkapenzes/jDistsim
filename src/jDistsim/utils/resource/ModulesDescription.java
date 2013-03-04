package jDistsim.utils.resource;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 21:53
 */
public class ModulesDescription {

    public String resolveTitle(String moduleName) {
        switch (moduleName) {
            case "create":
                return EventTextResource.CREATE_EVENT_DESCRIPTION_TITLE;
            case "dispose":
                return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TITLE;
            case "delay":
                return EventTextResource.DELAY_EVENT_DESCRIPTION_TITLE;
            case "sender":
                return EventTextResource.SENDER_EVENT_DESCRIPTION_TITLE;
            case "assign":
                return "Assign";
            default:
                throw new RuntimeException("Title description not found");
        }
    }

    public String resolveText(String moduleName) {
        switch (moduleName) {
            case "create":
                return EventTextResource.CREATE_EVENT_DESCRIPTION_TEXT;
            case "dispose":
                return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TEXT;
            case "delay":
                return EventTextResource.DELAY_EVENT_DESCRIPTION_TEXT;
            case "sender":
                return EventTextResource.SENDER_EVENT_DESCRIPTION_TEXT;
            case "assign":
                return EventTextResource.DELAY_EVENT_DESCRIPTION_TEXT;
            default:
                throw new RuntimeException("Text description not found");
        }
    }
}
