package me.gwnjoran.helium;

import com.mojang.logging.LogUtils;
import me.gwnjoran.helium.modules.AutoDupe;
import me.gwnjoran.helium.modules.donkey;
import me.gwnjoran.helium.modules.DonkeyRide;
import me.gwnjoran.helium.modules.autodisconnect;
import me.gwnjoran.helium.commands.dismount2;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Helium extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Helium");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Helium");

        // Modules
        Modules.get().add(new AutoDupe());
        Modules.get().add(new autodisconnect());
        Modules.get().add(new donkey());
        Modules.get().add(new DonkeyRide());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.gwnjoran.helium";
    }
}
