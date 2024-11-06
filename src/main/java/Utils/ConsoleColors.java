package Utils;

public class ConsoleColors {
    // Regular Colors
    public static final String BLACK = "\u001b[30m";
    public static final String RED = "\u001b[31m";
    public static final String GREEN = "\u001b[32m";
    public static final String YELLOW = "\u001b[33m";
    public static final String BLUE = "\u001b[34m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String WHITE = "\u001b[37m";

    // Bright Colors
    public static final String BR_BLACK = "\u001b[30;1m";
    public static final String BR_RED = "\u001b[31;1m";
    public static final String BR_GREEN = "\u001b[32;1m";
    public static final String BR_YELLOW = "\u001b[33;1m";
    public static final String BR_BLUE = "\u001b[34;1m";
    public static final String BR_BLACK_MAGENTA = "\u001b[35;1m";
    public static final String BR_CYAN = "\u001b[36;1m";
    public static final String BR_WHITE = "\u001b[37;1m";

    // Background Colors
    public static final String BG_BLACK = "\u001b[40m";
    public static final String BG_RED = "\u001b[41m";
    public static final String BG_GREEN = "\u001b[42m";
    public static final String BG_YELLOW = "\u001b[43m";
    public static final String BG_BLUE = "\u001b[44m";
    public static final String BG_MAGENTA = "\u001b[45m";
    public static final String BG_CYAN = "\u001b[46m";
    public static final String BG_WHITE = "\u001b[47m";

    // Bright Background Colors
    public static final String BB_BLACK = "\u001b[40;1m";
    public static final String BB_RED = "\u001b[41;1m";
    public static final String BB_GREEN = "\u001b[42;1m";
    public static final String BB_YELLOW = "\u001b[43;1m";
    public static final String BB_BLUE = "\u001b[44;1m";
    public static final String BB_MAGENTA = "\u001b[45;1m";
    public static final String BB_CYAN = "\u001b[46;1m";
    public static final String BB_WHITE = "\u001b[47;1m";

    // Reset
    public static final String RESET = "\u001b[0m";


    public static final String centerResponse ="\n\t\t\t\t\t\t\t\t";
    public static final String center = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";

    public static final String whiteSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+ConsoleColors.BB_WHITE+" "+ConsoleColors.BG_BLACK+"                                   " +
            "                             "+ConsoleColors.BB_WHITE+" "+ConsoleColors.RESET;
    public static final String greenSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+ConsoleColors.BB_GREEN+" "+ConsoleColors.BG_BLACK+"                                   " +
            "                             "+ConsoleColors.BB_GREEN+" "+ConsoleColors.RESET;
    public static final String redSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+ConsoleColors.BB_RED+" "+ConsoleColors.BG_BLACK+"                                   " +
            "                             "+ConsoleColors.BB_RED+" "+ConsoleColors.RESET;

    public static final String whiteLine = ConsoleColors.center+ConsoleColors.BB_WHITE+"                                                                  "+ConsoleColors.RESET;
    public static final String greenLine = ConsoleColors.center+ConsoleColors.BB_GREEN+"                                                                  "+ConsoleColors.RESET;
    public static final String redLine = ConsoleColors.center+ConsoleColors.BB_RED+"                                                                  "+ConsoleColors.RESET;
}

