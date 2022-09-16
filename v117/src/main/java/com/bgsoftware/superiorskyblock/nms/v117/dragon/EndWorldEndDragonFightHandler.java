package com.bgsoftware.superiorskyblock.nms.v117.dragon;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EndWorldEndDragonFightHandler extends EndDragonFight {

    private final Map<UUID, IslandEndDragonFight> worldDragonFightsMap = new HashMap<>();
    private final List<IslandEndDragonFight> worldDragonFightsList = new LinkedList<>();

    public EndWorldEndDragonFightHandler(ServerLevel serverLevel) {
        super(serverLevel, serverLevel.getSeed(), new CompoundTag());
    }

    @Override
    public void tick() {
        this.worldDragonFightsList.forEach(EndDragonFight::tick);
    }

    public void addDragonFight(UUID uuid, IslandEndDragonFight endDragonFight) {
        IslandEndDragonFight oldDragonFight = this.worldDragonFightsMap.put(uuid, endDragonFight);
        if (oldDragonFight != null)
            this.worldDragonFightsList.remove(oldDragonFight);
        this.worldDragonFightsList.add(endDragonFight);
    }

    @Nullable
    public IslandEndDragonFight getDragonFight(UUID uuid) {
        return this.worldDragonFightsMap.get(uuid);
    }

    @Nullable
    public IslandEndDragonFight removeDragonFight(UUID uuid) {
        IslandEndDragonFight endDragonFight = this.worldDragonFightsMap.remove(uuid);
        if (endDragonFight != null)
            this.worldDragonFightsList.remove(endDragonFight);
        return endDragonFight;
    }

}
