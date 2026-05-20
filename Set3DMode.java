import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A logic component that can set if the player is in 3D mode
*/
public class Set3DMode extends LogicOutputComponent {
  
  public static final Identifier ID = new Identifier("set_3D");
  
  int groupNumber=0;
  float offset=0;
  /**Place a new set 3D mode 
  @param context The context for the placement
  */
  public Set3DMode(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "  set 3D  ", context.getLogicBoard());
  }

  /**Create a new set 3D mode from saved json data
  @param data The saved json data
  */
  public Set3DMode(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "  set 3D  ", data.getJSONArray("connections"));
  }
  /**Create a ser 3D mode from serialized binarry data
  @param iterator The source of the data
  */
  public Set3DMode(SerialIterator iterator){
    super(iterator);
    groupNumber = iterator.getInt();
    offset = iterator.getFloat();
  }
  /**The function where the logic/functionality of this component is execuated
  */
  @Override
  public void tick() {
    if (inputTerminal1) {
      if (source.level.stages.get(source.currentStageIndex).type.equals("3Dstage"))
        if (source.level.multyplayerMode!=2){
          source.e3DMode=true;
        }
    }
    if (inputTerminal2) {
      if (source.level.stages.get(source.currentStageIndex).type.equals("3Dstage"))
        if (source.level.multyplayerMode!=2){
          source.e3DMode=false;
        }
    }
  }
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(15*source.Scale);
    context.render.textAlign(PConstants.LEFT, PConstants.CENTER);
    PVector truePos = context.scaleCoord(x+5,y+16);
    context.render.text("true", truePos.x, truePos.y);
    PVector falsePos = context.scaleCoord(x+5,y+56);
    context.render.text("false", falsePos.x, falsePos.y);
  }
  
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(groupNumber);
    data.addFloat(offset);
    return data;
  }
  
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
}
