import processing.data.*;
abstract class StageEntity extends Entity implements Killable{
  
  public abstract JSONObject save();
  
  public abstract StageEntity create(JSONObject input);
  
}
