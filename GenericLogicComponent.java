import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

//GenericLogicComponent

class GenericLogicComponent extends LogicComponent {
  
  public static final Identifier ID = new Identifier("GenericLogicComponent");
  
  GenericLogicComponent(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "GenericLogicComponent", context.getLogicBoard());
  }
  GenericLogicComponent(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "GenericLogicComponent", data.getJSONArray("connections"));
  }
  
  public GenericLogicComponent(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
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
