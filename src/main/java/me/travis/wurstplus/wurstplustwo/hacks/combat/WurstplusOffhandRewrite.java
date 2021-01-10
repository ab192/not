package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import net.minecraft.client.gui.inventory.GuiInventory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusPlayerUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
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
        WurstplusSetting OffhandHP = create("OffhandHealth", 16, 0, 36);
        WurstplusSetting delay = create("delay", false)
        WurstplusSetting find_in_hotbar = create("HotbarCrystal", false);  
          
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
           
           if (hp > OffhandHP.get_value(1)) {
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
            new_slot = find_in_hotbar();

            if (find_in_hotbar.in("true"(new_slot == 0)) {
                WurstplusMessageUtil.send_client_error_message("cannot find crystals in your hotbar");
                set_active(false);
            }
}
