void registerThings(){
  //Stage components
  StageComponentRegistry.register(Ground.ID, Ground::new,  Ground::new, Ground::new, (reder, x, y) -> {}, "Ground", new Boolean[]{true,true}, 0);
  StageComponentRegistry.register(Holo.ID,Holo::new,Holo::new,Holo::new, (reder, x, y) -> {}, "Holo", new Boolean[]{true,true}, 0);
  StageComponentRegistry.register(Sloap.ID,Sloap::new,Sloap::new,Sloap::new, (reder, x, y) -> {}, "Sloap", new Boolean[]{true,true}, 0);
  StageComponentRegistry.register(HoloTriangle.ID,HoloTriangle::new,HoloTriangle::new,HoloTriangle::new, (reder, x, y) -> {}, "Holo Sloap", new Boolean[]{true,true}, 0);
  StageComponentRegistry.register(DethPlane.ID,DethPlane::new,DethPlane::new,DethPlane::new, (reder, x, y) -> {}, "Death Plane", new Boolean[]{true,false}, 0);
  StageComponentRegistry.register(CheckPoint.ID,CheckPoint::new,CheckPoint::new,CheckPoint::new, (reder, x, y) -> {}, "Check Point", new Boolean[]{true,true});
  StageComponentRegistry.register(Coin.ID,Coin::new,Coin::new,Coin::new, (reder, x, y) -> {}, "Coin", new Boolean[]{true,true});
  StageComponentRegistry.register(Goal.ID,Goal::new,Goal::new,Goal::new, (reder, x, y) -> {}, "Goal", new Boolean[]{true,false});
  StageComponentRegistry.register(Interdimentional_Portal.ID,Interdimentional_Portal::new,Interdimentional_Portal::new,null, (reder, x, y) -> {}, "Portal", new Boolean[]{true,true,false});
  StageComponentRegistry.register(LogicButton.ID,LogicButton::new,LogicButton::new,LogicButton::new, (reder, x, y) -> {}, "Button", new Boolean[]{true,true});
  StageComponentRegistry.register(SoundBox.ID,SoundBox::new,SoundBox::new,SoundBox::new, (reder, x, y) -> {}, "Sound Box", new Boolean[]{true,false});
  StageComponentRegistry.register(WritableSign.ID,WritableSign::new,WritableSign::new,WritableSign::new, (reder, x, y) -> {}, "Sign", new Boolean[]{true,true});
  
  SerialRegistry.register(GenericStageComponent.ID,GenericStageComponent::new);//palce
  
  //Logic components
  SerialRegistry.register(AndGate.ID,AndGate::new);
  SerialRegistry.register(ConstantOnSignal.ID,ConstantOnSignal::new);
  SerialRegistry.register(Delay.ID,Delay::new);
  SerialRegistry.register(GenericLogicComponent.ID,GenericLogicComponent::new);
  SerialRegistry.register(LogicPlaySound.ID,LogicPlaySound::new);
  SerialRegistry.register(NAndGate.ID,NAndGate::new);
  SerialRegistry.register(NOrGate.ID,NOrGate::new);
  SerialRegistry.register(OrGate.ID,OrGate::new);
  SerialRegistry.register(Pulse.ID,Pulse::new);
  SerialRegistry.register(Random.ID,Random::new);
  SerialRegistry.register(Read3DMode.ID,Read3DMode::new);
  SerialRegistry.register(ReadVariable.ID,ReadVariable::new);
  SerialRegistry.register(Set3DMode.ID,Set3DMode::new);
  SerialRegistry.register(SetVariable.ID,SetVariable::new);
  SerialRegistry.register(SetVisibility.ID,SetVisibility::new);
  SerialRegistry.register(SetXOffset.ID,SetXOffset::new);
  SerialRegistry.register(SetYOffset.ID,SetYOffset::new);
  SerialRegistry.register(SetZOffset.ID,SetZOffset::new);
  SerialRegistry.register(SWoff3D.ID,SWoff3D::new);
  SerialRegistry.register(SWon3D.ID,SWon3D::new);
  SerialRegistry.register(XNorGate.ID,XNorGate::new);
  SerialRegistry.register(XorGate.ID,XorGate::new);
  
  //entitys
  SerialRegistry.register(Goon.ID,Goon::new);
  SerialRegistry.register(SimpleEntity.ID,SimpleEntity::new);
  
  //other
  SerialRegistry.register(BackToMenuRequest.ID,BackToMenuRequest::new);
  SerialRegistry.register(BestScore.ID,BestScore::new);
  SerialRegistry.register(Button.ID,Button::new);
  SerialRegistry.register(ClientInfo.ID,ClientInfo::new);
  SerialRegistry.register(CloseMenuRequest.ID,CloseMenuRequest::new);
  SerialRegistry.register(CoOpStateInfo.ID,CoOpStateInfo::new);
  SerialRegistry.register(GoonMovementManager.ID,GoonMovementManager::new);
  SerialRegistry.register(Group.ID,Group::new);
  SerialRegistry.register(InfoForClient.ID,InfoForClient::new);
  SerialRegistry.register(KillEntityDataPacket.ID,KillEntityDataPacket::new);
  SerialRegistry.register(LeaderBoard.ID,LeaderBoard::new);
  SerialRegistry.register(Level.ID,Level::new);
  SerialRegistry.register(LevelDownloadInfo.ID,LevelDownloadInfo::new);
  SerialRegistry.register(LevelFileComponentData.ID,LevelFileComponentData::new);
  SerialRegistry.register(LoadLevelRequest.ID,LoadLevelRequest::new);
  SerialRegistry.register(LogicBoard.ID,LogicBoard::new);
  SerialRegistry.register(MultyPlayerEntityInfo.ID,MultyPlayerEntityInfo::new);
  SerialRegistry.register(NetworkDataPacket.ID,NetworkDataPacket::new);
  SerialRegistry.register(NoMovementManager.ID,NoMovementManager::new);
  SerialRegistry.register(Player.ID,Player::new);
  SerialRegistry.register(PlayerInfo.ID,PlayerInfo::new);
  SerialRegistry.register(PlayerMovementManager.ID,PlayerMovementManager::new);
  SerialRegistry.register(PlayerPositionInfo.ID,PlayerPositionInfo::new);
  SerialRegistry.register(RequestLevel.ID,RequestLevel::new);
  SerialRegistry.register(RequestLevelFileComponent.ID,RequestLevelFileComponent::new);
  SerialRegistry.register(SelectedLevelInfo.ID,SelectedLevelInfo::new);
  SerialRegistry.register(Stage.ID,Stage::new);
  SerialRegistry.register(StageSound.ID,StageSound::new);
}
