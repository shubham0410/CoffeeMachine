package io.shubham.coffeemachine.VendingMachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CoffeeVendingMachineTest {

    private CoffeeVendingMachine coffeeVendingMachine = new CoffeeVendingMachine();
    private MyThread myThread;
    ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFetchConfigAndSettingObjectsInCoffeeVendingMachine() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Method method = coffeeVendingMachine.getClass().
                getDeclaredMethod("fetchConfigurationAndCreateObjects");
        Field privateStringField = coffeeVendingMachine.getClass().
                getDeclaredField("blackTea");

        privateStringField.setAccessible(true);
        method.setAccessible(true);
        method.invoke(coffeeVendingMachine);
        Assert.assertNotNull(privateStringField);
    }

}