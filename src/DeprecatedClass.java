@DeprecatedEx(message = "HeavyBox")
public class DeprecatedClass {
    @DeprecatedEx(message = "newMethod")
    public void oldMethod() { }
    public void newMethod() { }
    @DeprecatedEx(message = "getNewInteger")
    private int getOldInteger(int a, int b) {
        return a + (b * (-1));
    }
    private int getNewInteger(int a, int b) {
        return a - b;
    }

    @DeprecatedEx(message = "NewInnerClass")
    private class OldInnerClass {
        @DeprecatedEx(message = "newInnerMethod")
        public void oldInnerMethod() { }
        public void newInnerMethod() { }
    }

    public class NewInnerClass {
        public void innerMethod() { }
    }
}
