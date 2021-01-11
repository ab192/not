package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.item.Item;


        Public class WurstplusBetterAutototem extends WurstplusHack {
        
               public class WurstplusTest() {
                      super(WurstplusCategory.WURSTPLUS_COMBAT);
                      
                      this.name        = "Better AutoTotem";
                      this.tag         = "BetterAutoTotem";
                      this.description = "better autototem";
                     }
                     
                  @Override 
                  public void update() {
                  
                  if (mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                swap_items(get_item_slot(Items.TOTEM_OF_UNDYING),0))
                }
         }
}              
