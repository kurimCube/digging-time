package com.example.examplemod.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LongPickaxeItem extends PickaxeItem {

    public LongPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        super.mineBlock(stack, level, state, pos, miningEntity);
        if (miningEntity instanceof Player player) {
            Direction lookingDir = player.getDirection();
            for (int i = 0; i < 5; i++) {
                if (!level.getBlockState(pos.relative(lookingDir, i)).is(state.getBlock())) {
                    break;
                }
                level.destroyBlock(pos.relative(lookingDir, i), true, miningEntity);
            }
        }
        return true;
    }
}
