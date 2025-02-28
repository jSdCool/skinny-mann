import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class ConstantOnSignal extends LogicInputComponent {
  
  public static final Identifier ID = new Identifier("ON");
  
  ConstantOnSignal(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "ON", context.getLogicBoard());
    outputTerminal=true;
  }

  ConstantOnSignal(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "ON", data.getJSONArray("connections"));
    outputTerminal=true;
  }
  
  public ConstantOnSignal(SerialIterator iterator){
    super(iterator);
  }
  
  void tick() {
    outputTerminal=true;
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
