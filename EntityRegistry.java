import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import processing.core.PGraphics;
import processing.data.JSONObject;

public class EntityRegistry{
  
  private static ArrayList<Identifier> ids = new ArrayList<>();
  private static HashMap<Identifier, Function<JSONObject,StageEntity>> jsonConstructors = new HashMap<>();
  private static HashMap<Identifier, Function<StageEntityPlacementContext, StageEntity>> placementConstructors = new HashMap<>();
  private static HashMap<Identifier, String> desciprions = new HashMap<>();
  private static HashMap<Identifier, EntityButtonIconDraw> icons = new HashMap<>();
  
  public static void register(Identifier id, Function<SerialIterator,Serialization> serialConstructor, Function<JSONObject, StageEntity> jsonConstructor, Function<StageEntityPlacementContext, StageEntity> placementConstructor, EntityButtonIconDraw icon, String description){
    SerialRegistry.register(id,serialConstructor);
    if(!id.equals(SimpleEntity.ID)){
      ids.add(id);
    }
    jsonConstructors.put(id,jsonConstructor);
    placementConstructors.put(id,placementConstructor);
    icons.put(id,icon);
    desciprions.put(id,description);
  }
  
  
  public static int size(){
    return ids.size();
  }
  
  public static Identifier get(int index){
    return ids.get(index);
  }
  
  public static Function<JSONObject,StageEntity> getJsonConstructor(Identifier id){
    return jsonConstructors.get(id);
  }
  
  public static Function<StageEntityPlacementContext, StageEntity> getPlacementConstructor(Identifier id){
    return placementConstructors.get(id);
  }
  
  public static EntityButtonIconDraw getIcon(Identifier id){
    return icons.get(id);
  }
  
  interface EntityButtonIconDraw{
    void draw(PGraphics render, float x, float y);
  }
}
