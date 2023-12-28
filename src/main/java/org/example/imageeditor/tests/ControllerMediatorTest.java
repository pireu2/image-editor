package org.example.imageeditor.tests;
import org.example.imageeditor.util.ControllerMediator;

public class ControllerMediatorTest {

    public void testControllerMediator() {
        try {
            ControllerMediator.getInstance().put("testKey", "testValue");
            String value = ControllerMediator.getInstance().get("testKey", String.class);

            if ("testValue".equals(value)) {
                System.out.println("ControllerMediator is working correctly");
            } else {
                System.out.println("ControllerMediator is not working correctly");
            }
        } catch (Exception e) {
            System.out.println("Failed to test ControllerMediator");
        }
    }

    public static void main(String[] args) {
        ControllerMediatorTest test = new ControllerMediatorTest();
        test.testControllerMediator();
    }

}
