package engine.entities;

import engine.Handler;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class EntityManager implements Serializable{
    private Handler handler;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler){
        this.handler = handler;
        entities = new ArrayList<>();
    }

    public void tick(){
        for(int i = 0;i < entities.size();i++){
            Entity e = entities.get(i);
            e.tick();
            if (!e.active)
                entities.remove(e);
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
