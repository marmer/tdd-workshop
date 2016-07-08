package de.scag.tryouts.tdd.katas.minesweeper;

public class MinesweeperException extends Exception {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    public MinesweeperException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MinesweeperException(final String message) {
        super(message);
    }
}
