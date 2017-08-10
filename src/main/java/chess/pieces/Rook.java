package chess.pieces;

import java.util.stream.Stream;

import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    public Stream<Position> generatePositions(Position start) {
        return Stream.empty();
    }
}
