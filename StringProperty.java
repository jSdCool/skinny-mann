/**Represents a configruable String property of a component
*/
public class StringProperty extends Property<String>{
  
  private final Getter<String> getter;
  private final Setter<String> setter;
  
  /**Create a new string property with the given name
  @param getter The getter for this property
  @param setter The setter for this property
  @param name the visable name of the property
  */
  public StringProperty(Getter<String> getter, Setter<String> setter, String name){
    super(name);
    this.getter = getter;
    this.setter = setter;
  }
  
  /**Set the value of this property
  @param newValue The new value of this property
  */
  public void set(String newValue){
    setter.set(newValue);
  }
  
  /**Get the current value of this property
  @return The current value of this property
  */
  public String get(){
    return getter.get();
  }
  
  public PropertyConfigUi<StringProperty> getConfigUi(){
    return StringPropertyConfigUi.getInstace();
  }
}
