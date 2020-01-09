package com.gdx.game.factories;

import com.badlogic.gdx.Gdx;
import com.gdx.game.factories.movement_products.MovementSet;
import com.gdx.game.factories.movement_products.XMovement;
import com.gdx.game.factories.movement_products.YMovement;
import java.util.HashMap;

/**
 *
 * @author ammanas
 */
public class MovementSetFactory {

    private final static MovementSetFactory msf = new MovementSetFactory();

    private MovementSetFactory() {
    }

    public static MovementSetFactory instanceOf() {
        return msf;
    }

    /**
     * @param speed Slow Medium Fast
     * @param shape Square : Staring from upper right vertex, 4 movements
     * StraightLine : goes in a straight line, 2 movements
     * @param clockwise
     * @return
     */
    public MovementSet build(String speed, String shape, boolean clockwise) {
        HashMap<String, Float> speedModes = new HashMap<>();

        speedModes.put("Slow", 2f);
        speedModes.put("Medium", 4f);
        speedModes.put("Fast", 8f);

        Gdx.app.log("Speed", speedModes.get(speed).toString());

        return selectMoves(speedModes.get(speed), shape, clockwise);

    }

    protected MovementSet selectMoves(float selectedSpeed, String shape, boolean clockwise) {

        MovementSet mv = new MovementSet();

        System.out.println(selectedSpeed);

        switch (shape) {
            case ("Square"): {

                if (clockwise) {
                    mv.add(new YMovement(-selectedSpeed));
                    mv.add(new XMovement(-selectedSpeed));
                    mv.add(new YMovement(selectedSpeed));
                    mv.add(new XMovement(selectedSpeed));

                } else {

                    mv.add(new XMovement(-selectedSpeed));
                    mv.add(new YMovement(-selectedSpeed));
                    mv.add(new XMovement(selectedSpeed));
                    mv.add(new YMovement(selectedSpeed));

                }
                break;

            }
            case ("StraightLine"): {
                mv.add(new XMovement(-selectedSpeed));
                mv.add(new XMovement(selectedSpeed));

                break;
            }
        }
        return mv;
    }

}
