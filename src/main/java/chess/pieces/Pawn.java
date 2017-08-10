package chess.pieces;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public Stream<Position> generatePositions(Position start) {

        Set<Position> positions = new HashSet<>();
        // two squares moves
        if ((getOwner() == Player.White && start.getRow() == 2) || (getOwner() == Player.Black && start.getRow() == 7)) {
            positions.add(start.moveRelatively(0, sign() * 2));
        }
        // simple forward move
        positions.add(start.moveRelatively(0, sign()));
        // attack moves
        positions.add(start.moveRelatively(-1, sign()));
        positions.add(start.moveRelatively(1, sign()));

        return positions.stream();
    }

    private int sign() {
        return getOwner() == Player.White ? 1 : -1;
    }
}
