/** sends data about an entity from the server to the client in multyplayer mode 2
*/
class MultyPlayerEntityInfo extends DataPacket{
  float x,y,z;
  int stage,index;
  boolean dead;
  /**used on the server to create a data packet of info to send to the clients
  @param stage the index of the stages the entity is on
  @param entityIndex the index of the entity on the stage
  @param entity the entity to send data of
  */
  public MultyPlayerEntityInfo(int stage,int entityIndex,StageEntity entity){
    this.stage=stage;
    this.index = entityIndex;
    this.x = entity.getX();
    this.y = entity.getY();
    this.z = entity.getZ();
    this.dead = entity.isDead();
  }
  
  /** used by the client to extract entity position information
  @param entity the entity to set the position of
  */
  void setPos(StageEntity entity){
    entity.setX(x);
    entity.setY(y);
    entity.setZ(z);
  }
  
  /** used by the client to extract the entiotes death status
  @param entity the entity to set the death stsatus of
  */
  void setDead(StageEntity entity){
    if(dead){
      if(!entity.isDead()){
        entity.kill();
      }
    }else{
      if(entity.isDead()){
        entity.respawn();
      }
    }
  }
  
  int getStage(){
    return stage;
  }
  
  int getIndex(){
    return index;
  }
}