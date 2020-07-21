/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.common.core.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import vazkii.botania.common.entity.EntityDoppleganger;

public final class SleepingHandler {

	private SleepingHandler() {}

	public static void trySleep(PlayerSleepInBedEvent event) {
		World world = event.getPlayer().world;
		if (!world.isClient()) {
			boolean nearGuardian = ((ServerWorld) world).getEntities()
					.filter(e -> e instanceof EntityDoppleganger)
					.anyMatch(e -> ((EntityDoppleganger) e).getPlayersAround().contains(event.getPlayer()));

			if (nearGuardian) {
				event.setResult(PlayerEntity.SleepFailureReason.NOT_SAFE);
			}
		}
	}
}
