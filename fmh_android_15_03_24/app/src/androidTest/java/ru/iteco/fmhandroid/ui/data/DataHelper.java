package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import java.util.concurrent.TimeoutException;

public class DataHelper {
    public static String toastMessage = "Something went wrong. Try again later.";
    public static String toastMessageEmpty = "Login and password cannot be empty";
    public static String toastMessageAuthorization = "Authorization";
    public static String toastMessageCreateNews = "Fill empty fields";
    public static String toastMessageCategoryCreateNews = "Saving failed. Try again later.";

    private DataHelper() {
    }

    public static AuthInfo validLoginAndPassword() { // корректые учетные данные
        String login = "login2";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidLoginAndPasswordAnalogicValid() { // логин-парволь, не заявленные как валидные
        String login = "login1";
        String password = "password1";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidLoginAndPassword() { // логин-парволь, не заявленные как валидные
        String login = "dsff3";
        String password = "wrwef3";
        return new AuthInfo(login, password);
    }

    public static AuthInfo passwordAndLoginWithSpaceAtTheEnd() { // в логине и пароле пробел в конце
        String login = "login2 ";
        String password = "password2 ";
        return new AuthInfo(login, password);
    }

    public static AuthInfo passwordAndLoginWithSpaceAtTheBeginning() { // логин и парволь начинаются с пробела
        String login = " login2";
        String password = " password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo emptyLoginAndPassword() { // пустой логин и пароль
        String login = "";
        String password = "";
        return new AuthInfo(login, password);
    }

    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> has been displayed during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final org.hamcrest.Matcher<View> matchId = withId(viewId);
                final org.hamcrest.Matcher<View> matchDisplayed = isDisplayed();

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matchId.matches(child) && matchDisplayed.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static class AuthInfo { // набор данных для авторизации
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

}
