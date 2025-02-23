package de.melanx.aiotbotania.core.proxy;

import de.melanx.aiotbotania.AIOTBotania;
import de.melanx.aiotbotania.core.Registration;
import de.melanx.aiotbotania.core.handler.ContributorHandler;
import de.melanx.aiotbotania.items.terrasteel.ItemTerraSteelAIOT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

public class ClientProxy extends CommonProxy {
    private static final ResourceLocation active = new ResourceLocation(AIOTBotania.MODID, "active");
    private static final ResourceLocation tipped = new ResourceLocation(AIOTBotania.MODID, "tipped");

    @Override
    public void registerHandlers() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
    }

    private void initAuxiliaryRender() {
        Map<String, PlayerRenderer> skinMap = Minecraft.getInstance().getRenderManager().getSkinMap();
        PlayerRenderer render;
        render = skinMap.get("default");
        render.addLayer(new ContributorHandler(render));

        render = skinMap.get("slim");
        render.addLayer(new ContributorHandler(render));
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ItemModelsProperties.registerProperty(Registration.terrasteel_hoe.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.terrasteel_shovel.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.terrasteel_aiot.get(), tipped, (stack, world, entity) -> ItemTerraSteelAIOT.isTipped(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.terrasteel_aiot.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.alfsteel_hoe.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.alfsteel_shovel.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.alfsteel_aiot.get(), tipped, (stack, world, entity) -> ItemTerraSteelAIOT.isTipped(stack) ? 1.0F : 0.0F);
        ItemModelsProperties.registerProperty(Registration.alfsteel_aiot.get(), active, (stack, world, entity) -> ItemTerraSteelAIOT.isEnabled(stack) ? 1.0F : 0.0F);
    }


    private void loadComplete(FMLLoadCompleteEvent event) {
        DeferredWorkQueue.runLater(this::initAuxiliaryRender);
    }

}
