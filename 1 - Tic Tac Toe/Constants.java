public class Constants {
    public final static String UNKNOWN_PLAYER_NAME = "Choose a player, and start again.\nThe players: [human, clever, whatever, genius]";

    public final static String UNKNOWN_RENDERER_NAME = "Choose a renderer, and start again. \nPlease choose one of the following [console, none]";

    public final static String INVALID_COORDINATE = "Invalid mark position, please choose a different position.\n" +
            "Invalid coordinates, type again: ";

    public final static String OCCUPIED_COORDINATE = "Mark position is already occupied.\n" +
            "Invalid coordinates, type again: ";

    /**
     * Use this method to generate the text that HumanPlayer should send
     *
     * @param markSymbol according to the Mark the player uses in the current turn.
     * @return String to be printed to the player.
     */
    public static String playerRequestInputString(String markSymbol) {
        return "Player " + markSymbol + ", type coordinates: ";

    }
}
