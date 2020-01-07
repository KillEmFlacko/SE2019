package com.gdx.game.movements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;


/**
 *
 * @author ammanas
 */
public class MovementSetFactory {

    private static int nInstances = 1;
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
     * @param playerPosition the player position
     * @param towardsPlayer tells after how many times the boss goes towards the
     * Player
     * @return
     */
    public MovementSet build(String speed, String shape, boolean clockwise) {
        HashMap<String, Float> speedModes = new HashMap<>();

        speedModes.put("Slow", 2f);
        speedModes.put("Medium", 4f);
        speedModes.put("Fast", 8f);

        Gdx.app.log("Speed", speedModes.get(speed).toString());
        //Gdx.app.log("Shape", shape);

        return selectMoves(speedModes.get(speed),shape, clockwise);

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
