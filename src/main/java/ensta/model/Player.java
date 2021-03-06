package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;
import java.io.Serializable;
import java.util.List;

public class Player
{
  /*
   * ** Attributs
   */
  private Board board;
  protected Board opponentBoard;
  private int destroyedCount;
  protected AbstractShip[] ships;
  private boolean lose;

  /*
   * ** Constructeur
   */
  public Player(Board board, Board opponentBoard, List<AbstractShip> ships)
  {
    this.board = board;
    this.opponentBoard = opponentBoard;
    this.ships = ships.toArray(new AbstractShip[0]);
  }

  /*
   * ** Méthodes
   */

  /**
   * Read keyboard input to get ships coordinates. Place ships on given
   * coordinates.
   */
  public void putShips()
  {

    boolean done = false;
    int i = 0;
    Coords coords = new Coords();
    Boolean flagPutShip = false;

    do {
      AbstractShip ship = ships[i];
      String msg = String.format(
        "Placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
      System.out.println(msg);
      InputHelper.ShipInput res = InputHelper.readShipInput();
      // TODO set ship orientation
      switch (res.orientation) {
        case "north":
          ship.setOrientation(Orientation.NORTH);
          break;
        case "south":
          ship.setOrientation(Orientation.SOUTH);
          break;
        case "east":
          ship.setOrientation(Orientation.EAST);
          break;
        case "west":
          ship.setOrientation(Orientation.WEST);
          break;
      }

      coords.setX(res.x);
      coords.setY(res.y);

      // TODO put ship at given position
      flagPutShip = board.putShip(ship, coords);

      // TODO when ship placement successful
      if (flagPutShip) {
        ++i;
        board.printAll();
      }

      done = (i == ships.length);

    } while (!done);
  }

  public Hit sendHit(Coords coords)
  {
    Hit hit = null;

    System.out.println("Entrez vos coordonnées de frappe :");
    InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
    // TODO call sendHit on this.opponentBoard
    hit = opponentBoard.sendHit(hitInput.x, hitInput.y);
    // TODO : Game expects sendHit to return BOTH hit result & hit coords.
    // return hit is obvious. But how to return coords at the same time ?
    // -> modif par référence
    coords.setX(hitInput.x);
    coords.setY(hitInput.y);

    return hit;
  }

  public AbstractShip[] getShips() { return ships; }

  public void setShips(AbstractShip[] ships) { this.ships = ships; }

  public Board getBoard() { return board; }

  public void setBoard(Board board) { this.board = board; }

  public int getDestroyedCount() { return destroyedCount; }

  public void setDestroyedCount(int destroyedCount)
  {
    this.destroyedCount = destroyedCount;
  }

  public boolean isLose() { return lose; }

  public void setLose(boolean lose) { this.lose = lose; }
}
