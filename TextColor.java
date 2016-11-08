public class TextColor {
    String DEFAULT = "\u001B[0m";
    String BLACK = "\u001B[30m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String PURPLE = "\u001B[35m";
    String CYAN = "\u001B[36m";
    String WHITE = "\u001B[37m";

    public String setBLACK(String text) {
        return (BLACK + text + DEFAULT);
    }

    public final String setRED(String text) {
        return (RED + text + DEFAULT);
    }

    public String setGREEN(String text) {
        return (GREEN + text + DEFAULT);
    }

    public String setYELLOW(String text) {
        return (YELLOW + text + DEFAULT);
    }

    public String setBLUE(String text) {
        return (BLUE + text + DEFAULT);
    }

    public String setPURPLE(String text) {
        return (PURPLE + text + DEFAULT);
    }

    public String setCYAN(String text) {
        return (PURPLE + text + DEFAULT);
    }

    public String setWHITE(String text) {
        return (WHITE + text + DEFAULT);
    }
}
