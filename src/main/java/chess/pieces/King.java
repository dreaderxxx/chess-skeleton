package chess.pieces;

import java.util.stream.Stream;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    public Stream<Position> generatePositions(Position start, GameState gameState) {
        return Stream.of(
                start.moveRelatively(-1, -1),
                start.moveRelatively(-1, 0),
                start.moveRelatively(-1, 1),
                start.moveRelatively(0, -1),
                start.moveRelatively(0, 1),
                start.moveRelatively(1, -1),
                start.moveRelatively(1, 0),
                start.moveRelatively(1, 1));
    }
}
