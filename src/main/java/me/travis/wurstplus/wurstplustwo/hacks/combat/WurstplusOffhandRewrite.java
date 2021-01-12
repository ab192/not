package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusPlayerUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class WurstplusOffhandRewrite extends WurstplusHack {

  // Made by RailHack on January 10th, 2021, 10:15 AM
  
  // Finished by RailHack on January 10th, 2021, 11:27 AM
  
    Public WurstplusOffhandRewrite() {
          super(WurstplusCategory.WURSTPLUS_COMBAT);
          
          this.name        = "OffhandRewrite"
          this.tag         = "OffhandRewrite"
          this.description = "RailHack's rewrite";
        }
        
        WurstplusSetting mode = create("Offhand", combobox("Crystal"));
        WurstplusSetting offhandHP = create("OffhandHealth", 16, 0, 36);
        WurstplusSetting delay = create("delay", false)
          
        WurstplusSetting disableOnHealth = create("DisableOnHealth"), false;
        WurstplusSetting disableOnHealthHP = create("DisableOnHealthHP" 16, 0, 36);
  
        WurstplusSetting smart = create("SmartOffhand", false);
          
      private boolean switching = false;
      private int last_slot;
  
      @Override
      public void update() {
        
         if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {
           
           if (switching) {
             swap_items(last_slot, 2);
             return;
           }
           
           float hp = mc.player.getHealth() + mc.player.getAbsorptionAmount();
           
           if (hp > offhandHP.get_value(1)) {
             if (mode.in("Crystal") swap_items(get_item_slot(Items.END_CRYSTAL),0));
             return;
            }
         } else {
                swap_items(get_item_slot(Items.TOTEM_OF_UNDYING), delay.get_value(true) ? 1 : 0);
               return;
           }

            if (mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                swap_items(get_item_slot(Items.TOTEM_OF_UNDYING), delay.get_value(true) ? 1 : 0);
            }

       }

    }

    public void swap_items(int slot, int step) {
        if (slot == -1) return;
        if (step == 0) {
           mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
           mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        }
       if (step == 1) {
           mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            switching = true;
            last_slot = slot;
        }
        if (step == 2) {
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
           switching = false;
        }

        mc.playerController.updateController();
    }
           float disableHP = mc.player.getHealth() + mc.player.getAbsorptionAmount();

// this next part looking kinda nihao to me
// line 93, disableHP > disableOffhandHP.get_value(1) might be disableHP.get_value(1) > disableOffhandHP

          if (disableHP < disableOnHealthHP.get_value(1)) {
            if(disableOffhandHealth.get_value(true) swap_items(get_item_slot(Items.TOTEM_OF_UNDYING), 0));
           WurstplusMessageUtil.send_client_message("Disabling Offhand due to health requirement...");
                this.set_disable();
            return;
          }

private boolean is_in_hole_strength() {

        BlockPos player_block = WurstplusPlayerUtil.GetLocalPlayerPosFloored();

            return mc.world.getBlockState(player_block.east()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(player_block.west()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(player_block.north()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(player_block.south()).getBlock() != Blocks.AIR;
    }
    
    private int get_item_slot(Item input) {
        if (input == mc.player.getHeldItemOffhand().getItem()) return -1;
        for(int i = 36; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == input) {
                if (i < 9) {
                    if (input == Items.STRENGTH_2) {
                        return -1;
                    }
                    i += 36;
                }
                return i;
            }
        }
        return -1;
        if (smart.get_value(true) && mc.player.isPotionActive(MobEffects.STRENGTH_2)) {
            swap_items(get_item_slot(Items.GOLDEN_APPLE), delay.get_value(true) ? 1 : 0);
        }
    }
}
