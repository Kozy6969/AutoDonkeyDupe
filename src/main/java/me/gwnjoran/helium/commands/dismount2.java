package me.gwnjoran.helium.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import meteordevelopment.meteorclient.commands.Command;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.utils.entity.EntityUtils;
import meteordevelopment.meteorclient.utils.player.*;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import net.minecraft.network.packet.s2c.play.OpenHorseScreenS2CPacket;
import static com.mojang.brigadier.Command.SINGLE_SUCCESS;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import static meteordevelopment.meteorclient.MeteorClient.mc;

public class dismount2 extends Command {
    public dismount2() {
        super("dismount2", "does the funny men");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
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
            mc.getNetworkHandler().sendPacket(new PlayerInputC2SPacket(0, 0, false, true));
            mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(hose, false, mc.player.getActiveHand()));
            mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(hose, false, mc.player.getActiveHand()));
            return SINGLE_SUCCESS;
        });
    }
}