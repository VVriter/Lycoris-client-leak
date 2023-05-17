package ua.lycoris.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Logger {
    public static String prefix = Info.CHAT_PREFIX;

    public static void log (String text, int id) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix + ChatFormatting.GRAY+text+ChatFormatting.RESET);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion( component, id);
    }

    public static void log (String text, boolean deleteOld) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix +ChatFormatting.GRAY+text+ChatFormatting.RESET);
        if (deleteOld) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(component, -727);
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
        }
    }

    public static void error(String text, int id) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix +ChatFormatting.RED+text+ChatFormatting.RESET);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion( component, id);
    }

    public static void error(String text, boolean deleteOld) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix +ChatFormatting.RED+text+ChatFormatting.RESET);
        if (deleteOld) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(component, -727);
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
        }
    }

    public static void warn(String text, int id) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(   prefix +ChatFormatting.YELLOW+text+ChatFormatting.RESET);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion( component, id);
    }

    public static void warn(String text, boolean deleteOld) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(   prefix +ChatFormatting.YELLOW+text+ChatFormatting.RESET);
        if (deleteOld) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(component, -727);
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
        }
    }

    public static void sendMessageWithOptionPreview (String text, String tooltip, boolean deleteOld) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix +ChatFormatting.GRAY+text+ChatFormatting.RESET);
        component.setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(ChatFormatting.RED+tooltip))));
        if (deleteOld) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(component, -727);
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
        }
    }

    public static void sendClickableMessage (String text, String command, boolean deleteOld) {
        if (Minecraft.getMinecraft().player == null)
            return;
        final ITextComponent component = new TextComponentString(prefix +ChatFormatting.GRAY+text+ChatFormatting.RESET);
        component.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)));
        if (deleteOld) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(component, -727);
        } else {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
        }
    }


    public static void sendWebhookMsg(String message, String webhook) {
        new Thread(
                new Runnable() {
                    public void run() {
                        try {

                            PrintWriter out = null;
                            BufferedReader in = null;
                            StringBuilder result = new StringBuilder();
                            try {
                                URL realUrl = new URL(webhook);
                                URLConnection conn = realUrl.openConnection();
                                conn.setRequestProperty("accept", "*/*");
                                conn.setRequestProperty("connection", "Keep-Alive");
                                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                                conn.setDoOutput(true);
                                conn.setDoInput(true);
                                out = new PrintWriter(conn.getOutputStream());
                                String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
                                out.print(postData);
                                out.flush();
                                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String line;
                                while ((line = in.readLine()) != null) {
                                    result.append("/n").append(line);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (out != null) {
                                        out.close();
                                    }
                                    if (in != null) {
                                        in.close();
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            System.out.println(result.toString());


                        }catch (Exception e){
                            Logger.error("Webhook is invalid, use "+ Info.COMMAND_PREFIX+"webhook webhook link to link ur webhook",-1117);
                        }
                    }
                }).start();
    }
}