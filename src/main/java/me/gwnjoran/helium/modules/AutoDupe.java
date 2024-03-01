package me.gwnjoran.helium.modules;

import me.gwnjoran.helium.Helium;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.utils.entity.EntityUtils;
import meteordevelopment.meteorclient.utils.player.*;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class AutoDupe extends Module {
    public AutoDupe() {
        super(Helium.CATEGORY, "AutoDupe", "Automatically performs dupe exploits.");
    }
        private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay")
        .description("the delay blah bal hab abhah")
        .defaultValue(20)
        .min(0)
        .sliderMax(1000)
        .build()
    );
        private final Setting<Integer> attackdelay = sgGeneral.add(new IntSetting.Builder()
        .name("attack delay for frame or something i think idk")
        .description("the delay blah bal hab abhah")
        .defaultValue(20)
        .min(0)
        .sliderMax(1000)
        .build()
    );
        private final Setting<Boolean> multiple = sgGeneral.add(new BoolSetting.Builder()
        .name("multiple item frame support")
        .description("if u want to dupe with multiple item frames i think idk (doesnt work cuz im bad)")
        .defaultValue(false)
        .build()
    );
    

        private int timer;
        private int timer2;
        private Boolean multi;
    @Override
    public void onActivate() {
       timer = delay.get();
       timer2 = attackdelay.get();
       multi = multiple.get();
    }
    @EventHandler
    private void onTick(TickEvent.Pre event) {

        if (mc.world == null || mc.player == null)
            return;
ItemFrameEntity itemFrame2 = null;
        // Search for item frames
        ItemFrameEntity itemFrame = null;
        
        double lowestDistanceSoFar = Double.MAX_VALUE;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof ItemFrameEntity) {
                double distance2 = PlayerUtils.squaredDistanceTo(entity);
                if (distance2 < lowestDistanceSoFar) {
                itemFrame = (ItemFrameEntity) entity;
                if (multi == true) {
                    itemFrame2 = (ItemFrameEntity) entity;
                    break;
                }
                else{
                    //--
                }
            }
        }
        }

        


        if (itemFrame == null)
            return;

        ItemStack itemStack = mc.player.getMainHandStack();
        if (itemStack.getItem() instanceof AirBlockItem)
            return;

        int count = 0;
            count = itemStack.getCount();

        for (int i = 0; i < count; i++) {
            if (timer <= 0) {
            mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(itemFrame, false, mc.player.getActiveHand()));
            mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(itemFrame, false, mc.player.getActiveHand()));
            if (multi == true) {
mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(itemFrame2, false, mc.player.getActiveHand()));
mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(itemFrame2, false, mc.player.getActiveHand()));
            }
            else{
                //--
            }


            if (timer2 <= 0) {
            mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(itemFrame, true));
            timer2 = attackdelay.get();
            }
            else{
                mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(itemFrame, false, mc.player.getActiveHand()));
                timer2 = (timer2 - 4);
            }


            itemStack.setCount(itemStack.getCount() - 1);
            
            timer = delay.get();
            }
            else {
                timer--;
            }
        }
    }
}
