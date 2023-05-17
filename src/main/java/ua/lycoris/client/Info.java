package ua.lycoris.client;

import com.mojang.realmsclient.gui.ChatFormatting;

public class Info {
    public static final String MODID = "lk";
    public static final String NAME = "Lycoris";
    public static final String VERSION = "1.0";

    public static String CHAT_PREFIX = ChatFormatting.RED+"["+ChatFormatting.DARK_RED+"LYCORIS"+ChatFormatting.RED+"]"+ChatFormatting.RESET + " ";
    public static String COMMAND_PREFIX = "*";
    public static String LINKED_WEBHOOk = "";

    /** if verison = 0 - development version,
     * else if version = 1 - public version.
     */
    public static int version = 0;
}
