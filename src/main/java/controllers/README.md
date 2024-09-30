To write unit tests for the `AdminDashboardController` class, we need to focus on testing the public methods and ensuring that the interactions with the `AdminDashboardView` and other components are correctly handled. Since `AdminDashboardController` is heavily dependent on UI components and other controllers, we will use mocking to isolate the unit tests.

We'll use the `Mockito` library to mock dependencies and the `JUnit 5` framework for writing the tests.

First, ensure you have the necessary dependencies in your `pom.xml` file:

```xml
<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>4.0.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.8.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Now, let's create a test class for `AdminDashboardController`:

```java
package controllers;

import controllers.admin.*;
import main.SessionManager;
import models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import utils.IconManager;
import views.AdminDashboardView;
import views.LoginView;
import views.admin.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminDashboardControllerTest {

    private AdminDashboardView view;
    private AdminDashboardController controller;
    private SessionManager sessionManager;

    @BeforeEach
    public void setUp() {
        view = mock(AdminDashboardView.class);
        sessionManager = mock(SessionManager.class);
        SessionManager.setSessionManager(sessionManager);
        controller = new AdminDashboardController(view);
    }

    @Test
    public void testInitMenu() {
        IconManager im = mock(IconManager.class);
        when(im.getIcon(anyString())).thenReturn(null);

        controller.initMenu();

        verify(view, times(1)).getPanelSideBar();
    }

    @Test
    public void testAddEvent() {
        JButton btnLogout = mock(JButton.class);
        when(view.getBtnLogout()).thenReturn(btnLogout);

        controller.addEvent();

        ArgumentCaptor<ActionListener> captor = ArgumentCaptor.forClass(ActionListener.class);
        verify(btnLogout).addActionListener(captor.capture());

        ActionListener listener = captor.getValue();
        assertNotNull(listener);

        ActionEvent event = mock(ActionEvent.class);
        listener.actionPerformed(event);

        verify(view).dispose();
        verify(view).showError(any(SQLException.class));
    }

    @Test
    public void testOnMenuChange() {
        MenuItem item = mock(MenuItem.class);
        when(item.getId()).thenReturn("QLNV");

        controller.onMenuChange(item);

        verify(view).setPanel(any(JPanel.class));
    }

    @Test
    public void testSetView() {
        AdminDashboardView newView = mock(AdminDashboardView.class);
        controller.setView(newView);

        assertEquals(newView, controller.getView());
        verify(newView).getPanelSideBar();
    }

    @Test
    public void testGetView() {
        assertEquals(view, controller.getView());
    }
}
```

### Explanation:

1. **Dependencies**: Added `Mockito` and `JUnit 5` for mocking and testing.
2. **Setup**:
    - `setUp()`: Initializes the [`AdminDashboardView`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2FD%3A%2FDownload%2Frestaurant-manager-mvc-swing-1.3%2Frestaurant-manager-mvc-swing-1.3%2Fsrc%2Fmain%2Fjava%2Fviews%2FREADME.md%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A0%2C%22character%22%3A29%7D%7D%5D%2C%226a0898ab-144b-4c27-83a2-db418838417e%22%5D "Go to definition") and `AdminDashboardController` with mocked dependencies.
3. **Test Methods**:
    - `testInitMenu()`: Tests the `initMenu` method by verifying that the sidebar panel is retrieved.
    - `testAddEvent()`: Tests the `addEvent` method by verifying that the logout button's action listener is added and works correctly.
    - `testOnMenuChange()`: Tests the `onMenuChange` method by verifying that the correct panel is set based on the menu item ID.
    - `testSetView()`: Tests the `setView` method by verifying that the view is set correctly and the sidebar panel is retrieved.
    - `testGetView()`: Tests the `getView` method by verifying that the correct view is returned.

These tests cover the main functionalities of the `AdminDashboardController` class. Adjust the test cases as needed for your specific requirements.