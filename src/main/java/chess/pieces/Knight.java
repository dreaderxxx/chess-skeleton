package chess.pieces;

import java.util.stream.Stream;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    public Stream<Position> generatePositions(Position start, GameState gameState) {
        return Stream.of(
                start.moveRelatively(-1,2),
                start.moveRelatively(1,2),
                start.moveRelatively(2,1),
                start.moveRelatively(2,-1),
                start.moveRelatively(-1,-2),
                start.moveRelatively(1,-2),
                start.moveRelatively(-2,1),
                start.moveRelatively(-2,-1)
        );
    }
}
