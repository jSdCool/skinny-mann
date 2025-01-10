/**this class is used to send general data between the client and the server
 
 */
class ClientInfo extends DataPacket {
  
  public static final Identifier ID = new Identifier("ClientInfo");
  
  public String name;
  boolean readdy, atEnd;
  ClientInfo(String name, boolean ready, boolean atEnd) {
    this.name=name;
    this.readdy=ready;
    this.atEnd=atEnd;
  }
  
  //SerialIterator iterator
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    data.addObject(SerializedData.ofString(name));
    data.addBool(readdy);
    data.addBool(atEnd);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
