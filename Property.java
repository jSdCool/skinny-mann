/**Represents a configruable property of a component
 @param <T> The type of the property
 @param <P> A refrnce to the extending class, so bsacially its a self refrence
*/
public abstract class Property<T, P extends Property<T,P>>{
  /**The visable name of this property
  */
  private final String name;
  
  /**Create a new property with the given name
  @param name the visable name of the property
  */
  public Property(String name){
    this.name = name;
  }
  
  /**Set the value of this property
  @param newValue The new value of this property
  */
  public abstract void set(T newValue);
  /**Get the current value of this property
  @return The current value of this property
  */
  public abstract T get();
  
  /**Get the visable name of this property
  @return The name of this property
  */
  public String getName(){
    return name;
  }
  
  /**Get the config UI for this type of property
  @return The single instace of the config ui for this property
  */
  public abstract PropertyConfigUi<T,P> getConfigUi();
  
  /**Functional interface used as a getter for this property's value.<br>
  Implment this as a lambda expression
  @param <T> The type of the property
  */
  public static interface Getter<T>{
    /**Get the current value of this property
    @return The current value of this property
    */
    T get();
  }
  
  /**Functional interface used as a setter for this property's value.<br>
  Implment this as a lambda expression
  @param <T> The type of the property
  */
  public static interface Setter<T>{
    /**Set the current value of this property
    @param value The new value of this property
    */
    void set(T value);
  }
  
  /**Render the configuration for this propery at the given slot.<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void draw(int slotId){
    getConfigUi().draw(slotId,self());
  }
  
  /**Process mouse clicks that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void mouseClicked(int slotId){
    getConfigUi().mouseClicked(slotId,self());
  }
  
  /**Process mouse pressed events that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void mousePressed(int slotId){
    getConfigUi().mousePressed(slotId,self());
  }
  
  /**Process mouse released that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void mouseReleased(int slotId){
    getConfigUi().mouseReleased(slotId,self());
  }
  
  /**Process key pressed events that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void keyPressed(int slotId){
    getConfigUi().keyPressed(slotId,self());
  }
  
  /**Process key released that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void keyReleased(int slotId){
    getConfigUi().keyReleased(slotId,self());
  }
  
  /**Process key typed events that happen while a property is being configured..<br>
  Called by the tool box window, do not call
  @param slotId The index of the slot to display this property
  */
  public final void keyTyped(int slotId){
    getConfigUi().keyTyped(slotId,self());
  }
  
  /**Return this but cast to whatever type P is because generics can be a bit of a bith
  @return this but as a P
  */
  @SuppressWarnings("unchecked")
  private final P self(){
    return (P)this;
  }
}
