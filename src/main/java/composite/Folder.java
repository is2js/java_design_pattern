package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder extends Component {

    private List<Component> children = new ArrayList<>();

    public Folder(final String name) {
        super(name);
    }

    public boolean addComponent(Component component) {
        return children.add(component);
    }

    public boolean removeComponent(Component component) {
        return children.remove(component);
    }

    public List<Component> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
