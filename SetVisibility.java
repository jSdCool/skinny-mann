import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A logic component to set the visability of a group
*/
public class SetVisibility extends LogicOutputComponent implements Configurable{
  
  public static final Identifier ID = new Identifier("set_visable");
  
  int groupNumber=0;
  boolean reText = false;
  /**Place a new set visability
  @param context The context for the placement
  */
  public SetVisibility(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "set visable", context.getLogicBoard());
  }
  /**Create a new set visability from saved json data
  @param data The saved json data
  */
  public SetVisibility(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "set visable", data.getJSONArray("connections"));
    groupNumber=data.getInt("group number");
  }
  
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    super.commonInit(uiScale,render);
    reText = true;
  }
  /**Create a set visability from serialized binarry data
  @param iterator The source of the data
  */
  public SetVisibility(SerialIterator iterator){
    super(iterator);
    groupNumber = iterator.getInt();
  }
  /**The function where the logic/functionality of this component is execuated
  */
  @Override
  public void tick() {
    if (inputTerminal1) {
      source.level.groups.get(groupNumber).visable=true;
    }
    if (inputTerminal2) {
      source.level.groups.get(groupNumber).visable=false;
    }
  }
  /**Get a JSONObject representation of this component that can be saved to a file
  @return JSONObject representation of this object
  */
  @Override
  public JSONObject save() {
    JSONObject component=super.save();
    component.setInt("group number", groupNumber);
    return component;
  }

  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    if(reText){
      button.setText("  visibility of "+context.getGroupNames().get(groupNumber));
      reText=false;
    }
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(context.scale(15));
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
    return data;
  }
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
  
  /**Get the properties that can be configured on this component
  @return An array of the properties that can be configured
  */
  @Override
  public Property[] getProperties(){
    return new Property[]{new GroupProperty(() -> groupNumber, (value) -> {groupNumber=value;reText = true;},"Current Group")};
  }
}
