import java.util.ArrayList;
class StageEntityCollisionManager{
  
  //TODO: this needs to be changed to be fore effishent
  public static boolean level_colide(Collider2D hitbox, ArrayList<Collider2D> stageBoxes){
    for (Collider2D stageBox:stageBoxes) {//loop over all the objects in the stage
      if (CollisionDetection.collide2D(hitbox, stageBox)) {//check if the objects collide
        return true;
      }
    }
    return false;
  }
  
  public static boolean level_colide(Collider3D hitbox, ArrayList<Collider3D> stageBoxes){
    for (Collider3D stageBox:stageBoxes) {//loop over all the objects in the stage
      if (CollisionDetection.collide3D(hitbox, stageBox)) {//check if the objects collide
        return true;
      }
    }
    return false;
  }
}
