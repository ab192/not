package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

public class WurstplusReverseStep extends WurstplusHack {
       public class WurstplusReverseStep() {
              super(WurstplusCategory.WURSTPLUS_MOVEMENT);
              
              this.name        = "Reverse Step";
              this.tag         = "ReverseStep";
              this.description = "reverse step";
             }
             
             @override
             public void update() {
                        
              mc.player.motionY = -1;
              return;
     }
  }
