package me.travis.wurstplus.wurstplustwo.hacks.combat;

//import

public class WurstplusOffhandRewriteTwo extends WurstplusHack {
       public class WurstplusOffhandRewriteTwo() {
              super(WurstplusCategory.WURSTPLUS_COMBAT);
              
              this.tag         = "Offhand Rewrite Two";
              this.name        = "OffhandRewriteTwo";
              this.description = "Offhand rewrite with no pasting";
             }
             
             WurstplusSetting mode = create("Offhand", combobox("Crystal", "Gapple", "Totem"));
             WurstplusSetting totem_health = create("TotemHealth", 16, 1, 20);
             
             private boolean switching = false;
             private int last_slot;
             
             @Override
             public void update() {
             
             if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {

            if (switching) {
                swap_items(last_slot);
                return;
            }
             
             float hp = mc.player.getHealth() + mc.player.getAbsorptionAmount();
             
             if (hp > totem_health.get_value(1)) {
               if (mode.in("Crystal")) {
                  swap_items(get_item_slot(Items.END_CRYSTAL))
                  return;
               }
            }
             if (totem_health.get_value(1) > hp) {
               if (mode.in("Crystal")) {
               swap_items(get_item_slot(Items.TOTEM_OF_UNDYING))
               return;
               }
            }
               
             if (hp > totem_health.get_value(1)) {
               if (mode.in("Gapple")) {
               swap_items(get_item_slot(Items.GOLDEN_APPLE))
               return;
               }
            }  
            
             if (totem_health.get_value(1) > hp) {
               if (mode.in("Gapple")) {
               swap_items(get_item_slot(Items.TOTEM_OF_UNDYING))
               return
               }
            }
            
            if (hp > totem_health.get_value(1)) {
              if (mode.in("Totem")) {
              swap_items(get_item_slot(Items.TOTEM_OF_UNDYING))
              return;
              }
            }  
            
            if (totem_health.get_value(1) > hp) {
              if (mode.in("Totem")) {
              swap_items(get_item_slot(Items.TOTEM_OF_UNDYING))
              return;
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
 }
                    
