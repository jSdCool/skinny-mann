import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class Read3DMode extends LogicInputComponent {
  
  public static final Identifier ID = new Identifier("Read3DMode");
  
  Read3DMode(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "read 3D ", context.getLogicBoard());
  }

  Read3DMode(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "read 3D ", data.getJSONArray("connections"));
  }
  
  public Read3DMode(SerialIterator iterator){
    super(iterator);
  }
  
  void tick() {
    if (source.level.multyplayerMode!=2)
      outputTerminal=source.e3DMode;
    else
      outputTerminal=false;
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
