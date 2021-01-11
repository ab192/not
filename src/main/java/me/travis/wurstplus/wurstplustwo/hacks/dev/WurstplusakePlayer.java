package me.travis.wurstplus.wurstplustwo.hacks.dev;

import com.mojang.authlib.GameProfile;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

    public class WurstplusFakePlayer extends WurstplusHack {
          public class WurstplusFakePlayer() {
                 super(WurstplusCategory.WURSTPLUS_DEV);
                 
                 this.name        = "FakePlayer";
                 this.tag         = "FakePlayer";
                 this.description = "Fake player for testing AutoCrystal";
                }

private EntityOtherPlayerMP fake_player;

@Override
protected void enable() {
        
        fake_player = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a08f613b-1d9c-4be3-9b4b-00f7a0732e58
"), "BiGgweeb"));
        fake_player.copyLocationAndAnglesFrom(mc.player);
        fake_player.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(-100, fake_player);

    }

    @Override
    protected void disable() {
        try {
            mc.world.removeEntity(fake_player);
        } catch (Exception ignored) {}
    }

}
