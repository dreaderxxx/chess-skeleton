package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

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
    public Stream<Position> generatePositions(Position start, GameState gameState) {
        Collection<Position> positions = new HashSet<>();
        // right-top moves
        for (Position pos = start.moveRelatively(1, 1); pos.isValid(); pos = pos.moveRelatively(1, 1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // right-bot moves
        for (Position pos = start.moveRelatively(1, -1); pos.isValid(); pos = pos.moveRelatively(1, -1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // left-top moves
        for (Position pos = start.moveRelatively(-1, 1); pos.isValid(); pos = pos.moveRelatively(-1, 1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }
        // left-bot moves
        for (Position pos = start.moveRelatively(-1, -1); pos.isValid(); pos = pos.moveRelatively(-1, -1)) {
            if (isEndPosition(positions, pos, gameState)) break;
        }

        return positions.stream();
    }
}
