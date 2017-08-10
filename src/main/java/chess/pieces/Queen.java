package chess.pieces;

import java.util.stream.Stream;

import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public Stream<Position> generatePositions(Position start) {
        return Stream.empty();
    }
}
