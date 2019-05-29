/**
 * Object representing a link between two positions
 * used for calculating a team's chemistry.
 */
public final class Link {

    /**
     * Links for the standard squad formation.
     * Different squads might have different links.
     */
    public static final Link[] links = {
            new Link(Position.LF, Position.RF),
            new Link(Position.LF, Position.CAM),
            new Link(Position.RF, Position.CAM),
            new Link(Position.LM, Position.LF),
            new Link(Position.LM, Position.CDM),
            new Link(Position.LM, Position.LB),
            new Link(Position.LB, Position.LCB),
            new Link(Position.LCB, Position.GK),
            new Link(Position.LCB, Position.RCB),
            new Link(Position.LCB, Position.CDM),
            new Link(Position.GK, Position.RCB),
            new Link(Position.RCB, Position.CDM),
            new Link(Position.RCB, Position.RB),
            new Link(Position.RB, Position.RB),
            new Link(Position.RM, Position.CDM),
            new Link(Position.RM, Position.CAM),
            new Link(Position.RM, Position.RF),
    };

    private final Position position1;
    private final Position position2;

    /**
     * Constructor for a new link between two positions.
     * @param position1 position of the first player
     * @param position2 position of the second player
     */
    private Link(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
    }

    /**
     * Getter for first position.
     * @return position1
     */
    public Position getPosition1() {
        return position1;
    }

    /**
     * Getter for the second position.
     * @return position2
     */
    public Position getPosition2() {
        return position2;
    }
}
