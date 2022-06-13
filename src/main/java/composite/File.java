package composite;

public class File extends Component {

    private Object data;

    public File(final String name) {
        super(name);
    }

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }
}
