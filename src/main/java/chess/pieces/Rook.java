package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

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
    public Stream<Position> generatePositions(Position start, GameState gameState) {
        Collection<Position> positions = new HashSet<>();
        // right moves
        for (Position pos = start.moveRelatively(1, 0); pos.isValid(); pos = pos.moveRelatively(1, 0)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // left moves
        for (Position pos = start.moveRelatively(-1, 0); pos.isValid(); pos = pos.moveRelatively(-1, 0)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // top moves
        for (Position pos = start.moveRelatively(0, 1); pos.isValid(); pos = pos.moveRelatively(0, 1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // bot moves
        for (Position pos = start.moveRelatively(0, -1); pos.isValid(); pos = pos.moveRelatively(0, -1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        return positions.stream();
    }
}
