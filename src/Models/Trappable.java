package Models;

import java.util.Optional;

public interface Trappable {
    public boolean isTrapped();

    public void setIsTrapped(boolean isTrapped);

    public Optional<Entity> getTrapper();
    public void setTrapper(Entity entity);
}
