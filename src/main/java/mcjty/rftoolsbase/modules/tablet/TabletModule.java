package mcjty.rftoolsbase.modules.tablet;

import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;
import mcjty.lib.modules.IModule;
import mcjty.lib.setup.DeferredItem;
import mcjty.rftoolsbase.modules.tablet.client.GuiTablet;
import mcjty.rftoolsbase.modules.tablet.items.TabletContainer;
import mcjty.rftoolsbase.modules.tablet.items.TabletItem;
import mcjty.rftoolsbase.modules.tablet.items.TabletItemHandler;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static mcjty.lib.datagen.DataGen.has;
import static mcjty.rftoolsbase.RFToolsBase.tab;
import static mcjty.rftoolsbase.setup.Registration.CONTAINERS;
import static mcjty.rftoolsbase.setup.Registration.ITEMS;

public class TabletModule implements IModule {

    public static final DeferredItem<TabletItem> TABLET = ITEMS.register("tablet", tab(TabletItem::new));
    public static final Supplier<MenuType<TabletContainer>> CONTAINER_TABLET = CONTAINERS.register("tablet", TabletModule::createTabletContainer);

    public static final DeferredItem<TabletItem> TABLET_FILLED = ITEMS.register("tablet_filled", TabletItem::new);

    private static MenuType<TabletContainer> createTabletContainer() {
        return IForgeMenuType.create((windowId, inv, data) -> {
            Player player = inv.player;
            TabletContainer container = new TabletContainer(windowId, player.blockPosition(), player);
            container.setupInventories(new TabletItemHandler(player), inv);
            return container;
        });
    }

    @Override
    public void init(FMLCommonSetupEvent event) {

    }

    @Override
    public void initClient(FMLClientSetupEvent event) {
        GuiTablet.register();
    }

    @Override
    public void initConfig() {

    }

    @Override
    public void initDatagen(DataGen dataGen) {
        dataGen.add(
                Dob.itemBuilder(TABLET)
                        .shaped(builder -> builder
                                        .define('g', Tags.Items.NUGGETS_GOLD)
                                        .define('Q', Tags.Items.STORAGE_BLOCKS_QUARTZ)
                                        .unlockedBy("quartz", has(Blocks.QUARTZ_BLOCK)),
                                "geg", "RQR", "gRg")
        );
    }
}
