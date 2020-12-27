package Parametrage

//Dans ce programme, nous allons  définir l'objet Espace en lisant grâce à une fonction run les informations
// nécessaires,récupérées dans un fichier texte Input, à savoir les limites de l'espace, la position de départ
// et l'orientation de la tondeuse. //


object Espace {
//On initialise deux variables xmax et ymax qui vont nous permettre de définir les limites de notre espace.
  private var XMAX: Int = 0
  private var YMAX: Int = 0

//La fonction run va nous servir à lire l'input qui contient les coordonnées de la limite de l'espace,
// de la position ainsi que l'orientation des tondeuses et à nous sortir, avec ces instructions,
// sa position finale et son orientation.//

  def run(fileName: String): Unit = {
    val InputSource = io.Source.fromFile(getClass.getResource(s"/$fileName").getPath)

    // On récupère l'ensemble des lignes du fichier
    val lignes = InputSource.getLines()

    // On va récupérer dans le fichier, les coordonnées du coin supérieur droit de l'espace à partir
    // de la premère ligne de l'input.

    var ligne = lignes.next()
// On gère l'espace dans les intructions qui séparent le X et le Y max.
    val limite = ligne.split(" ")

    //On définit les limites avec les données de l'input.
    XMAX = limite(0).toInt
    YMAX = limite(1).toInt

    // On crée une boucle pour la lecture des lignes suivantes.
    var i = 1

    while (lignes.hasNext){
      ligne = lignes.next()
      // On récupère la position initiale de la tondeuse (coordonnées dans l'espace ainsi que son orientation)

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

     // Grâce à la classe Position on calcule les nouvelles coordonnées de la tondeuse ainsi que sa nouvelle
     // orientation suite à son mouvement (fonction mouvement dans la classe Tondeuse)

      val tondeuse = new Tondeuse(new Position(x, y, orientation), XMAX, YMAX)

      // On récupère la ligne d'instruction pour le déplacement de la tondeuse

      ligne = lignes.next()
      val commands = ligne.toList
      commands.foreach( tondeuse.mouvement(_) )

      // On affiche la position finale de la tondeuse

      println(s"Tondeuse $i : $tondeuse")
      i = i + 1
    }

    InputSource.close()
  }
}