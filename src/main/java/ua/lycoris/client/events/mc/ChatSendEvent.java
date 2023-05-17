package ua.lycoris.client.events.mc;

import com.google.common.base.Strings;
import ua.puncakecat.beet.Event;

public class ChatSendEvent extends Event {
    private String message;
    private final String originalMessage;
    public ChatSendEvent(String message)
    {
        this.setMessage(message);
        this.originalMessage = Strings.nullToEmpty(message);
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = Strings.nullToEmpty(message);
    }

    public String getOriginalMessage()
    {
        return originalMessage;
    }
}
