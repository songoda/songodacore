package com.songoda.core.compatibility;

public enum ClassMapping {

    BIOME_BASE("world.level.biome", "BiomeBase"),
    BIOME_STORAGE("world.level.chunk", "BiomeStorage"),
    BLOCK("level.block", "BlockPosition"),
    BLOCK_BASE("world.level.block.state", "BlockBase"),
    BLOCK_BUTTON_ABSTRACT("world.level.block", "BlockButtonAbstract"),
    BLOCK_PRESSURE_PLATE_ABSTRACT("world.level.block", "BlockPressurePlateAbstract"),
    BLOCK_LEVER("world.level.block", "BlockLever"),
    BLOCKS("world.level.block", "Blocks"),
    BLOCK_POSITION("core", "BlockPosition"),
    CHAT_MESSAGE_TYPE("network.chat", "ChatMessageType"),
    CHUNK("world.level.chunk", "Chunk"),
    ENTITY("world.entity", "Entity"),
    ENTITY_INSENTIENT("world.entity", "EntityInsentient"),
    ENTITY_PLAYER("server.level", "EntityPlayer"),
    I_BLOCK_DATA("world.level.block.state", "IBlockData"),
    I_CHAT_BASE_COMPONENT("network.chat", "IChatBaseComponent"),
    I_REGISTRY("core", "IRegistry"),
    ITEM("world.item", "Item"),
    ITEM_STACK("world.item", "ItemStack"),
    LEVEL_ENTITY_GETTER("level.entity", "LevelEntityGetter"),
    MINECRAFT_SERVER("server", "MinecraftServer"),
    NBT_TAG_COMPOUND("nbt", "NBTTagCompound"),
    NBT_TAG_LIST("nbt", "NBTTagList"),
    NBT_BASE("nbt", "NBTBase"),
    PERSISTENT_ENTITY_SECTION_MANAGER("world.level.entity", "PersistentEntitySectionManager"),
    PACKET("network.protocol", "Packet"),
    PACKET_PLAY_OUT_CHAT("network.protocol.game", "PacketPlayOutChat"),
    PACKET_PLAY_OUT_WORLD_BORDER("PacketPlayOutWorldBorder"), // Removed in 1.17
    PLAYER_CONNECTION("server.network", "PlayerConnection"),
    WORLD("world.level", "World"),
    WORLD_BORDER("world.level.border", "WorldBorder"),
    WORLD_SERVER("server.level", "WorldServer"),

    CRAFT_BLOCK("block", "CraftBlock"),
    CRAFT_BLOCK_DATA("block.data", "CraftBlockData"),
    CRAFT_CHUNK("craftChunk"),
    CRAFT_ENTITY("entity", "CraftEntity"),
    CRAFT_PLAYER("entity", "CraftPlayer"),
    CRAFT_WORLD("CraftWorld");

    private final String packageName;
    private final String className;

    ClassMapping(String packageName) {
        this(null, packageName);
    }

    ClassMapping(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public Class<?> getClazz() {
        return getClazz(null);
    }

    public Class<?> getClazz(String sub) {
        try {
            String name = sub == null ? className : className + "$" + sub;
            if (className.startsWith("Craft"))
                return Class.forName("org.bukkit.craftbukkit." + ServerVersion.getServerVersionString()
                        + (packageName == null ? "" : "." + packageName) + "." + name);
            return Class.forName("net.minecraft." + (
                    ServerVersion.isServerVersionAtLeast(ServerVersion.V1_17) && packageName != null
                            ? packageName : "server." + ServerVersion.getServerVersionString()) + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}