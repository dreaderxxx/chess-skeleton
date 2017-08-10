package chess.pieces;

import java.util.stream.Stream;

import chess.Move;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Generating stream of possible moves
     *
     * @param start initial position
     * @return stream of possible position for his piece
     */
    public Stream<Move> getPossibleMoves(Position start) {
        return generatePositions(start).filter(Position::isValid).map(end -> new Move(start, end));
    }

    protected abstract Stream<Position> generatePositions(Position start);

    protected abstract char getIdentifyingCharacter();
}
