package com.gregtechceu.gtstoragedrawers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(GregTechStorageDrawers.MODID)
public class GregTechStorageDrawers {

    public static final String MODID = "gtstoragedrawers";
    public static final String MOD_NAME = "GregTech Storage Drawers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(MODID, MODID));

    public GregTechStorageDrawers() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        for (DrawerTypes type : DrawerTypes.values()) {
            type.registerBlocks(BLOCK_REGISTER);
            type.registerItems(ITEM_REGISTER);
        }

        BLOCK_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);

        bus.addListener(GregTechStorageDrawers::registerCreativeTab);
    }

    public static void registerCreativeTab(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, helper -> helper.register(TAB, CreativeModeTab.builder()
                .icon(() -> new ItemStack(DrawerTypes.RUBBER.getData().blockFull2.get())) // todo verify
                .title(Component.translatable("itemGroup.gtstoragedrawers"))
                .displayItems((params, output) -> ITEM_REGISTER.getEntries()
                        .forEach(reg -> output.accept(new ItemStack(reg.get()))))
                .build()));
    }
}
