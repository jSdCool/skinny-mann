void registerThings(){
  //Stage components
  StageComponentRegistry.register(Ground.ID, Ground::new,  Ground::new, Ground::new, (render, x, y) -> {
    render.fill(-7254783);
    render.stroke(-7254783);
    render.rect(x, y+30, 50, 20);
    render.fill(-16732415);
    render.stroke(-16732415);
    render.rect(x, y+20, 50, 10);
  }, "Ground", new Boolean[]{true,true}, (render, x, y, dx, dy, color_, rotation, scale)->{
    render.fill(color_);
    render.rect(x,y,dx,dy);
  });
  StageComponentRegistry.register(Holo.ID,Holo::new,Holo::new,Holo::new, (render, x, y) -> {}, "Holo", new Boolean[]{true,true}, (render, x, y, dx, dy, color_, rotation, scale)->{
    render.fill(color_);
    render.rect(x,y,dx,dy);
  });
  StageComponentRegistry.register(Sloap.ID,Sloap::new,Sloap::new,Sloap::new, (render, x, y) -> {
    render.fill(-7254783);
    render.stroke(-7254783);
    render.strokeWeight(0);
    render.triangle(x+5, y+45, x+45, y+45, x+45, y+5);
  }, "Sloap", new Boolean[]{true,true}, (render, x, y, dx, dy, color_, rotation, scale)->{
    render.fill(color_);
    if (rotation==0) {//display the triangle that will be created
      render.triangle(x, y, dx, dy, dx, y);
    }
    if (rotation==1) {
      render.triangle(x, y, x, dy, dx, y);
    }
    if (rotation==2) {
      render.triangle(x, y, dx, dy, x, dy);
    }
    if (rotation==3) {
      render.triangle(x, dy, dx, dy, dx, y);
    }
  });
  StageComponentRegistry.register(HoloTriangle.ID,HoloTriangle::new,HoloTriangle::new,HoloTriangle::new, (render, x, y) -> {
    render.fill(-4623063);
    render.stroke(-4623063);
    render.strokeWeight(0);
    render.triangle(x+5, y+45, x+45, y+45, x+45, y+5);
  }, "Holo Sloap", new Boolean[]{true,true}, (render, x, y, dx, dy, color_, rotation, scale)->{
    render.fill(color_);
    if (rotation==0) {//display the triangle that will be created
      render.triangle(x, y, dx, dy, dx, y);
    }
    if (rotation==1) {
      render.triangle(x, y, x, dy, dx, y);
    }
    if (rotation==2) {
      render.triangle(x, y, dx, dy, x, dy);
    }
    if (rotation==3) {
      render.triangle(x, dy, dx, dy, dx, y);
    }
  });
  StageComponentRegistry.register(DethPlane.ID,DethPlane::new,DethPlane::new,DethPlane::new, (render, x, y) -> {
    render.fill(-114431);
    render.stroke(-114431);
    render.rect(x+5, y+25, 40, 20);
  }, "Death Plane", new Boolean[]{true,false}, (render, x, y, dx, dy, color_, rotation, scale)->{
    render.fill(-114431);
    render.rect(x,y,dx,dy);
  });
  StageComponentRegistry.register(CheckPoint.ID,CheckPoint::new,CheckPoint::new,CheckPoint::new, (render, x, y) -> {
    render.fill(#B9B9B9);
    render.strokeWeight(0);
    render.rect(x+8, y+5, 5, 40);
    render.fill(#EA0202);
    render.stroke(#EA0202);
    render.strokeWeight(0);
    render.triangle(x+10, y+5, x+10, y+25, x+40,y+15);
    render.strokeWeight(0);
  }, "Check Point", new Boolean[]{true,true},(render, x, y, scale)->{
    drawCheckPoint(x,y,scale,render);
  });
  StageComponentRegistry.register(Coin.ID,Coin::new,Coin::new,Coin::new, (render, x, y) -> {
    drawCoin(x+25, y+25, 4,render);
  }, "Coin", new Boolean[]{true,true},(render, x, y, scale)->{
    drawCoin(x*scale,y*scale,3*scale,render);
  });
  StageComponentRegistry.register(Goal.ID,Goal::new,Goal::new,Goal::new, (render, x, y) -> {
    render.fill(0);
    render.stroke(0);
    render.strokeWeight(0);
    render.rect(x+3, y+3, 15, 15);
    render.rect(x+33, y+3, 15, 15);
    render.rect(x+18, y+18, 15, 15);
    render.rect(x+3, y+33, 15, 15);
    render.rect(x+33, y+33, 15, 15);
  }, "Goal", new Boolean[]{true,false,false,false},(render, x, y, scale)->{
    x*=scale;
    y*=scale;
    render.fill(255);
    render.rect(x, (y), 50, 50);
    render.rect((x+100), (y), 50, 50);
    render.rect((x+200), (y), 50, 50);
    render.fill(0);
    render.rect((x+50), (y), 50, 50);
    render.rect((x+150), (y), 50, 50);
  });
  StageComponentRegistry.register(Interdimentional_Portal.ID,Interdimentional_Portal::new,Interdimentional_Portal::new,null, (render, x, y) -> {
    drawPortal(x+25, y+25, 0.45,render);
  }, "Portal", new Boolean[]{true,true,false,false},(render, x, y, scale)->{});
  StageComponentRegistry.register(LogicButton.ID,LogicButton::new,LogicButton::new,LogicButton::new, (render, x, y) -> {
    drawLogicButton(x+25, y+25, 1, false,render);
  }, "Button", new Boolean[]{true,true,true,false},(render, x, y, scale)->{
    drawLogicButton(x*scale,y*scale,scale,false,render);
  });
  StageComponentRegistry.register(SoundBox.ID,SoundBox::new,SoundBox::new,SoundBox::new, (render, x, y) -> {
    drawSpeakericon(x+25, y+25, 0.5,render);
  }, "Sound Box", new Boolean[]{true,false,true,false},(render, x, y, scale)->{
    drawSoundBox(x*scale,y*scale,scale,render);
  });
  StageComponentRegistry.register(WritableSign.ID,WritableSign::new,WritableSign::new,WritableSign::new, (render, x, y) -> {
    drawSign(x+25, y+50, 0.6,render);
  }, "Sign", new Boolean[]{true,true},(render, x, y, scale)->{
    drawSign(x*scale,y*scale,scale,render);
  });
  StageComponentRegistry.register(SWoff3D.ID,SWoff3D::new,SWoff3D::new,SWoff3D::new,(render, x, y) -> {
    draw3DSwitch2(x+25, y+40, 1,render);
  }, "3D off switch", new Boolean[]{false,true},(render, x, y, scale)->{
    draw3DSwitch2(x,y,scale,render);
  });
  StageComponentRegistry.register(SWon3D.ID,SWon3D::new,SWon3D::new,SWon3D::new,(render, x, y) -> {
    draw3DSwitch1(x+25, y+40, 1,render);
  }, "3D on switch", new Boolean[]{false,true},(render, x, y, scale)->{
    draw3DSwitch1(x,y,scale,render);
  });
  
  SerialRegistry.register(GenericStageComponent.ID,GenericStageComponent::new);//palce
  
  //Logic components
  LogicComponentRegistry.register(AndGate.ID,AndGate::new,AndGate::new,AndGate::new,(render,x,y)->{},"And Gate");
  LogicComponentRegistry.register(ConstantOnSignal.ID,ConstantOnSignal::new,ConstantOnSignal::new,ConstantOnSignal::new,(render,x,y)->{},"Constant On signal");
  LogicComponentRegistry.register(Delay.ID,Delay::new,Delay::new,Delay::new,(render,x,y)->{},"Delay");
  LogicComponentRegistry.register(GenericLogicComponent.ID,GenericLogicComponent::new,GenericLogicComponent::new,GenericLogicComponent::new,(render,x,y)->{},"Generic");
  LogicComponentRegistry.register(LogicPlaySound.ID,LogicPlaySound::new,LogicPlaySound::new,LogicPlaySound::new,(render,x,y)->{
    drawSpeakericon(x+25,y+25,0.5,render);
  },"Play Sound");
  LogicComponentRegistry.register(NAndGate.ID,NAndGate::new,NAndGate::new,NAndGate::new,(render,x,y)->{},"NAnd Gate");
  LogicComponentRegistry.register(NOrGate.ID,NOrGate::new,NOrGate::new,NOrGate::new,(render,x,y)->{},"NOr Gate");
  LogicComponentRegistry.register(OrGate.ID,OrGate::new,OrGate::new,OrGate::new,(render,x,y)->{},"Or Gate");
  LogicComponentRegistry.register(Pulse.ID,Pulse::new,Pulse::new,Pulse::new,(render,x,y)->{},"Pulse");
  LogicComponentRegistry.register(Random.ID,Random::new,Random::new,Random::new,(render,x,y)->{},"Random");
  LogicComponentRegistry.register(Read3DMode.ID,Read3DMode::new,Read3DMode::new,Read3DMode::new,(render,x,y)->{},"Read 3D");
  LogicComponentRegistry.register(ReadVariable.ID,ReadVariable::new,ReadVariable::new,ReadVariable::new,(render,x,y)->{},"Read Var");
  LogicComponentRegistry.register(Set3DMode.ID,Set3DMode::new,Set3DMode::new,Set3DMode::new,(render,x,y)->{},"Set 3D");
  LogicComponentRegistry.register(SetVariable.ID,SetVariable::new,SetVariable::new,SetVariable::new,(render,x,y)->{},"Set Var");
  LogicComponentRegistry.register(SetVisibility.ID,SetVisibility::new,SetVisibility::new,SetVisibility::new,(render,x,y)->{},"Set Visability");
  LogicComponentRegistry.register(SetXOffset.ID,SetXOffset::new,SetXOffset::new,SetXOffset::new,(render,x,y)->{},"Set X Offset");
  LogicComponentRegistry.register(SetYOffset.ID,SetYOffset::new,SetYOffset::new,SetYOffset::new,(render,x,y)->{},"Set Y Offset");
  LogicComponentRegistry.register(SetZOffset.ID,SetZOffset::new,SetZOffset::new,SetZOffset::new,(render,x,y)->{},"Ser Z Offset");
  LogicComponentRegistry.register(XNorGate.ID,XNorGate::new,XNorGate::new,XNorGate::new,(render,x,y)->{},"XNor Gate");
  LogicComponentRegistry.register(XorGate.ID,XorGate::new,XorGate::new,XorGate::new,(render,x,y)->{},"Xor Gate");
  
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
