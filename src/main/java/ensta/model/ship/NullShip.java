package ensta.model.ship;

import ensta.util.Orientation;

public class NullShip extends AbstractShip{
    public NullShip(){
        super("NullShip", 'N', 0, Orientation.WEST);
    }
}