package Parametrage
//Dans ce programme, on va créer la class tondeuse qui définit sa position ainsi que //
// les valeurs limites de l'esapce//
class Tondeuse (val _position: Position, val _XMAX: Int, _YMAX: Int){

  private var position: Position = _position

  private val XMAX: Int = _XMAX
  private val YMAX: Int = _YMAX
//La fonction mouvement permet l'avancée de la tondeuse en fonction de son oreintation dans l'espace.
// On aura au final, la postion d'arrivée de la tondeuse de la tondeuse.//
  def mouvement(command: Char): Unit = {
    command match {
      case 'A' => {
        position.orientation match {
          case Orientation.EAST =>
            if (position.x < XMAX) position.x = position.x + 1
          case Orientation.WEST =>
            if (position.x > 0) position.x = position.x -1
          case Orientation.NORTH =>
            if (position.y < YMAX) position.y = position.y + 1
          case Orientation.SOUTH =>
            if (position.y > 0) position.y = position.y - 1
        }
      }
      case 'D' => {
        position.orientation match {
          case Orientation.EAST => position.orientation = Orientation.SOUTH
          case Orientation.WEST => position.orientation = Orientation.NORTH
          case Orientation.NORTH => position.orientation = Orientation.EAST
          case Orientation.SOUTH => position.orientation = Orientation.WEST
        }
      }
      case 'G' => {
        position.orientation match {
          case Orientation.EAST => position.orientation = Orientation.NORTH
          case Orientation.WEST => position.orientation = Orientation.SOUTH
          case Orientation.NORTH => position.orientation = Orientation.WEST
          case Orientation.SOUTH => position.orientation = Orientation.EAST
        }
      }
      case default => println("Veuillez insérer une commande correcte")
    }
  }


  override def toString =  s"$position"
}
