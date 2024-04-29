import processing.data.*;
abstract class StageEntity extends Entity implements Killable{
  
  StageEntity(Stage stage){
    this.stage=stage;
  }
  
  private Stage stage;
  
  public final Stage getStage(){
    return stage;
  }
  
  public abstract JSONObject save();
  
  public abstract StageEntity create(JSONObject input,Stage stage);
  
  
}
