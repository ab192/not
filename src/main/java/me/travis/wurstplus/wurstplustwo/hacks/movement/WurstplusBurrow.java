package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventMove;
import me.travis.wurstplus.wurstplustwo.manager.WurstplusModuleManager;
import me.travis.wurstplus.wurstplustwo.manager.WurstplusSettingManager;
import me.travis.wurstplus.wurstplustwo.util.WurstplusBlockUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusInventoryUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusRotationUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusTimer;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WurstplusBurrow extends WurstplusHack {
  public () {
        super("WurstplusCategory.WURSTPLUS_MOVEMENT);
        
        this.name        = "Burrow";
        this.tag         = "WurstPlusBurrow";
        this.description = "Jumps and phases you into a block";
    }


    WurstplusSetting packet = create("Packet", true);
    WurstplusSetting invalidPacket = create("InvalidPacket", false);
    WurstplusSetting rotations = create("Rotations", 5, 1, 10);
    WurstplusSetting timeOut = create("TimeOut", 194, 0, 1000);
    
    private BlockPos startPos;
    private final Timer timer = new Timer();
    private int lastHotbarSlot = -1;
    private int blockSlot = -1;
    private static WurstplusBurrow INSTANCE;

    public static WurstplusBurrow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WurstplusBurrow();
        }
        return INSTANCE;
    }

    @Override
    public void enable() {
        this.lastHotbarSlot = -1;
        this.blockSlot = -1;
        if (LagBlock.fullNullCheck()) {
            this.disable();
            return;
        }
        this.blockSlot = this.findBlockSlot();
        this.startPos = new BlockPos(WurstplusBurrow.mc.player.getPositionVector());
        if (!WurstplusBlockUtil.isElseHole(this.startPos) || this.blockSlot == -1) {
            this.disable();
            return;
        }
        WurstplusBurrow.mc.player.jump();
        this.timer.reset();
    }

    @SubscribeEvent
    public void WurstplusEventMove(WurstplusEventMove event) {
        if (event.getStage() != 0 || !this.timer.passedMs(this.timeOut.getValue().intValue())) {
            return;
        }
        this.lastHotbarSlot = WurstplusBurrow.mc.player.inventory.currentItem;
        WurstplusInventoryUtil.switchToHotbarSlot(this.blockSlot, false);
        for (int i = 0; i < this.rotations.getValue(); ++i) {
            WurstplusRotationUtil.faceVector(new Vec3d((Vec3i)this.startPos), true);
        }
        WurstplusBlockUtil.placeBlock(this.startPos, this.blockSlot == -2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, false, this.packet.getValue(), WurstplusBurrow.mc.player.isSneaking());
        WurstplusInventoryUtil.switchToHotbarSlot(this.lastHotbarSlot, false);
        if (this.invalidPacket.getValue().booleanValue()) {
            WurstplusBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(LagBlock.mc.player.posX, 1337.0, WurstplusBurrow.mc.player.posZ, true));
        }
        this.disable();
    }

    private int findBlockSlot() {
        int obbySlot = WurstplusInventoryUtil.findHotbarBlock(BlockObsidian.class);
        if (obbySlot == -1) {
            if (InventoryUtil.isBlock(WurstplusBurrow.mc.player.getHeldItemOffhand().getItem(), BlockObsidian.class)) {
                return -2;
            }
            int echestSlot = WurstplusInventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (echestSlot == -1 && WurstplusInventoryUtil.isBlock(WurstplusBurrow.mc.player.getHeldItemOffhand().getItem(), BlockEnderChest.class)) {
                return -2;
            }
            return -1;
        }
        return obbySlot;
    }
}
