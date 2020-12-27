package Parametrage
//La position est définit par les coordonnées et l'orientation de la tondeuse.//
class Position (val _x:Int, val _y: Int, val _orientation: Orientation.Value) {

  var x: Int = _x
  var y: Int = _y
  var orientation = _orientation


  override def toString = s"$x $y ${orientation.toString().charAt(0)}"
}


