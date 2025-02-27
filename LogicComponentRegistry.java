import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import processing.core.*;
import processing.data.*;

public class LogicComponentRegistry{
  private static ArrayList<Identifier> ids = new ArrayList<>();
  private static HashMap<Identifier, Function<JSONObject, LogicComponent>> jsonConstructors = new HashMap<>();
  private static HashMap<Identifier, Function<LogicCompoentnPlacementContext, LogicComponent>> placementConstructors = new HashMap<>();
  private static HashMap<Identifier, ComponentButtonIconDraw> icons = new HashMap<>();
  private static HashMap<Identifier, String> descriptions = new HashMap<>();
  
  public static void register(Identifier id,Function<SerialIterator,Serialization> serialConstructor, Function<JSONObject, LogicComponent> jsonConstructor, Function<LogicCompoentnPlacementContext,LogicComponent> placementConstructor, ComponentButtonIconDraw icon, String description){
    SerialRegistry.register(id,serialConstructor);
    if(!id.equals(GenericLogicComponent.ID)){//make this one not show up in the toolbox
      ids.add(id);  
    }
    jsonConstructors.put(id, jsonConstructor);
    placementConstructors.put(id, placementConstructor);
    icons.put(id, icon);
    descriptions.put(id,description);
  }
  
  public static int size(){
    return ids.size();
  }
  
  public static Identifier get(int index){
    return ids.get(index);
  }
  
  public static Function<JSONObject, LogicComponent> getJsonConstructor(Identifier id){
    return jsonConstructors.get(id);
  }
  
  public static Function<LogicCompoentnPlacementContext, LogicComponent> getPlacementConstructor(Identifier id){
    return placementConstructors.get(id);
  }
  
  public static ComponentButtonIconDraw geticon(Identifier id){
    return icons.get(id);
  }
  
  public static String getDescription(Identifier id){
    return descriptions.get(id);
  }
  
  
}


interface ComponentButtonIconDraw{
  void draw(PGraphics render, float x, float y); 
}
