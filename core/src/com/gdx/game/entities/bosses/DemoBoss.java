package com.gdx.game.entities.bosses;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.gdx.game.entities.Player;
import com.gdx.game.movements.MovementSet;
import java.util.Random;
import com.gdx.game.factories.Weapon;
import com.gdx.game.movements.Movement;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private Float timeAcc = 2f;
    //per farlo muovere subito senza dover istanziare un movimento
    private MovementSet movementQ;
    private BossState bossState;
    private Player player;
    private Weapon weapon;
    private Vector2 actVelocity = new Vector2(0, 0);

    private Movement prevMovement;

    public DemoBoss(BossDef entityDef) {
        super(entityDef);

        RepeatAction playerAction = new RepeatAction();
        playerAction.setAction(new DemoBossAction());
        playerAction.setCount(RepeatAction.FOREVER);

        addAction(playerAction);
    }

    private class DemoBossAction extends Action {

        @Override
        public boolean act(float delta) {
            setPosition(getBody().getPosition());
            if (DemoBoss.this.getHP() <= 0) {
                kill();
                return false;
            }
            timeAcc += delta;
            stateTime += delta;

            setRegionToDraw(getEntityDef().getAnimations().get("run").getKeyFrame(stateTime));

            Vector2 playerPosition = player.getPosition();
            Vector2 newMovePlayer = new Vector2(playerPosition.sub(DemoBoss.this.getPosition()));

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
            return true;
        }
    }

}
