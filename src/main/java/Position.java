import java.util.List;

public enum Position {

    CAM, CDM, GK, LB, LCB, LF, LM, RB, RCB, RF, RM;

    public static Position[] getPositions() {
        return new Position[]{CAM, CDM, GK, LB, LCB, LF, LM, RB, RCB, RF, RM};
    }

    public static Position[] getPosition(String string) {
        switch (string) {
            case "CM":
            case "CAM":
            case "CDM":
            case "CF":
            case "ST": {
                return new Position[]{CAM, CDM};
            }
            case "GK": {
                return new Position[]{GK};
            }
            case "RW":
            case "RF":
            case "RM": {
                return new Position[]{RF, RM};
            }
            case "LW":
            case "LF":
            case "LM": {
                return new Position[]{LF, LM};
            }
            case "RB":
            case "RWB": {
                return new Position[]{RB};
            }
            case "LB":
            case "LWB": {
                return new Position[]{LB};
            }
            default: {
                return new Position[]{RCB, LCB};
            }
        }
    }

}
