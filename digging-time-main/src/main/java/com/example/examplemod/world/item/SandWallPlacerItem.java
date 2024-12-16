package com.example.examplemod.world.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.network.payload.ItemMouseScrolledPayload;
import com.example.examplemod.registries.ModDataComponentTypes;
import com.example.examplemod.registries.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;

import java.util.List;

//TODO Component.literalをComponent.translatableにする
public class SandWallPlacerItem extends Item implements ScrollableItem{

    private static final Logger LOGGER = LogUtils.getLogger();

    private final int maxHeight;

    public SandWallPlacerItem(Properties properties, int maxHeight) {
        super(properties.component(ModDataComponentTypes.WALL_HEIGHT, 5));
        this.maxHeight = maxHeight;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos clickedPos = context.getClickedPos().above(1);
        Direction horizontalDir = context.getHorizontalDirection();
        if (player != null) {
            InteractionHand hand = context.getHand();
            ItemStack stack = player.getItemInHand(hand);
            if (player.getOnPos().equals(clickedPos)) {
                return InteractionResult.FAIL;
            }
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < stack.getOrDefault(ModDataComponentTypes.WALL_HEIGHT, 5); y++) {
                    BlockPos checkedPos = clickedPos.relative(horizontalDir, x).above(y);
                    if (level.getBlockState(checkedPos).isAir() || level.getBlockState(checkedPos).liquid()) {
                        level.setBlockAndUpdate(checkedPos, Blocks.SAND.defaultBlockState());
                    }
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Wall Height: " + stack.get(ModDataComponentTypes.WALL_HEIGHT)));
    }

    @Override
    public void onScrolled(ServerPlayer player, ItemStack stack, double deltaY) {
        int wallHeight = stack.get(ModDataComponentTypes.WALL_HEIGHT);
        wallHeight = wallHeight + Mth.floor(deltaY);
        if (wallHeight <= 0) {
            player.displayClientMessage(Component.literal("壁の高さの最小値です！").withStyle(ChatFormatting.RED), true);
            return;
        }
        if (wallHeight >= this.maxHeight + 1) {
            player.displayClientMessage(Component.literal("壁の高さの最大値です！").withStyle(ChatFormatting.RED), true);
            return;
        }
        stack.set(ModDataComponentTypes.WALL_HEIGHT, wallHeight);
        player.displayClientMessage(Component.literal("Wall Height: " + wallHeight), true);
    }

    @EventBusSubscriber(modid = ExampleMod.MODID, value = Dist.CLIENT)
    public static class EventHandler {

        @SubscribeEvent
        public static void onMouseScrolled(final InputEvent.MouseScrollingEvent event) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (player.isShiftKeyDown() && mainHandStack.is(ModItems.SAND_WALL_PLACER)) {
                    double delta = event.getScrollDeltaY();
                    if (delta != 0) {
                        LOGGER.info("delta: {}", delta);
                        PacketDistributor.sendToServer(new ItemMouseScrolledPayload(delta));
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
