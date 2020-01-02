package com.gdx.game.entities.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.entities.Player;
import com.gdx.game.movements.MovementSet;
import java.util.Random;
import com.gdx.game.entities.*;
import com.gdx.game.factories.Weapon;
import com.gdx.game.movements.Movement;
import java.util.TreeMap;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private Float timeAcc = 2f;
    //per farlo muovere subito senza dover istanziare un movimento
    private float stateTime = 0f;
    private Texture regions;
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;
    private MovementSet movementQ;
    private BossState bossState;
    private Player player;
    private Weapon weapon;
    private Vector2 actVelocity = new Vector2(0, 0);

    private Movement prevMovement;

    public DemoBoss(Stats stats, int hp) {
        super(stats, hp);
    }

    /**
     * Initializes the Actor physics. Do not call directly.
     */
    private void init() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getPosition());

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() * 0.6f / 2, getWidth() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 0f;        
        shape.dispose();

        atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/big_demon/big_demon.atlas"));
        movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), PlayMode.LOOP);
        
        BossDef bd = new BossDef(bodyDef);
        bd.getAnimations().put("moving", movementAnimation);
        bd.getFixtureDefs().put("basicHitbox", fixtureDef);
        this.setEntityDef(bd);

    }

    @Override
    public void act(float delta) {
        setPosition(getBody().getPosition());
        if (this.getHP() <= 0) {
            kill();
            return;
        }
        timeAcc += delta;
        stateTime += delta;

        this.setRegionToDraw(movementAnimation.getKeyFrame(stateTime, true));

        Vector2 playerPosition = player.getPosition();
        Vector2 newMovePlayer = new Vector2(playerPosition.sub(this.getPosition()));

        if (timeAcc >= 2.0f) {
            Random r = new Random();

            if ((r.nextFloat() * 10) >= 6) {
                //si lancia contro il player
                //System.out.println("Player position" + playerPosition);
                actVelocity.set(newMovePlayer.scl(1.3f));
                //checkDirection(newMovePlayer);

                timeAcc = 1.5f;
                //checkDirection(newMovePlayer);
            } else {
                //spara LELLO SPARA
                Movement movement = movementQ.frontToBack();
                //Gdx.app.log("V", movement.toString());
                actVelocity.set(movement);
                weapon.fire(newMovePlayer.scl(1f));
                //checkDirection(newMovePlayer);

                timeAcc = 0f;
            }
        }
    }
    
    @Override
    public void isHitBy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void kill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
