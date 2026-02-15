public interface EntityGetMovementManagerContext{
  /**Get the player that is currently being controlled by this client.<br>
  This was added so that the player could remove a reference to the main class. 
  @return THe current player
  */
  Player getCurrentPlayer();
  
  /**Get the movement manager for the player the client is currenly controlling
  @return the movemnt manager for the player
  */
  MovementManager getCurrentPlayerMovementManager();
}
