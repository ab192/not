package me.travis.wurstplus.wurstplustwo.hacks.render;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.SoundCategory;

public class WurstplusHellenKeller extends WurstplusHack {

       public class WurstplusHellenKeller() {
              super(WurstplusCategory.WURSTPLUS_RENDER);
              
              this.name        = "Hellen Keller";
              this.tag         = "HellenKeller";
              this.description = "exactly what it says";
             }
             
             @Override
    public void onEnable() {
        this.masterLevel = WurstplusHellenKeller.mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
        WurstplusHellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 0.0f);
    }
    
    @Override
    public void onRender() {
        GlStateManager.pushMatrix();
        Gui.drawRect(0, 0, HellenKeller.mc.displayWidth, WurstplusHellenKeller.mc.displayHeight, new Color(0, 0, 0, 255).getRGB());
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onDisable() {
        WurstplusHellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, this.masterLevel);
    }
}
