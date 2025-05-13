package seng201.team0.models;

public class RaceResult {
    private final boolean gameFinished;
    private final String message;
    private final int gamePosition;
    private final int prizeValue;

    public RaceResult(boolean gameFinished, String message, int gamePosition, int prizeValue) {
        this.gameFinished = gameFinished;
        this.message = message;
        this.gamePosition = gamePosition;
        this.prizeValue = prizeValue;
    }
    public boolean isGameFinished() {
        return gameFinished;
    }
    public String getMessage() {
        return message;
    }
    public int getGamePosition() {
        return gamePosition;
    }
    public int getPrizeValue() {
        return prizeValue;
    }
}
