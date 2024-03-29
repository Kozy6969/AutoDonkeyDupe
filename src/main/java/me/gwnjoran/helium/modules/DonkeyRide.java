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
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import net.minecraft.network.packet.s2c.play.OpenHorseScreenS2CPacket;
public class DonkeyRide extends Module {
    public DonkeyRide() {
        super(Helium.CATEGORY, "ride donkey", "auto ride donkey (in theory)");
    }
            private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay")
        .description("the delay blah bal hab abhah")
        .defaultValue(10)
        .min(0)
        .sliderMax(1000)
        .build()
    );
    private int timer;
        @Override
    public void onActivate() {
       timer = delay.get();
    }
    @EventHandler
    private void onTick(TickEvent.Pre event) {
                if (mc.world == null || mc.player == null)
            return;

            AbstractDonkeyEntity hose = null;
                double lowestDistanceSoFar = Double.MAX_VALUE;
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof AbstractDonkeyEntity) {
                double distance = PlayerUtils.squaredDistanceTo(entity);
                if (distance < lowestDistanceSoFar) {
                hose = (AbstractDonkeyEntity) entity;

            }
        }
        }
        if (hose != null) {
if (timer <= 0) {
mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(hose, false, mc.player.getActiveHand()));
timer = delay.get();
}
else{
timer--;
}
        }
    }
}
