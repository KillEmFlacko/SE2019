/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.movements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author ammanas
 */
public class MovementSetFactory {

    private static int nInstances = 0;
    private static MovementSetFactory msf;

    private MovementSetFactory() {
    }

    public static MovementSetFactory instanceOf() {
        if (nInstances == 0) {
            nInstances = 1;
            msf = new MovementSetFactory();
            return msf;
        } else {
            return msf;
        }
    }

    /**
     * @param speed Slow Medium Fast
     * @param shape Square : Staring from upper right vertex Triangle : (not
     * accurate) base is parallel to x-axis Circle : not implemented yet just
     * use Square for now
     * @param clockwise
     * @param random if you want to make random moves do not use for now
     * @param v position of the body
     * @return
     */
    public MovementSet build(String speed, String shape, boolean clockwise, boolean random, Vector2 v) {
        HashMap<String, Float> speedModes = new HashMap<>();

        speedModes.put("Slow", 20f);
        speedModes.put("Medium", 40f);
        speedModes.put("Fast", 100f);

        Gdx.app.log("Speed", speedModes.get(speed).toString());
        Gdx.app.log("shape", shape);
        return selectMoves(speedModes.get(speed), shape, clockwise, random, v);

    }

    protected MovementSet selectMoves(float selectedSpeed, String shape, boolean clockwise, boolean random, Vector2 v) {
        MovementSet mv = new MovementSet();
        System.out.println(selectedSpeed);
        if (!random) {

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
                case ("Triangle"): {

                    Gdx.app.log("shape", shape);
                    float straightLine = v.y / v.x;
                    if (clockwise) {

                        XMovement x = new XMovement(selectedSpeed);
                        mv.add(x);
                        float abs = x.x;
                        Float newSpeed =  ((float)((abs)/(Math.cos(60*Math.PI/180)))) - 0.000000000006f;
                        //we are trying to adjust loss from double to float conversion
                        Movement y = new Movement(-selectedSpeed, -newSpeed);
                        
                        mv.add(y);
                        

                        //System.out.println("ciao" + first.getV().len() * 2 * (float) (Math.cos(180 - first.getV().angle())));
                        //float newVel = (float) Math.sqrt(Math.pow(first.getV().x, 2) + Math.pow(first.getV().y, 2)) * 2 * (float) Math.cos(180 - first.getV().angle());
                        //XMovement x = new XMovement(newVel + 13)
                        
                        mv.add(new Movement(-selectedSpeed, newSpeed));
                    }
                    break;
                }
                case ("Circle"): {
                    throw new NotImplementedException();

                }
            }

        } else {

        }
        return mv;
    }

}
