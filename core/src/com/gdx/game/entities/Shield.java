package com.gdx.game.entities;

import com.badlogic.gdx.physics.box2d.Filter;
import com.gdx.game.factories.FilterFactory;

/**
 *
 * @author david
 */
public class Shield extends Entity implements Cloneable {

    private Filter filter;

    public Shield(LightShieldDef entityDef, Filter filter) {
        super(entityDef);
        this.filter = filter;
        FilterFactory ff = new FilterFactory();
        ff.copyFilter(entityDef.getFixtureDefs().get("colliding").filter, filter);
    }

    @Override
    public Shield clone() {
        return new Shield(getEntityDef(), filter);
    }

    @Override
    public LightShieldDef getEntityDef() {
        return (LightShieldDef) super.getEntityDef();
    }
}
