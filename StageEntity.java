import java.util.ArrayList;
import processing.data.*;
abstract class StageEntity extends Entity implements Killable,Serialization{
  
public abstract JSONObject save();
  
  public abstract PlayerIniteractionResult playerInteraction(Collider2D playerHitBox);
  public abstract PlayerIniteractionResult playerInteraction(Collider3D playerHitBox);
  
  public abstract void update(float dt,ArrayList<Collider2D> stageHitBoxes);
  
}
