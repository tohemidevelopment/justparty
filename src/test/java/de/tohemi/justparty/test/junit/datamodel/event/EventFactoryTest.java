package de.tohemi.justparty.test.junit.datamodel.event;

import de.tohemi.justparty.datamodel.event.*;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by Micha Piertzik on 09.05.2016.
 */
public class EventFactoryTest {

    @Test
    public void call_Default_Create_Method_And_Create_Concrete_Event(){
        Event event = EventFactory.createEvent();
        Assert.isTrue(event instanceof ConcreteEvent);
    }

    @Test
    public void create_DBAccessEvent(){
        int id = 0;
        Event event = EventFactory.createEvent(id, true);
        Assert.isTrue(event instanceof DBAccessEvent);
    }

    @Test
    public void create_BirthdayEvent(){
        int id = 0;
        Event event = EventFactory.createEvent(id, EventType.BIRTHDAY);
        Assert.isTrue(event instanceof BirthdayEvent);
    }
}
