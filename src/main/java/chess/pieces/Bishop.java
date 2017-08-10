package chess.pieces;

import java.util.stream.Stream;

import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    public Stream<Position> generatePositions(Position start) {
        return Stream.empty();
    }
}
