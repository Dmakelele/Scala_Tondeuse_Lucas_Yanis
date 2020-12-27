package Parametrage
//Dans ce programme, nous allons  définir l'objet Espace en lisant grâce à une fonction run les informations nécessaire,
// récupérées dans un fichier texte Input//
object Espace {
//On crée deux variables xmax et ymax qui vont nous permettre de définir les limites de notre espace//
  private var XMAX: Int = 0
  private var YMAX: Int = 0

//La fonction run va nous servir à lire l'input qui contient les coordonnées de la limite de l'espace,//
// de la position aisni que l'orientation des tondeuses et à nous sortir, avec ces instructions,//
// sa position finale et son orientation.//
  def run(fileName: String): Unit = {
    val InputSource = io.Source.fromFile(getClass.getResource(s"/$fileName").getPath)

    // On récupère l'ensemble des lignes du fichier
    val lignes = InputSource.getLines()

    // On va récupérer dans le fichier, les coordonnées du coin supérieur droit de l'espace à partir de la premère ligne de l'input//
    var ligne = lignes.next()

    val limite = ligne.split(" ")

    XMAX = limite(0).toInt
    YMAX = limite(1).toInt

    var i = 1

    // On bouble tant qu'il n'y a plus de ligne à lire.
    while (lignes.hasNext){
      ligne = lignes.next()
      // On recupere la position initiale de la tondeuse

      val start = ligne.split(" ")
      val x = start(0).toInt
      val y = start(1).toInt

      val orientation_text = start(2)
      var orientation = Orientation.NORTH
      orientation_text match {
        case "N" => orientation = Orientation.NORTH
        case "E" => orientation = Orientation.EAST
        case "W" => orientation = Orientation.WEST
        case "S" => orientation = Orientation.SOUTH
      }

      val tondeuse = new Tondeuse(new Position(x, y, orientation), XMAX, YMAX)

      // On récupère la ligne contenant la liste des instructions à fournir à la tondeuse
      ligne = lignes.next()
      val commands = ligne.toList
      commands.foreach( tondeuse.mouvement(_) )

      // Affichage de la position finale de la tondeuse
      println(s"Tondeuse $i : $tondeuse")
      i = i + 1
    }

    // On ferme le buffer de lecture du fichier
    InputSource.close()
  }
}