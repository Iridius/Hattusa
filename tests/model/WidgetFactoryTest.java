package model;

import org.junit.Assert;
import org.junit.Test;
import framework.TestFramework;

import java.util.HashMap;

public class WidgetFactoryTest {
    @Test
    public void testCreate() throws Exception {
        HashMap<String, String> params = TestFramework.getWidgetParams();
        Widget widget = WidgetFactory.create(params);

        Assert.assertTrue("Expected factory creates Tree-widget.", widget instanceof Tree);
    }
}