import java.util.function.Function;
import processing.data.*;
import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PGraphics;

public class StageComponentRegistry{
  private static ArrayList<Identifier> ids = new ArrayList<>();
  private static HashMap<Identifier, Function<JSONObject, StageComponent>> jsonConstructors = new HashMap<>();
  private static HashMap<Identifier, Function<StageComponentPlacementContext, StageComponent>> placementConstrucors = new HashMap<>();
  private static HashMap<Identifier, Function<StageComponentDragPlacementContext, StageComponent>> dragPlacementConstructors = new HashMap<>();
  private static HashMap<Identifier, ComponentButtonIconDraw> buttonIcons = new HashMap<>();
  private static HashMap<Identifier, String> descriptionText = new HashMap<>();
  private static HashMap<Identifier, Boolean[]> dimentionAllow = new HashMap<>();
  private static HashMap<Identifier, PlacementPreview> previews = new HashMap<>();
  private static HashMap<Identifier, DraggablePlacementPreview> draggablePreviews = new HashMap<>();
  
  
  public static void register(Identifier id, Function<SerialIterator, Serialization> serialConstructor, Function<JSONObject, StageComponent> jsonConstructor, Function<StageComponentPlacementContext, StageComponent> placementConstructor, ComponentButtonIconDraw icon, String description, Boolean[] dimentionAllows, PlacementPreview preview){
    SerialRegistry.register(id, serialConstructor);
    ids.add(id);
    jsonConstructors.put(id,jsonConstructor);
    placementConstrucors.put(id, placementConstructor);
    buttonIcons.put(id,icon);
    descriptionText.put(id, description);
    dimentionAllow.put(id, dimentionAllows);
    previews.put(id,preview);
  }
  
  public static void register(Identifier id, Function<SerialIterator, Serialization> serialConstructor, Function<JSONObject, StageComponent> jsonConstructor, Function<StageComponentDragPlacementContext, StageComponent> placementConstructor, ComponentButtonIconDraw icon, String description, Boolean[] dimentionAllows, DraggablePlacementPreview preview){
    SerialRegistry.register(id, serialConstructor);
    ids.add(id);
    jsonConstructors.put(id,jsonConstructor);
    dragPlacementConstructors.put(id, placementConstructor);
    buttonIcons.put(id,icon);
    descriptionText.put(id, description);
    dimentionAllow.put(id, dimentionAllows);
    draggablePreviews.put(id, preview);
  }
  
  static int size(){
    return ids.size();
  }
  
  static Identifier get(int index){
    return ids.get(index);
  }
  
  static Function<JSONObject, StageComponent> getJsonConstructor(Identifier id){
    return jsonConstructors.get(id);
  }
  
  static Function<StageComponentPlacementContext, StageComponent> getPlacementConstructor(Identifier id){
    return placementConstrucors.get(id);
  }
  
  static Function<StageComponentDragPlacementContext, StageComponent> getDragConstructor(Identifier id){
    return dragPlacementConstructors.get(id);
  }
  
  static ComponentButtonIconDraw getIcon(Identifier id){
    return buttonIcons.get(id);
  }
  
  static String getDescription(Identifier id){
    return descriptionText.get(id);
  }
  
  static Boolean[] getAllowedDimentions(Identifier id){
    return dimentionAllow.get(id);
  }
  
  static boolean isDraggable(Identifier id){
    return dragPlacementConstructors.get(id) != null;
  }
  
  static PlacementPreview getPreview(Identifier id){
    return previews.get(id);
  }
  
  static DraggablePlacementPreview getDragPreview(Identifier id){
    return draggablePreviews.get(id);
  }
  
  interface ComponentButtonIconDraw{
    void draw(PGraphics render, float x, float y);
  }
  
  interface PlacementPreview{
    void draw(PGraphics render, float x, float y, float scale);
  }
  
  interface DraggablePlacementPreview{
    void draw(PGraphics render, float x, float y, float dx, float dy, int color, int rotation, float scale);
  }
  
}
