package Utils;

public class ConsoleColors {
        // Regular Colors
        public final String BLACK = "\u001b[30m";
        public final String RED = "\u001b[31m";
        public final String GREEN = "\u001b[32m";
        public final String YELLOW = "\u001b[33m";
        public final String BLUE = "\u001b[34m";
        public final String MAGENTA = "\u001b[35m";
        public final String CYAN = "\u001b[36m";
        public final String WHITE = "\u001b[37m";

        // Bright Colors
        public final String BR_BLACK = "\u001b[30;1m";
        public final String BR_RED = "\u001b[31;1m";
        public final String BR_GREEN = "\u001b[32;1m";
        public final String BR_YELLOW = "\u001b[33;1m";
        public final String BR_BLUE = "\u001b[34;1m";
        public final String BR_BLACK_MAGENTA = "\u001b[35;1m";
        public final String BR_CYAN = "\u001b[36;1m";
        public final String BR_WHITE = "\u001b[37;1m";

        // Background Colors
        public final String BG_BLACK = "\u001b[40m";
        public final String BG_RED = "\u001b[41m";
        public final String BG_GREEN = "\u001b[42m";
        public final String BG_YELLOW = "\u001b[43m";
        public final String BG_BLUE = "\u001b[44m";
        public final String BG_MAGENTA = "\u001b[45m";
        public final String BG_CYAN = "\u001b[46m";
        public final String BG_WHITE = "\u001b[47m";

        // Bright Background Colors
        public final String BB_BLACK = "\u001b[40;1m";
        public final String BB_RED = "\u001b[41;1m";
        public final String BB_GREEN = "\u001b[42;1m";
        public final String BB_YELLOW = "\u001b[43;1m";
        public final String BB_BLUE = "\u001b[44;1m";
        public final String BB_MAGENTA = "\u001b[45;1m";
        public final String BB_CYAN = "\u001b[46;1m";
        public final String BB_WHITE = "\u001b[47;1m";

        // Reset
        public final String RESET = "\u001b[0m";

        public final String centerResponse = "\n\t\t\t\t\t\t\t\t";
        public final String center = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";

        public final String whiteSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + BB_WHITE + " " + BG_BLACK
                        + "                                   " +
                        "                             " + BB_WHITE + " " + RESET;
        public final String greenSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + BB_GREEN + " " + BG_BLACK
                        + "                                   " +
                        "                             " + BB_GREEN + " " + RESET;
        public final String redSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + BB_RED + " " + BG_BLACK
                        + "                                   " +
                        "                             " + BB_RED + " " + RESET;
        public final String yellowSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + BB_YELLOW + " " + BG_BLACK
                        + "                                   " +
                        "                             " + BB_YELLOW + " " + RESET;

        public final String whiteLine = center + BB_WHITE
                        + "                                                                  " + RESET;
        public final String greenLine = center + BB_GREEN
                        + "                                                                  " + RESET;
        public final String redLine = center + BB_RED
                        + "                                                                  " + RESET;
        public final String yellowLine = center + BB_YELLOW
                        + "                                                                  " + RESET;
}