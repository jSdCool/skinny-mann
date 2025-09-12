/**Represents a configruable property of a component
 @param <T> The type of the property
*/
public abstract class Property<T>{
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
  
  //TODO add a method to get a refrence to the Tool Box Ui things.
  
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
}
