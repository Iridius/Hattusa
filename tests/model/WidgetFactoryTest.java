package model;

import org.junit.Assert;
import org.junit.Test;

public class WidgetFactoryTest {

    @Test
    public void testCreate() throws Exception {
        Widget widget = WidgetFactory.create("Tree","Some source");

        Assert.assertTrue("Expected factory creates Tree-widget.", widget instanceof Tree);
    }
}