package model;

import org.junit.Assert;
import org.junit.Test;

public class WidgetFactoryTest {

    @Test
    public void testCreate() throws Exception {
        Widget widget = WidgetFactory.create("Tree","Some source", "", "");

        Assert.assertTrue("Expected factory creates Tree-widget.", widget instanceof Tree);
    }

    @Test
    public void testCreate_some_type() throws Exception {
        Widget widget = WidgetFactory.create("Tree","Some source", "1", "1");
        Assert.assertTrue("Expected widget will be located in cell {1,1}", widget.getX() = 1);
    }
}