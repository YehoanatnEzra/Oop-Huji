/**
 * The PlayerFactory class is responsible for creating instances of
 * different types of players based on the provided type.
 * It acts as a factory for creating player objects.
 */
class PlayerFactory {

    /**
     * Constructs a PlayerFactory object with a default constructor.
     */
    public PlayerFactory() {
    }

    /**
     * Builds and returns a player object based on the provided type.
     *
     * @param type The type of player to be created ("human", "genius", "whatever", or "clever").
     * @return A player object of the specified type, or null if the type is not recognized.
     */
    public Player buildPlayer (String type) {
        String lowCaseType = type.toLowerCase();
        Player newPlayer = switch (lowCaseType) {
            case "human" -> new HumanPlayer();
            case "genius" -> new GeniusPlayer();
            case "whatever" -> new WhateverPlayer();
            case "clever" -> new CleverPlayer();
            default -> null;
        };
        return newPlayer;
    }
}