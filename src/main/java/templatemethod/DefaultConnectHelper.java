package templatemethod;

public class DefaultConnectHelper extends AbstConnectHelper {
    @Override
    protected String doSecurity(final String info) {
        throw new UnsupportedOperationException("DefaultConnectHelper#doSecurity not implemented.");
    }

    @Override
    protected boolean authentication(final String id, final String password) {
        throw new UnsupportedOperationException("DefaultConnectHelper#authentication not implemented.");
    }

    @Override
    protected int authorization(final String userName) {
        throw new UnsupportedOperationException("DefaultConnectHelper#authorization not implemented.");
    }

    @Override
    protected String connection(final String info) {
        throw new UnsupportedOperationException("DefaultConnectHelper#connection not implemented.");
    }
}
