package chess.pieces;

import java.util.stream.Stream;

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
    public Stream<Position> generatePositions(Position start) {
        return Stream.empty();
    }
}
